package top.lightblog.thread;

import top.lightblog.helper.SendAndRecUtil;
import top.lightblog.helper.StatusCode;

/**
 * Created by LeiYun on 2016/10/12 0012.
 */

public class SendThread extends Thread {

    @Override
    public void run() {
        while (StatusCode.fly) {
            try {
                SendAndRecUtil.SendCmd(StatusCode.b);
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
