package top.lightblog.helper;

/**
 * Created by imliu on 2016/9/30.
 */

public class StatusCode {
    public static boolean jumpStatus = false;       //启动跳转状态
    public static boolean switchStatus = false;     //开关状态
    public static boolean blinkMod = false;         //快闪true  慢闪false
    public static final int BLINK_ON = 1;           //闪烁信息
    public static final int BLINK_OFF = 2;          //熄灭信息

    public static boolean is_connection = false;    //是否连接上无人机

    //connection message
    public static final String CONNECTION_FAIL = "Socket创建失败";
    public static final String CONNECTION_SUCCE = "连接成功";

    //控制飞行状态
    public static  boolean fly = false;
    public static final int YOUMEN = 3;             //油门
    public static final int HANGXIANG = 5;          //航向
    public static final int HENGGUN = 7;            //横滚
    public static final int FUYANG = 9;             //俯仰

    //状态数组
    public static byte[] b = new byte[34];

    static{
        b[0] = (byte)0xAA;
        b[1] = (byte)0xC0;
        b[2] = (byte)0x1C;
        //油门
        b[3] = (byte)(600 >> 8);
        b[4] = (byte)(600 & 0xff);

        //航向
        b[5] = (byte)(1500 >> 8);
        b[6] = (byte)(1500 & 0xff);
        //航滚
        b[7] = (byte)(1500 >> 8);
        b[8] = (byte)(1500 & 0xFF);
        //俯视
        b[9] = (byte)(1700 >> 8);
        b[10] = (byte)(1700 & 0xFF);

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
}
