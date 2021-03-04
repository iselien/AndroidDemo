package io.yovelas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import io.yovelas.databinding.ActivityMain1Binding;

public class MainActivity extends AppCompatActivity {

    ViewModelWithLiveData viewModel;
    ActivityMain1Binding binding;
    RecyclerView recyclerView;
    HeroAdapter adapter;
    List<Hero> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main1);
        viewModel = ViewModelProviders.of(this).get(ViewModelWithLiveData.class);
        binding.setData(viewModel);
        binding.setLifecycleOwner(this);
//        viewModel.getNumber().observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                binding.textView2.setText(String.valueOf(integer));
//            }
//        });
//
//        binding.button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewModel.addNumber(1);
//            }
//        });
//        binding.button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                viewModel.addNumber(1);
//            }
//        });

//        recyclerView = findViewById(R.id.recycler);
////        recyclerView.setHasFixedSize(true);
////        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//
//        HeroViewModel model = ViewModelProviders.of(this).get(HeroViewModel.class);
//
//        model.getHeroes().observe(this, new Observer<List<Hero>>() {
//            @Override
//            public void onChanged(@Nullable List<Hero> heroList) {
//                adapter = new HeroAdapter(MainActivity.this, heroList);
//                recyclerView.setAdapter(adapter);
//            }
//        });
    }
}