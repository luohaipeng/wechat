package com.petrel.service.impl;

import com.petrel.domain.WechatButton;
import com.petrel.mapper.WechatButtonMapper;
import com.petrel.service.IWechatButtonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WechatButtonServiceImpl implements IWechatButtonService {
    @Autowired
    private WechatButtonMapper wechatButtonMapper;

    @Override
    public List<WechatButton> getAllWechatButtom() {
        return wechatButtonMapper.selectAll();
    }
}
