package com.example.datatransport.dao;

import com.example.datatransport.pojo.C_dgm2000;
import com.example.datatransport.pojo.D_id_envelope;
import com.example.datatransport.pojo.D_id_speed;
import com.example.datatransport.pojo.D_id_vib;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author xuy
 */
@Mapper
public interface VibDao {

    /**
     * 获取采集器信息列表
     * @param mac_id
     * @return java.util.List<com.example.datatransport.pojo.C_dgm2000>
     * @author xuy
     * @date 2023/9/26 14:17
     */
    @Select("Select dgm_id from qacs2000_config_2010.c_machine_dgm2000 where  machine_id = ${mac_id}")
    List<Integer> getDgmIdsByMacId(@Param("mac_id") String mac_id);


    /**
     * 获取采集器
     * @param dgm_id
     * @return java.util.List<com.example.datatransport.pojo.C_dgm2000>
     * @author xuy
     * @date 2023/9/26 16:12
     */
    @Select("select * from qacs2000_config_2010.c_dgm2000 where dgm_id =${dgm_id}")
    List<C_dgm2000> getDgmByDgm_id(@Param("dgm_id") int dgm_id);


    /**
     * 获取最新一条震动数据消息
     * @param pos_id
     * @param machine_id
     * @return com.example.datatransport.pojo.D_id_vib
     * @author xuy
     * @date 2023/9/26 17:16
     */
    @Select("select * from dgm2000_1_2010.d_${machine_id}_vib where id = ${pos_id} and vib_wave_len > 0 order by saveTime_Com desc limit 1")
    D_id_vib getVibByChannelId(@Param("pos_id") int pos_id,@Param("machine_id") String machine_id);

    /**
     * 获取最新一条包络数据消息
     * @param pos_id
     * @param machine_id
     * @return com.example.datatransport.pojo.D_id_envelope
     * @author xuy
     * @date 2023/10/8 15:13
     */
    @Select("select * from dgm2000_1_2010.d_${machine_id}_envelope where id = ${pos_id} and spm_wave is not null order by saveTime_Com desc limit 1")
    D_id_envelope getEnvelopeByChannelId(@Param("pos_id") int pos_id, @Param("machine_id") String machine_id);

    /**
     * 获取最新一条转速数据消息
     * @param pos_id
     * @param machine_id
     * @return com.example.datatransport.pojo.D_id_speed
     * @author xuy
     * @date 2023/10/8 15:20
     */
    @Select("select * from dgm2000_1_2010.d_${machine_id}_speed where id = ${pos_id} and speed_len > 0 order by saveTime_Com desc limit 1")
    D_id_speed getSpeedByChannelId(@Param("pos_id") int pos_id, @Param("machine_id") String machine_id);
}
