package com.sd.style.common.socket;

import com.orhanobut.logger.Logger;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Author: HeLei on 2017/11/13 23:51
 */

public class Client {

    public static void socketTest() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket client = new Socket("192.168.1.120", 1122);
                    Logger.e("连接成功...");
                    OutputStream out = client.getOutputStream();
                    DataOutputStream outs = new DataOutputStream(out);
                    genProtocol(outs, "我是客户端，请求连接哦");
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    Logger.e("连接失败：" + e.getMessage());
                }
            }
        }).start();
    }

    /**
     * 构造协议
     *
     * @param out
     * @param msg
     * @throws IOException
     */
    private static void genProtocol(DataOutputStream out, String msg) throws IOException {
        int type = 1;                          //消息类型
        byte[] bytes = msg.getBytes();         //消息内容
        int totalLen = 1 + 4 + bytes.length;   //消息长度

        out.writeByte(type);                   //写入消息类型
        out.writeInt(totalLen);                //写入消息长度
        out.write(bytes);                      //写入消息内容

        out.flush();

    }
}
