package top.lightblog.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;

import top.lightblog.helper.StatusCode;

/**
 * Created by imliu on 2016/10/9.
 */

public class ConnectUAVTask extends AsyncTask<Context, String, Boolean> {

    private Socket socket = null;
    private Context context = null;

    @Override
    protected Boolean doInBackground(Context... contexts) {

        this.context = contexts[0];

        try {
            //连接无人机
            socket = new Socket("192.168.4.1", 333);
            OutputStream os = socket.getOutputStream();
            os.write("GEC\r\n".getBytes());

            //确认连接
            InputStream in = socket.getInputStream();
            byte[] rec = new byte[34];
            in.read(rec);
            if(rec[1] == 0x50){
                publishProgress(StatusCode.CONNECTION_SUCCE);
            }else {
                publishProgress(StatusCode.CONNECTION_FAIL);
            }

        } catch (IOException e) {
            publishProgress(StatusCode.CONNECTION_FAIL);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        Toast.makeText(context, values[0], Toast.LENGTH_SHORT).show();
    }

}
