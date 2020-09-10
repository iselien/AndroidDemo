package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private User user;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = AppDatabase.getDatabase(getApplicationContext());

        // cleanup for testing some initial data
        database.userDao().removeAllUsers();
        // add some data
        List<User> users = database.userDao().getAllUser();
        if (users.size()==0) {
            database.userDao().addUser(new User(1, "Test 1", 1));
            user = database.userDao().getAllUser().get(0);
            Toast.makeText(this, String.valueOf(user.id), Toast.LENGTH_SHORT).show();
            Trophy trophy = new Trophy(user.id, "Learned to use 3");
            database.trophyDao().addTrophy(trophy);
            database.userDao().addUser(new User(2, "Test 2", 2));
            database.userDao().addUser(new User(3, "Test 3", 3));
        }

        updateFirstUserData();

    }


    private void updateFirstUserData() {
        List<User> user = database.userDao().getAllUser();
        List<Trophy> trophiesForUser = database.trophyDao().findTrophiesForUser(user.get(0).id);
        TextView textView = findViewById(R.id.result);
        Toast.makeText(this, trophiesForUser.toString(), Toast.LENGTH_SHORT).show();
        if (user.size()>0){
            textView.setText(user.get(0).name + " Skill points " + user.get(0).skillPoints + " Trophys " + trophiesForUser.size() );
        }
    }

    public void onClick(View view){
        if (view.getId()==R.id.addtrophybutton) {
            // TODO add trophy
            // TODO call updatefirstUserData
            Toast.makeText(this,String.valueOf(user.id), Toast.LENGTH_SHORT).show();
            Trophy trophy = new Trophy(user.id, "More stuff");
            database.trophyDao().addTrophy(trophy);
        }
        if (view.getId()==R.id.increaseskills ){
            user.skillPoints++;
            database.userDao().updateUser(user);
            // TODO to skillpoints

        }
        // TODO call updatefirstUserData
        updateFirstUserData();

    }
    @Override
    protected void onDestroy() {
        AppDatabase.destroyInstance();
        super.onDestroy();
    }



//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        SharedPreferences.Editor edit = getPreferences(Context.MODE_PRIVATE).edit();
//
//        edit.putString("abc","123");
//
//        edit.commit();
//
//
//        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
//        sharedPref.getString("","");
//    }
//
//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
//        return true;
//    }
//    public void onClickAddDetails(View view) {
//        ContentValues values = new ContentValues();
//        values.put(UsersProvider.name, ((EditText) findViewById(R.id.txtName)).getText().toString());
//        getContentResolver().insert(UsersProvider.CONTENT_URI, values);
//        Toast.makeText(getBaseContext(), "New Record Inserted", Toast.LENGTH_LONG).show();
//    }
//
//    public void onClickShowDetails(View view) {
//        // Retrieve employee records
//        TextView resultView= (TextView) findViewById(R.id.res);
//        Cursor cursor = getContentResolver().query(Uri.parse("content://com.tutlane.contentprovider.UserProvider/users"), null, null, null, null);
//        if(cursor.moveToFirst()) {
//            StringBuilder strBuild=new StringBuilder();
//            while (!cursor.isAfterLast()) {
//                strBuild.append("\n"+cursor.getString(cursor.getColumnIndex("id"))+ "-"+ cursor.getString(cursor.getColumnIndex("name")));
//                cursor.moveToNext();
//            }
//            resultView.setText(strBuild);
//        }
//        else {
//            resultView.setText("No Records Found");
//        }
//    }


}