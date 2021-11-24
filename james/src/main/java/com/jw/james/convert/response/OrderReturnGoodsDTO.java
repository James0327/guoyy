package com.jw.james.convert.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Description: trade-postsalecore-parent
 * com.ly.travel.mall.trade.postsalecore.facade.model.response.OrderReturnGoodsDTO
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/10/17 14:29
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Data
public class OrderReturnGoodsDTO implements Serializable {
    /**
     * 下单方式：1:开放平台,2:其它
     */
    private Integer                       orderType;
    /**
     * 发货物流单号
     */
    private String                        sendDeliveryCorpSn;
    /**
     * 卖家手机号
     */
    private String                        userPhone;
    /**
     * 退货商品明细
     */
    private List<OrderReturnGoodsItemDTO> goodsList;
    /**
     * 是否删除默认为0（1：删除，0：未删除）
     */
    private Integer                       modifyUserId;
    /**
     * 售后状态（1,待卖家审核2,卖家拒绝退款：3,退款成功：4,卖家拒绝退货退款：5,待买家退货：
     * 6,买家已退货，待卖家收货：7,买家已退货，卖家拒绝收货：8,卖家已收货,待确认退款：
     * 9,退货退款成功：10,卖家拒绝换货：11,买家已退货，待卖家换货：12,卖家已换货，待买家收货：
     * 13,换货成功：14,已关闭：15,卖家同意退款）
     */
    private Integer                       returnStatus;
    /**
     * 分销商id
     */
    private Integer                       buyerId;

    private String                        deliveryCorpSn;        // 退货/换货物流公司编码
    private String                        modifyTime;            // 最后更新时间
    private String                        receiveName;           // 买家姓名
    private String                        appId;                 // 下单渠道
    private String                        returnSn;              // 订单退款售后单号
    private String                        sendDeliverySn;        // 发货物流公司编码
    private BigDecimal                    applyReturnAmount;     // 申请退费金额
    private Integer                       id;                    // 订单退货退款表id
    private String                        appealCountdown;       // 申诉倒计时
    private String                        finishTime;            // 售后完成时间
    private Integer                       supplierOperationId;   // 供应商所属运营商id
    private String                        receiveAddress;        // 买家联系地址
    private String                        totalOrderPrice;       // 订单总金额
    private String                        supplierOperationType; // 供应商运营方式(业务平台运营方式:1自营、2分销)
    private String                        sendAddress;           // 收货详细地址
    private String                        sendTime;              // 换货发货时间
    private String                        userAddress;           // 卖家详细地址
    private String                        supplierUserName;      // 供应商负责人
    private String                        returnStatusName;      // 退货退款状态名称
    private String                        returnComment;         // 描述
    private String                        applyReturnPictureurl; // 上传图片url，最多支持3张，用逗号隔开
    private Integer                       operationType;         // 运营方式(申诉平台运营方式:1自营、2分销)
    private List<String>                  pictureurlList;        // 退货上传图片URL集合	array
    private Integer                       returnType;            // 售后类型，1：退款，2：退货退款，3：换货
    private String                        supplierId;            // 供应商id
    private String                        orderSn;               // 订单编号
    private BigDecimal                    totalPrice;            // 可退总额
    private String                        sendDeliveryName;      // 发货物流公司名称
    private Integer                       isAppeal;              // 申诉状态(0无,1可申诉,2已申诉)
    private String                        returnGoodsTime;       // 退货发货时间
    private String                        deliverySn;            // 退货/换货快递单号
    private String                        supplierOperationName; // 供应商平台
    private String                        returnReason;          // 申请退款理由
    private String                        channelReturnSn;       // 第三方退款单号
    private String                        operationId;           // 运营ID
    private String                        thirdOrderSn;          // 外部订单编号
    private Integer                       totalGoodsQuantity;    //	总数
    private Integer                       returnWay;             // 发货方式，1自己联系，2无需物流
    private String                        resellerOperationType; // 分销商运营方式(申诉平台运营方式:1自营、2分销)
    private String                        supplierName;          // 供应商名称
    private Integer                       orderSource;           // 下单方式1-开放平台,2-线上下单
    private String                        receivePhone;          // 买家联系方式
    private String                        sendName;              // 联系人
    private String                        appName;               // 应用名称
    private BigDecimal                    postFee;               // 运费
    private String                        operationName;         // 分销商平台
    private String                        buyerName;             // 分销商名称
    private String                        userName;              // 卖家联系人
    private String                        distributor;           // 申请机构（分销商）
    private String                        supplierPhone;         // 供应商联系电话
    private String                        supplierReason;        // 供应商拒绝理由
    private BigDecimal                    supplyGoodsAmount;     // 订单商品供货总金额
    private String                        createTime;            // 创建时间
    private String                        deliveryCorpName;      // 退货/换货物流公司名称
    private String                        sendPhone;             // 联系电话
    private Integer                       tenantId;              // 租户id
    private BigDecimal                    distriMarketAmount;    // 分销市场价总金额
    private BigDecimal                    actualReturnAmount;    // 实际退费金额
}
