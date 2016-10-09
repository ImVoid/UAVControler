package top.lightblog.businesslogic;

import android.view.MotionEvent;

import top.lightblog.helper.BuilByteUtil;
import top.lightblog.helper.SendAndRecUtil;
import top.lightblog.helper.StatusCode;

/**
 * Created by imliu on 2016/10/3.
 */

public class ForwardLogic {
    public static void doLogic(int action){
        if(action == MotionEvent.ACTION_DOWN){
            BuilByteUtil.buil(StatusCode.FUYANG, 100, true);
        }

        if(action == MotionEvent.ACTION_UP){
            BuilByteUtil.buil(StatusCode.FUYANG, 100, false);
        }
    }
}
