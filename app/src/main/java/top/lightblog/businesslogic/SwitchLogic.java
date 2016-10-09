package top.lightblog.businesslogic;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Button;

import top.lightblog.thread.ConnectUAVTask;
import top.lightblog.thread.SwitchBlinkTask;

/**
 * Created by imliu on 2016/10/3.
 */

public class SwitchLogic {
    public static void doLogic(Button btn, Context context){
        new SwitchBlinkTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, btn);
        new ConnectUAVTask(context).start();
    }
}
