package top.lightblog.helper;

import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by imliu on 2016/10/9.
 */

public class SendAndRecUtil{

    public static Socket socket = null;
    public static InputStream in = null;
    public static OutputStream out = null;

    public static void SendCmd(byte[] cmd){
        try {
            out.write(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] recive(){
        byte[] rec = new byte[34];
        try {
            in.read(rec);
            return rec;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
