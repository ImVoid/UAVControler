package top.lightblog.businesslogic;

import android.os.AsyncTask;
import android.widget.Button;

import top.lightblog.asynctask.SwitchBlinkTask;

/**
 * Created by imliu on 2016/10/3.
 */

public class SwitchLogic {
    public static void doLogic(Button btn){
        new SwitchBlinkTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, btn);
    }
}
