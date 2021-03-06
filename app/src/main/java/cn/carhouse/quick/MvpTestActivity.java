package cn.carhouse.quick;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lven.retrofit.callback.OnCallback;

import java.util.List;

import cn.carhouse.base.ui.mvp.MvpActivity;
import cn.carhouse.quick.presenter.MainPresenter;
import cn.carhouse.quick.view.IMainView;
import cn.carhouse.titlebar.DefTitleBar;


public class MvpTestActivity extends MvpActivity<MainPresenter> implements IMainView {


    TextView tv;

    @Override
    public int getContentLayout() {
        return R.layout.activity_mvp_test;
    }

    @Override
    protected void initTitle(DefTitleBar titleBar) {
        titleBar.setTitle("我是主页面");
        // 不要返回按钮
        titleBar.clearBackImage();
        titleBar.setRightTextColor(Color.WHITE);
        titleBar.setRightText("测试", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MvpTestActivity.this, "测试", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initViews(View view) {
        tv = findViewById(R.id.tv);
    }

    public void loadItem(View view) {
        //  getPresenter().loadItem();


        MainPresenter.loadItem(this, new OnCallback() {
            @Override
            public void onSuccess(String response) {
                showItem(response);
            }
        });
    }

    public void loadList(View view) {
        getPresenter().loadList();
    }

//    @Override
//    public void initViews(View view) {
//        FragmentUtils.changeFragment(getSupportFragmentManager(), R.id.fl_fragment, new TestFragment());
//    }


    @Override
    public void showItem(String item) {
        tv.setText(item);
    }

    @Override
    public void showList(List<String> items) {
        tv.setText(items.toString());
    }
}
