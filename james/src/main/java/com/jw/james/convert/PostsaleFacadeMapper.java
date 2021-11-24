package com.jw.james.convert;

import com.jw.james.convert.response.BasePostsaleResponseDTO;
import com.jw.james.convert.response.BasePostsaleResponseVO;
import com.jw.james.convert.response.OrderReturnGoodsDTO;
import com.jw.james.convert.response.OrderReturnGoodsVO;
import org.mapstruct.Mapper;

/**
 * Description: integrationcore-parent
 * com.ly.travel.mall.api.facade.mapper.PostsaleFacadeMapper
 * <p>
 * Author: @author guoyiyong/james
 * Date: @date 2021/10/18 13:35
 * Version: @version 1.0
 * <p>
 * Copyright (C) 2021 JW All rights reserved.
 */
@Mapper(componentModel = "spring")
public interface PostsaleFacadeMapper {

    BasePostsaleResponseDTO<OrderReturnGoodsDTO> vo2dto(BasePostsaleResponseVO<OrderReturnGoodsVO> responseVO);
}
