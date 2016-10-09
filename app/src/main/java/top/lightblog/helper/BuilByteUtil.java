package top.lightblog.helper;

import java.util.Arrays;

/**
 * Created by imliu on 2016/10/9.
 */

public class BuilByteUtil {

    private static byte[] b = new byte[34];

    static{
        b[0] = (byte)0xAA;
        b[1] = (byte)0xC0;
        b[2] = (byte)0x1C;
        //油门
        b[3] = (byte)(500 >> 8);
        b[4] = (byte)(500 & 0xff);
        //航向
        b[5] = (byte)(1700 >> 8);
        b[6] = (byte)(1700 & 0xff);
        //航滚
        b[7] = (byte)(2000 >> 8);
        b[8] = (byte)(2000 & 0xFF);
        //俯视
        b[9] = (byte)(2000 >> 8);
        b[10] = (byte)(2000 & 0xFF);

        b[11] = (byte)0x00;
        b[12] = (byte)0x00;
        b[13] = (byte)0x00;
        b[14] = (byte)0x00;
        b[15] = (byte)0x00;
        b[16] = (byte)0x00;
        b[17] = (byte)0x00;
        b[18] = (byte)0x00;
        b[19] = (byte)0x00;
        b[20] = (byte)0x00;
        b[21] = (byte)0x00;
        b[22] = (byte)0x00;
        b[23] = (byte)0x00;
        b[24] = (byte)0x00;
        b[25] = (byte)0x00;
        b[26] = (byte)0x00;
        b[27] = (byte)0x00;
        b[28] = (byte)0x00;
        b[29] = (byte)0x00;
        b[30] = (byte)0x00;
        b[31] = (byte)0x1C;
        b[32] = (byte)0x0D;
        b[33] = (byte)0x0A;

    }

    public static byte[] buil(int index, int value){
        b[index] = (byte)(value >> 8);
        b[index + 1] = (byte)(value & 0xFF);
        return b;
    }
}
