package top.lightblog.asynctask;

import android.os.AsyncTask;
import android.widget.Button;

import top.lightblog.helper.StatusCode;
import top.lightblog.uavcontroler.R;

/**
 * Created by imliu on 2016/10/2.
 */

public class SwitchBlinkTask extends AsyncTask<Button, Integer, Boolean> {

    private Button btnSwitch;

    @Override
    protected Boolean doInBackground(Button... buttons) {

        btnSwitch = buttons[0];

        if(StatusCode.switchStatus){    //如果开启了开关则关闭它
            publishProgress(StatusCode.BLINK_OFF);
            StatusCode.switchStatus = false;
        }else{  //关闭了就开启开关，先是快闪然后慢闪
            StatusCode.switchStatus = true;
            int lastStatus = StatusCode.BLINK_OFF;  //记录上一次闪烁状态
            //快闪3下，即6次交替
            StatusCode.blinkMod = true;    //设置快闪模式
            for(int i = 0; i < 6; ++i){
                if(StatusCode.switchStatus) {   //响应开启状态
                    try {
                        publishProgress(lastStatus = (lastStatus == StatusCode.BLINK_OFF ? StatusCode.BLINK_ON : StatusCode.BLINK_OFF));
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {     //响应关闭状态
                    publishProgress(StatusCode.BLINK_OFF);
                    StatusCode.switchStatus = false;
                    break;
                }
            }
            //循环慢闪
            StatusCode.blinkMod = false;    //设置慢闪模式
            while (true){
                if(StatusCode.switchStatus){    //响应开启状态
                    try {
                        Thread.sleep(800);
                        publishProgress(lastStatus = (lastStatus == StatusCode.BLINK_OFF ? StatusCode.BLINK_ON : StatusCode.BLINK_OFF));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else {     //响应关闭状态
                    publishProgress(StatusCode.BLINK_OFF);
                    StatusCode.switchStatus = false;
                    break;
                }
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        if(!StatusCode.switchStatus ) {  //响应关闭状态时
            btnSwitch.setBackgroundResource(R.drawable.switch_off);
        }else{      //响应开启状态时
            if(StatusCode.blinkMod){    //快闪模式闪蓝灯
                btnSwitch.setBackgroundResource(values[0] == StatusCode.BLINK_ON ? R.drawable.switch_on : R.drawable.switch_off);
            }else {     //慢闪模式闪绿灯
                btnSwitch.setBackgroundResource(values[0] == StatusCode.BLINK_ON ? R.drawable.switch_on_green : R.drawable.switch_off);
            }

        }
    }
}
