package com.example.datatransport.dao;

import com.example.datatransport.pojo.C_machine;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xuy
 */
@Mapper
public interface MacDao {


    /**
     * 根据组织id获取机组列表
     * @param tree_id
     * @return java.util.List<com.example.datatransport.pojo.C_machine>
     * @author xuy
     * @date 2023/9/26 15:19
     */
    @Select("select * from qacs2000_config_2010.c_machine where tree_id = ${tree_id}")
    List<C_machine> getMacListByTree_id(@Param("tree_id") long tree_id);
}
