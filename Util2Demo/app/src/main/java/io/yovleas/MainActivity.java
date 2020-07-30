package io.yovleas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // 进入 "获取一张照片" Activity
    public void TakeAPhoto(View view){
        System.out.println("aaaaa");
        startActivity(new Intent(this, TakeAPhotoActivity.class));
    }
}