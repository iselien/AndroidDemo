package io.yovelas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class OpenURLActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_url);

        View btn = findViewById(R.id.btnNavigate);

        btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Log.i("事件","打开链接");
               startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(((TextView) findViewById(R.id.urlText)).getText().toString())));
            }
        });
    }
}
