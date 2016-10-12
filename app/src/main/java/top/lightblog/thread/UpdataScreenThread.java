package top.lightblog.thread;

import top.lightblog.helper.HandlerManager;
import top.lightblog.helper.StatusCode;

/**
 * Created by LeiYun on 2016/10/12 0012.
 */

public class UpdataScreenThread extends Thread {
    @Override
    public void run() {
        while (StatusCode.fly) {
            try {
                sleep(100);
                HandlerManager.handler.sendMessage(HandlerManager.handler.obtainMessage(StatusCode.ARGS_CHANGED));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
