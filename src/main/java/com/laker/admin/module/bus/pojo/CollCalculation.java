package com.laker.admin.module.bus.pojo;

import com.laker.admin.module.bus.entity.Coll;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


// 合集 计算数
@Data
public class CollCalculation {



    @ApiModelProperty(value = "主键id")
    private Long id;

    @ApiModelProperty(value = "收藏数,默认为0,可以不传递")
    private Long favoritesCount = 0L;

    @ApiModelProperty(value = "阅读数 ,默认为0,可以不传递")
    private Long readCount = 0L;

    @ApiModelProperty(value = "月票总数量 默认为0,可以不传递")
    private Long monthlyTicketCount = 0L;

    @ApiModelProperty(value = "评论数 默认为0,可以不传递")
    private Long commentsCount = 0L;

    @ApiModelProperty(value = "吐槽数量,默认为0,可以不传递")
    private Long complaintsCount = 0L;

    @ApiModelProperty(value = "分享数,默认为0,可以不传递")
    private Long sharesCount = 0L;

    /**
     *     获取计算的参数,然后在进行设置参数
     */
    public static Coll getCalculationParam( Coll byId,CollCalculation collCalculation){
        Long favoritesCount = byId.getFavoritesCount()+collCalculation.getFavoritesCount();
        Long readCount = byId.getReadCount()+collCalculation.getReadCount();
        Long monthlyTicketCount = byId.getMonthlyTicketCount()+collCalculation.getMonthlyTicketCount();
        Long commentsCount = byId.getCommentsCount()+collCalculation.getCommentsCount();
        Long complaintsCount = byId.getComplaintsCount()+collCalculation.getComplaintsCount();
        Long sharesCount = byId.getSharesCount()+collCalculation.getSharesCount();

        byId.setFavoritesCount(favoritesCount);
        byId.setReadCount(readCount);
        byId.setMonthlyTicketCount(monthlyTicketCount);
        byId.setCommentsCount(commentsCount);
        byId.setComplaintsCount(complaintsCount);
        byId.setSharesCount(sharesCount);
        return byId;
    }
}