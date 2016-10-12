package top.lightblog.businesslogic;

import top.lightblog.helper.StatusCode;

/**
 * Created by imliu on 2016/10/5.
 */

public class FunALogic {

    public static void doLogic() {
        if (StatusCode.fly)
            StatusCode.fly = false;
    }

}
