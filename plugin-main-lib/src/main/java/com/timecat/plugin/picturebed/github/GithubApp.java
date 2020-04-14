package com.timecat.plugin.picturebed.github;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;

import com.timecat.component.setting.DEF;
import com.timecat.plugin.window.StandOutFlags;
import com.timecat.plugin.window.StandOutWindow;
import com.timecat.plugin.window.Window;

import java.util.ArrayList;
import java.util.List;

public class GithubApp extends StandOutWindow {
    private Context context;
    private int publicId;
    private View publicView;

    public String getAppName() {
        return getString(R.string.main_miniapp_Github);
    }

    public int getAppIcon() {
        return R.drawable.ic_window_menu;
    }

    public String getTitle(int id) {
        return getString(R.string.main_miniapp_Github);
    }

    public String getPersistentNotificationTitle(int id) {
        return getString(R.string.main_miniapp_Github);
    }

    public String getPersistentNotificationMessage(int id) {
        return getString(R.string.main_miniapp_running);
    }

    public int getHiddenIcon() {
        return R.drawable.ic_github;
    }

    public String getHiddenNotificationTitle(int id) {
        return getString(R.string.main_miniapp_Github);
    }

    public String getHiddenNotificationMessage(int id) {
        return getString(R.string.main_miniapp_mininized);
    }

    public Intent getHiddenNotificationIntent(int id) {
        return StandOutWindow.getShowIntent(this, getClass(), id);
    }

    public Animation getShowAnimation(int id) {
        if (isExistingId(id)) {
            return AnimationUtils.loadAnimation(this, android.R.anim.fade_in);
        }
        return super.getShowAnimation(id);
    }

    public Animation getHideAnimation(int id) {
        return AnimationUtils.loadAnimation(this, android.R.anim.fade_out);
    }

    public static int dp2px(Context context, int pixels) {
        return (int) (((float) pixels) * (((float) context.getResources().getDisplayMetrics().densityDpi) / 160.0f));
    }

    public StandOutLayoutParams getParams(int id, Window window) {
        int h = DEF.floatview().getInt(getAppName() + "HEIGHT", 200);
        int w = DEF.floatview().getInt(getAppName() + "WIDTH", 200);
        int x = DEF.floatview().getInt(getAppName() + "XPOS", Integer.MIN_VALUE);
        int y = DEF.floatview().getInt(getAppName() + "YPOS", Integer.MIN_VALUE);
        if (h < dp2px(window.getContext(), 200)) {
            h = dp2px(window.getContext(), 200);
        }
        if (w < dp2px(window.getContext(), 200)) {
            w = dp2px(window.getContext(), 200);
        }
        return new StandOutLayoutParams(id, w, h, x, y, dp2px(window.getContext(), 56), dp2px(window.getContext(), 56));
    }

    public int getFlags(int id) {
        return (((StandOutFlags.FLAG_DECORATION_SYSTEM | StandOutFlags.FLAG_BODY_MOVE_ENABLE) | StandOutFlags.FLAG_WINDOW_HIDE_ENABLE) | StandOutFlags.FLAG_WINDOW_BRING_TO_FRONT_ON_TAP) | StandOutFlags.FLAG_WINDOW_EDGE_LIMITS_ENABLE;
    }

    public List<DropDownListItem> getDropDownItems(int id) {
        return new ArrayList<>();
    }

    public void createAndAttachView(int id, FrameLayout frame) {
        this.publicId = id;
        new GithubBedCreator(getApplicationContext(), frame);
    }

//    <EditTextPreference
//    android:key="repo"
//    android:title="仓库名*" />
//    <EditTextPreference
//    android:key="owner"
//    android:title="用户名*" />
//    <EditTextPreference
//    android:key="email"
//    android:title="邮箱*"
//    android:summary="提交 commit 时要用"/>
//    <EditTextPreference
//    android:key="GithubToken"
//    android:summary="请到 https://github.com/settings/tokens 申请"
//    android:title="Github Token *" />
//    <EditTextPreference
//    android:key="extra"
//    android:title="备注" />
}
