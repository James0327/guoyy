package com.jw.james.convert.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description: trade-postsalecore-parent
 * com.ly.travel.mall.trade.postsalecore.facade.model.response.OrderReturnGoodsItemDTO
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/10/17 15:04
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Data
public class OrderReturnGoodsItemDTO implements Serializable {
    /**
     * 商品总价
     */
    private BigDecimal totalPrice;
    /**
     * 商品Id
     */
    private String     goodsId;
    /**
     * 订单产品表ID,关联表t_order_item的ID
     */
    private Integer    orderItemId;
    /**
     * 退费
     */
    private BigDecimal postFee;
    /**
     * 小计
     */
    private BigDecimal totalMoney;
    /**
     * 规格Id
     */
    private String     goodsSpecId;
    /**
     * 商品图片URL
     */
    private String     goodsPhotoUrl;
    /**
     * 商品单价
     */
    private BigDecimal perPrice;
    /**
     * 产品规格,json格式保存
     */
    private String     goodsSpecJson;
    /**
     * 订单退货退款售后单号
     */
    private String     returnSn;
    /**
     * 商品数
     */
    private Integer    goodsQuantity;
    /**
     * 商品单位
     */
    private String     goodsUnit;
    /**
     * ID
     */
    private Integer    id;
    /**
     * 发货状态
     */
    private String     sendGoodStatus;
    /**
     *  分销市场价
     */
    private BigDecimal distriMarketPrice;
    /**
     * 商品名称
     */
    private String     goodsName;
    /**
     * 售后类型，1：退款，2：退货退款，3：换货
     */
    private Integer    returnType;
    /**
     * 底价
     */
    private BigDecimal basePrice;
}
