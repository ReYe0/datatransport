package com.example.datatransport.pojo;

import java.util.Date;

//振动通道历史数据表
public class D_id_vib implements java.io.Serializable {

    private static final long serialVersionUID = 1183205879578576822L;

    private int id;//取值：0—15，主键
    private Date saveTime;//保存时间
    private long saveTime_Com;//毫秒数
    private int dataId;//数据类型0~15位；月时间，周时间......
    private int jc;//报警状态；
    private float speed= 0f;//转速
    private float vib_rms= 0f;//振动有效值
    private float vib_p= 0f;//振动单峰值
    private float vib_pp= 0f;//振动峰峰值
    private float vib_vsx1= 0f;//监测带1频谱幅值平方和开方
    private float vib_vsx2= 0f;//监测带2频谱幅值平方和开方
    private float vib_vsx3= 0f;//监测带3频谱幅值平方和开方
    private float vib_vsx4= 0f;
    private float vib_vsx5= 0f;
    private float vib_vsx6= 0f;
    private float vib_vsx7= 0f;
    private float vib_vsx8= 0f;
    private float vib_vsx1_scale= 0f;//VIB_Vsx1与全频谱幅值平方和开方的比值
    private float vib_vsx2_scale= 0f;//VIB_Vsx2与全频谱幅值平方和开方的比值
    private float vib_vsx3_scale= 0f;
    private float vib_vsx4_scale= 0f;
    private float vib_vsx5_scale= 0f;
    private float vib_vsx6_scale= 0f;
    private float vib_vsx7_scale= 0f;
    private float vib_vsx8_scale= 0f;
    private float vib_avg= 0f;//振动平均值
    private float gap= 0f;//Gap电压
    private Integer vib_wave_len = 0;//压缩后的振动波形数据的字节长度
    private Float pow_rms = 0f; //能量有效值
    private Float vib_k = 0f; //峭度
    private Float vib_pf = 0f; //峰值因素
    private Float vib_cf = 0f; //裕度
    private Float vib_sf = 0f; //偏斜度
    private Float sv = 0f; //冲击值
    private Float vib_rms1 = 0f; //一次积分有效值
    private Float vib_rms2 = 0f; //二次积分有效值
    private Float temperature = 0f; //温度
    private Float temperature_rise = 0f; //温升
    private int diagnosis = 0;// 振动结果
    private byte[] vib_wave;//1、如果VIB_Wave_len > 0，压缩后振动波形点描述 char  WaveBuff [VIB_Wave_len ]；
    private String reserve;//保留字段；
    private String machine_id;//机组ID,匹配表
    private float tempValue;
    private int count;//查询波形行数

	public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getTempValue() {
        return tempValue;
    }

    public void setTempValue(float tempValue) {
        this.tempValue = tempValue;
    }

    public String getMachine_id() {
        return machine_id;
    }

    public void setMachine_id(String machine_id) {
        this.machine_id = machine_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getVib_rms() {
        return vib_rms;
    }

    public void setVib_rms(float vib_rms) {
        this.vib_rms = vib_rms;
    }

    public float getVib_p() {
        return vib_p;
    }

    public void setVib_p(float vib_p) {
        this.vib_p = vib_p;
    }

    public float getVib_pp() {
        return vib_pp;
    }

    public void setVib_pp(float vib_pp) {
        this.vib_pp = vib_pp;
    }

    public float getVib_vsx1() {
        return vib_vsx1;
    }

    public void setVib_vsx1(float vib_vsx1) {
        this.vib_vsx1 = vib_vsx1;
    }

    public float getVib_vsx2() {
        return vib_vsx2;
    }

    public void setVib_vsx2(float vib_vsx2) {
        this.vib_vsx2 = vib_vsx2;
    }

    public float getVib_vsx3() {
        return vib_vsx3;
    }

    public void setVib_vsx3(float vib_vsx3) {
        this.vib_vsx3 = vib_vsx3;
    }

    public float getVib_vsx4() {
        return vib_vsx4;
    }

    public void setVib_vsx4(float vib_vsx4) {
        this.vib_vsx4 = vib_vsx4;
    }

    public float getVib_vsx5() {
        return vib_vsx5;
    }

    public void setVib_vsx5(float vib_vsx5) {
        this.vib_vsx5 = vib_vsx5;
    }

    public float getVib_vsx6() {
        return vib_vsx6;
    }

    public void setVib_vsx6(float vib_vsx6) {
        this.vib_vsx6 = vib_vsx6;
    }

    public float getVib_vsx7() {
        return vib_vsx7;
    }

    public void setVib_vsx7(float vib_vsx7) {
        this.vib_vsx7 = vib_vsx7;
    }

    public float getVib_vsx8() {
        return vib_vsx8;
    }

    public void setVib_vsx8(float vib_vsx8) {
        this.vib_vsx8 = vib_vsx8;
    }

    public float getVib_vsx1_scale() {
        return vib_vsx1_scale;
    }

    public void setVib_vsx1_scale(float vib_vsx1_scale) {
        this.vib_vsx1_scale = vib_vsx1_scale;
    }

    public float getVib_vsx2_scale() {
        return vib_vsx2_scale;
    }

    public void setVib_vsx2_scale(float vib_vsx2_scale) {
        this.vib_vsx2_scale = vib_vsx2_scale;
    }

    public float getVib_vsx3_scale() {
        return vib_vsx3_scale;
    }

    public void setVib_vsx3_scale(float vib_vsx3_scale) {
        this.vib_vsx3_scale = vib_vsx3_scale;
    }

    public float getVib_vsx4_scale() {
        return vib_vsx4_scale;
    }

    public void setVib_vsx4_scale(float vib_vsx4_scale) {
        this.vib_vsx4_scale = vib_vsx4_scale;
    }

    public float getVib_vsx5_scale() {
        return vib_vsx5_scale;
    }

    public void setVib_vsx5_scale(float vib_vsx5_scale) {
        this.vib_vsx5_scale = vib_vsx5_scale;
    }

    public float getVib_vsx6_scale() {
        return vib_vsx6_scale;
    }

    public void setVib_vsx6_scale(float vib_vsx6_scale) {
        this.vib_vsx6_scale = vib_vsx6_scale;
    }

    public float getVib_vsx7_scale() {
        return vib_vsx7_scale;
    }

    public void setVib_vsx7_scale(float vib_vsx7_scale) {
        this.vib_vsx7_scale = vib_vsx7_scale;
    }

    public float getVib_vsx8_scale() {
        return vib_vsx8_scale;
    }

    public void setVib_vsx8_scale(float vib_vsx8_scale) {
        this.vib_vsx8_scale = vib_vsx8_scale;
    }

    public float getVib_avg() {
        return vib_avg;
    }

    public void setVib_avg(float vib_avg) {
        this.vib_avg = vib_avg;
    }

    public float getGap() {
        return gap;
    }

    public void setGap(float gap) {
        this.gap = gap;
    }

    public Integer getVib_wave_len() {
        return vib_wave_len;
    }

    public void setVib_wave_len(Integer vib_wave_len) {
        this.vib_wave_len = vib_wave_len;
    }

    public byte[] getVib_wave() {
        return vib_wave;
    }

    public void setVib_wave(byte[] vib_wave) {
        this.vib_wave = vib_wave;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
    }

    public Date getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(Date saveTime) {
        this.saveTime = saveTime;
    }


	public int getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(int diagnosis) {
		this.diagnosis = diagnosis;
	}

	public Float getPow_rms() {
		return pow_rms;
	}

	public void setPow_rms(Float pow_rms) {
		this.pow_rms = pow_rms;
	}

	public Float getVib_k() {
		return vib_k;
	}

	public void setVib_k(Float vib_k) {
		this.vib_k = vib_k;
	}

	public Float getVib_cf() {
		return vib_cf;
	}

	public void setVib_cf(Float vib_cf) {
		this.vib_cf = vib_cf;
	}

	public Float getVib_pf() {
		return vib_pf;
	}

	public void setVib_pf(Float vib_pf) {
		this.vib_pf = vib_pf;
	}

	public Float getVib_sf() {
		return vib_sf;
	}

	public void setVib_sf(Float vib_sf) {
		this.vib_sf = vib_sf;
	}

	public Float getSv() {
		return sv;
	}

	public void setSv(Float sv) {
		this.sv = sv;
	}

    public Float getVib_rms1() {
        return vib_rms1;
    }

    public void setVib_rms1(Float vib_rms1) {
        this.vib_rms1 = vib_rms1;
    }

    public Float getVib_rms2() {
        return vib_rms2;
    }

    public void setVib_rms2(Float vib_rms2) {
        this.vib_rms2 = vib_rms2;
    }

    public Float getTemperature() {
        return temperature;
    }

    public void setTemperature(Float temperature) {
        this.temperature = temperature;
    }

    public Float getTemperature_rise() {
        return temperature_rise;
    }

    public void setTemperature_rise(Float temperature_rise) {
        this.temperature_rise = temperature_rise;
    }
}
