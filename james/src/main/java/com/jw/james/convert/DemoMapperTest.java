package com.jw.james.convert;

import com.alibaba.fastjson.JSON;
import com.jw.james.convert.response.BasePostsaleResponseDTO;
import com.jw.james.convert.response.BasePostsaleResponseVO;
import com.jw.james.convert.response.OrderReturnGoodsDTO;
import com.jw.james.convert.response.OrderReturnGoodsItemVO;
import com.jw.james.convert.response.OrderReturnGoodsVO;
import org.assertj.core.util.Lists;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * Description: guoyy
 * com.jw.convert.DemoMapperTest
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/10/18 13:59
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
public class DemoMapperTest {

    public static void main(String[] args) {
        PostsaleFacadeMapper mapper = Mappers.getMapper(PostsaleFacadeMapper.class);

        List<OrderReturnGoodsItemVO> goodsList = Lists.newArrayList();
        OrderReturnGoodsItemVO orderReturnGoodsItemVO = new OrderReturnGoodsItemVO();
        goodsList.add(orderReturnGoodsItemVO);
        orderReturnGoodsItemVO.setReturnSn("returnSn");

        OrderReturnGoodsVO orderReturnGoodsVO = new OrderReturnGoodsVO();
        orderReturnGoodsVO.setAppId("appId");
        orderReturnGoodsVO.setGoodsList(goodsList);

        BasePostsaleResponseVO<OrderReturnGoodsVO> responseVO = new BasePostsaleResponseVO<>();
        responseVO.setTraceId("xxx");
        responseVO.setData(orderReturnGoodsVO);

        BasePostsaleResponseDTO<OrderReturnGoodsDTO> resp = mapper.vo2dto(responseVO);

        System.out.println(JSON.toJSONString(resp));
    }

}
