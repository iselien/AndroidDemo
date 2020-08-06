package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase database = Room.databaseBuilder(this, AppDatabase.class, "mydb").allowMainThreadQueries().build();
        ItemDao itemDao = database.getItemDao();
        Item item = new Item();
        item.setName("item001");
        item.setDescription("item001 Description");
        item.setQuantity(1000L);
        item.setId(111L);

//        itemDao.insert(item);

        List<Item> items = itemDao.getItems();
        for (Item i : items){
            Log.e(TAG, "onCreate: getitems: " + i);
        }


    }

}