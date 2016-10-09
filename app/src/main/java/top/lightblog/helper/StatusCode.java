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
}
