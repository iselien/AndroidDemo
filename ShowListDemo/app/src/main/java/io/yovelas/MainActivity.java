package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mListView;
    private ArrayAdapter aAdapter;
    private String[] users = { "Suresh Dasari", "Rohini Alavala", "Trishika Dasari", "Praveen Alavala", "Madav Sai", "Hamsika Yemineni"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mListView = (ListView) findViewById(R.id.userlist);
        aAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, users);
        mListView.setAdapter(aAdapter);

//        // 获取用户列表
//        ArrayList userList = getListData();
//
//        // 获取 ListView
//        final ListView userListView = (ListView) findViewById(R.id.userlist);
//
//        userListView.setAdapter(new UserAdapter( userList,userListView));
//
//        userListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
//                ListItem user = (ListItem) lv.getItemAtPosition(position);
//                Toast.makeText(MainActivity.this, "Selected :" + " " + user.getName()+", "+ user.getLocation(), Toast.LENGTH_SHORT).show();
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       MenuInflater inflater = getMenuInflater(); inflater.inflate(R.menu.menu_example, menu);
        return true;
    }

//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu( menu, v, menuInfo );
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_example, menu);
//    }

    private ArrayList getListData() {
        ArrayList<User> results = new ArrayList<>();
        User user1 = new User();

        user1.setName("毛病同学");
        user1.setAge("20");
        user1.setLocation("上海");
        results.add(user1);

        User user2 = new User();
        user2.setName("葛二哈");
        user2.setAge("19");
        user2.setLocation("广州");
        results.add(user2);

        User user3 = new User();
        user3.setName("哈敏儿");
        user3.setAge("22");
        user3.setLocation("深圳");
        results.add(user3);

        return results;
    }

}
