package top.lightblog.businesslogic;

import top.lightblog.helper.StatusCode;
import top.lightblog.thread.SendThread;
import top.lightblog.thread.UpdataScreenThread;

/**
 * Created by imliu on 2016/10/5.
 */
public class FunBLogic {

    public static void doLogic() {
        if (!StatusCode.fly) {
            StatusCode.fly = true;
            new SendThread().start();
            new UpdataScreenThread().start();
        }
    }
}
