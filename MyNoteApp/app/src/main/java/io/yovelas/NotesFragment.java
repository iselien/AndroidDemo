package io.yovelas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class NotesFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.view_notes, container, false);

        // 从填充好布局的 "view" 查找ListView元素
        ListView listView = (ListView) view.findViewById(R.id.note_listview);

        // 需要填充的数据
        ArrayList<Note> notes = new ArrayList<>();
        notes.add(new Note("abc","123\nfjdsfjdsfj\nkwekfewfjwlkef\nwefwefwefw"));
        notes.add(new Note("abe","124"));
        notes.add(new Note("abf","125"));
        notes.add(new Note("abg","126"));

        // specify an adapter
        listView.setAdapter(new NoteAdapter(getActivity(),notes));

        return view;
    }
}