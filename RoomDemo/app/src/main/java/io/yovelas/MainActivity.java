package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.ClipData;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    UserDatabase userDatabase;
    UserDao userDao;
    TextView textView;
    Button buttonInsert, buttonUpdate, buttonDelete, buttonClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userDatabase = Room.databaseBuilder(getApplicationContext(), UserDatabase.class, "user_database")
                .allowMainThreadQueries()
                .build();
        userDao = userDatabase.getUserDao();
        textView = findViewById(R.id.textview);
        updateView();
        buttonInsert = findViewById(R.id.buttonInsert);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user1 = new User("yove","las");
                User user2 = new User("dou","la");
                userDao.insertUser(user1, user2);
                updateView();
            }
        });
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user1 = new User("yove","las");
                user1.setUid(120);
                userDao.updateUsers(user1);
                updateView();
            }
        });
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user1 = new User("yove","las");
                user1.setUid(121);
                userDao.delete(user1);
                updateView();
            }
        });
        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userDao.deleteAll();
                updateView();
            }
        });
    }

    void updateView(){
        List<User> all = userDao.getAll();
        String text = "";
        for (User user : all){
            text += user.getUid() + ":" + user.getFirstName() + "," + user.getLastName() + "\n";
        }
        textView.setText(text);
    }

}