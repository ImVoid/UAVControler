package top.lightblog.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.net.Socket;

import top.lightblog.helper.SendAndRecUtil;
import top.lightblog.helper.StatusCode;

/**
 * Created by imliu on 2016/10/9.
 */

public class ConnectUAVTask extends AsyncTask<Context, String, Boolean> {

    private Context context = null;

    @Override
    protected Boolean doInBackground(Context... contexts) {

        try {
            Socket socket = new Socket("192.168.4.1", 333);
            SendAndRecUtil.socket = socket;

            //连接无人机
            if(SendAndRecUtil.socket != null)
                SendAndRecUtil.SendCmd("GEC\r\n".getBytes());

            //确认连接
            byte[] rec = SendAndRecUtil.recive();
            if(rec[1] == (byte) 0x50){
                StatusCode.is_connection = true;
                publishProgress(StatusCode.CONNECTION_SUCCE);
            }else {
                publishProgress(StatusCode.CONNECTION_FAIL);
            }
        } catch (IOException e) {
            e.printStackTrace();
            publishProgress(StatusCode.CONNECTION_FAIL);
        }

        this.context = contexts[0];




        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Toast.makeText(context, values[0], Toast.LENGTH_SHORT).show();
    }

}
