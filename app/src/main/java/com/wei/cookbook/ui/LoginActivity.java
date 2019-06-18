package com.wei.cookbook.ui;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.wei.cookbook.App;
import com.wei.cookbook.R;
import com.wei.cookbook.model.UserBean;
import com.wei.cookbook.net.BasePresenter;
import com.wei.cookbook.utils.StringUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**

 * 功能：登录页面
 */

public class LoginActivity extends BaseActivity implements TextWatcher
{
    @Bind(R.id.et_phone)
    EditText mEtPhone;
    @Bind(R.id.et_passWord)
    EditText mEtPassWord;
    @Bind(R.id.tv_action)
    TextView mTvAction;


    @Override
    protected int getLayoutResource()
    {
        return R.layout.activity_login;
    }

    @Override
    protected void setStatusBarColor()
    {

    }

    @Override
    protected BasePresenter createPresenter()
    {
        return null;
    }

    @Override
    protected void initView(Bundle savedInstanceState)
    {
        super.initView(savedInstanceState);
        mEtPhone.addTextChangedListener(this);
        mEtPassWord.addTextChangedListener(this);
        mTvAction.setClickable(false);

    }

    @OnClick({R.id.tv_action, R.id.tv_register, R.id.img_weixin, R.id.img_qq, R.id.img_weibo})
    public void onViewClicked(View view)
    {
        switch (view.getId())
        {
            case R.id.tv_action://登录
                /*判断当前状态是否符合登录状态*/
                String account = mEtPhone.getText().toString();
                if (TextUtils.isEmpty(account) || (!StringUtils.isMobile(account)))
                {
                    alert(getString(R.string.alert_enter_phone));
                    return;
                }

                String pass = mEtPassWord.getText().toString();
                if (TextUtils.isEmpty(pass) || pass.length() < 6)
                {
                    alert(getString(R.string.alert_enter_passWord));
                    return;
                }
                UserBean user = new UserBean();
                user.setAccount(account);
                user.setPassWord(pass);
                if (user.verify())
                {
                    alert(getString(R.string.alert_login_success));
                    App.setUser(user);
                    openActivity(MainActivity.class, null);
                    doFinish(200);
                } else
                {
                    alert(getString(R.string.alert_login_failed));
                }
                break;
            case R.id.tv_register://注册
                openActivity(RegisterActivity.class, null);
                break;
            case R.id.img_weixin://微信
                alert(getString(R.string.alert_have_no_function));
                break;
            case R.id.img_qq://QQ:
                alert(getString(R.string.alert_have_no_function));
                break;
            case R.id.img_weibo://微博:
                alert(getString(R.string.alert_have_no_function));
                break;
            default:
                break;
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {

    }

    @Override
    public void afterTextChanged(Editable s)
    {
        String account = mEtPhone.getText().toString();
        String pass = mEtPassWord.getText().toString();
        //判断当前两个输入框是否均为空
        boolean isClick = ((!TextUtils.isEmpty(account)) && (!TextUtils.isEmpty(pass)));
        showActionBtn(isClick);
    }


    /*设置主按钮的功能和样式*/
    private void showActionBtn(boolean isClick)
    {
        if (mTvAction == null) return;
        mTvAction.setClickable(isClick);
        mTvAction.setBackgroundColor(getResources().getColor(isClick ? R.color.color : R.color.textBG));
    }



}
