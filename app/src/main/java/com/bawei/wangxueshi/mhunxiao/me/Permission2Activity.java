package com.bawei.wangxueshi.mhunxiao.me;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bawei.wangxueshi.mhunxiao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;
//在Activity 或者Fragment中 需要添加，处理权限的问题
@RuntimePermissions
public class Permission2Activity extends Activity {

    @BindView(R.id.dian1)
    Button dian1;
    @BindView(R.id.activity_permission2)
    RelativeLayout activityPermission2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission2);
        ButterKnife.bind(this);

    }
    @OnClick(R.id.dian1)
    public void onClick(){
      //  tocam();
        //这个类是自动生成的，因为注解
        //可能生成的比较慢，要工程clear或rebuild  在等等
        //方法为   tocam  检测这个activity的这个tocam 的所有的有关权限的
        Permission2ActivityPermissionsDispatcher.tocamWithCheck(this);

    }

  // 该方法需要哪些权限，获得权限后 会调用此注释 的方法
    @NeedsPermission(Manifest.permission.CAMERA)
    public void tocam(){
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//为什么需要此 权限，：当权限发送变化后，显示给用户，
    @OnShowRationale(Manifest.permission.CAMERA)
    public void showRationaleForCamera(final PermissionRequest request){
        new AlertDialog.Builder(this).setTitle("这个权限的功能是照相机，您确定是否授权")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 请求授权
                        request.proceed();
                    }
                }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                request.cancel();
            }
        }).create().show();
    }
//权限被拒绝 会走此注释 的方法
    @OnPermissionDenied(Manifest.permission.CAMERA)
    public void onDenied(){
        Toast.makeText(this, "onDenied", Toast.LENGTH_SHORT).show();
    }
    //不再显示   选择后   不在提示用户  这个权限的功能
    @OnNeverAskAgain(Manifest.permission.CAMERA)
    public void onNeverAskAgain(){
        Toast.makeText(this, "onNeverAskAgain", Toast.LENGTH_SHORT).show();
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", getPackageName());
        }
        startActivity(localIntent);

    }


}
