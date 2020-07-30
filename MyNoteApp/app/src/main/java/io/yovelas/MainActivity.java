package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);

//        ListView listView = (ListView) findViewById(R.id.note_recycler_view);
//
//        // use this setting to improve performance if you know that changes
//        // in content do not change the layout size of the RecyclerView
////        recyclerView.setHasFixedSize(true);
//
//        // use a linear layout manager
//        layoutManager = new LinearLayoutManager(this);
////        listView.setLayoutManager(layoutManager);
//
//        ArrayList<Note> notes = new ArrayList<>();
//        notes.add(new Note("abc","123\nfjdsfjdsfj\nkwekfewfjwlkef\nwefwefwefw"));
//        notes.add(new Note("abe","124"));
//        notes.add(new Note("abf","125"));
//        notes.add(new Note("abg","126"));
//
//        // specify an adapter (see also next example)
//        listView.setAdapter(new NoteAdapter(this,notes));
    }

    public void toDetail(View view){
//        new Intent(this,)
       navController.navigate(R.id.HomeToDetail);
    }
}