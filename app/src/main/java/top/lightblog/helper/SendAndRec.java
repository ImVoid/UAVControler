package top.lightblog.helper;

import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by imliu on 2016/10/9.
 */

public class SendAndRec {
    public static OutputStream sendStream = null;
    public static InputStream  recStream = null;

    public byte[] SendCmd(byte[] cmd){
        if(sendStream != null) {
            try {
                sendStream.write(cmd);
            } catch (IOException e) {
                e.printStackTrace();
            }
            byte[] rec = new byte[34];
            try {
                recStream.read(rec);
                return rec;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
