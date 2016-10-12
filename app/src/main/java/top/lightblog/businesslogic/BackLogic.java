package top.lightblog.businesslogic;

import android.view.MotionEvent;

import top.lightblog.helper.BuilByteUtil;
import top.lightblog.helper.StatusCode;

/**
 * Created by imliu on 2016/10/3.
 */

public class BackLogic {
    public static void doLogic(int action){
        if(action == MotionEvent.ACTION_DOWN){
            BuilByteUtil.buil(StatusCode.FUYANG, StatusCode.offset, false);
        }

        if(action == MotionEvent.ACTION_UP){
            BuilByteUtil.buil(StatusCode.FUYANG, StatusCode.offset, true);
        }
    }
}
