package com.example.datatransport.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashMap;
import java.util.Map;

public class WaveObject {

    public int wave_type;
    public int comtype;
    public int endianness;
    public float[] b = new float[3];
    public float[] p = new float[3];
    public float speed;
    public float freq;
    public int cycles;
    public int samples;
    public int valuetype;
    public int wave_len;
    public float[] wave;
    public long  currTime;

    public int getWave_type() {
        return wave_type;
    }

    public void setWave_type(int wave_type) {
        this.wave_type = wave_type;
    }

    public int getComtype() {
        return comtype;
    }

    public void setComtype(int comtype) {
        this.comtype = comtype;
    }

    public int getEndianness() {
        return endianness;
    }

    public void setEndianness(int endianness) {
        this.endianness = endianness;
    }

    public float[] getB() {
        return b;
    }

    public void setB(float[] b) {
        this.b = b;
    }

    public float[] getP() {
        return p;
    }

    public void setP(float[] p) {
        this.p = p;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getFreq() {
        return freq;
    }

    public void setFreq(float freq) {
        this.freq = freq;
    }

    public int getCycles() {
        return cycles;
    }

    public void setCycles(int cycles) {
        this.cycles = cycles;
    }

    public int getSamples() {
        return samples;
    }

    public void setSamples(int samples) {
        this.samples = samples;
    }

    public int getValuetype() {
        return valuetype;
    }

    public void setValuetype(int valuetype) {
        this.valuetype = valuetype;
    }

    public int getWave_len() {
        return wave_len;
    }

    public void setWave_len(int wave_len) {
        this.wave_len = wave_len;
    }

    public float[] getWave() {
        return wave;
    }

    public void setWave(float[] wave) {
        this.wave = wave;
    }

    public long getCurrTime() {
        return currTime;
    }

    public void setCurrTime(long currTime) {
        this.currTime = currTime;
    }
    public float[]  getwave() {
        return wave;
    }

    public WaveObject() {

    }

    public WaveObject(byte[] vibwave) {
        try {
            InputStream in = new ByteArrayInputStream(vibwave);
            DataInputStream dis = new DataInputStream(in);
            wave_type = dis.readByte();
            comtype = dis.readByte();//是否压缩
            endianness = dis.readByte();//字节序   3
            b[0] = dis.readFloat();
            b[1] = dis.readFloat();
            b[2] = dis.readFloat();
            p[0] = dis.readFloat();
            p[1] = dis.readFloat();
            p[2] = dis.readFloat();
            speed = dis.readFloat();//转速
            freq = dis.readFloat();//频率  32
            cycles = dis.readInt();
            samples = dis.readInt();
            valuetype = dis.readInt();
            wave_len = dis.readInt();// 16
            byte[] waveByte = new byte[wave_len];

            //int read = dis.read(waveByte);
            dis.readFully(waveByte);
            if (comtype == 1) {
                waveByte = ZLibUtils.decompress(waveByte);
            }
            wave_len = waveByte.length;
            ByteBuffer bb = ByteBuffer.allocate(wave_len).order(ByteOrder.LITTLE_ENDIAN);
            if (endianness == 1) {
                bb = bb.order(ByteOrder.BIG_ENDIAN);
            }
            bb.put(waveByte);
            bb.rewind();
            float a = b[1] * p[1];
            float c = p[1] * b[0] + p[0];
            switch (valuetype) {
                case 0:
                case 3:
                    wave = new float[wave_len];
                    for (int i = 0; i < wave_len; i++) {
                        wave[i] = bb.get() * a + c;
                    }
                    break;
                case 1:
                case 4:
                    wave = new float[wave_len / 2];
                    for (int i = 0; i < wave_len / 2; i++) {
                        wave[i] = bb.getShort() * a + c;
                    }
                    break;
                case 2:
                case 5:
                    wave = new float[wave_len / 4];
                    for (int i = 0; i < wave_len / 4; i++) {
                        wave[i] = bb.getInt() * a + c;
                    }
                    break;
                case 6:
                    wave = new float[wave_len / 4];
                    for (int i = 0; i < wave_len / 4; i++) {
                        wave[i] = bb.getFloat() * a + c;
                    }
                    break;
                case 7:
                    wave = new float[wave_len / 8];
                    for (int i = 0; i < wave_len / 8; i++) {
                        wave[i] = (float) bb.getDouble() * a + c;
                    }
                    break;
            }
            in.close();
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  Map<String, Object> WaveObjects(byte[] vibwave) {
        Map<String, Object> info_map=new HashMap<>();
        try {

            InputStream in = new ByteArrayInputStream(vibwave);
            DataInputStream dis = new DataInputStream(in);
            wave_type = dis.readByte();
            comtype = dis.readByte();//是否压缩
            endianness = dis.readByte();//字节序
            b[0] = dis.readFloat();
            b[1] = dis.readFloat();
            b[2] = dis.readFloat();
            p[0] = dis.readFloat();
            p[1] = dis.readFloat();
            p[2] = dis.readFloat();
            speed = dis.readFloat();//转速
            freq = dis.readFloat();//频率
            cycles = dis.readInt();
            samples = dis.readInt();
            valuetype = dis.readInt();
            wave_len = dis.readInt();
            byte[] waveByte = new byte[wave_len];

            //int read = dis.read(waveByte);

            dis.readFully(waveByte);
            if (comtype == 1) {
                waveByte = ZLibUtils.decompress(waveByte);
            }
            wave_len = waveByte.length;
            ByteBuffer bb = ByteBuffer.allocate(wave_len).order(ByteOrder.LITTLE_ENDIAN);
            if (endianness == 1) {
                bb = bb.order(ByteOrder.BIG_ENDIAN);
            }
            bb.put(waveByte);
            bb.rewind();
            float a = b[1] * p[1];
            float c = p[1] * b[0] + p[0];
            switch (valuetype) {
                case 0:
                case 3:
                    wave = new float[wave_len];
                    for (int i = 0; i < wave_len; i++) {
                        wave[i] = bb.get() * a + c;
                    }
                    break;
                case 1:
                case 4:
                    wave = new float[wave_len / 2];
                    for (int i = 0; i < wave_len / 2; i++) {
                        wave[i] = bb.getShort() * a + c;
                    }
                    break;
                case 2:
                case 5:
                    wave = new float[wave_len / 4];
                    for (int i = 0; i < wave_len / 4; i++) {
                        wave[i] = bb.getInt() * a + c;
                    }
                    break;
                case 6:
                    wave = new float[wave_len / 4];
                    for (int i = 0; i < wave_len / 4; i++) {
                        wave[i] = bb.getFloat() * a + c;
                    }
                    break;
                case 7:
                    wave = new float[wave_len / 8];
                    for (int i = 0; i < wave_len / 8; i++) {
                        wave[i] = (float) bb.getDouble() * a + c;
                    }
                    break;
            }
            info_map.put("wave",wave);
            info_map.put("freq",freq);
            info_map.put("wave_len",wave_len);
            in.close();
            dis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info_map;
    }
    public byte[] creatWave(Float[] data) {
        if (data == null) {
            return new byte[4];
        }
        float[] wave = new float[data.length];
        for (int i = 0; i < data.length; i++) {
            wave[i] = data[i];
        }
        return creatWave(wave);
    }

    public static float[] toFloat(byte[] data) {
        ByteBuffer allocate = ByteBuffer.allocate(data.length);
        float[] floats = new float[data.length / 4];
        for (int i = 0; i < data.length; i += 4) {
            floats[i / 4] = allocate.getFloat(i);
        }
        return floats;
    }

    public byte[] creatWave(float[] data) {
        try {
            byte comtype = 0; // 压缩方式：0：不压缩；1：zip
            byte endianness = 1; // 字节序：0：主机字节序；1：网络字节序

            float[] b = {0, 1, 0}; // 校正参数
            float[] p = {0, 1, 0}; // 率定参数

            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            DataOutputStream dos = new DataOutputStream(buffer);
            dos.writeByte(0);
            dos.writeByte(comtype);
            dos.writeByte(endianness);
            for (float bb : b) {
                dos.writeFloat(bb);
            }
            for (float pp : p) {
                dos.writeFloat(pp);
            }
            dos.writeFloat(speed);
            dos.writeFloat(freq);
            dos.writeInt(cycles);
            dos.writeInt(samples);
            dos.writeInt(valuetype);
            dos.writeInt(samples * 4);
            for (float f : data) {
                dos.writeFloat(f);
            }
            dos.close();
            buffer.close();
            return buffer.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[4];
    }

    public Map waveFFT(float[] data, float freq) {
        int spectral = data.length;
        int mi = 0;
        for (; spectral > 1; spectral >>= 1) {
            mi++;
        }
        int nPointNumber = 1;
        int i;
        for (i = 0; i < mi; i++) {
            nPointNumber *= 2;
        }
        float[] temp = new float[nPointNumber];
        for (i = 0; i < temp.length; i++) {
            temp[i] = data[i];
        }
        data = temp;

        int pBegin = 0;
        int nCount = data.length;
        float fDF = freq / nPointNumber;

        // 抽取
        float[] x_arr = new float[nPointNumber + 1];
        float[] y_arr = new float[nPointNumber + 1];
        x_arr[0] = 0; // 插入空白点
        for (i = 0; i < (nPointNumber - 1); i++) {
            int pos = i;
            pos = (int) ((long) pos * (nCount - 1) / nPointNumber);
            int ipos0 = pos;
            int ipos1 = ipos0 + 1;
            float v0 = data[pBegin + ipos0];
            float v1 = data[pBegin + ipos1];
            // 插值
            float t = pos - ipos0;
            float v = v0 + (v1 - v0) * t;
            x_arr[i + 1] = v;
        }
        x_arr[i + 1] = data[pBegin + nCount - 1]; // 插入最后一个点

        // 计算平均值average，并且令x[i] -= average以消除直流分量
        float sum = 0;
        for (i = 1; i < x_arr.length; i++) {
            sum += x_arr[i];
        }
        float average = sum / nPointNumber;
        for (i = 1; i < x_arr.length; i++) {
            x_arr[i] -= average;
        }

        _o_fft(1, mi, x_arr, y_arr, nPointNumber);

        // 处理计算结果
        int number = (int) (nPointNumber / 2.56);
        float[] OutX = new float[number];
        float[] OutY = new float[number];
        float[] OutPhase = new float[number];
        for (i = 0; i < number; i++) {
            float xi = x_arr[i + 1];
            float yi = y_arr[i + 1];
            // 频率
            OutX[i] = i * fDF;
            // 幅值 mantis 695 *4 改为 *2
            OutY[i] = (float) (Math.sqrt(xi * xi + yi * yi) * 2 / nPointNumber);
            // 相位 (弧度)
            OutPhase[i] = (float) Math.atan2(yi, xi);
        }
        Map map = new HashMap();
        map.put("OutX", OutX);
        map.put("OutY", OutY);
        map.put("OutPhase", OutPhase);
        return map;
    }

    private void _o_fft(float dir, float i_dianshu_mi, float[] x, float[] y, int i_dianshu) {
        int i;
        int j;
        int k;
        int l;
        int m;
        int l1;
        float t1;
        float t2;
        float u1;
        float u2;
        float w1;
        float w2;
        float p2;
        float z;

        // Calculate the number of points
        if (i_dianshu == 0) {
            i_dianshu = 1;
            for (i = 0; i < i_dianshu_mi; i++) {
                i_dianshu *= 2;
            }
        }

        for (i = 0; i <= i_dianshu; i++) {
            y[i] = 0;
        }

        j = 1;

        for (l = 1; l <= (i_dianshu - 1); l++) {
            if (l < j) {
                t1 = x[j];
                t2 = y[j];
                x[j] = x[l];
                y[j] = y[l];
                x[l] = t1;
                y[l] = t2;
            }
            k = (i_dianshu) >> 1;
            while (k < j) {
                j -= k;
                k = k >> 1;
            }
            j = j + k;
        }
        m = 1;

        for (i = 1; i <= i_dianshu_mi; i++) {
            u1 = 1;
            u2 = 0;
            k = m;
            m = m << 1;
            p2 = 3.1415926f / k;
            w1 = (float) (Math.cos(p2));
            w2 = (float) (-Math.sin(p2));
            w2 = -w2;
            for (j = 1; j <= k; j++) {
                for (l = j; l <= i_dianshu; l += m) {
                    l1 = l + k;
                    t1 = x[l1] * u1 - y[l1] * u2;
                    t2 = x[l1] * u2 + y[l1] * u1;
                    x[l1] = x[l] - t1;
                    y[l1] = y[l] - t2;
                    x[l] += t1;
                    y[l] += t2;
                }
                z = u1 * w1 - u2 * w2;
                u2 = u1 * w2 + u2 * w1;
                u1 = z;
            }
        }
    }

    //type为1，需要加汉宁窗
    public Map waveFFT(float[] data, float freq, int type) {
        //data加了汉宁窗
        if (type == 1) {
            for (int i = 0; i < data.length; i++) {
                data[i] = (float) (data[i] * (0.5 - 0.5 * Math.cos(2 * Math.PI * i / data.length)));
            }
        }
        int spectral = data.length;
        int mi = 0;
        for (; spectral > 1; spectral >>= 1) {
            mi++;
        }
        int nPointNumber = 1;
        int i;
        for (i = 0; i < mi; i++) {
            nPointNumber *= 2;
        }
        float[] temp = new float[nPointNumber];
        for (i = 0; i < temp.length; i++) {
            temp[i] = data[i];
        }
        data = temp;

        int pBegin = 0;
        int nCount = data.length;
        float fDF = freq / nPointNumber;

        // 抽取
        float[] x_arr = new float[nPointNumber + 1];
        float[] y_arr = new float[nPointNumber + 1];
        x_arr[0] = 0; // 插入空白点
        for (i = 0; i < (nPointNumber - 1); i++) {
            int pos = i;
            pos = (int) ((long) pos * (nCount - 1) / nPointNumber);
            int ipos0 = pos;
            int ipos1 = ipos0 + 1;
            float v0 = data[pBegin + ipos0];
            float v1 = data[pBegin + ipos1];
            // 插值
            float t = pos - ipos0;
            float v = v0 + (v1 - v0) * t;
            x_arr[i + 1] = v;
        }
        x_arr[i + 1] = data[pBegin + nCount - 1]; // 插入最后一个点

        // 计算平均值average，并且令x[i] -= average以消除直流分量
        float sum = 0;
        for (i = 1; i < x_arr.length; i++) {
            sum += x_arr[i];
        }
        float average = sum / nPointNumber;
        for (i = 1; i < x_arr.length; i++) {
            x_arr[i] -= average;
        }

        _o_fft(1, mi, x_arr, y_arr, nPointNumber);

        // 处理计算结果
        int number = (int) (nPointNumber / 2.56);
        float[] OutX = new float[number];
        float[] OutY = new float[number];
        float[] OutPhase = new float[number];
        for (i = 0; i < number; i++) {
            float xi = x_arr[i + 1];
            float yi = y_arr[i + 1];
            // 频率
            OutX[i] = i * fDF;
            // 幅值 mantis 695 *4 改为 *2
            OutY[i] = (float) (Math.sqrt(xi * xi + yi * yi) * 2 / nPointNumber);
            // 相位 (弧度)
            OutPhase[i] = (float) Math.atan2(yi, xi);
        }
        Map map = new HashMap();
        map.put("OutX", OutX);
        if (type == 1) {
            for (i = 0; i < OutY.length; i++) {
                OutY[i] *= 2;
            }
        }
        map.put("OutY", OutY);
        map.put("OutPhase", OutPhase);
        return map;
    }
}
