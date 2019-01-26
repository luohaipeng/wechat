package com.petrel.mapper;

import com.petrel.domain.Catalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CatalogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Catalog record);

    Catalog selectByPrimaryKey(Long id);

    List<Catalog> selectAll();

    int updateByPrimaryKey(Catalog record);

    List<Catalog> getChild(@Param("catalogId") Long catalogId);

    int countProductNumber(Long catalogId);

    int countPropertyNumber(Long catalogId);

    List<Catalog> getParentCatalogList(Long catalogId);
}