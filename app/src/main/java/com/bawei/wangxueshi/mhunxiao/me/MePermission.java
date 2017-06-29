package com.bawei.wangxueshi.mhunxiao.me;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bawei.wangxueshi.mhunxiao.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MePermission extends Activity {

    @BindView(R.id.dian)
    Button dian;
    @BindView(R.id.activity_me_permission)
    RelativeLayout activityMePermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me_permission);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.dian)
     public void on(){

        //andoird 6.0 以后 23  若应用没有给权限，而调用需要权限的功能时，可以动态配置权限（用户可以操作请求权限，体验教好）
        //andoird 6.0 以后 23 若应用有给权限，而调用需要权限的功能时，功能正常运行；

        //如果被权限被拒绝
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            //这个权限的状态  如果变化
            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
                new AlertDialog.Builder(this)
                        .setTitle("是否确定？")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    //申请权限
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //请求权限
                        ActivityCompat.requestPermissions(MePermission.this,new String[] {Manifest.permission.CAMERA},1);

                    }
                })      .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                        .show();
            }
            //这个权限的状态没有变化
            else{
                ActivityCompat.requestPermissions(MePermission.this,new String[] {Manifest.permission.CAMERA},1);
            }
        }
        //权限没有被拒绝
        else{
            onclick();
        }
     }
    //andoird 6.0 以前22  若应用没有给权限，而调用需要权限的功能时，功能无反应；（用户不知道为什么，功能无反应）
    //andoird 6.0 以前22  若应用有给权限，而调用需要权限的功能时，功能正常运行；

    private void onclick() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivity(intent);
    }

    /**
     * @param requestCode
     * @param permissions  请求的权限
     * @param grantResults 请求权限返回的结果
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            // camear 权限回调
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 表示用户授权
                Toast.makeText(this, " user Permission", Toast.LENGTH_SHORT).show();
                onclick();
            } else {
                //用户拒绝权限
                Toast.makeText(this, " no Permission", Toast.LENGTH_SHORT).show();
            }
        }

    }
}

