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

    //message
    public static final String SOCKET_CREATE_FAIL = "Socket创建失败";
}
