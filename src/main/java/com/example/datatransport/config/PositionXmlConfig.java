package com.example.datatransport.config;

import java.io.Serializable;

/**
 * 测点XML配置中的属性 此类暂用于有人+倾角
 *
 * @author Jun
 * @date 2022/12/15
 */
public class PositionXmlConfig implements Serializable {

    /**
     * 谱线数
     */
    private int spectral;

    /**
     * 采样频率
     */
    private int samples;

    /**
     * 倾角X 通道id 类型
     */
    private int rock1Id;

    private int rock1ChannelId;

    private int rock1ChannelType;

    private int slaveId1;

    /**
     * 倾角Y 通道id 类型
     */
    private int rock2Id;

    private int rock2ChannelId;

    private int rock2ChannelType;

    private int slaveId2;

    /**
     * 安装位置
     */
    private int posLoc;

    /**
     * 直径
     */
    private float diameter;

    /**
     * x倾角偏移量
     */
    private float offSetX;

    /**
     * y倾角偏移量
     */
    private float offSetY;

    /**
     * 传感器类型
     */
    private int dipModel;


    public int getSpectral() {
        return spectral;
    }

    public void setSpectral(int spectral) {
        this.spectral = spectral;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    public int getRock1Id() {
        return rock1Id;
    }

    public void setRock1Id(int rock1Id) {
        this.rock1Id = rock1Id;
    }

    public int getRock1ChannelId() {
        return rock1ChannelId;
    }

    public void setRock1ChannelId(int rock1ChannelId) {
        this.rock1ChannelId = rock1ChannelId;
    }

    public int getRock1ChannelType() {
        return rock1ChannelType;
    }

    public void setRock1ChannelType(int rock1ChannelType) {
        this.rock1ChannelType = rock1ChannelType;
    }

    public int getSlaveId1() {
        return slaveId1;
    }

    public void setSlaveId1(int slaveId1) {
        this.slaveId1 = slaveId1;
    }

    public int getRock2Id() {
        return rock2Id;
    }

    public void setRock2Id(int rock2Id) {
        this.rock2Id = rock2Id;
    }

    public int getRock2ChannelId() {
        return rock2ChannelId;
    }

    public void setRock2ChannelId(int rock2ChannelId) {
        this.rock2ChannelId = rock2ChannelId;
    }

    public int getRock2ChannelType() {
        return rock2ChannelType;
    }

    public void setRock2ChannelType(int rock2ChannelType) {
        this.rock2ChannelType = rock2ChannelType;
    }

    public int getSlaveId2() {
        return slaveId2;
    }

    public void setSlaveId2(int slaveId2) {
        this.slaveId2 = slaveId2;
    }

    public int getPosLoc() {
        return posLoc;
    }

    public void setPosLoc(int posLoc) {
        this.posLoc = posLoc;
    }

    public float getDiameter() {
        return diameter;
    }

    public void setDiameter(float diameter) {
        this.diameter = diameter;
    }

    public float getOffSetX() {
        return offSetX;
    }

    public void setOffSetX(float offSetX) {
        this.offSetX = offSetX;
    }

    public float getOffSetY() {
        return offSetY;
    }

    public void setOffSetY(float offSetY) {
        this.offSetY = offSetY;
    }

    public int getDipModel() {
        return dipModel;
    }

    public void setDipModel(int dipModel) {
        this.dipModel = dipModel;
    }
}
