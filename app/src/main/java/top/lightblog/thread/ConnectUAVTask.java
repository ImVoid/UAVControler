package top.lightblog.thread;

import android.content.Context;

import java.io.IOException;
import java.net.Socket;

import top.lightblog.helper.HandlerManager;
import top.lightblog.helper.SendAndRecUtil;
import top.lightblog.helper.StatusCode;

/**
 * Created by imliu on 2016/10/9.
 */

public class ConnectUAVTask extends Thread {

    private Context context;
    Socket socket = null;


    public ConnectUAVTask(final Context context) {
        this.context = context;
    }


    @Override
    public void run() {
        boolean is_connection = false;

        try {
            Socket socket = new Socket("192.168.4.1", 333);
            SendAndRecUtil.socket = socket;
            SendAndRecUtil.in = socket.getInputStream();
            SendAndRecUtil.out = socket.getOutputStream();
            is_connection = socket.isConnected();
        } catch (IOException e) {
            HandlerManager.handler.sendMessage(HandlerManager.handler.obtainMessage(1));
            e.printStackTrace();
        }

        //连接无人机
        if( is_connection ) {
            StatusCode.is_connection = true;
            SendAndRecUtil.SendCmd("GEC\r\n".getBytes());
            //确认连接
            byte[] rec = SendAndRecUtil.recive();
            if(rec[1] == (byte) 0x50){
                StatusCode.is_connection = true;
                HandlerManager.handler.sendMessage(HandlerManager.handler.obtainMessage(StatusCode.CONNECT_CHANGED));
            }
        }
    }
}
