package top.lightblog.uavcontroler;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

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
import top.lightblog.businesslogic.LuanchLogic;
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

    private SeekBar sbAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Screen.setFullScreen(this);

        //获取震动传感器
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //获取所有button添加到List中
        List<Button> btnList = new ArrayList<Button>();
        btnList.add(btnSwitch = (Button) findViewById(R.id.btn_switch));
        btnList.add(btnForward = (Button) findViewById(R.id.btn_forward));
        btnList.add(btnBack = (Button) findViewById(R.id.btn_back));
        btnList.add(btnLeft = (Button) findViewById(R.id.btn_left));
        btnList.add(btnRight = (Button) findViewById(R.id.btn_right));
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
                LuanchLogic.doLogic();
                break;
            case R.id.btn_landing:
                LandingLogic.doLogic();
                break;
            case R.id.btn_funA:
                FunALogic.doLogic();
                break;
            case R.id.btn_funB:
                FunBLogic.doLogic();
                break;
            case R.id.btn_funC:
                FunCLogic.doLogic();
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
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

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
                    ForwardLogic.doLogic(MotionEvent.ACTION_DOWN);
                    break;
                case R.id.btn_back:
                    BackLogic.doLogic(MotionEvent.ACTION_DOWN);
                    break;
                case R.id.btn_left:
                    LeftLogic.doLogic(MotionEvent.ACTION_DOWN);
                    break;
                case R.id.btn_right:
                    RightLogic.doLogic(MotionEvent.ACTION_DOWN);
                    break;
                default:
                    break;
            }
        }

        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            vibrator.vibrate(pattern, -1);   //触发按钮释放震动反馈
            switch (view.getId()) {
                case R.id.btn_forward:
                    ForwardLogic.doLogic(MotionEvent.ACTION_UP);
                    break;
                case R.id.btn_back:
                    BackLogic.doLogic(MotionEvent.ACTION_UP);
                    break;
                case R.id.btn_left:
                    LeftLogic.doLogic(MotionEvent.ACTION_UP);
                    break;
                case R.id.btn_right:
                    RightLogic.doLogic(MotionEvent.ACTION_UP);
                    break;
                default:
                    break;
            }
        }
        //false:传递给下一个监听器   true:监听事件到此结束
        return false;
    }
}
