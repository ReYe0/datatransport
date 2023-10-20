package com.example.datatransport.pojo;

import java.io.Serializable;

public class C_overturn implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String machineId; // 机组id
	private String machineName; //机组名称
	private long treeId;       //组织id
	private int status;        //状态类型
	private String xml_config; //自定义属性
	private String manufacturer;//生产厂商
	private String model_number;//机组型号
	private String xml_alarm;   //报警设置
	private double self1;        //自振频率1
	private double self2;        //自振频率2
	private double ratedSpeed;    //额定转速
	private String reserve;      //保留字段
	public String getMachineId() {
		return machineId;
	}
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public long getTreeId() {
		return treeId;
	}
	public void setTreeId(long treeId) {
		this.treeId = treeId;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getXml_config() {
		return xml_config;
	}
	public void setXml_config(String xml_config) {
		this.xml_config = xml_config;
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
	public String getXml_alarm() {
		return xml_alarm;
	}
	public void setXml_alarm(String xml_alarm) {
		this.xml_alarm = xml_alarm;
	}
	public String getReserve() {
		return reserve;
	}
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	public double getSelf1() {
		return self1;
	}
	public void setSelf1(double self1) {
		this.self1 = self1;
	}
	public double getSelf2() {
		return self2;
	}
	public void setSelf2(double self2) {
		this.self2 = self2;
	}
	public double getRatedSpeed() {
		return ratedSpeed;
	}
	public void setRatedSpeed(double ratedSpeed) {
		this.ratedSpeed = ratedSpeed;
	}
	
}
