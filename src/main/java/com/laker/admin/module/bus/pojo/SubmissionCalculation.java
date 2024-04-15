package com.laker.admin.module.bus.pojo;

import com.laker.admin.module.bus.entity.BusSubmission;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author johnny
 * @Classname SubmissionCalculation
 * @Description 稿件计算
 * @Date 2024/4/15 15:08
 */
@Data
public class SubmissionCalculation {


    @ApiModelProperty(value = "主键id")
    private Long id;


    @ApiModelProperty(value = "阅读数")
    private Long readCount = 0L;

    @ApiModelProperty(value = "月票数")
    private Long monthlyTicketCount = 0L;

    @ApiModelProperty(value = "收藏数")
    private Long favoritesCount = 0L;

    @ApiModelProperty(value = "吐槽数")
    private Long complaintsCount = 0L;

    @ApiModelProperty(value = "分享数")
    private Long sharesCount = 0L;


    /**
     * 获取计算的参数,然后在进行设置参数
     */
    public static BusSubmission getCalculationParam(BusSubmission byId, SubmissionCalculation collCalculation) {
        Long readCount = byId.getReadCount() + collCalculation.getReadCount();
        Long monthlyTicketCount = byId.getMonthlyTicketCount() + collCalculation.getMonthlyTicketCount();
        Long favoritesCount = byId.getFavoritesCount() + collCalculation.getFavoritesCount();
        Long complaintsCount = byId.getComplaintsCount() + collCalculation.getComplaintsCount();
        Long sharesCount = byId.getSharesCount() + collCalculation.getSharesCount();

        byId.setFavoritesCount(favoritesCount);
        byId.setReadCount(readCount);
        byId.setMonthlyTicketCount(monthlyTicketCount);
        byId.setComplaintsCount(complaintsCount);
        byId.setSharesCount(sharesCount);

        return byId;
    }
}
