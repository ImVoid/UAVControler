package top.lightblog.businesslogic;

import android.view.MotionEvent;

import top.lightblog.helper.BuilByteUtil;
import top.lightblog.helper.StatusCode;

/**
 * Created by imliu on 2016/10/3.
 */

public class RightLogic {
    public static void doLogic(int action){
        if(action == MotionEvent.ACTION_DOWN){
            BuilByteUtil.buil(StatusCode.HENGGUN, StatusCode.offset, false);
        }

        if(action == MotionEvent.ACTION_UP){
            BuilByteUtil.buil(StatusCode.HENGGUN, StatusCode.offset, true);
        }
    }
}
