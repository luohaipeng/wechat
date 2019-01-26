package com.petrel.service.impl;

import com.petrel.domain.Property;
import com.petrel.domain.PropertyValue;
import com.petrel.mapper.PropertyMapper;
import com.petrel.mapper.PropertyValueMapper;
import com.petrel.service.IPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PropertyServiceImpl implements IPropertyService {

    @Autowired
    private PropertyMapper propertyMapper;

    @Autowired
    private PropertyValueMapper propertyValueMapper;


    @Override
    public List<Property> getPropertyByCatalogId(Long id) {
        return propertyMapper.getPropertyByCatalogId(id);
    }

    @Override
    public void save(Property property) {
        try {
            //通过id是否为null来判断当前的操作是新增还是修改
            if(property.getId() == null){
                //新增属性
                propertyMapper.insert(property);
            }else{
                propertyMapper.updateByPrimaryKey(property);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Long id) {
        propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<Property> getProValByCatalogId(Long catalogId) {
        //通过分类Id获取分类下的属性
        List<Property> propertyList = propertyMapper.getPropertyByCatalogId(catalogId);
        //遍历分类属性的列表，
        propertyList.forEach(property -> {
            //判断该属性类型
            if(property.getType() == 1){
                // 如果是下拉列表类型，就通过该属性的id获取属性值
                List<PropertyValue> valueList = propertyValueMapper.getProListByProId(property.getId());
                property.setPropertyValueList(valueList);
            }

        });
        return propertyList;
    }


}
