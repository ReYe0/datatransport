package com.example.datatransport.pojo;


import com.example.datatransport.config.PositionXmlConfig;

/*
 * @author:严家祯
 * */
public class C_machine_position extends PositionXmlConfig implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -60962501949447234L;
    /*
     * 测点表
     * 存放机组和风电采集器和通道关联表
     */

    private int position_id;    //测点id，主键
    private int position_type;    //测点类型（1 转速2 过程量3振动 4包络 5数字量 6晃度）
    private String position_name;        //测点名称
    private String machine_id;    //机组编号，主键
    private Integer dgm_id;// 风电采集器编号，主键，非空
    private int channel_id;// 通道id，长度为2
    private int channel_type;// 通道类型 长度为2
    private String channel_name; //管理的通道名称
    private String pos_xml_config;    //xml配置
    private String json_config;    //xml配置
    private int isalarm;        //是否报警
    private int status;            //状态
    private String pointID;

    public String getPointID() {
        return pointID;
    }

    public void setPointID(String pointID) {
        this.pointID = pointID;
    }

    public String getJson_config() {
        return json_config;
    }

    public void setJson_config(String json_config) {
        this.json_config = json_config;
    }



    public int getPosition_id() {
        return position_id;
    }

    public void setPosition_id(int position_id) {
        this.position_id = position_id;
    }

    public int getPosition_type() {
        return position_type;
    }

    public void setPosition_type(int position_type) {
        this.position_type = position_type;
    }

    public String getPosition_name() {
        return position_name;
    }

    public void setPosition_name(String position_name) {
        this.position_name = position_name;
    }

    public String getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }

    public Integer getDgm_id() {
        return dgm_id;
    }

    public void setDgm_id(Integer dgm_id) {
        this.dgm_id = dgm_id;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }

    public int getChannel_type() {
        return channel_type;
    }

    public void setChannel_type(int channel_type) {
        this.channel_type = channel_type;
    }

    public String getChannel_name() {
        return channel_name;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getPos_xml_config() {
        return pos_xml_config;
    }

    public void setPos_xml_config(String posXmlConfig) {
        pos_xml_config = posXmlConfig;
    }

    public int getIsalarm() {
        return isalarm;
    }

    public void setIsalarm(int isalarm) {
        this.isalarm = isalarm;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
