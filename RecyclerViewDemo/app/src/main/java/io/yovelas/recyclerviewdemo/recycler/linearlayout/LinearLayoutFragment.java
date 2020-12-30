package io.yovelas.recyclerviewdemo.recycler.linearlayout;

import android.os.Bundle;
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

import io.yovelas.recyclerviewdemo.R;
import io.yovelas.recyclerviewdemo.recycler.model.Person;

public class LinearLayoutFragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<Person> personList = new ArrayList<>();

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
        adapter = new PersonAdapter(personList);
        recyclerView.setAdapter(adapter);
    }

}