package top.lightblog.helper;

import java.util.Arrays;

/**
 * Created by imliu on 2016/10/9.
 */

public class BuilByteUtil {

    /**
     *
     * @param index int值， 控制飞行方向操作
     * @param value int值，飞行的数据
     * @param isadd boolean值，true是加值，false是减值
     */
    public static void buil(int index, int value, boolean isadd){

        //将两个byte位转为十进制数值
        int raw = Byte2Int.getRaw(index);

        if(isadd) {
            StatusCode.b[index] = (byte) ((value + raw) >> 8);
            StatusCode.b[index + 1] = (byte) ((value + raw) & 0xFF);
        }else {
            StatusCode.b[index] = (byte) ((raw - value) >> 8);
            StatusCode.b[index + 1] = (byte) ((raw - value) & 0xFF);
        }
    }
}
