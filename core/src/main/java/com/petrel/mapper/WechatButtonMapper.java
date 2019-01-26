package com.petrel.mapper;

import com.petrel.domain.WechatButton;
import java.util.List;

public interface WechatButtonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatButton record);

    WechatButton selectByPrimaryKey(Long id);

    List<WechatButton> selectAll();

    int updateByPrimaryKey(WechatButton record);
}