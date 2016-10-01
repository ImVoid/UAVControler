package top.lightblog.uavcontroler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import top.lightblog.helper.*;

public class ControlActivity extends AppCompatActivity implements View.OnClickListener{

    //声明控件
    private Button btnSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Screen.setFullScreen(this);

        btnSwitch = (Button)findViewById(R.id.btn_switch);
        btnSwitch.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_switch:   processBtnSwitch();
                break;
            default:
                break;
        }
    }

    /**
     * 处理异步消息更新UI
     */
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case StatusCode.BLINK_ON:   btnSwitch.setBackgroundResource(StatusCode.blinkMod ? R.drawable.switch_on_green : R.drawable.switch_on);
                    break;
                case StatusCode.BLINK_OFF:   btnSwitch.setBackgroundResource(R.drawable.switch_off);
                    break;
                default:
                    break;
            }
        }
    };

    /**
     * 按钮点击事件处理方法
     */
    private void processBtnSwitch(){
        new Thread(new Runnable() {

            Message message = null;
            int lastMessage = StatusCode.BLINK_OFF;

            @Override
            public void run() {
                //如果打开了则关闭
                if(StatusCode.switchStatus) {
                    message = new Message();
                    message.what = StatusCode.BLINK_OFF;
                    handler.sendMessage(message);
                    StatusCode.switchStatus = false;
                }else { //否则启动
                    StatusCode.switchStatus = true;
                    StatusCode.blinkMod = false;    //设置快闪模式
                    //启动时快闪
                    for (int i = 0; i < 6; ++i) {
                        if(StatusCode.switchStatus ) {
                            startBlink(100);
                        }else { //中途被关闭
                            stopBlink();
                            break;
                        }
                    }
                    //开关慢闪效果
                    StatusCode.blinkMod = true;     //设置慢闪模式
                    lastMessage = StatusCode.BLINK_ON;
                    while (StatusCode.switchStatus){
                        if(StatusCode.switchStatus ) {
                            startBlink(800);
                        }else { //中途被关闭
                            stopBlink();
                            break;
                        }
                    }
                }
            }

            /**
             * 发送闪烁信息
             * @param time 闪烁间隔
             */
            private void startBlink(int time){
                message = new Message();
                message.what = lastMessage == StatusCode.BLINK_OFF ? StatusCode.BLINK_ON : StatusCode.BLINK_OFF;
                lastMessage = message.what;
                handler.sendMessage(message);
                try {
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            /**
             * 停止闪烁
             */
            private void stopBlink(){
                message = new Message();
                message.what = StatusCode.BLINK_OFF;
                handler.sendMessage(message);
                StatusCode.switchStatus = false;
                StatusCode.blinkMod = false;
            }
        }).start();
    }
}
