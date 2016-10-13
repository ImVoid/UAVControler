package top.lightblog.businesslogic;

import top.lightblog.helper.Byte2Int;
import top.lightblog.helper.StatusCode;

/**
 * Created by imliu on 2016/10/3.
 */
public class LandingLogic {
    public static void doLogic(){
        if (StatusCode.fly) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    //将两个byte位转为十进制数值
                    int raw = Byte2Int.getRaw(StatusCode.YOUMEN);
                    while (raw > 10) {
                        raw -= 1;
                        StatusCode.b[3] = (byte) (raw >> 8);
                        StatusCode.b[4] = (byte) (raw & 0xff);
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
}
