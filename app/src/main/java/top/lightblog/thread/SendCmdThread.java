package top.lightblog.thread;

import top.lightblog.helper.BuilByteUtil;
import top.lightblog.helper.SendAndRecUtil;
import top.lightblog.helper.StatusCode;

/**
 * Created by imliu on 2016/10/9.
 */

public class SendCmdThread extends Thread{

    @Override
    public void run() {
        while (StatusCode.fly){
            SendAndRecUtil.SendCmd(StatusCode.b);
            try {
                Thread.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
