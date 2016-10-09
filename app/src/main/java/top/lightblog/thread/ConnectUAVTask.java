package top.lightblog.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

import top.lightblog.helper.SendAndRecUtil;
import top.lightblog.helper.StatusCode;

/**
 * Created by imliu on 2016/10/9.
 */

public class ConnectUAVTask extends Thread {

    private Context context;
    Socket socket = null;
    private Handler mHandler;

    public ConnectUAVTask(final Context context) {
        this.context = context;
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case 0:
                        Toast.makeText(context, msg.obj.toString(), Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context, "连接失败", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };

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
            mHandler.sendMessage(mHandler.obtainMessage(1));
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
                mHandler.sendMessage(mHandler.obtainMessage(0, "连接成功"));
            }else {
                mHandler.sendMessage(mHandler.obtainMessage(1));
            }
        }

    }
}
