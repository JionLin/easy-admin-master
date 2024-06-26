package com.laker.admin.module.sms.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.laker.admin.module.sms.service.SmsService;
import com.laker.admin.module.sms.entity.Sms;
import com.laker.admin.utils.JsonResult;
import com.laker.admin.utils.Result;
import com.laker.admin.utils.ResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 手机验证码相关程序猿
 *
 * @author bug
 * @date 2020/08/18
 */
// @PropertySource("phone.properties")
@Service
@Slf4j
public class SmsServiceImpl implements SmsService {
    @Value("${sms.accessId}")
    private String accessId;
    @Value("${sms.accessKey}")
    private String accessKey;
    @Value("${sms.signName}")
    private String signName ;
    @Value("${sms.codeTemplate}")
    private String codeTemplate;
    /**
     * 短信API产品名称（短信产品名固定，无需修改）
     */
    @Value("${sms.product}")
    private String product;
    /**
     * dysmsapi.aliyuncs.com
     */
    @Value("${sms.domain}")
    private String domain;


    @Autowired
    private StringRedisTemplate redisTemplate;



    /**
     * 根据用户输入的phone发送验证码
     *
     * @param phone 电话号码
     */
    @Override
    public Result sendSmsCode(String phone) {
        if (!phone.matches("^1[3|4|5|7|8][0-9]{9}$")) {
            System.out.println("手机号码格式不正确");
            return JsonResult.error(ResultEnum.FAILURE, "手机号码格式不正确");
        }
        //判断用户输入的电话号码是否频繁发送
        if (isSendOfen(phone)) {
            System.out.println("发送短信频繁，请稍后再试");
            return JsonResult.error(ResultEnum.FAILURE, "发送短信频繁，请稍后再试");
        }
        Sms sms = makeCode(phone);      //制作验证码，6位随机数字
        JSONObject smsJson = new JSONObject();
        smsJson.put("code", sms.getCode());
        smsJson.put("product", "Dysmsapi");
        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = send(phone, signName, codeTemplate, smsJson);
            log.info("获取的短信信息参数为：{}",  com.alibaba.fastjson.JSON.toJSONString(sendSmsResponse));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("短信验证码发送失败");
            return JsonResult.error(ResultEnum.FAILURE, "短信验证码发送失败");
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
            //短信发送成功，将短信记录到redis中
            redisCode(sms);
            System.out.println("短信发送成功");
            return JsonResult.success(ResultEnum.SUCCESS, "短信发送成功");
        }
        return JsonResult.error(ResultEnum.FAILURE, sendSmsResponse.getMessage());

    }

    //将验证码缓存到redis中，10分钟过后自动清除该缓存
    private void redisCode(Sms sms) {
        redisTemplate.opsForValue().set(sms.getPhone(), sms.getCode(), 600, TimeUnit.SECONDS);
    }

    //随机生成6位数的短信码
    private Sms makeCode(String phone) {
        Random random = new Random();
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            int next = random.nextInt(10);
            code.append(next);
        }
        return new Sms(phone, code.toString(), System.currentTimeMillis());
    }

    //判断验证功发送时候频繁
    private boolean isSendOfen(String phone) {
        if (redisTemplate.opsForValue().get(phone) == null) {
            return false;
        } else {
            //判断上一次记录的时间和当前时间进行对比，如果两次相隔时间小于120s，视为短信发送频繁
            Long expire = redisTemplate.getExpire(phone, TimeUnit.SECONDS);
            // 两次发送短信中间至少有2分钟的间隔时间
            if (expire + 120 * 1000 >= System.currentTimeMillis()) {
                return true;
            }
            return false;
        }
    }

    /**
     * 验证短信
     *
     * @param phone
     * @param code
     * @return
     */
    @Override
    public boolean validSmsCode(String phone, String code) {
        //取出所有有关该手机号的短信验证码
        if (redisTemplate.opsForValue().get(phone) == null) {
            System.out.println("短信验证失败");
            return false;
        }
        String saveCode = redisTemplate.opsForValue().get(phone);
        if (saveCode.equals(code)) {
            System.out.println("短信验证成功");
            //删除掉该redis
            redisTemplate.opsForValue().getOperations().delete(phone);
            return true;
        }
        return false;
    }

    /**
     * 发信
     *
     * @param phone
     * @param signName
     * @param templateCode
     * @param params
     * @return
     * @throws ClientException
     */
    SendSmsResponse send(String phone, String signName, String templateCode, JSONObject params) throws ClientException {
        //初始化ascClient,暂时不支持多region（请勿修改）
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessId,
                accessKey);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象
        SendSmsRequest request = new SendSmsRequest();
        //使用post提交
        request.setMethod(MethodType.POST);
        //必填:待发送手机号。支持以逗号分隔的形式进行批量调用，批量上限为1000个手机号码,批量调用相对于单条调用及时性稍有延迟,验证码类型的短信推荐使用单条调用的方式
        request.setPhoneNumbers(phone);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //友情提示:如果JSON中需要带换行符,请参照标准的JSON协议对换行符的要求,比如短信内容中包含\r\n的情况在JSON中需要表示成\\r\\n,否则会导致JSON在服务端解析失败
        request.setTemplateParam(params.toJSONString());
        request.setOutId(UUID.randomUUID().toString());
        //请求失败这里会抛ClientException异常
        return acsClient.getAcsResponse(request);
    }

}
