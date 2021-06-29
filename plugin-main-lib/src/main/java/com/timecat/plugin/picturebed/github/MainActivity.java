package com.timecat.plugin.picturebed.github;

import android.Manifest;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.timecat.plugin.picturebed.github.permission.OverlayPermission;
import com.timecat.plugin.picturebed.github.permission.Permission;
import com.timecat.plugin.picturebed.github.permission.RxPermissions;
import com.timecat.plugin.window.WindowAgreement;

import java.util.Random;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!OverlayPermission.canDrawOverlays(getApplicationContext())) {
            OverlayPermission.requestDrawOverlays(getApplicationContext());
            finish();
        } else {
            requestPermission(
                    Manifest.permission.INTERNET,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE);
        }
        Log.e("MainActivity", "end");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void requestPermission(String... permissions) {
        new RxPermissions(getFragmentManager()).requestEachCombined(permissions).subscribe(new Consumer<Permission>() {
            @Override
            public void accept(Permission permission) throws Exception {
                if (permission.granted) {
                    // 用户已经同意该权限
                    Context context = getApplicationContext();
                    final Context contextThemeWrapper = new ContextThemeWrapper(context, R.style.Theme_AppCompat);
//                    LayoutInflater themeAwareInflater = inflater.cloneInContext(contextThemeWrapper);
//                    Context themeContext = ContextThemeWrapper(context)
                    WindowAgreement.show(contextThemeWrapper, GithubApp.class, new Random().nextInt());
                } else if (permission.shouldShowRequestPermissionRationale) {
                    // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框
                    Toast.makeText(MainActivity.this, "没有权限，为了不崩溃，拒绝启动", Toast.LENGTH_LONG).show();
                } else {
                    // 用户拒绝了该权限，并且选中『不再询问』
                    Toast.makeText(MainActivity.this, "没有权限，为了不崩溃，拒绝启动", Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });
    }
}