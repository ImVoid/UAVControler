package top.lightblog.helper;

/**
 * Created by LeiYun on 2016/10/12 0012.
 */

public class Byte2Int {
    public static int getRaw(int index){
        int raw = StatusCode.b[index] << 8;
        if((StatusCode.b[index + 1] >> 7 & 0x01) == 1){
            raw = raw | (StatusCode.b[index + 1] & 0x7F);
            raw = raw | 0x80;
        }else {
            raw = raw | StatusCode.b[index + 1];
        }
        return raw;
    }
}
