package com.example.datatransport.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenquan
 *         转速测点历史数据表（d机组id_speed）
 */
public class D_id_speed implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -6033037038697195381L;
    private int id;
    private Date saveTime;
    private long saveTime_Com;
    private int dataId;
    private int jc;
    private int measuring_point;//测点位置：1：发电机输入轴转速2：齿轮箱输出转速3：叶轮输出转速4：行星轮输出转速5：齿轮箱中间轴转速
    private float speed_value;//这个测点的转速值
    private Float speed_dc;//温度
    private int speed_len;//转速波形长度
    private byte[] speed_wave;//转速波形
    private Integer speed_hd_len;//转速波形长度
    private byte[] speed_hd_wave;//转速波形
    private byte[] reserve;
    private int count;//用户输入的查询波形的行数
    private float tempValue;//趋势别名
    private String machine_id;

    public float getTempValue() {
        return tempValue;
    }

    public void setTempValue(float tempValue) {
        this.tempValue = tempValue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }

    public long getSaveTime_Com() {
        return saveTime_Com;
    }

    public void setSaveTime_Com(long saveTime_Com) {
        this.saveTime_Com = saveTime_Com;
    }

    public int getDataId() {
        return dataId;
    }

    public void setDataId(int dataId) {
        this.dataId = dataId;
    }

    public int getJc() {
        return jc;
    }

    public void setJc(int jc) {
        this.jc = jc;
    }

    public int getMeasuring_point() {
        return measuring_point;
    }

    public void setMeasuring_point(int measuring_point) {
        this.measuring_point = measuring_point;
    }

    public float getSpeed_value() {
        return speed_value;
    }

    public void setSpeed_value(float speed_value) {
        this.speed_value = speed_value;
    }

    public int getSpeed_len() {
        return speed_len;
    }

    public void setSpeed_len(int speed_len) {
        this.speed_len = speed_len;
    }

    public byte[] getReserve() {
        return reserve;
    }

    public byte[] getSpeed_wave() {
        return speed_wave;
    }

    public void setSpeed_wave(byte[] speed_wave) {
        this.speed_wave = speed_wave;
    }

    public void setReserve(byte[] reserve) {
        this.reserve = reserve;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }

	public byte[] getSpeed_hd_wave() {
		return speed_hd_wave;
	}

	public void setSpeed_hd_wave(byte[] speed_hd_wave) {
		this.speed_hd_wave = speed_hd_wave;
	}

	public Float getSpeed_dc() {
		return speed_dc;
	}

	public void setSpeed_dc(Float speed_dc) {
		this.speed_dc = speed_dc;
	}

	public Integer getSpeed_hd_len() {
		return speed_hd_len;
	}

	public void setSpeed_hd_len(Integer speed_hd_len) {
		this.speed_hd_len = speed_hd_len;
	}
}
