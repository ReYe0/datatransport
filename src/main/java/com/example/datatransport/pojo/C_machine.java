package com.example.datatransport.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * C_machine entity.
 *
 * @author MyEclipse Persistence Tools
 */
// 机组配置表
public class C_machine implements java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7317938201677278612L;
    private String machine_id;// 全局机组编码
    private Long tree_id;// 直属目录id
    private Integer m_type;// 机组类型，目前为1表示风电  1 :  振动  2：倾覆  3：组合  4 无效
    private String m_me;// 机组名称
    private String dbname;// 历史数据库名称
    @JsonIgnore
    private String xml_config;// Xml 配置文件
    private Integer status;// 机组状态
    private Integer source;// 远程和本机组
    @JsonIgnore
    private String mach_model_xml;
    private String manufacturer;
    private String model_number;

    private List<C_server_list> c_server_lists = null;// 上级服务器集合
    private List<C_dgm2000> c_dgm2000s = null;// 机组中拥有的采集器的集合
    private List<C_machine_position> c_machine_positions = null;    //测点
    private List<C_overturn> c_overturn=null;
    private List<C_pump> c_pumps=null;
    private String json_config;

    public String getJson_config() {
        return json_config;
    }

    public void setJson_config(String json_config) {
        this.json_config = json_config;
    }

    public List<C_overturn> getC_overturn() {
		return c_overturn;
	}

	public void setC_overturn(List<C_overturn> c_overturn) {
		this.c_overturn = c_overturn;
	}

	public C_machine() {
    }

    public String getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }


    public Long getTree_id() {
        return tree_id;
    }

    public void setTree_id(Long tree_id) {
        this.tree_id = tree_id;
    }

    public Integer getM_type() {
        return m_type;
    }

    public void setM_type(Integer m_type) {
        this.m_type = m_type;
    }

    public String getM_me() {
        return m_me;
    }

    public void setM_me(String m_me) {
        this.m_me = m_me;
    }

    public String getDbname() {
        return dbname;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getXml_config() {
        return xml_config;
    }

    public void setXml_config(String xml_config) {
        this.xml_config = xml_config;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getMach_model_xml() {
        return mach_model_xml;
    }

    public void setMach_model_xml(String mach_model_xml) {
        this.mach_model_xml = mach_model_xml;
    }

    public List<C_server_list> getC_server_lists() {
        return c_server_lists;
    }

    public void setC_server_lists(List<C_server_list> c_server_lists) {
        this.c_server_lists = c_server_lists;
    }

    public List<C_dgm2000> getC_dgm2000s() {
        return c_dgm2000s;
    }

    public void setC_dgm2000s(List<C_dgm2000> c_dgm2000s) {
        this.c_dgm2000s = c_dgm2000s;
    }

    public List<C_machine_position> getC_machine_positions() {
        return c_machine_positions;
    }

    public void setC_machine_positions(List<C_machine_position> cMachinePositions) {
        c_machine_positions = cMachinePositions;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getModel_number() {
        return model_number;
    }

    public void setModel_number(String model_number) {
        this.model_number = model_number;
    }

	public List<C_pump> getC_pumps() {
		return c_pumps;
	}

	public void setC_pumps(List<C_pump> c_pumps) {
		this.c_pumps = c_pumps;
	}


    // Property accessors

}