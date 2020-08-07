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

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "mydb").build();

                UserDao userDao = db.userDao();

                User user = new User();
                user.setUid(1);
                user.setFirstName("yove");
                user.setLastName("las");

                userDao.insertAll(user);
            }
        });


//        List<User> all = userDao.getAll();
//        for (User u : all){
//            Log.e(TAG, "onCreate: getitems: " + u);
//        }


    }

}