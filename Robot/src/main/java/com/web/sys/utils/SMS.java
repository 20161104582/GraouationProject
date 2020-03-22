package com.web.sys.utils;

import com.github.qcloudsms.SmsMultiSender;
import com.github.qcloudsms.SmsMultiSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import org.apache.commons.lang3.StringEscapeUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SMS {
    private static int appid =  1400146931;
    private static String appkey = AES.desEncrypt("Vo1aNh1eTKwo40qH+3fClMm/hXIJ0U634bMZJfbKIfs=",E.AESKEY,E.AESIV);
    private static int studentTemplateId = 234761;//Integer.parseInt(AES.desEncrypt("s0g4PogC6nbe6MBw33X9nA==",E.AESKEY,E.AESIV)); // {1}���ã�����һ�����Խ���{2} {3} ��ʼ���ԣ�����ǰ��Сʱ�볡׼����
    private static int teeacherTemplateId =234757;// Integer.parseInt(AES.desEncrypt("ewisP+Y639/10yhgoukFSg==",E.AESKEY,E.AESIV));//234757; // {1}���ã�����һ�����Խ���{2} {3} ��ʼ���ԣ�����ǰ��Сʱ�볡���ÿ�����
    private static String smsSign = AES.desEncrypt("bPTfRYjZAbSHyUddSbj/eA==",E.AESKEY,E.AESIV); // NOTE: �����ǩ��"��Ѷ��"ֻ��һ��ʾ������ʵ��ǩ����Ҫ�ڶ��ſ���̨�����룬����ǩ������ʹ�õ���`ǩ������`��������`ǩ��ID`

    public static  Map<String,Object> sendMessage(String username,String date,String time,String[] phoneNumbers,boolean isTeacher){
        Map<String,Object> err = new HashMap<>();
        err.put("result","-1");
        err.put("msg","����ʧ��");
        int tmpid = studentTemplateId ;
        if(isTeacher){
            tmpid = teeacherTemplateId ;
        }

        try {
            String[] params = {username,date,time};//��������Ԫ�ظ�����ģ���б�����������һ�£�����������templateId:5678��Ӧһ������������������Ԫ�ظ���Ҳ������һ��
            SmsMultiSender msender = new SmsMultiSender(appid, appkey);
            SmsMultiSenderResult resultsms =  msender.sendWithParam("86", phoneNumbers,
                    tmpid, params, "", "", "");  // ǩ������δ�ṩ����Ϊ��ʱ����ʹ��Ĭ��ǩ�����Ͷ���
            String ret = decode(resultsms.toString());
            return new JSONObject(ret).toMap();
        } catch (HTTPException e) {
            // HTTP��Ӧ�����
            e.printStackTrace();
        } catch (JSONException e) {
            // json��������
            e.printStackTrace();
        } catch (IOException e) {
            // ����IO����
            e.printStackTrace();
        }
        return err;
    }

    public static String decode(String unicodeStr) {
        if (unicodeStr == null) {
            return null;
        }
        StringBuffer retBuf = new StringBuffer();
        int maxLoop = unicodeStr.length();
        for (int i = 0; i < maxLoop; i++) {
            if (unicodeStr.charAt(i) == '\\') {
                if ((i < maxLoop - 5)
                        && ((unicodeStr.charAt(i + 1) == 'u') || (unicodeStr
                        .charAt(i + 1) == 'U')))
                    try {
                        retBuf.append((char) Integer.parseInt(
                                unicodeStr.substring(i + 2, i + 6), 16));
                        i += 5;
                    } catch (NumberFormatException localNumberFormatException) {
                        retBuf.append(unicodeStr.charAt(i));
                    }
                else
                    retBuf.append(unicodeStr.charAt(i));
            } else {
                retBuf.append(unicodeStr.charAt(i));
            }
        }
        return retBuf.toString();
    }

    public  static void main(String[] args){
        Map<String,Object> map = sendMessage("��һ","2018-12-26","���",new String[]{"123"},true);
        for(String key:map.keySet()){
            T.print(key,map.get(key));
        }
    }
}
