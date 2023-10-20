package com.example.datatransport.dao;

import com.example.datatransport.pojo.C_machine_position;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author xuy
 */
@Mapper
public interface PosDao {

    /**
     * 获取测点列表
     * @param machine_id
     * @return java.util.List<com.example.datatransport.pojo.C_machine_position>
     * @author xuy
     * @date 2023/9/26 14:55
     */
    @Select("select * from qacs2000_config_2010.c_machine_position where machine_id = ${machine_id}")
    List<C_machine_position> getPosList(@Param("machine_id") String machine_id);
}
