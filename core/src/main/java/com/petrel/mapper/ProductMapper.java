package com.petrel.mapper;

import com.petrel.domain.Product;
import com.petrel.qo.QueryObject;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Product record);

    Product selectByPrimaryKey(Long id);

    List<Product> selectAll();

    int updateByPrimaryKey(Product record);

    int queryCount(QueryObject qo);

    List queryList(QueryObject qo);

}