package top.lightblog.businesslogic;

import top.lightblog.helper.StatusCode;
import top.lightblog.thread.SendThread;
import top.lightblog.thread.UpdataScreenThread;

/**
 * Created by imliu on 2016/10/3.
 */

public class LaunchLogic {
    public static void doLogic() {
        if (!StatusCode.fly) {
            StatusCode.b[3] = (byte) (500 >> 8);
            StatusCode.b[4] = (byte) (500 & 0xff);
            StatusCode.fly = true;
            new UpdataScreenThread().start();
            new SendThread().start();
        }
    }
}
