package cn.carhouse.base.ui.core;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import org.greenrobot.eventbus.EventBus;

import cn.carhouse.base.ui.KeyBordUtils;

public class ActivityPresenter<V extends IBaseView> {
    // 这个就是传递进来的Activity
    private V baseView;

    public void attach(final V view, Bundle savedInstanceState, LayoutInflater inflater) {
        if (view == null) {
            return;
        }
        this.baseView = view;
        // 绑定EventBus
        registerEvent();
        // 1. 初始化数据
        baseView.initData(savedInstanceState);
        // 2. 设置ContentView
        View contentView = initContentView(inflater);
        baseView.setContentView(contentView);
        baseView.bindView(contentView);
        // 4. 初始化标题
        baseView.initTitle();
        // 5. 初始化View
        baseView.initViews(contentView);
        // 6. 初化View之后
        baseView.afterInitViews();
        // 7. 网络请求
        baseView.initNet();
    }

    private View initContentView(LayoutInflater inflater) {
        int layoutId = baseView.getContentLayout();
        return inflater.inflate(layoutId, null, false);
    }


    /**
     * 注册EventBus
     */
    private void registerEvent() {
        if (baseView.isNeedEvent()) {
            EventBus.getDefault().register(baseView);
        }
    }

    /**
     * 解绑EventBus
     */
    private void unregisterEvent() {
        // 事件
        if (baseView.isNeedEvent()) {
            EventBus.getDefault().unregister(baseView);
        }
    }

    /**
     * 关闭软键盘
     */
    private void closeKeyBord() {
        KeyBordUtils.closeKeyBord(baseView.getAppActivity());
        // 优化键盘内存漏泄
        KeyBordUtils.fixInputMethodManagerLeak(baseView.getAppActivity());
    }

    public void detach() {
        // 7. 解绑View
        unregisterEvent();
        baseView.unbindView();
        closeKeyBord();
        baseView = null;
    }

}
