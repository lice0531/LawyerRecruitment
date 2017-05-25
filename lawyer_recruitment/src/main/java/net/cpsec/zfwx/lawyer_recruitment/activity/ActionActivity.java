package net.cpsec.zfwx.lawyer_recruitment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.LinearLayout;

import net.cpsec.zfwx.lawyer_recruitment.R;


public class ActionActivity extends BaseActivity {
    //private ImageView ivAction;
    private LinearLayout ll_yindao;
    Button btn_jinru;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);
        initView();
        setAnimation();
    }

    private void initView() {
        //ivAction = (ImageView) findViewById(R.id.iv_action);
        ll_yindao = (LinearLayout) findViewById(R.id.ll_yindao);
        btn_jinru = (Button) findViewById(R.id.btn_jinru);
        btn_jinru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActionActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void setAnimation() {
        AlphaAnimation animation = new AlphaAnimation(1, 0);
        animation.setDuration(3000);//设置动画持续时间
/** 常用方法 */
//        animation.setRepeatCount(1);//设置重复次数
        animation.setFillAfter(true);//动画执行完后是否停留在执行完的状态
//        animation.setStartOffset(1000);//执行前的等待时间
        ll_yindao.setAnimation(animation);
        // ivAction.setAnimation(animation);
        animation.startNow();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                Intent intent = new Intent(ActionActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
//                if (!sharedPreferences.getString(PreferencesStorageKey.USER_ID, "").isEmpty() && sharedPreferences.getString(PreferencesStorageKey.USER_ID, "") != null) {
//                    intent = new Intent(GuidanceActivity.this, EmployeeMainActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else if (!INSTALL_SUCCESS.isEmpty() && INSTALL_SUCCESS != null) {
//                    intent = new Intent(GuidanceActivity.this, LoginActivity.class);
//                    startActivity(intent);
//                    finish();
//                }
//                Intent intent;
//                if (sharedPreferences.getString(PreferencesStorageKey.USER_PHONENUMBER, "").isEmpty()) {
//                    ivAction.setVisibility(View.GONE);
//                    intent = new Intent(ActionActivity.this, EntranceActivity.class);
//                    startActivity(intent);
//                    finish();
//                } else {
//                    intent = new Intent(ActionActivity.this, ClassificationActivity.class);
//                    startActivity(intent);
//                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
