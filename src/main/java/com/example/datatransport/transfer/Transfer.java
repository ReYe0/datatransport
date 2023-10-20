package com.example.datatransport.transfer;

import com.example.datatransport.dao.MacDao;
import com.example.datatransport.dao.PosDao;
import com.example.datatransport.dao.VibDao;
import com.example.datatransport.pojo.*;
import com.example.datatransport.util.WaveObject;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xuy
 */
@Component
@Slf4j
public class Transfer {
    public static short channelNum = 0;
    @Autowired
    public VibDao vibDao;
    @Autowired
    public PosDao posDao;
    @Autowired
    public MacDao macDao;

    /**
     * 将short转为低字节在前，高字节在后的byte数组
     *
     * @param n short
     * @return byte[]
     */
    public static byte[] toLH(short n) {
        byte[] b = new byte[2];
        b[0] = (byte) (n & 0xff);
        b[1] = (byte) (n >> 8 & 0xff);
        return b;
    }

    /**
     * 将short转为高字节在前，低字节在后的byte数组
     *
     * @param n short
     * @return byte[]
     */
    public static byte[] toHH(short n) {
        byte[] b = new byte[2];
        //按位与
        b[1] = (byte) (n & 0xff);
        b[0] = (byte) (n >> 8 & 0xff);
        return b;
    }

    public static short getCRC16(byte[] bytes) throws IOException {
        Integer[] auchCRCHi = {0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40, 0x01, 0xC0, 0x80, 0x41, 0x01, 0xC0, 0x80, 0x41, 0x00, 0xC1, 0x81, 0x40};

        Integer[] auchCRCLo = {0x00, 0xC0, 0xC1, 0x01, 0xC3, 0x03, 0x02, 0xC2, 0xC6, 0x06, 0x07, 0xC7, 0x05, 0xC5, 0xC4, 0x04, 0xCC, 0x0C, 0x0D, 0xCD, 0x0F, 0xCF, 0xCE, 0x0E, 0x0A, 0xCA, 0xCB, 0x0B, 0xC9, 0x09, 0x08, 0xC8, 0xD8, 0x18, 0x19, 0xD9, 0x1B, 0xDB, 0xDA, 0x1A, 0x1E, 0xDE, 0xDF, 0x1F, 0xDD, 0x1D, 0x1C, 0xDC, 0x14, 0xD4, 0xD5, 0x15, 0xD7, 0x17, 0x16, 0xD6, 0xD2, 0x12, 0x13, 0xD3, 0x11, 0xD1, 0xD0, 0x10, 0xF0, 0x30, 0x31, 0xF1, 0x33, 0xF3, 0xF2, 0x32, 0x36, 0xF6, 0xF7, 0x37, 0xF5, 0x35, 0x34, 0xF4, 0x3C, 0xFC, 0xFD, 0x3D, 0xFF, 0x3F, 0x3E, 0xFE, 0xFA, 0x3A, 0x3B, 0xFB, 0x39, 0xF9, 0xF8, 0x38, 0x28, 0xE8, 0xE9, 0x29, 0xEB, 0x2B, 0x2A, 0xEA, 0xEE, 0x2E, 0x2F, 0xEF, 0x2D, 0xED, 0xEC, 0x2C, 0xE4, 0x24, 0x25, 0xE5, 0x27, 0xE7, 0xE6, 0x26, 0x22, 0xE2, 0xE3, 0x23, 0xE1, 0x21, 0x20, 0xE0, 0xA0, 0x60, 0x61, 0xA1, 0x63, 0xA3, 0xA2, 0x62, 0x66, 0xA6, 0xA7, 0x67, 0xA5, 0x65, 0x64, 0xA4, 0x6C, 0xAC, 0xAD, 0x6D, 0xAF, 0x6F, 0x6E, 0xAE, 0xAA, 0x6A, 0x6B, 0xAB, 0x69, 0xA9, 0xA8, 0x68, 0x78, 0xB8, 0xB9, 0x79, 0xBB, 0x7B, 0x7A, 0xBA, 0xBE, 0x7E, 0x7F, 0xBF, 0x7D, 0xBD, 0xBC, 0x7C, 0xB4, 0x74, 0x75, 0xB5, 0x77, 0xB7, 0xB6, 0x76, 0x72, 0xB2, 0xB3, 0x73, 0xB1, 0x71, 0x70, 0xB0, 0x50, 0x90, 0x91, 0x51, 0x93, 0x53, 0x52, 0x92, 0x96, 0x56, 0x57, 0x97, 0x55, 0x95, 0x94, 0x54, 0x9C, 0x5C, 0x5D, 0x9D, 0x5F, 0x9F, 0x9E, 0x5E, 0x5A, 0x9A, 0x9B, 0x5B, 0x99, 0x59, 0x58, 0x98, 0x88, 0x48, 0x49, 0x89, 0x4B, 0x8B, 0x8A, 0x4A, 0x4E, 0x8E, 0x8F, 0x4F, 0x8D, 0x4D, 0x4C, 0x8C, 0x44, 0x84, 0x85, 0x45, 0x87, 0x47, 0x46, 0x86, 0x82, 0x42, 0x43, 0x83, 0x41, 0x81, 0x80, 0x40};

        byte uchCRCHi = (byte) 0xFF;
        byte uchCRCLo = (byte) 0xFF;
        int index;
        for (byte b : bytes) {
            index = (byte) (uchCRCHi ^ b) & 0x00FF;
            uchCRCHi = (byte) (uchCRCLo ^ auchCRCHi[index].byteValue());
            uchCRCLo = auchCRCLo[index].byteValue();
        }
        return getShort(uchCRCHi, uchCRCLo);
    }

    public static short getShort(byte a, byte b) {
        return (short) (((a & 0x00ff) << 8) | (b & 0x00ff));
    }

    public static String getSignature(byte[] data, byte[] key) {
        String base64 = "";
        try {
            SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA1");
            Mac mac = Mac.getInstance("HmacSHA1");
            mac.init(signingKey);
            byte[] rawHmac = mac.doFinal(data);
            BASE64Encoder be = new BASE64Encoder();
            base64 = be.encode(rawHmac);
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return base64;
    }

    public static byte[] intToBigEndian(int value) {
        byte[] byteArray = new byte[4];  // 一个int类型有4个字节

        byteArray[0] = (byte) ((value >> 24) & 0xFF);  // 取最高8位放到byte数组的最后一位
        byteArray[1] = (byte) ((value >> 16) & 0xFF);  // 取次高8位放到byte数组的次后一位
        byteArray[2] = (byte) ((value >> 8) & 0xFF);   // 取次低8位放到byte数组的次后一位
        byteArray[3] = (byte) (value & 0xFF);         // 取最低8位放到byte数组的最前一位

        return byteArray;
    }

    public static byte[] intToLittleEndian(int value) {
        byte[] byteArray = new byte[4];  // 一个int类型有4个字节

        byteArray[0] = (byte) (value & 0xFF);  // 取最低8位放到byte数组的最前一位
        byteArray[1] = (byte) ((value >> 8) & 0xFF);  // 取次低8位放到byte数组的次前一位
        byteArray[2] = (byte) ((value >> 16) & 0xFF);  // 取次高8位放到byte数组的次前一位
        byteArray[3] = (byte) ((value >> 24) & 0xFF);  // 取最高8位放到byte数组的最后一位

        return byteArray;
    }

    public static byte[] float2byte(float s) {
        int intBits = Float.floatToIntBits(s);
        return new byte[]{
                (byte) (intBits >> 24), (byte) (intBits >> 16), (byte) (intBits >> 8), (byte) (intBits)};
    }

    public String doPosSpeedData(String url, long tree_id, short type) {
        log.info("转速波形数据开始");
        try {
            //处理head
            byte[] head = "yinghuada".getBytes();
            int headLen = 48;
            ByteBuffer byteBuffer = ByteBuffer.allocate(head.length);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            byteBuffer.put(head);
            head = byteBuffer.array();
            byte[] head_bytes = new byte[headLen];
            byte[] head_type = null;
            String signature = null;

            List<C_machine> macList = macDao.getMacListByTree_id(tree_id);
            for (int k = 0; k < macList.size(); k++) {
                byte[] bytes = null;
                C_machine c_machine = macList.get(k);
                short macId = Short.parseShort(c_machine.getM_me().split("#")[0]);
                head_type = getHeadBytes(head_bytes, head, headLen, type, macId);
                signature = getSignature(head_type, "51821b77b30e83b2fceaec2b2aca96a2".getBytes());
                //获取采集器配置 解析xml获取通道数量 然后每个通道的原始信息
                List<Integer> dgmIds = vibDao.getDgmIdsByMacId(c_machine.getMachine_id());
                for (int i = 0; i < dgmIds.size(); i++) {
                    bytes = head_bytes;
                    Integer dgm_id = dgmIds.get(i);
                    List<C_dgm2000> c_dgm2000List = vibDao.getDgmByDgm_id(dgm_id);
                    C_dgm2000 c_dgm2000 = c_dgm2000List.get(0);
                    Document document = DocumentHelper.parseText(c_dgm2000.getXml_config());
                    document.setXMLEncoding("UTF-8");
                    Element node = document.getRootElement();
                    Element vibChannel = node.element("VibChannel");
                    List<Element> channelList = vibChannel.elements("ChannelList");

                    //处理数据
                    List<C_machine_position> posList = posDao.getPosList(c_machine.getMachine_id());
                    List<C_machine_position> vibList = posList.stream().filter(obj -> obj.getPosition_type() == 1).collect(Collectors.toList());
                    Map<String,Object> posMap = speedDgmMap.get(dgm_id.toString())!= null ?(Map)speedDgmMap.get(dgm_id.toString()) : new HashMap<>();
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int j = 0; j < vibList.size(); j++) {
                        C_machine_position pos = vibList.get(j);
                        D_id_speed vib = vibDao.getSpeedByChannelId(pos.getPosition_id(), c_machine.getMachine_id());
                        long vibTime = vib == null ? 0L : vib.getSaveTime_Com();
                        long saveTime_Com = posMap.get(String.valueOf(pos.getPosition_id())) != null ? (long) posMap.get(String.valueOf(pos.getPosition_id())) : 0L;
                        if (saveTime_Com != 0L && saveTime_Com >= vibTime) {
                            //测点没有数据或者没有更新数据
                            list.add(j);
                        }
                        speedPosMap.put(String.valueOf(pos.getPosition_id()), vibTime);
                        speedDgmMap.put(String.valueOf(dgm_id), speedPosMap);
                    }
                    Collections.reverse(list);
                    for (int j = 0; j < list.size(); j++) {
                        vibList.remove((int)list.get(j));
                    }
                    if (vibList.size() ==0 ){
                        log.info("没有新的转速数据，则跳过这个采集器：dgmId=" + dgm_id);
                        continue;
                    }
                    //通道数量,不考虑通道数量为0的情况
                    byte[] cmsData = new byte[2];
                    channelNum = 0;
                    byte[] channelInfo = getSpeedInfo(c_machine, channelList, vibList);
                    byte[] waveByte = getSpeedWaveByte(c_machine, vibList);
                    if (channelInfo != null && channelInfo.length >0 && waveByte != null && waveByte.length >0) {
                        writeUnsignedShort(cmsData, channelNum, 0);
                        cmsData = mergeArrayByte(cmsData, channelInfo);
                        cmsData = mergeArrayByte(cmsData, waveByte);
                        bytes = mergeArrayByte(bytes, cmsData);
                    } else {
                        //没有振动数据，则跳过这个采集器
                        log.info("没有转速数据，则跳过这个采集器：dgmId=" + dgm_id);
                        continue;
                    }

                    HttpURLConnection httpConn = getHttpURLConnection(url, signature);

                    DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
                    dos.write(bytes);
                    short crc16 = getCRC16(bytes);
                    dos.write(toHH(crc16));
                    dos.flush();
                    dos.close();
                    log.info("ResponseCode:{},ResponseMessage:{}",httpConn.getResponseCode(),httpConn.getResponseMessage());
                    if (httpConn.getResponseCode() == 200) {
                        log.info("dgmId=" + dgm_id + ",response status：200,success！");
                    } else {
                        log.error("dgmId=" + dgm_id + ",response status：" + httpConn.getResponseCode() + ",fail！");
                    }
                    try {
                        if (dos != null) {
                            dos.close();
                        }
                        if (httpConn != null) {
                            httpConn.disconnect();
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    public String doPostEnvelopeData(String url, long tree_id, short type) {
        log.info("包络波形数据开始");
        try {
            //处理head
            byte[] head = "yinghuada".getBytes();
            int headLen = 48;
            ByteBuffer byteBuffer = ByteBuffer.allocate(head.length);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            byteBuffer.put(head);
            head = byteBuffer.array();
            byte[] head_bytes = new byte[headLen];
            byte[] head_type = null;
            String signature = null;

            List<C_machine> macList = macDao.getMacListByTree_id(tree_id);
            for (int k = 0; k < macList.size(); k++) {
                byte[] bytes = null;
                C_machine c_machine = macList.get(k);
                short macId = Short.parseShort(c_machine.getM_me().split("#")[0]);
                head_type = getHeadBytes(head_bytes, head, headLen, type, macId);
                signature = getSignature(head_type, "51821b77b30e83b2fceaec2b2aca96a2".getBytes());
                //获取采集器配置 解析xml获取通道数量 然后每个通道的原始信息
                List<Integer> dgmIds = vibDao.getDgmIdsByMacId(c_machine.getMachine_id());
                for (int i = 0; i < dgmIds.size(); i++) {
                    bytes = head_bytes;
                    Integer dgm_id = dgmIds.get(i);
                    List<C_dgm2000> c_dgm2000List = vibDao.getDgmByDgm_id(dgm_id);
                    C_dgm2000 c_dgm2000 = c_dgm2000List.get(0);
                    Document document = DocumentHelper.parseText(c_dgm2000.getXml_config());
                    document.setXMLEncoding("UTF-8");
                    Element node = document.getRootElement();
                    Element vibChannel = node.element("VibChannel");
                    List<Element> channelList = vibChannel.elements("ChannelList");

                    //处理数据
                    List<C_machine_position> posList = posDao.getPosList(c_machine.getMachine_id());
                    List<C_machine_position> vibList = posList.stream().filter(obj -> obj.getPosition_type() == 4).collect(Collectors.toList());

                    Map<String,Object> posMap = evelopeDgmMap.get(dgm_id.toString())!= null ?(Map)evelopeDgmMap.get(dgm_id.toString()) : new HashMap<>();
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int j = 0; j < vibList.size(); j++) {
                        C_machine_position pos = vibList.get(j);
                        D_id_envelope vib = vibDao.getEnvelopeByChannelId(pos.getPosition_id(), c_machine.getMachine_id());
                        long vibTime = vib == null ? 0L : vib.getSaveTime_Com();
                        long saveTime_Com = posMap.get(String.valueOf(pos.getPosition_id())) != null ? (long) posMap.get(String.valueOf(pos.getPosition_id())) : 0L;
                        if (saveTime_Com != 0L && saveTime_Com >= vibTime) {
                            //测点没有数据或者没有更新数据
                            list.add(j);
                        }
                        evelopePosMap.put(String.valueOf(pos.getPosition_id()), vibTime);
                        evelopeDgmMap.put(String.valueOf(dgm_id), evelopePosMap);
                    }
                    Collections.reverse(list);
                    for (int j = 0; j < list.size(); j++) {
                        vibList.remove((int)list.get(j));
                    }
                    if (vibList.size() ==0 ){
                        log.info("没有新的包络数据，则跳过这个采集器：dgmId=" + dgm_id);
                        continue;
                    }
                    //通道数量,不考虑通道数量为0的情况
                    byte[] cmsData = new byte[2];
                    channelNum = 0;
                    byte[] channelInfo = getEnvelopeInfo(c_machine, channelList, vibList);
                    byte[] waveByte = getEnvelopeWaveByte(c_machine, vibList);
                    if (channelInfo != null && channelInfo.length >0 && waveByte != null && waveByte.length >0) {
                        writeUnsignedShort(cmsData, channelNum, 0);
                        cmsData = mergeArrayByte(cmsData, channelInfo);
                        cmsData = mergeArrayByte(cmsData, waveByte);
                        bytes = mergeArrayByte(bytes, cmsData);
                    } else {
                        //没有振动数据，则跳过这个采集器
                        log.info("没有包络数据，则跳过这个采集器：dgmId=" + dgm_id);
                        continue;
                    }

                    HttpURLConnection httpConn = getHttpURLConnection(url, signature);

                    DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
                    dos.write(bytes);
                    short crc16 = getCRC16(bytes);
                    dos.write(toHH(crc16));
                    dos.flush();
                    dos.close();
                    log.info("ResponseCode:{},ResponseMessage:{}",httpConn.getResponseCode(),httpConn.getResponseMessage());
                    if (httpConn.getResponseCode() == 200) {
                        log.info("dgmId=" + dgm_id + ",response status：200,success！");
                    } else {
                        log.error("dgmId=" + dgm_id + ",response status：" + httpConn.getResponseCode() + ",fail！");
                    }
                    try {
                        if (dos != null) {
                            dos.close();
                        }
                        if (httpConn != null) {
                            httpConn.disconnect();
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

//    private static long vib_saveTime_com = 0L;
    private static long evelope_saveTime_com = 0L;
    private static long speed_saveTime_com = 0L;
    private static Map<String,Object>  vibDgmMap =new HashMap<>();
    private static Map<String,Object>  vibPosMap =new HashMap<>();

    private static Map<String,Object>  evelopeDgmMap =new HashMap<>();
    private static Map<String,Object>  evelopePosMap =new HashMap<>();

    private static Map<String,Object>  speedDgmMap =new HashMap<>();
    private static Map<String,Object>  speedPosMap =new HashMap<>();
    /**
     * 发送波形数据
     *
     * @param url
     * @return java.lang.String
     * @author xuy
     * @date 2023/9/27 14:12
     */
    public String doPostCmsVib(String url, long tree_id, short type) {
        log.info("振动波形开始");
        try {
            //处理head
            byte[] head = "yinghuada".getBytes();
            int headLen = 48;
            ByteBuffer byteBuffer = ByteBuffer.allocate(head.length);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            byteBuffer.put(head);
            head = byteBuffer.array();
            byte[] head_bytes = new byte[headLen];
            byte[] head_type = null;
            String signature = null;

            List<C_machine> macList = macDao.getMacListByTree_id(tree_id);
            for (int k = 0; k < macList.size(); k++) {
                byte[] bytes = null;
                C_machine c_machine = macList.get(k);
                short macId = Short.parseShort(c_machine.getM_me().split("#")[0]);
                head_type = getHeadBytes(head_bytes, head, headLen, type, macId);
                signature = getSignature(head_type, "51821b77b30e83b2fceaec2b2aca96a2".getBytes());
                //获取采集器配置 解析xml获取通道数量 然后每个通道的原始信息
                List<Integer> dgmIds = vibDao.getDgmIdsByMacId(c_machine.getMachine_id());
                for (int i = 0; i < dgmIds.size(); i++) {
                    bytes = head_bytes;
                    Integer dgm_id = dgmIds.get(i);
                    List<C_dgm2000> c_dgm2000List = vibDao.getDgmByDgm_id(dgm_id);
                    C_dgm2000 c_dgm2000 = c_dgm2000List.get(0);
                    Document document = DocumentHelper.parseText(c_dgm2000.getXml_config());
                    document.setXMLEncoding("UTF-8");
                    Element node = document.getRootElement();
                    Element vibChannel = node.element("VibChannel");
                    List<Element> channelList = vibChannel.elements("ChannelList");

                    //处理数据
                    List<C_machine_position> posList = posDao.getPosList(c_machine.getMachine_id());
                    List<C_machine_position> vibList = posList.stream().filter(obj -> obj.getPosition_type() == 3).collect(Collectors.toList());

                    Map<String,Object> posMap = vibDgmMap.get(dgm_id.toString())!= null ?(Map)vibDgmMap.get(dgm_id.toString()) : new HashMap<>();
                    ArrayList<Integer> list = new ArrayList<>();
                    for (int j = 0; j < vibList.size(); j++) {
                        C_machine_position pos = vibList.get(j);
                        D_id_vib vib = vibDao.getVibByChannelId(pos.getPosition_id(), c_machine.getMachine_id());
                        long vibTime = vib == null ? 0L : vib.getSaveTime_Com();
                        long saveTime_Com = posMap.get(String.valueOf(pos.getPosition_id())) != null ? (long) posMap.get(String.valueOf(pos.getPosition_id())):0L;
                        if (saveTime_Com != 0L && saveTime_Com >= vibTime){
                            //测点没有数据或者没有更新数据
                            list.add(j);
                        }
                        vibPosMap.put(String.valueOf(pos.getPosition_id()),vibTime);
                        vibDgmMap.put(String.valueOf(dgm_id),vibPosMap);

                    }
                    Collections.reverse(list);
                    for (int j = 0; j < list.size(); j++) {
                        vibList.remove((int)list.get(j));
                    }
                    if (vibList.size() ==0 ){
                        log.info("没有新的振动数据，则跳过这个采集器：dgmId=" + dgm_id);
                        continue;
                    }
                    //通道数量,不考虑通道数量为0的情况
                    byte[] cmsData = new byte[2];
                    channelNum = 0;
                    byte[] channelInfo = getChannelInfo(c_machine, channelList, vibList);
                    byte[] waveByte = getWaveByte(c_machine, vibList);
                    if (channelInfo != null && waveByte != null && channelInfo.length > 0 && waveByte.length > 0) {
                        writeUnsignedShort(cmsData, channelNum, 0);
                        cmsData = mergeArrayByte(cmsData, channelInfo);
                        cmsData = mergeArrayByte(cmsData, waveByte);
                        bytes = mergeArrayByte(bytes, cmsData);
                    } else {
                        //没有振动数据，则跳过这个采集器
                        log.info("没有振动数据，则跳过这个采集器：dgmId=" + dgm_id);
                        continue;
                    }

                    HttpURLConnection httpConn = getHttpURLConnection(url, signature);

                    DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
                    dos.write(bytes);
                    short crc16 = getCRC16(bytes);
                    dos.write(toHH(crc16));
                    dos.flush();
                    dos.close();
                    log.info("ResponseCode:{},ResponseMessage:{}",httpConn.getResponseCode(),httpConn.getResponseMessage());
                    if (httpConn.getResponseCode() == 200) {
                        log.info("dgmId=" + dgm_id + ",response status：200,success！");
                    } else {
                        log.error("dgmId=" + dgm_id + ",response status：" + httpConn.getResponseCode() + ",fail！");
                    }
                    try {
                        if (dos != null) {
                            dos.close();
                        }
                        if (httpConn != null) {
                            httpConn.disconnect();
                        }
                    } catch (Exception e) {
                        log.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    /**
     * TODO methods
     *
     * @param url
     * @param tree_id
     * @param type    协议类型
     * @return java.lang.String
     * @author xuy
     * @date 2023/9/27 15:04
     */
    public String doPostNavigateConfig(String url, long tree_id, short type) {
        log.info("组态开始");
        try {
            //处理head
            byte[] head = "yinghuada".getBytes();
            int headLen = 48;
            ByteBuffer byteBuffer = ByteBuffer.allocate(head.length);
            byteBuffer.order(ByteOrder.BIG_ENDIAN);
            byteBuffer.put(head);
            head = byteBuffer.array();
            byte[] head_bytes = new byte[headLen];
            byte[] head_type = null;
            String signature = null;

            List<C_machine> macList = macDao.getMacListByTree_id(tree_id);
            for (int k = 0; k < macList.size(); k++) {
//                log.info("开始处理机组ID：{}的数据",macList.get(k));
                byte[] bytes = null;
                C_machine c_machine = macList.get(k);
                short macId = Short.parseShort(c_machine.getM_me().split("#")[0]);
                head_type = getHeadBytes(head_bytes, head, headLen, type, macId);
                signature = getSignature(head_type, "51821b77b30e83b2fceaec2b2aca96a2".getBytes());
                //获取采集器配置 解析xml获取通道数量 然后每个通道的原始信息
                List<Integer> dgmIds = vibDao.getDgmIdsByMacId(c_machine.getMachine_id());
                for (int i = 0; i < dgmIds.size(); i++) {
                    bytes = head_bytes;
                    Integer dgm_id = dgmIds.get(i);
//                    log.info("开始处理采集器ID为：{} 的数据",dgm_id);
                    List<C_dgm2000> c_dgm2000List = vibDao.getDgmByDgm_id(dgm_id);
                    C_dgm2000 c_dgm2000 = c_dgm2000List.get(0);

                    Document document = DocumentHelper.parseText(c_dgm2000.getXml_config());
                    document.setXMLEncoding("UTF-8");
                    Element node = document.getRootElement();
                    Element vibChannel = node.element("VibChannel");
                    List<Element> channelList = vibChannel.elements("ChannelList");

                    //处理数据
                    int vsersion = c_dgm2000.getUpgrade_state();

                    List<C_machine_position> posList = posDao.getPosList(c_machine.getMachine_id());
                    List<C_machine_position> vibList = posList.stream().filter(obj -> obj.getPosition_type() == 3).collect(Collectors.toList());
                    if (vibList.size() == 0) {
//                        log.info("该采集器没有振动测点，跳过");
                        continue;
                    }


                    byte[] cmsData = new byte[6];
                    byte[] verBytes = intToBigEndian(vsersion);
                    for (int j = 0; j < verBytes.length; j++) {
                        cmsData[j] = verBytes[j];
                    }
//                    log.info("开始处理振动测点的通道数据");
                    byte[] channelInfo = getConfigChannelInfo(c_machine, channelList, vibList);
//                    log.info("振动测点通道数据处理完成");
                    if (channelInfo != null && channelInfo.length > 0) {
//                        log.info("开始拼接数据");
                        writeUnsignedShort(cmsData, (short) vibList.size(), 4);
                        cmsData = mergeArrayByte(cmsData, channelInfo);
                        bytes = mergeArrayByte(bytes, cmsData);
                    }
//                    log.info("数据拼接完成，开始连接服务器");

                    HttpURLConnection httpConn = getHttpURLConnection(url, signature);
//                    log.info("获取httpConn，开始写入数据");
                    DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
                    dos.write(bytes);
                    short crc16 = getCRC16(bytes);
                    dos.write(toHH(crc16));
                    dos.flush();
                    dos.close();
//                    log.info("写入数据完成，开始获取结果：resCode:"+httpConn.getResponseCode());
                    log.info("ResponseCode:{},ResponseMessage:{}",httpConn.getResponseCode(),httpConn.getResponseMessage());
                    if (httpConn.getResponseCode() == 200) {
                        log.info("dgmId=" + dgm_id + ",response status：200,success！");
                    } else {
                        log.info("dgmId=" + dgm_id + ",response status：" + httpConn.getResponseCode() + ",fail！");
                    }
//                    log.info("获取结果完成，开始关闭连接");
                    try {
                        if (dos != null) {
                            dos.close();
                        }
                        if (httpConn != null) {
                            httpConn.disconnect();
                        }
                    } catch (Exception e) {
                        log.error("关闭dos和httpConn异常:"+e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            log.error("组态信息异常："+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    private HttpURLConnection getHttpURLConnection(String url, String signature) throws IOException {
        HttpURLConnection httpConn = null;
        URL urlObj = new URL(url);
        httpConn = (HttpURLConnection) urlObj.openConnection();
        httpConn.setDoInput(true);
        // 允许传入body参数
        httpConn.setDoOutput(true);
        httpConn.setRequestMethod("POST");
        // POST不支持缓存
        httpConn.setUseCaches(false);
        httpConn.setRequestProperty("Connection", "Keep-Alive");
        httpConn.setRequestProperty("Accept", "*/*");
        httpConn.setRequestProperty("Accept-Encoding", "gzip, deflate");
        httpConn.setRequestProperty("Cache-Control", "no-cache");
        httpConn.setRequestProperty("Charset", "utf-8");
        // 这个比较重要，按照上面分析的拼装出Content-Type头的内容 https://blog.csdn.net/weiguang102/article/details/119645861
        httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        httpConn.setRequestProperty("Content-SHA1", signature);
        httpConn.connect();
        return httpConn;
    }

    private byte[] getHeadBytes(byte[] bytes, byte[] head, int headLen, short type, short macId) {
        for (int n = 0; n < head.length; n++) {
            bytes[n] = head[n];
        }
        byte[] head_type = new byte[headLen];
        for (int n = 0; n < head.length; n++) {
            head_type[n] = head[n];
        }
        if (head.length <= 21) {
            //无符号short值，风场代码
            writeUnsignedShort(bytes, head_type, (short) 15, 21);
            //无符号short值，风机编号
            writeUnsignedShort(bytes, head_type, macId, 23);
            //无符号short值，协议类型
            writeUnsignedShort(bytes, head_type, type, 25);
            //时间戳
            byte[] timestamp = String.valueOf(System.currentTimeMillis()).getBytes();
            for (int n = 0; n < timestamp.length; n++) {
                bytes[27 + n] = timestamp[n];
                head_type[27 + n] = timestamp[n];
            }
        }
        return head_type;
    }

    private byte[] getSpeedInfo(C_machine c_machine, List<Element> channelList, List<C_machine_position> vibList) throws DocumentException {
        byte[] cmsData = null;
        for (int j = 0; j < vibList.size(); j++) {
            byte[] channelInfo = new byte[2];
            C_machine_position pos = vibList.get(j);
            //转速
            float speed = 0F;
            D_id_speed speedObj = vibDao.getSpeedByChannelId(pos.getPosition_id(), c_machine.getMachine_id());
            if (speedObj != null) {
                speed = speedObj.getSpeed_value();
                channelNum++;
            } else {
                //跳过没有振动数据的测点
                continue;
            }
            List<Element> channels = channelList.stream().filter(obj -> Short.parseShort(obj.attribute("byId").getValue()) == (short) pos.getPosition_id()).collect(Collectors.toList());
            Element channel = channels.get(0);
            //通道编号
            short ch_id = Short.parseShort(channel.attribute("byId").getValue());
            writeUnsignedShort(channelInfo, ch_id, 0);
            //通道位置
            String name = channel.attribute("byName").getValue();
            byte[] nameBytes = new byte[42];
            byte[] objBytes = name.getBytes();
            for (int o = 0; o < objBytes.length; o++) {
                nameBytes[o] = objBytes[o];
            }
            channelInfo = mergeArrayByte(channelInfo, nameBytes);

            String xml = pos.getPos_xml_config();
            String result = xml.replaceFirst("^<\\?xml.*\\?>", "");
            Document doc = DocumentHelper.parseText(result);
            Element root = doc.getRootElement();
            //采样点数
//            int spectral = Integer.parseInt(root.attribute("spectral").getValue());
            int spectral = 0;
            float frequency = 0f;
            byte[] vib_wave = speedObj.getSpeed_wave();
            if (vib_wave.length > 0) {
                WaveObject waveObject = new WaveObject(speedObj.getSpeed_wave());
                frequency = waveObject.freq;
                spectral = waveObject.wave.length;
            }
            byte[] spectralBytes = intToBigEndian(spectral);
            channelInfo = mergeArrayByte(channelInfo, spectralBytes);
            //采样频率
//            float frequency = Float.parseFloat(root.attribute("frequency").getValue());
            byte[] freqBytes = ByteBuffer.allocate(4).putFloat(frequency).array();
            channelInfo = mergeArrayByte(channelInfo, freqBytes);
            //单位
            String unit = channel.attribute("byUnitName").getValue();
            byte[] unitBytes = new byte[21];
            byte[] unitObjBytes = unit.getBytes();
            for (int o = 0; o < unitObjBytes.length; o++) {
                unitBytes[o] = unitObjBytes[o];
            }
            channelInfo = mergeArrayByte(channelInfo, unitBytes);
            //放大系数
            Float scale = 1F;
            byte[] scaleBytes = ByteBuffer.allocate(4).putFloat(scale).array();
            channelInfo = mergeArrayByte(channelInfo, scaleBytes);
            //加入转速
            byte[] speedBytes = ByteBuffer.allocate(4).putFloat(speed).array();
            channelInfo = mergeArrayByte(channelInfo, speedBytes);
            //包络过滤器上下限过滤
            float filter_l = 0l;
            float filter_h = 0l;
            byte[] filter_l_bytes = ByteBuffer.allocate(4).putFloat(filter_l).array();
            byte[] filter_h_bytes = ByteBuffer.allocate(4).putFloat(filter_h).array();
            channelInfo = mergeArrayByte(channelInfo, filter_l_bytes);
            channelInfo = mergeArrayByte(channelInfo, filter_h_bytes);
            if (cmsData == null) {
                cmsData = new byte[0];
            }
            cmsData = mergeArrayByte(cmsData, channelInfo);
        }
        return cmsData;
    }
    private byte[] getEnvelopeInfo(C_machine c_machine, List<Element> channelList, List<C_machine_position> vibList) throws DocumentException {
        byte[] cmsData = null;
        for (int j = 0; j < vibList.size(); j++) {
            byte[] channelInfo = new byte[2];
            C_machine_position pos = vibList.get(j);
            //转速
            float speed = 0F;
            D_id_envelope envelope = vibDao.getEnvelopeByChannelId(pos.getPosition_id(), c_machine.getMachine_id());
            if (envelope != null) {
                speed = envelope.getSpeed();
                channelNum++;
            } else {
                //跳过没有振动数据的测点
                continue;
            }
            List<Element> channels = channelList.stream().filter(obj -> Short.parseShort(obj.attribute("byId").getValue()) == (short) pos.getPosition_id()).collect(Collectors.toList());
            Element channel = channels.get(0);
            //通道编号
            short ch_id = Short.parseShort(channel.attribute("byId").getValue());
            writeUnsignedShort(channelInfo, ch_id, 0);
            //通道位置
            String name = channel.attribute("byName").getValue();
            byte[] nameBytes = new byte[42];
            byte[] objBytes = name.getBytes();
            for (int o = 0; o < objBytes.length; o++) {
                nameBytes[o] = objBytes[o];
            }
            channelInfo = mergeArrayByte(channelInfo, nameBytes);

            String xml = pos.getPos_xml_config();
            String result = xml.replaceFirst("^<\\?xml.*\\?>", "");
            Document doc = DocumentHelper.parseText(result);
            Element root = doc.getRootElement();
            //采样点数
//            int spectral = Integer.parseInt(root.attribute("spectral").getValue());
            int spectral = 0;
            byte[] vib_wave = envelope.getSpm_wave();
            if (vib_wave.length > 0) {
                WaveObject waveObject = new WaveObject(envelope.getSpm_wave());
                spectral = waveObject.wave.length;
            }
            byte[] spectralBytes = intToBigEndian(spectral);
            channelInfo = mergeArrayByte(channelInfo, spectralBytes);
            //采样频率
            float frequency = Float.parseFloat(root.attribute("frequency").getValue());
            byte[] freqBytes = ByteBuffer.allocate(4).putFloat(frequency).array();
            channelInfo = mergeArrayByte(channelInfo, freqBytes);
            //单位
            String unit = channel.attribute("byUnitName").getValue();
            byte[] unitBytes = new byte[21];
            byte[] unitObjBytes = unit.getBytes();
            for (int o = 0; o < unitObjBytes.length; o++) {
                unitBytes[o] = unitObjBytes[o];
            }
            channelInfo = mergeArrayByte(channelInfo, unitBytes);
            //放大系数
            Float scale = 1F;
            byte[] scaleBytes = ByteBuffer.allocate(4).putFloat(scale).array();
            channelInfo = mergeArrayByte(channelInfo, scaleBytes);
            //加入转速
            byte[] speedBytes = ByteBuffer.allocate(4).putFloat(speed).array();
            channelInfo = mergeArrayByte(channelInfo, speedBytes);
            //包络过滤器上下限过滤
            float filter_l = 0l;
            float filter_h = 0l;
            byte[] filter_l_bytes = ByteBuffer.allocate(4).putFloat(filter_l).array();
            byte[] filter_h_bytes = ByteBuffer.allocate(4).putFloat(filter_h).array();
            channelInfo = mergeArrayByte(channelInfo, filter_l_bytes);
            channelInfo = mergeArrayByte(channelInfo, filter_h_bytes);
            if (cmsData == null) {
                cmsData = new byte[0];
            }
            cmsData = mergeArrayByte(cmsData, channelInfo);
        }
        return cmsData;
    }

    private byte[] getChannelInfo(C_machine c_machine, List<Element> channelList, List<C_machine_position> vibList) throws DocumentException {
        byte[] cmsData = null;
        for (int j = 0; j < vibList.size(); j++) {
            byte[] channelInfo = new byte[2];
            C_machine_position pos = vibList.get(j);
            //转速
            float speed = 0F;
            D_id_vib vib = vibDao.getVibByChannelId(pos.getPosition_id(), c_machine.getMachine_id());
            if (vib != null) {
                speed = vib.getSpeed();
                channelNum++;
            } else {
                //跳过没有振动数据的测点
                continue;
            }
            List<Element> channels = channelList.stream().filter(obj -> Short.parseShort(obj.attribute("byId").getValue()) == (short) pos.getPosition_id()).collect(Collectors.toList());
            Element channel = channels.get(0);
            //通道编号
            short ch_id = Short.parseShort(channel.attribute("byId").getValue());
            writeUnsignedShort(channelInfo, ch_id, 0);
            //通道位置
            String name = channel.attribute("byName").getValue();
            byte[] nameBytes = new byte[42];
            byte[] objBytes = name.getBytes();
            for (int o = 0; o < objBytes.length; o++) {
                nameBytes[o] = objBytes[o];
            }
            channelInfo = mergeArrayByte(channelInfo, nameBytes);

            String xml = pos.getPos_xml_config();
            String result = xml.replaceFirst("^<\\?xml.*\\?>", "");
            Document doc = DocumentHelper.parseText(result);
            Element root = doc.getRootElement();
            //采样点数
//            int spectral = Integer.parseInt(root.attribute("spectral").getValue());
            int spectral = 0;
            byte[] vib_wave = vib.getVib_wave();
            if (vib_wave.length > 0) {
                WaveObject waveObject = new WaveObject(vib.getVib_wave());
                spectral = waveObject.wave.length;
            }
            byte[] spectralBytes = intToBigEndian(spectral);
            channelInfo = mergeArrayByte(channelInfo, spectralBytes);
            //采样频率
            float frequency = Float.parseFloat(root.attribute("frequency").getValue());
            byte[] freqBytes = ByteBuffer.allocate(4).putFloat(frequency).array();
            channelInfo = mergeArrayByte(channelInfo, freqBytes);
            //单位
            String unit = channel.attribute("byUnitName").getValue();
            byte[] unitBytes = new byte[21];
            byte[] unitObjBytes = unit.getBytes();
            for (int o = 0; o < unitObjBytes.length; o++) {
                unitBytes[o] = unitObjBytes[o];
            }
            channelInfo = mergeArrayByte(channelInfo, unitBytes);
            //放大系数
            Float scale = 1F;
            byte[] scaleBytes = ByteBuffer.allocate(4).putFloat(scale).array();
            channelInfo = mergeArrayByte(channelInfo, scaleBytes);
            //加入转速
            byte[] speedBytes = ByteBuffer.allocate(4).putFloat(speed).array();
            channelInfo = mergeArrayByte(channelInfo, speedBytes);
            if (cmsData == null) {
                cmsData = new byte[0];
            }
            cmsData = mergeArrayByte(cmsData, channelInfo);
        }
        return cmsData;
    }

    private byte[] getConfigChannelInfo(C_machine c_machine, List<Element> channelList, List<C_machine_position> vibList) throws DocumentException {
        byte[] cmsData = null;
        for (int j = 0; j < vibList.size(); j++) {
            byte[] channelInfo = new byte[2];
            C_machine_position pos = vibList.get(j);
            List<Element> channels = channelList.stream().filter(obj -> Short.parseShort(obj.attribute("byId").getValue()) == (short) pos.getPosition_id()).collect(Collectors.toList());
            Element channel = channels.get(0);
            //通道编号
            short ch_id = Short.parseShort(channel.attribute("byId").getValue());
            writeUnsignedShort(channelInfo, ch_id, 0);
            //通道位置
            String name = channel.attribute("byName").getValue();
            byte[] nameBytes = new byte[42];
            byte[] objBytes = name.getBytes();
            for (int o = 0; o < objBytes.length; o++) {
                nameBytes[o] = objBytes[o];
            }
            channelInfo = mergeArrayByte(channelInfo, nameBytes);
            //传感器参数
            String sensorType = channel.attribute("bySensorType").getValue();
            byte[] sensorBytes = new byte[21];
            byte[] objsensor = sensorType.getBytes();
            for (int i = 0; i < objsensor.length; i++) {
                sensorBytes[i] = objBytes[i];
            }
            channelInfo = mergeArrayByte(channelInfo, sensorBytes);

            String xml = pos.getPos_xml_config();
            String result = xml.replaceFirst("^<\\?xml.*\\?>", "");
            Document doc = DocumentHelper.parseText(result);
            Element root = doc.getRootElement();

            //采样点数
            int spectral = Integer.parseInt(root.attribute("spectral").getValue());
            byte[] spectralBytes = intToBigEndian(spectral);
            channelInfo = mergeArrayByte(channelInfo, spectralBytes);
            //采样频率
            float frequency = Float.parseFloat(root.attribute("frequency").getValue());
            byte[] freqBytes = ByteBuffer.allocate(4).putFloat(frequency).array();
            channelInfo = mergeArrayByte(channelInfo, freqBytes);
            //单位
            String unit = channel.attribute("byUnitName").getValue();
            byte[] unitBytes = new byte[21];
            byte[] unitObjBytes = unit.getBytes();
            for (int o = 0; o < unitObjBytes.length; o++) {
                unitBytes[o] = unitObjBytes[o];
            }
            channelInfo = mergeArrayByte(channelInfo, unitBytes);
            if (cmsData == null) {
                cmsData = new byte[0];
            }
            cmsData = mergeArrayByte(cmsData, channelInfo);
        }
        return cmsData;
    }
    private byte[] getSpeedWaveByte(C_machine c_machine, List<C_machine_position> vibList) throws DocumentException {
        byte[] waveData = null;
        for (int j = 0; j < vibList.size(); j++) {
            C_machine_position pos = vibList.get(j);
            D_id_speed speed = vibDao.getSpeedByChannelId(pos.getPosition_id(), c_machine.getMachine_id());
            if (speed == null) {
                //跳过没有振动数据的测点
                continue;
            }
            byte[] vib_wave = speed.getSpeed_wave();
            if (vib_wave != null && vib_wave.length > 0) {
                WaveObject waveObject = new WaveObject(vib_wave);
                float[] wave = waveObject.wave;
                byte[] target_wave = new byte[0];
                for (int i = 0; i < wave.length; i++) {
                    float v = wave[i];
//                    byte[] val = ByteBuffer.allocate(4).putFloat(v).array();
                    target_wave = mergeArrayByte(target_wave, float2byte(v));
                }
                waveData = waveData != null ? mergeArrayByte(waveData, target_wave) : target_wave;
            }
        }
        return waveData;
    }
    private byte[] getEnvelopeWaveByte(C_machine c_machine, List<C_machine_position> vibList) throws DocumentException {
        byte[] waveData = null;
        for (int j = 0; j < vibList.size(); j++) {
            C_machine_position pos = vibList.get(j);
            D_id_envelope envelope = vibDao.getEnvelopeByChannelId(pos.getPosition_id(), c_machine.getMachine_id());
            if (envelope == null) {
                //跳过没有振动数据的测点
                continue;
            }
            byte[] vib_wave = envelope.getSpm_wave();
            if (vib_wave != null && vib_wave.length > 0) {
                WaveObject waveObject = new WaveObject(vib_wave);
                float[] wave = waveObject.wave;
                byte[] target_wave = new byte[0];
                for (int i = 0; i < wave.length; i++) {
                    float v = wave[i];
//                    byte[] val = ByteBuffer.allocate(4).putFloat(v).array();
                    target_wave = mergeArrayByte(target_wave, float2byte(v));
                }
                waveData = waveData != null ? mergeArrayByte(waveData, target_wave) : target_wave;
            }
        }
        return waveData;
    }

    private byte[] getWaveByte(C_machine c_machine, List<C_machine_position> vibList) throws DocumentException {
        byte[] waveData = null;
        for (int j = 0; j < vibList.size(); j++) {
            C_machine_position pos = vibList.get(j);
            D_id_vib vib = vibDao.getVibByChannelId(pos.getPosition_id(), c_machine.getMachine_id());
            if (vib == null) {
                //跳过没有振动数据的测点
                continue;
            }
            byte[] vib_wave = vib.getVib_wave();
            if (vib_wave != null && vib_wave.length > 0) {
                WaveObject waveObject = new WaveObject(vib_wave);
                float[] wave = waveObject.wave;
                byte[] target_wave = new byte[0];
                for (int i = 0; i < wave.length; i++) {
                    float v = wave[i];
//                    byte[] val = ByteBuffer.allocate(4).putFloat(v).array();
                    target_wave = mergeArrayByte(target_wave, float2byte(v));
                }
                waveData = waveData != null ? mergeArrayByte(waveData, target_wave) : target_wave;
            }
        }
        return waveData;
    }

    public void writeUnsignedShort(byte[] bytes, byte[] head_type, short value, int index) {
        // 将无符号short值转换为有符号int值
        int macID = value & 0xFFFF;
        // 将有符号int值转换为byte数组
        bytes[index] = (byte) ((macID >> 8) & 0xFF);
        bytes[index + 1] = (byte) (macID & 0xFF);
        head_type[index] = bytes[index];
        head_type[index + 1] = bytes[index + 1];
    }

    public void writeUnsignedShort(byte[] bytes, short value, int index) {
        // 将无符号short值转换为有符号int值
        int macID = value & 0xFFFF;
        // 将有符号int值转换为byte数组
        bytes[index] = (byte) ((macID >> 8) & 0xFF);
        bytes[index + 1] = (byte) (macID & 0xFF);
    }


    public byte[] mergeArrayByte(byte[] src1, byte[] src2) {
        byte[] target = new byte[src1.length + src2.length];
        System.arraycopy(src1, 0, target, 0, src1.length);
        System.arraycopy(src2, 0, target, src1.length, src2.length);
        return target;
    }
}
