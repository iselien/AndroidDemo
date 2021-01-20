package io.yovelas.recyclerviewdemo.recycler.linearlayout;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.yovelas.recyclerviewdemo.Audio;
import io.yovelas.recyclerviewdemo.R;
import io.yovelas.recyclerviewdemo.recycler.model.Person;

public class LinearLayoutFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Person> personList = new ArrayList<>();
    private ContentResolver contentResolver;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_linear_layout, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(LinearLayoutFragment.this)
                        .navigate(R.id.action_LinearLayoutFragment_to_SecondFragment);
            }
        });

        for (int i = 0; i < 30; ++i) {
            Person person = new Person(i, "yovelas_" + i, 10 + i);
            personList.add(person);
        }

        contentResolver = getActivity().getContentResolver();




        String[] projection = new String[] {
                MediaStore.Audio.Media._ID,
                MediaStore.Audio.Media.DISPLAY_NAME,
                MediaStore.Audio.Media.ARTIST,
                MediaStore.Audio.Media.DURATION,
                MediaStore.Audio.Media.SIZE
        };

        String selection = MediaStore.Video.Media.DURATION +
                " >= ?";
        String[] selectionArgs = new String[] {String.valueOf(TimeUnit.MILLISECONDS.convert(5, TimeUnit.MINUTES))};
        String sortOrder = MediaStore.Video.Media.DISPLAY_NAME + " ASC";

        ArrayList<Audio> audioList = new ArrayList<>();

        Cursor cursor = contentResolver.query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                projection,
                selection,
                selectionArgs,
                sortOrder);

        while (cursor.moveToNext()){
            Audio audio = new Audio();
            Log.e("audio", "loadData:aaa " + cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME) );
            audio.setDisplay_name(cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME)));
            audioList.add(audio);
        }
        Log.e("audio", "loadData:List " + audioList );

        adapter = new PersonAdapter(audioList);

//        adapter = new PersonAdapter(personList);
        recyclerView.setAdapter(adapter);
    }

}