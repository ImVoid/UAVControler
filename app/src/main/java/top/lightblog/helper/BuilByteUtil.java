package top.lightblog.helper;

import java.util.Arrays;

/**
 * Created by imliu on 2016/10/9.
 */

public class BuilByteUtil {

    public static void buil(int index, int value, boolean isadd){

        int raw = (StatusCode.b[0] << 8) | StatusCode.b[1];

        if(isadd) {
            StatusCode.b[index] = (byte) ((value + raw) >> 8);
            StatusCode.b[index + 1] = (byte) ((value + raw) & 0xFF);
        }else {
            StatusCode.b[index] = (byte) ((value - raw) >> 8);
            StatusCode.b[index + 1] = (byte) ((value - raw) & 0xFF);
        }
    }
}
