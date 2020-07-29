package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnSubmit;  // 提交按钮
    EditText name, password, email, dob, phoneno;  // 表单字段
    TextView result;  // 提交结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 从布局中查找元素
        name=(EditText)findViewById(R.id.txtName);
        password = (EditText)findViewById(R.id.txtPwd);
        email = (EditText)findViewById(R.id.txtEmai);
        dob = (EditText)findViewById(R.id.txtDate);
        phoneno= (EditText)findViewById(R.id.txtPhone);
        btnSubmit = (Button)findViewById(R.id.btnSend);
        result = (TextView)findViewById(R.id.resultView);

        // 为 "提交按钮" 绑定点击事件
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (name.getText().toString().isEmpty()
                        || password.getText().toString().isEmpty()
                        || email.getText().toString().isEmpty()
                        || dob.getText().toString().isEmpty()
                        || phoneno.getText().toString().isEmpty()) {  // 判断是否有字段是空的
                    // 任意字段为空
                    result.setText("Please Fill All the Details");
                } else {
                    // 拼接字段在 "提交结果" 中显示
                    result.setText("Name -  " + name.getText().toString() + " \n" + "Password -  " + password.getText().toString()
                            + " \n" + "E-Mail -  " + email.getText().toString() + " \n" + "DOB -  " + dob.getText().toString()
                            + " \n" + "Contact -  " + phoneno.getText().toString());
                }
            }
        });
    }
}