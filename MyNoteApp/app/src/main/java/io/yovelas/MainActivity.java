package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.note_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
//        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
//        listView.setLayoutManager(layoutManager);

        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("abc","123\nfjdsfjdsfj\nkwekfewfjwlkef\nwefwefwefw"));
        notes.add(new Note("abe","124"));
        notes.add(new Note("abf","125"));
        notes.add(new Note("abg","126"));

        // specify an adapter (see also next example)
        listView.setAdapter(new NoteAdapter(this,notes));
    }
}