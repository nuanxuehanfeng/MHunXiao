package com.bawei.wangxueshi.mhunxiao;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.bawei.wangxueshi.jiajian.AmountView;
import com.bawei.wangxueshi.mhunxiao.me.Permission2Activity;


public class MainActivity extends Activity {

    private AmountView mAmountView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, Permission2Activity.class);
        startActivity(intent);

//        mAmountView = (AmountView) findViewById(R.id.amount_view);
//        //设置最大数量为50；
//        mAmountView.setGoods_storage(50);
//        //数量变化的监听
//        mAmountView.setOnAmountChangeListener(new AmountView.OnAmountChangeListener() {
//            @Override
//            public void onAmountChange(View view, int amount) {
//                Toast.makeText(getApplicationContext(), "Amount=>  " + amount, Toast.LENGTH_SHORT).show();
//            }
//        });
    }
}
