package cn.carhouse.quick;

import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import cn.carhouse.base.ui.AppActivity;
import cn.carhouse.base.ui.FragmentUtils;
import cn.carhouse.titlebar.DefTitleBar;

@Route(path = "/b/videos")
public class TestActivity extends AppActivity {
    // 阿里路由注入
    @Autowired
    String path;


    @Override
    public int getContentLayout() {
        return R.layout.activity_test;
    }

    @Override
    protected void initTitle(DefTitleBar titleBar) {
        titleBar.setTitle("我是另一个页面");
    }

    @Override
    public void initViews(View view) {
        ARouter.getInstance().inject(this);
        FragmentUtils.changeFragment(getSupportFragmentManager(), R.id.fl_fragment, new TestFragment());
    }

    @Override
    public void initNet() {

    }
}
