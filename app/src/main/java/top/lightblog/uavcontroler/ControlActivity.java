package top.lightblog.uavcontroler;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import top.lightblog.businesslogic.BackLogic;
import top.lightblog.businesslogic.ForwardLogic;
import top.lightblog.businesslogic.FunALogic;
import top.lightblog.businesslogic.FunBLogic;
import top.lightblog.businesslogic.FunCLogic;
import top.lightblog.businesslogic.FunDLogic;
import top.lightblog.businesslogic.LandingLogic;
import top.lightblog.businesslogic.LeftLogic;
import top.lightblog.businesslogic.LaunchLogic;
import top.lightblog.businesslogic.RightLogic;
import top.lightblog.businesslogic.SwitchLogic;
import top.lightblog.helper.*;

public class ControlActivity extends AppCompatActivity
        implements View.OnClickListener, View.OnTouchListener, SeekBar.OnSeekBarChangeListener {

    //震动传感器
    private Vibrator vibrator;
    long[] pattern = {0, 40};   // 震动设置
    //声明控件
    private Button btnSwitch;
    private Button btnForward;
    private Button btnBack;
    private Button btnLeft;
    private Button btnRight;
    private Button btnLaunch;
    private Button btnLanding;
    private Button btnFunA;
    private Button btnFunB;
    private Button btnFunC;
    private Button btnFunD;
    private TextView tv_forward;
    private TextView tv_back;
    private TextView tv_right;
    private TextView tv_left;
    private TextView tv_speed;
    private TextView tv_connect;

    private SeekBar sbAcc;

    public Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case StatusCode.CONNECT_CHANGED:
                    if ((tv_connect.getText().toString()).equals("未连接")) {
                        tv_connect.setText("已连接");
                        tv_connect.setTextColor(Color.BLUE);
                    }else{
                        tv_connect.setText("未连接");
                        tv_connect.setTextColor(Color.parseColor("#d4d4d4"));
                    }
                    break;
                case StatusCode.ARGS_CHANGED:
                    tv_speed.setText("速度：" + Byte2Int.getRaw(StatusCode.YOUMEN));
                    tv_forward.setText("前：" + (Byte2Int.getRaw(StatusCode.FUYANG)));
                    tv_back.setText("后：" + (Byte2Int.getRaw(StatusCode.FUYANG)));
                    tv_left.setText("左：" + (Byte2Int.getRaw(StatusCode.HENGGUN))) ;
                    tv_right.setText("右：" + (Byte2Int.getRaw(StatusCode.HENGGUN)));
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Screen.setFullScreen(this);

        HandlerManager.handler = handler;

        //获取震动传感器
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        tv_back = (TextView) findViewById(R.id.tv_back);
        tv_forward = (TextView) findViewById(R.id.tv_forward);
        tv_right = (TextView) findViewById(R.id.tv_right);
        tv_left = (TextView) findViewById(R.id.tv_left);
        tv_speed = (TextView) findViewById(R.id.tv_speed);
        tv_connect = (TextView) findViewById(R.id.tv_connect);


        //获取所有button添加到List中
        List<Button> btnList = new ArrayList<Button>();
        btnList.add(btnSwitch = (Button) findViewById(R.id.btn_switch));
        btnList.add(btnLaunch = (Button) findViewById(R.id.btn_launch));
        btnList.add(btnLanding = (Button) findViewById(R.id.btn_landing));
        btnList.add(btnFunA = (Button) findViewById(R.id.btn_funA));
        btnList.add(btnFunB = (Button) findViewById(R.id.btn_funB));
        btnList.add(btnFunC = (Button) findViewById(R.id.btn_funC));
        btnList.add(btnFunD = (Button) findViewById(R.id.btn_funD));
        //所有button添加onClick和onTouch监听器
        for (Button btn : btnList) {
            btn.setOnClickListener(this);
            btn.setOnTouchListener(this);
        }

        (btnForward = (Button) findViewById(R.id.btn_forward)).setOnTouchListener(this);
        (btnBack = (Button) findViewById(R.id.btn_back)).setOnTouchListener(this);
        (btnLeft = (Button) findViewById(R.id.btn_left)).setOnTouchListener(this);
        (btnRight = (Button) findViewById(R.id.btn_right)).setOnTouchListener(this);

        //设置SeekBar监听器
        sbAcc = (SeekBar) findViewById(R.id.sb_acc);
        sbAcc.setOnTouchListener(this);
        sbAcc.setOnSeekBarChangeListener(this);

    }

    @Override
    public void onClick(View view) {
        //按一次即可释放的按钮写这，需要按下和释放两种状态的在onTouch()写
        switch (view.getId()) {
            case R.id.btn_switch:
                SwitchLogic.doLogic(btnSwitch, this);
                break;
            case R.id.btn_launch:
                if(StatusCode.is_connection){
                    LaunchLogic.doLogic();
                }
                break;
            case R.id.btn_landing:
                if (StatusCode.is_connection) {
                    LandingLogic.doLogic();
                }
                break;
            case R.id.btn_funA:
                if (StatusCode.is_connection) {
                    FunALogic.doLogic();
                }
                break;
            case R.id.btn_funB:
                if (StatusCode.is_connection) {
                    FunBLogic.doLogic();
                }
                break;
            case R.id.btn_funC:
                if (StatusCode.is_connection) {
                    FunCLogic.doLogic();
                }
                break;
            case R.id.btn_funD:
                FunDLogic.doLogic();
                break;
            default:
                break;
        }
    }

    //该方法拖动进度条开始拖动的时候调用。
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    //该方法拖动进度条进度改变的时候调用


    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        StatusCode.b[3] = (byte) (progress >> 8);
        StatusCode.b[4] = (byte) (progress & 0xff);
    }

    //该方法拖动进度条停止拖动的时候调用
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
            vibrator.vibrate(pattern, -1);   //触发按钮按下震动反馈
            switch (view.getId()) {
                case R.id.btn_forward:
                    if (StatusCode.is_connection) {
                        ForwardLogic.doLogic(MotionEvent.ACTION_DOWN);
                    }
                    break;
                case R.id.btn_back:
                    if (StatusCode.is_connection) {
                        BackLogic.doLogic(MotionEvent.ACTION_DOWN);
                    }
                    break;
                case R.id.btn_left:
                    if (StatusCode.is_connection) {
                        LeftLogic.doLogic(MotionEvent.ACTION_DOWN);
                    }
                    break;
                case R.id.btn_right:
                    if (StatusCode.is_connection) {
                        RightLogic.doLogic(MotionEvent.ACTION_DOWN);
                    }
                    break;
                default:
                    break;
            }
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            vibrator.vibrate(pattern, -1);   //触发按钮释放震动反馈
            switch (view.getId()) {
                case R.id.btn_forward:
                    if (StatusCode.is_connection) {
                        ForwardLogic.doLogic(MotionEvent.ACTION_UP);
                    }
                    break;
                case R.id.btn_back:
                    if (StatusCode.is_connection) {
                        BackLogic.doLogic(MotionEvent.ACTION_UP);
                    }
                    break;
                case R.id.btn_left:
                    if (StatusCode.is_connection) {
                        LeftLogic.doLogic(MotionEvent.ACTION_UP);
                    }
                    break;
                case R.id.btn_right:
                    if (StatusCode.is_connection) {
                        RightLogic.doLogic(MotionEvent.ACTION_UP);
                    }
                    break;
                default:
                    break;
            }
        }
        //false:传递给下一个监听器   true:监听事件到此结束
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (SendAndRecUtil.socket != null) {
            try {
                SendAndRecUtil.socket.close();
                SendAndRecUtil.in.close();
                SendAndRecUtil.out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
