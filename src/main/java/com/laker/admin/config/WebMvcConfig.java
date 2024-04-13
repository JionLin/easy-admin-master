package com.laker.admin.config;

import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.stp.StpUtil;
import com.laker.admin.framework.ext.interceptor.TraceAnnotationInterceptor;
import com.laker.admin.framework.ext.mvc.CurrentUser;
import com.laker.admin.framework.ext.mvc.PageRequestArgumentResolver;
import com.laker.admin.framework.ext.mvc.StringToEnumConvertFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

/**
 * @author laker
 */
@Configuration
@Slf4j
public class WebMvcConfig implements WebMvcConfigurer {
    private static final String[] exclude_path = {"/admin/**",
            "/admin/login.html",
            "/error",
            "/swagger-resources/**",
            "/api/v1/login",
            "/captcha",
            "/thumbnail",
            "/testSms/**",
            "/file/uploads"
    };
    private static final String[] trace_exclude_path = {"/admin/**",
            "/admin/login.html",
            "/error",
            "/swagger-resources/**"};
    @Resource
    LakerConfig lakerConfig;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置跨域访问
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("*")
                .maxAge(3600);
    }

    /**
     * 注册sa-token的拦截器，打开注解式鉴权功能 (如果您不需要此功能，可以删除此类)
     *  进行去除掉,进行测试功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册注解拦截器，并排除不需要注解鉴权的接口地址 (与登录拦截器无关)
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(exclude_path);
        // 配置自定义拦截器
        registry.addInterceptor(new TraceAnnotationInterceptor()).addPathPatterns("/**")
                .excludePathPatterns(trace_exclude_path);
    }

    // 2024-04-13 注释掉,关于文件保存问题
   /*
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 配置静态资源，自定义虚拟磁盘功能
        File web = new File("web");
        String path = lakerConfig.getOssFile().getPath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        log.info(file.getAbsolutePath());
        registry.addResourceHandler("/admin/**")
                .addResourceLocations("file:" + web.getAbsolutePath() + "/admin/");

        registry.addResourceHandler("/" + path + "/**")
                .addResourceLocations("file:" + file.getAbsolutePath() + "/");
    }
    */

    @Value("${files.upload.path}")
    private String fileSavePath;

    // 静态资源到处理 也就是图片到问题
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        // 配置静态资源，自定义虚拟磁盘功能
        File web = new File("web");
        String path = lakerConfig.getOssFile().getPath();
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        log.info(file.getAbsolutePath());
        registry.addResourceHandler("/admin/**")
                .addResourceLocations("file:" + web.getAbsolutePath() + "/admin/");

        registry.addResourceHandler("/" + path + "/**")
                .addResourceLocations("file:" + file.getAbsolutePath() + "/");

        /**
         * 配置资源映射
         * 意思是：如果访问的资源路径是以“/images/”开头的，
         * 就给我映射到本机的“E:/images/”这个文件夹内，去找你要的资源
         * 注意：E:/images/ 后面的 “/”一定要带上
         */
        registry.addResourceHandler("/uploadFile/**")
                .addResourceLocations("file:"+fileSavePath);
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        // 路由匹配规则
//        configurer.setUseTrailingSlashMatch(false);
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        // 添加自定义类型转换器
        // 比如将字符串转换为日期类型，可通过DateFormatter类来实现自动转换。
        // formatters和converters用于对日期格式进行转换，默认已注册了Number和Date类型的formatters，
        // 支持@NumberFormat和@DateTimeFormat注解，需要自定义formatters和converters可以实现addFormatters方法。
//        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
        registry.addConverter(new StringToEnumConvertFactory());
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        // 配置异常解析器
    }


    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // 添加自定义方法参数处理器
        argumentResolvers.add(new PageRequestArgumentResolver());
        argumentResolvers.add(new HandlerMethodArgumentResolver() {
            @Override
            public boolean supportsParameter(MethodParameter parameter) {
                return CurrentUser.class.equals(parameter.getParameterType());
            }

            @Override
            public Object resolveArgument(
                    MethodParameter parameter,
                    ModelAndViewContainer mvContainer,
                    NativeWebRequest nativeWebRequest,
                    WebDataBinderFactory webDataBinderFactory) throws Exception {
                HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
                if (request == null) {
                    return null;
                }
                // CurrentUser  = from request
                return CurrentUser.builder().id(StpUtil.getLoginId().toString()).build();
            }
        });
    }
}