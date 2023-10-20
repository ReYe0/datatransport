package com.example.datatransport.pojo;

import java.util.List;

public class C_navigate_tree implements java.io.Serializable {
    private Long t_id;// 编号
    private String t_name;// 名称

    /**
     * 组织类型  -1：通用，0：风电，1:工业设备，2：走行部，3：油田
     */
    private Long t_root;
    private Long t_pid;// 父节点
    private Integer t_depth;// 深度
    private String t_path;// 路径
    private String t_desc;// 描述
    private String xml_expand;// 属性的增加
    private List children;// 子节点


    /**
     * 组织下机组
     */
    private List<C_machine> machineList;
    public Long getT_id() {
        return t_id;
    }

    public void setT_id(Long t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public Long getT_root() {
        return t_root;
    }

    public void setT_root(Long t_root) {
        this.t_root = t_root;
    }

    public Long getT_pid() {
        return t_pid;
    }

    public void setT_pid(Long t_pid) {
        this.t_pid = t_pid;
    }

    public Integer getT_depth() {
        return t_depth;
    }

    public void setT_depth(Integer t_depth) {
        this.t_depth = t_depth;
    }

    public String getT_path() {
        return t_path;
    }

    public void setT_path(String t_path) {
        this.t_path = t_path;
    }

    public String getT_desc() {
        return t_desc;
    }

    public void setT_desc(String t_desc) {
        this.t_desc = t_desc;
    }

    public String getXml_expand() {
        return xml_expand;
    }

    public void setXml_expand(String xml_expand) {
        this.xml_expand = xml_expand;
    }

    public List getChildren() {
        return children;
    }

    public void setChildren(List children) {
        this.children = children;
    }

    public List<C_machine> getMachineList() {
        return machineList;
    }

    public void setMachineList(List<C_machine> machineList) {
        this.machineList = machineList;
    }
}
