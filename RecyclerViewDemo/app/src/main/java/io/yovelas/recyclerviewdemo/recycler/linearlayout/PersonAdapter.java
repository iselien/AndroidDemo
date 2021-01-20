package io.yovelas.recyclerviewdemo.recycler.linearlayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.yovelas.recyclerviewdemo.Audio;
import io.yovelas.recyclerviewdemo.R;
import io.yovelas.recyclerviewdemo.recycler.model.Person;

public class PersonAdapter extends RecyclerView.Adapter {
    public static interface OnRecyclerViewListener {
        void onItemClick(int position);

        boolean onItemLongClick(int position);
    }

    private OnRecyclerViewListener onRecyclerViewListener;

    public void setOnRecyclerViewListener(OnRecyclerViewListener onRecyclerViewListener) {
        this.onRecyclerViewListener = onRecyclerViewListener;
    }

    private static final String TAG = PersonAdapter.class.getSimpleName();
    private List<Audio> list;

    public PersonAdapter(List<Audio> list) {
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int type) {
        Log.d(TAG, "onCreateViewHolder, type: " + type);
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view_test_item_person, null);
//        不知道为什么在xml设置的“android:layout_width="match_parent"”无效了，需要在这里重新设置
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        Vh holder = (Vh) viewHolder;
        Log.e(TAG, "onBindViewHolder: " + list.get(i) );
//        Person person = list.get(i);
//        holder.nameTv.setText(person.getName());
//        holder.ageTv.setText(person.getAge() + "岁");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Vh extends RecyclerView.ViewHolder{
        View rootView;
        TextView nameTv;
        TextView ageTv;
        public Vh(@NonNull View itemView) {
            super(itemView);
            nameTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_name_tv);
            ageTv = (TextView) itemView.findViewById(R.id.recycler_view_test_item_person_age_tv);
            rootView = itemView.findViewById(R.id.recycler_view_test_item_person_view);
        }
    }

}
