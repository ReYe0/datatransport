package com.example.datatransport.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Administrator
 *         包络测点历史数据表（d机组id_envelope）
 */
public class D_id_envelope implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -6921972453884004126L;
    private int id;
    private Date saveTime;
    private long saveTime_Com;
    private int dataId;
    private int jc;
    private float speed;//转速
    private float spm_rms;//SPM 有效值（-1为无效），
    private float spm_p;//包络峰值
    private Float spm_pp;
    private float vib_vsx1;//监测带1频谱幅值平方和开方
    private float vib_vsx2;//监测带2频谱幅值平方和开方
    private float vib_vsx3;//监测带3频谱幅值平方和开方
    private float vib_vsx4;
    private float vib_vsx5;
    private float vib_vsx6;
    private float vib_vsx7;
    private float vib_vsx8;
    private float vib_vsx1_scale;//VIB_Vsx1与全频谱幅值平方和开方的比值
    private float vib_vsx2_scale;//VIB_Vsx2与全频谱幅值平方和开方的比值
    private float vib_vsx3_scale;
    private float vib_vsx4_scale;
    private float vib_vsx5_scale;
    private float vib_vsx6_scale;
    private float vib_vsx7_scale;
    private float vib_vsx8_scale;
    private Float sv; //冲击值
    private Float sv0; //冲击值
    private Float sv1; //冲击值
    private Float sv2; //冲击值
    private Float sv3; //冲击值
    private Float sv4; //冲击值
    private Float sv5; //冲击值
    private Float sv6; //冲击值
    private Float sv7; //冲击值
    private Float sv8; //冲击值
    private Float sv10; //冲击值
    private Float sv11; //冲击值
    private Float sv12; //冲击值
    private Float sv13; //冲击值
    private Float sv14; //冲击值
    private Float sv15; //冲击值
    private Float sv16; //冲击值
    private Float sv17; //冲击值
    private Float sv18; //冲击值
    private Float sv20; //冲击值
    private Float sv21; //冲击值
    private Float sv22; //冲击值
    private Float sv23; //冲击值
    private Float sv24; //冲击值
    private Float sv25; //冲击值
    private Float sv26; //冲击值
    private Float sv27; //冲击值
    private Float sv28; //冲击值
    private Float sv30; //冲击值
    private Float sv31; //冲击值
    private Float sv32; //冲击值
    private Float sv33; //冲击值
    private Float sv34; //冲击值
    private Float sv35; //冲击值
    private Float sv36; //冲击值
    private Float sv37; //冲击值
    private Float sv38; //冲击值
    private Float sv40; //冲击值
    private Float sv41; //冲击值
    private Float sv42; //冲击值
    private Float sv43; //冲击值
    private Float sv44; //冲击值
    private Float sv45; //冲击值
    private Float sv46; //冲击值
    private Float sv47; //冲击值
    private Float sv48; //冲击值
    private Float sv50; //冲击值
    private Float sv51; //冲击值
    private Float sv52; //冲击值
    private Float sv53; //冲击值
    private Float sv54; //冲击值
    private Float sv55; //冲击值
    private Float sv56; //冲击值
    private Float sv57; //冲击值
    private Float sv58; //冲击值
    private Float sv60; //冲击值
    private Float sv61; //冲击值
    private Float sv62; //冲击值
    private Float sv63; //冲击值
    private Float sv64; //冲击值
    private Float sv65; //冲击值
    private Float sv66; //冲击值
    private Float sv67; //冲击值
    private Float sv68; //冲击值
    private Float sv70; //冲击值
    private Float sv71; //冲击值
    private Float sv72; //冲击值
    private Float sv73; //冲击值
    private Float sv74; //冲击值
    private Float sv75; //冲击值
    private Float sv76; //冲击值
    private Float sv77; //冲击值
    private Float sv78; //冲击值
    private Float sv80; //冲击值
    private Float sv81; //冲击值
    private Float sv82; //冲击值
    private Float sv83; //冲击值
    private Float sv84; //冲击值
    private Float sv85; //冲击值
    private Float sv86; //冲击值
    private Float sv87; //冲击值
    private Float sv88; //冲击值
    private Float sv90; //冲击值
    private Float sv91; //冲击值
    private Float sv92; //冲击值
    private Float sv93; //冲击值
    private Float sv94; //冲击值
    private Float sv95; //冲击值
    private Float sv96; //冲击值
    private Float sv97; //冲击值
    private Float sv98; //冲击值
    private String part_name0;
    private String part_name1;
    private String part_name2;
    private String part_name3;
    private String part_name4;
    private String part_name5;
    private String part_name6;
    private String part_name7;
    private String part_name8;
    private String part_name9;
    private Float temperature; //温度
    private Float temperature_rise; //温升
    private int spm_wave_len;//压缩后的SPM波形数据的字节长度
    private byte[] spm_wave;
    private String reserve;
    private float tempValue;//趋势别名
    private String machine_id;
    private int count;//查询波形行数
    private Float spm_avg;
    private Float gap;
    private float pow_rms; //能量有效值
    private int diagnosis;// 振动结果
   
	public int getDiagnosis() {
		return diagnosis;
	}

	public void setDiagnosis(int diagnosis) {
		this.diagnosis = diagnosis;
	}

	public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
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

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getSpm_rms() {
        return spm_rms;
    }

    public void setSpm_rms(float spm_rms) {
        this.spm_rms = spm_rms;
    }

    public String getReserve() {
        return reserve;
    }

    public void setReserve(String reserve) {
        this.reserve = reserve;
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

    public float getSpm_p() {
        return spm_p;
    }

    public void setSpm_p(float spm_p) {
        this.spm_p = spm_p;
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

    public int getSpm_wave_len() {
        return spm_wave_len;
    }

    public void setSpm_wave_len(int spm_wave_len) {
        this.spm_wave_len = spm_wave_len;
    }

    public byte[] getSpm_wave() {
        return spm_wave;
    }

    public void setSpm_wave(byte[] spm_wave) {
        this.spm_wave = spm_wave;
    }

	public float getPow_rms() {
		return pow_rms;
	}

	public void setPow_rms(float pow_rms) {
		this.pow_rms = pow_rms;
	}

	public Float getSpm_pp() {
		return spm_pp;
	}

	public void setSpm_pp(Float spm_pp) {
		this.spm_pp = spm_pp;
	}

	public Float getSpm_avg() {
		return spm_avg;
	}

	public void setSpm_avg(Float spm_avg) {
		this.spm_avg = spm_avg;
	}

	public Float getGap() {
		return gap;
	}

	public void setGap(Float gap) {
		this.gap = gap;
	}

	public Float getSv() {
		return sv;
	}

	public void setSv(Float sv) {
		this.sv = sv;
	}

    public Float getSv0() {
        return sv0;
    }

    public void setSv0(Float sv0) {
        this.sv0 = sv0;
    }

    public Float getSv1() {
        return sv1;
    }

    public void setSv1(Float sv1) {
        this.sv1 = sv1;
    }

    public Float getSv2() {
        return sv2;
    }

    public void setSv2(Float sv2) {
        this.sv2 = sv2;
    }

    public Float getSv3() {
        return sv3;
    }

    public void setSv3(Float sv3) {
        this.sv3 = sv3;
    }

    public Float getSv4() {
        return sv4;
    }

    public void setSv4(Float sv4) {
        this.sv4 = sv4;
    }

    public Float getSv5() {
        return sv5;
    }

    public void setSv5(Float sv5) {
        this.sv5 = sv5;
    }

    public Float getSv6() {
        return sv6;
    }

    public void setSv6(Float sv6) {
        this.sv6 = sv6;
    }

    public Float getSv7() {
        return sv7;
    }

    public void setSv7(Float sv7) {
        this.sv7 = sv7;
    }

    public Float getSv8() {
        return sv8;
    }

    public void setSv8(Float sv8) {
        this.sv8 = sv8;
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

    public Float getSv10() {
        return sv10;
    }

    public void setSv10(Float sv10) {
        this.sv10 = sv10;
    }

    public Float getSv11() {
        return sv11;
    }

    public void setSv11(Float sv11) {
        this.sv11 = sv11;
    }

    public Float getSv12() {
        return sv12;
    }

    public void setSv12(Float sv12) {
        this.sv12 = sv12;
    }

    public Float getSv13() {
        return sv13;
    }

    public void setSv13(Float sv13) {
        this.sv13 = sv13;
    }

    public Float getSv14() {
        return sv14;
    }

    public void setSv14(Float sv14) {
        this.sv14 = sv14;
    }

    public Float getSv15() {
        return sv15;
    }

    public void setSv15(Float sv15) {
        this.sv15 = sv15;
    }

    public Float getSv16() {
        return sv16;
    }

    public void setSv16(Float sv16) {
        this.sv16 = sv16;
    }

    public Float getSv17() {
        return sv17;
    }

    public void setSv17(Float sv17) {
        this.sv17 = sv17;
    }

    public Float getSv18() {
        return sv18;
    }

    public void setSv18(Float sv18) {
        this.sv18 = sv18;
    }

    public Float getSv20() {
        return sv20;
    }

    public void setSv20(Float sv20) {
        this.sv20 = sv20;
    }

    public Float getSv21() {
        return sv21;
    }

    public void setSv21(Float sv21) {
        this.sv21 = sv21;
    }

    public Float getSv22() {
        return sv22;
    }

    public void setSv22(Float sv22) {
        this.sv22 = sv22;
    }

    public Float getSv23() {
        return sv23;
    }

    public void setSv23(Float sv23) {
        this.sv23 = sv23;
    }

    public Float getSv24() {
        return sv24;
    }

    public void setSv24(Float sv24) {
        this.sv24 = sv24;
    }

    public Float getSv25() {
        return sv25;
    }

    public void setSv25(Float sv25) {
        this.sv25 = sv25;
    }

    public Float getSv26() {
        return sv26;
    }

    public void setSv26(Float sv26) {
        this.sv26 = sv26;
    }

    public Float getSv27() {
        return sv27;
    }

    public void setSv27(Float sv27) {
        this.sv27 = sv27;
    }

    public Float getSv28() {
        return sv28;
    }

    public void setSv28(Float sv28) {
        this.sv28 = sv28;
    }

    public Float getSv30() {
        return sv30;
    }

    public void setSv30(Float sv30) {
        this.sv30 = sv30;
    }

    public Float getSv31() {
        return sv31;
    }

    public void setSv31(Float sv31) {
        this.sv31 = sv31;
    }

    public Float getSv32() {
        return sv32;
    }

    public void setSv32(Float sv32) {
        this.sv32 = sv32;
    }

    public Float getSv33() {
        return sv33;
    }

    public void setSv33(Float sv33) {
        this.sv33 = sv33;
    }

    public Float getSv34() {
        return sv34;
    }

    public void setSv34(Float sv34) {
        this.sv34 = sv34;
    }

    public Float getSv35() {
        return sv35;
    }

    public void setSv35(Float sv35) {
        this.sv35 = sv35;
    }

    public Float getSv36() {
        return sv36;
    }

    public void setSv36(Float sv36) {
        this.sv36 = sv36;
    }

    public Float getSv37() {
        return sv37;
    }

    public void setSv37(Float sv37) {
        this.sv37 = sv37;
    }

    public Float getSv38() {
        return sv38;
    }

    public void setSv38(Float sv38) {
        this.sv38 = sv38;
    }

    public Float getSv40() {
        return sv40;
    }

    public void setSv40(Float sv40) {
        this.sv40 = sv40;
    }

    public Float getSv41() {
        return sv41;
    }

    public void setSv41(Float sv41) {
        this.sv41 = sv41;
    }

    public Float getSv42() {
        return sv42;
    }

    public void setSv42(Float sv42) {
        this.sv42 = sv42;
    }

    public Float getSv43() {
        return sv43;
    }

    public void setSv43(Float sv43) {
        this.sv43 = sv43;
    }

    public Float getSv44() {
        return sv44;
    }

    public void setSv44(Float sv44) {
        this.sv44 = sv44;
    }

    public Float getSv45() {
        return sv45;
    }

    public void setSv45(Float sv45) {
        this.sv45 = sv45;
    }

    public Float getSv46() {
        return sv46;
    }

    public void setSv46(Float sv46) {
        this.sv46 = sv46;
    }

    public Float getSv47() {
        return sv47;
    }

    public void setSv47(Float sv47) {
        this.sv47 = sv47;
    }

    public Float getSv48() {
        return sv48;
    }

    public void setSv48(Float sv48) {
        this.sv48 = sv48;
    }

    public Float getSv50() {
        return sv50;
    }

    public void setSv50(Float sv50) {
        this.sv50 = sv50;
    }

    public Float getSv51() {
        return sv51;
    }

    public void setSv51(Float sv51) {
        this.sv51 = sv51;
    }

    public Float getSv52() {
        return sv52;
    }

    public void setSv52(Float sv52) {
        this.sv52 = sv52;
    }

    public Float getSv53() {
        return sv53;
    }

    public void setSv53(Float sv53) {
        this.sv53 = sv53;
    }

    public Float getSv54() {
        return sv54;
    }

    public void setSv54(Float sv54) {
        this.sv54 = sv54;
    }

    public Float getSv55() {
        return sv55;
    }

    public void setSv55(Float sv55) {
        this.sv55 = sv55;
    }

    public Float getSv56() {
        return sv56;
    }

    public void setSv56(Float sv56) {
        this.sv56 = sv56;
    }

    public Float getSv57() {
        return sv57;
    }

    public void setSv57(Float sv57) {
        this.sv57 = sv57;
    }

    public Float getSv58() {
        return sv58;
    }

    public void setSv58(Float sv58) {
        this.sv58 = sv58;
    }

    public Float getSv60() {
        return sv60;
    }

    public void setSv60(Float sv60) {
        this.sv60 = sv60;
    }

    public Float getSv61() {
        return sv61;
    }

    public void setSv61(Float sv61) {
        this.sv61 = sv61;
    }

    public Float getSv62() {
        return sv62;
    }

    public void setSv62(Float sv62) {
        this.sv62 = sv62;
    }

    public Float getSv63() {
        return sv63;
    }

    public void setSv63(Float sv63) {
        this.sv63 = sv63;
    }

    public Float getSv64() {
        return sv64;
    }

    public void setSv64(Float sv64) {
        this.sv64 = sv64;
    }

    public Float getSv65() {
        return sv65;
    }

    public void setSv65(Float sv65) {
        this.sv65 = sv65;
    }

    public Float getSv66() {
        return sv66;
    }

    public void setSv66(Float sv66) {
        this.sv66 = sv66;
    }

    public Float getSv67() {
        return sv67;
    }

    public void setSv67(Float sv67) {
        this.sv67 = sv67;
    }

    public Float getSv68() {
        return sv68;
    }

    public void setSv68(Float sv68) {
        this.sv68 = sv68;
    }

    public Float getSv70() {
        return sv70;
    }

    public void setSv70(Float sv70) {
        this.sv70 = sv70;
    }

    public Float getSv71() {
        return sv71;
    }

    public void setSv71(Float sv71) {
        this.sv71 = sv71;
    }

    public Float getSv72() {
        return sv72;
    }

    public void setSv72(Float sv72) {
        this.sv72 = sv72;
    }

    public Float getSv73() {
        return sv73;
    }

    public void setSv73(Float sv73) {
        this.sv73 = sv73;
    }

    public Float getSv74() {
        return sv74;
    }

    public void setSv74(Float sv74) {
        this.sv74 = sv74;
    }

    public Float getSv75() {
        return sv75;
    }

    public void setSv75(Float sv75) {
        this.sv75 = sv75;
    }

    public Float getSv76() {
        return sv76;
    }

    public void setSv76(Float sv76) {
        this.sv76 = sv76;
    }

    public Float getSv77() {
        return sv77;
    }

    public void setSv77(Float sv77) {
        this.sv77 = sv77;
    }

    public Float getSv78() {
        return sv78;
    }

    public void setSv78(Float sv78) {
        this.sv78 = sv78;
    }

    public Float getSv80() {
        return sv80;
    }

    public void setSv80(Float sv80) {
        this.sv80 = sv80;
    }

    public Float getSv81() {
        return sv81;
    }

    public void setSv81(Float sv81) {
        this.sv81 = sv81;
    }

    public Float getSv82() {
        return sv82;
    }

    public void setSv82(Float sv82) {
        this.sv82 = sv82;
    }

    public Float getSv83() {
        return sv83;
    }

    public void setSv83(Float sv83) {
        this.sv83 = sv83;
    }

    public Float getSv84() {
        return sv84;
    }

    public void setSv84(Float sv84) {
        this.sv84 = sv84;
    }

    public Float getSv85() {
        return sv85;
    }

    public void setSv85(Float sv85) {
        this.sv85 = sv85;
    }

    public Float getSv86() {
        return sv86;
    }

    public void setSv86(Float sv86) {
        this.sv86 = sv86;
    }

    public Float getSv87() {
        return sv87;
    }

    public void setSv87(Float sv87) {
        this.sv87 = sv87;
    }

    public Float getSv88() {
        return sv88;
    }

    public void setSv88(Float sv88) {
        this.sv88 = sv88;
    }

    public Float getSv90() {
        return sv90;
    }

    public void setSv90(Float sv90) {
        this.sv90 = sv90;
    }

    public Float getSv91() {
        return sv91;
    }

    public void setSv91(Float sv91) {
        this.sv91 = sv91;
    }

    public Float getSv92() {
        return sv92;
    }

    public void setSv92(Float sv92) {
        this.sv92 = sv92;
    }

    public Float getSv93() {
        return sv93;
    }

    public void setSv93(Float sv93) {
        this.sv93 = sv93;
    }

    public Float getSv94() {
        return sv94;
    }

    public void setSv94(Float sv94) {
        this.sv94 = sv94;
    }

    public Float getSv95() {
        return sv95;
    }

    public void setSv95(Float sv95) {
        this.sv95 = sv95;
    }

    public Float getSv96() {
        return sv96;
    }

    public void setSv96(Float sv96) {
        this.sv96 = sv96;
    }

    public Float getSv97() {
        return sv97;
    }

    public void setSv97(Float sv97) {
        this.sv97 = sv97;
    }

    public Float getSv98() {
        return sv98;
    }

    public void setSv98(Float sv98) {
        this.sv98 = sv98;
    }

    public String getPart_name0() {
        return part_name0;
    }

    public void setPart_name0(String part_name0) {
        this.part_name0 = part_name0;
    }

    public String getPart_name1() {
        return part_name1;
    }

    public void setPart_name1(String part_name1) {
        this.part_name1 = part_name1;
    }

    public String getPart_name2() {
        return part_name2;
    }

    public void setPart_name2(String part_name2) {
        this.part_name2 = part_name2;
    }

    public String getPart_name3() {
        return part_name3;
    }

    public void setPart_name3(String part_name3) {
        this.part_name3 = part_name3;
    }

    public String getPart_name4() {
        return part_name4;
    }

    public void setPart_name4(String part_name4) {
        this.part_name4 = part_name4;
    }

    public String getPart_name5() {
        return part_name5;
    }

    public void setPart_name5(String part_name5) {
        this.part_name5 = part_name5;
    }

    public String getPart_name6() {
        return part_name6;
    }

    public void setPart_name6(String part_name6) {
        this.part_name6 = part_name6;
    }

    public String getPart_name7() {
        return part_name7;
    }

    public void setPart_name7(String part_name7) {
        this.part_name7 = part_name7;
    }

    public String getPart_name8() {
        return part_name8;
    }

    public void setPart_name8(String part_name8) {
        this.part_name8 = part_name8;
    }

    public String getPart_name9() {
        return part_name9;
    }

    public void setPart_name9(String part_name9) {
        this.part_name9 = part_name9;
    }
}
