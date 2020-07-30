package io.yovelas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ComputeResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compute_result);
        Intent intent = getIntent();
        ((TextView)findViewById(R.id.resultView)).setText(intent.getSerializableExtra("SUM").toString());
    }
}
