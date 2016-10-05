package top.lightblog.helper;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import top.lightblog.uavcontroler.ControlActivity;
import top.lightblog.uavcontroler.R;

/**
 * Created by LeiYun on 2016/10/4 0004.
 */

public class AnimationCode {
    TextView tv_welcome;
    ImageView img_airscerw_aboveL, img_airscerw_aboveR;
    ImageView img_airscerw_belowL, img_airscerw_belowR, img_body;
    ObjectAnimator animator_AL, animator_AR, animator_BL, animator_BR;
    AnimatorSet uav, welcome, translation;
    RelativeLayout uav_body;

    public void animator_UAV(Activity activity) {
        img_airscerw_aboveL = (ImageView) activity.findViewById(R.id.img_airscerw_aboveL);
        img_airscerw_aboveR = (ImageView) activity.findViewById(R.id.img_airscerw_aboveR);
        img_airscerw_belowL = (ImageView) activity.findViewById(R.id.img_airscerw_belowL);
        img_airscerw_belowR = (ImageView) activity.findViewById(R.id.img_airscerw_belowR);
        img_body = (ImageView) activity.findViewById(R.id.img_body);
        tv_welcome = (TextView) activity.findViewById(R.id.tv_welcome);
        uav_body = (RelativeLayout) activity.findViewById(R.id.uav_body);


        animator_AL = ObjectAnimator.ofFloat(img_airscerw_aboveL, "rotation", 0f, 180f, 360f);
        animator_AR = ObjectAnimator.ofFloat(img_airscerw_aboveR, "rotation", 360f, 180f, 0f);
        animator_BL = ObjectAnimator.ofFloat(img_airscerw_belowL, "rotation", 360f, 180f, 0f);
        animator_BR = ObjectAnimator.ofFloat(img_airscerw_belowR, "rotation", 0f, 180f, 360f);


        uav = (AnimatorSet) AnimatorInflater.loadAnimator(activity, R.animator.animator_uav_alpha);
        welcome = (AnimatorSet) AnimatorInflater.loadAnimator(activity, R.animator.animator_welcome_alpha);
        translation = (AnimatorSet) AnimatorInflater.loadAnimator(activity, R.animator.animator_uav_translation);

        animator_AL.setRepeatCount(-1);
        animator_AR.setRepeatCount(-1);
        animator_BL.setRepeatCount(-1);
        animator_BR.setRepeatCount(-1);

        AnimatorSet animationSet = new AnimatorSet();
        animationSet.setInterpolator(new LinearInterpolator());
        animationSet.setDuration(170);
        animationSet.playTogether(animator_AL, animator_AR, animator_BL, animator_BR);
        animationSet.start();


        translation.setTarget(uav_body);
        translation.start();

        welcome.setTarget(tv_welcome);
        welcome.start();

        uav.setTarget(img_body);
        uav.start();
    }


    public void launchController(final Activity activity){
        Timer time = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(activity, ControlActivity.class);
                if(!StatusCode.jumpStatus) {
                    activity.startActivity(intent);
                    activity.finish();
                }
            }
        };
        time.schedule(task, 9000);
    }

}
