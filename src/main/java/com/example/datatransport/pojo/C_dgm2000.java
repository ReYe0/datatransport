package com.example.datatransport.pojo;

/*
 * @author:严家祯
 * */

// 风电采集器配置表
public class C_dgm2000 implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -8680014200665308340L;

    private int dgm_id;// 全局采集器编码
    private String name;// 名称
    private Byte dgm_type;// 采集器类型
    private String dgm_ip;//
    private String xml_config;// 采集器的配置内容：包括键相设置，通道设置等，所有采集器的配置
    private Integer config_state;// 配置的状态, 1:未下载到硬件; 0:表示下载到硬件
    private int upgrade_state;// 升级状态 1:需要升级 0:已升级
    private Long upgrade_id;// 升级文件id
    private short source;// 是否本地
    private short dgm_state;// 是否启用

    public int getDgm_id() {
        return dgm_id;
    }

    public void setDgm_id(int dgm_id) {
        this.dgm_id = dgm_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getDgm_type() {
        return dgm_type;
    }

    public void setDgm_type(Byte dgm_type) {
        this.dgm_type = dgm_type;
    }

    public String getDgm_ip() {
        return dgm_ip;
    }

    public void setDgm_ip(String dgm_ip) {
        this.dgm_ip = dgm_ip;
    }

    public String getXml_config() {
        return xml_config;
    }

    public void setXml_config(String xml_config) {
        this.xml_config = xml_config;
    }

    public Integer getConfig_state() {
        return config_state;
    }

    public void setConfig_state(Integer config_state) {
        this.config_state = config_state;
    }

    public short getSource() {
        return source;
    }

    public void setSource(short source) {
        this.source = source;
    }

    public short getDgm_state() {
        return dgm_state;
    }

    public void setDgm_state(short dgm_state) {
        this.dgm_state = dgm_state;
    }

    public int getUpgrade_state() {
        return upgrade_state;
    }

    public void setUpgrade_state(int upgrade_state) {
        this.upgrade_state = upgrade_state;
    }

    public Long getUpgrade_id() {
        return upgrade_id;
    }

    public void setUpgrade_id(Long upgrade_id) {
        this.upgrade_id = upgrade_id;
    }


}