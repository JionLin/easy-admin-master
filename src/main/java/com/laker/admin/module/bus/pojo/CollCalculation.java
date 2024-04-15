package com.laker.admin.module.bus.pojo;

import com.laker.admin.module.bus.entity.Coll;
import lombok.Data;


// 合集 计算数
@Data
public class CollCalculation {


    private Long id;

    private Long favoritesCount = 0L;

    private Long readCount = 0L;

    private Long monthlyTicketCount = 0L;

    private Long commentsCount = 0L;

    private Long complaintsCount = 0L;

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