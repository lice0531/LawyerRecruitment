package net.cpsec.zfwx.lawyer_recruitment.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.alibaba.fastjson.JSON;

import net.cpsec.zfwx.lawyer_recruitment.R;
import net.cpsec.zfwx.lawyer_recruitment.utils.Constant;
import net.cpsec.zfwx.lawyer_recruitment.utils.Toast;
import net.cpsec.zfwx.lawyer_recruitment.utils.VerifyPhoneNumber;

import java.util.Map;


public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private Button btn_login;
    private EditText etPhoneNumber, etVerificationCode;
    private String phoneNums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        btn_login = (Button) findViewById(R.id.btn_login);
        etPhoneNumber = (EditText) findViewById(R.id.et_login_phonenumber);
        etVerificationCode = (EditText) findViewById(R.id.et_login_phonecode);
        btn_login.setOnClickListener(this);
        etPhoneNumber.setText("17600382402");
        etVerificationCode.setText("1234");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                startActivity(new Intent(this,MainActivity.class));
//                phoneNums = etPhoneNumber.getText().toString().trim();
//                // 1. 通过规则判断手机号
//                if (!judgePhoneNums(phoneNums)) {
//                    phoneNums = "";
//                    return;
//                }
//                if (etVerificationCode.getText().toString().trim().isEmpty()) {
//                    Toast.prompt(this, "验证码不能为空");
//                    phoneNums = "";
//                    return;
//                }
//                if (!etPhoneNumber.getText().toString().trim().equals(phoneNums)) {
//                    Toast.prompt(this, "获取验证码的手机号与该手机号不同");
//                    phoneNums = "";
//                    return;
//                }
//                RequestMap params = new RequestMap();
//                params.put("phone", phoneNums);
//                params.put("code", etVerificationCode.getText().toString().trim());
//                setParams(NetUrl.LOGIN, params, 0);
        }
    }

    @Override
    public void onSuccess(String response, Map<String, String> headers, String url, int actionId) {
        super.onSuccess(response, headers, url, actionId);
        try {
            if (!"登录成功".equals(JSON.parseObject(response).getString("msg"))) {
                Toast.prompt(this, JSON.parseObject(response).getString("infor"));
                return;
            } else {
                //editor.putString(PreferencesStorageKey.USER_PHONENUMBER, JSON.parseObject(response).getString("userphone")).commit();
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            }
        } catch (Exception e) {
            Toast.prompt(this, "数据异常");
        }
    }

    /**
     * 判断手机号码是否合理
     *
     * @param phoneNums
     */
    private boolean judgePhoneNums(String phoneNums) {
        int phoneVerify = VerifyPhoneNumber.isMatchLength(phoneNums, 11);
        if (phoneVerify == Constant.PHONE_NULL) {
            Toast.prompt(this, "手机号不能为空");
            return false;
        } else if (phoneVerify == Constant.PHONE_LENGTH_ERROR) {
            Toast.prompt(this, "手机号位数不对");
            return false;
        } else if (phoneVerify == Constant.PHONE_FORMAT_ERROR) {
            Toast.prompt(this, "手机号格式不对");
            return false;
        } else {
            return true;
        }
    }
}
