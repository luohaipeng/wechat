package com.petrel.service.impl;

import com.petrel.domain.SkuPropertyValue;
import com.petrel.mapper.SkuPropertyValueMapper;
import com.petrel.service.ISkuPropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SkuPropertyValueServiceImpl implements ISkuPropertyValueService {

    @Autowired
    private SkuPropertyValueMapper propertyValueMapper;

    @Override
    public List<SkuPropertyValue> getProListByProId(Long propertyId) {
        return propertyValueMapper.getProListByProId(propertyId);
    }

    @Override
    public void save(List<SkuPropertyValue> propertyValueList) {
        propertyValueList.forEach(val -> {
            //判断每一个属性值的主键是否为null
            if(val.getId() == null){
                //如果为null，则执行新增操作
                propertyValueMapper.insert(val);
            }else{
                //否则执行修改操作
                propertyValueMapper.updateByPrimaryKey(val);
            }
        });
    }

    @Override
    public void delete(Long id) {
        propertyValueMapper.deleteByPrimaryKey(id);
    }
}
