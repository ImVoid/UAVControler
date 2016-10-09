package top.lightblog.businesslogic;

import top.lightblog.helper.BuilByteUtil;
import top.lightblog.helper.SendAndRecUtil;
import top.lightblog.helper.StatusCode;
import top.lightblog.thread.SendCmdThread;

/**
 * Created by imliu on 2016/10/3.
 */

public class LaunchLogic {
    public static void doLogic(){
        StatusCode.fly = true;
        new SendCmdThread().start();
    }
}
