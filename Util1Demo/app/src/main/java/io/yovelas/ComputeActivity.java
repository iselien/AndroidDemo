package io.yovelas;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ComputeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute);

        // 获取 "Add" 按扭
        Button add = (Button)findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String firstNum = ((EditText) findViewById(R.id.firstNum)).getText().toString();
                String secondNum = ((EditText) findViewById(R.id.secondNum)).getText().toString();
                Intent intent = new Intent(ComputeActivity.this,ComputeResultActivity.class);
                intent.putExtra("SUM",Integer.parseInt(firstNum) + Integer.parseInt(secondNum));
                startActivity(intent);
            }
        });

    }
}
