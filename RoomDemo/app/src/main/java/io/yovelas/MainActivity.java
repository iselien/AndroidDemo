package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.ClipData;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.btn);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").allowMainThreadQueries().build();

        UserDao userDao = db.userDao();

        User user = new User();
        user.setUid(7);
        user.setFirstName("yove1");
        user.setLastName("las2");

//        userDao.insertAll(user);

        List<User> all = userDao.getAll();
        for (User u : all){
            Log.e(TAG, "onCreate: getitems: " + u);
        }



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppDatabase database = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "database-name").allowMainThreadQueries().build();

                UserDao userDao = database.userDao();

                User user = new User();
                user.setUid(3);
                user.setFirstName("yove");
                user.setLastName("las");

//                userDao.insertAll(user);

                List<User> all = userDao.getAll();
                for (User u : all){
                    Log.e(TAG, "onCreate: getitems: " + u);
                }
            }
        });


//        List<User> all = userDao.getAll();
//        for (User u : all){
//            Log.e(TAG, "onCreate: getitems: " + u);
//        }


    }

}