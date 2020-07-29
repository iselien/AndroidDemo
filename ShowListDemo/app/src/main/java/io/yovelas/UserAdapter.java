package io.yovelas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private ArrayList<User> userList;
    private LayoutInflater layoutInflater;

    public UserAdapter(ArrayList<User> userList, LayoutInflater layoutInflater) {
        this.userList = userList;
        this.layoutInflater = layoutInflater;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int i) {
        return userList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.user_list_row, null);
            holder = new ViewHolder();
            holder.uName = (TextView) view.findViewById(R.id.name);
            holder.uDesignation = (TextView) view.findViewById(R.id.designation);
            holder.uLocation = (TextView) view.findViewById(R.id.location);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.uName.setText(userList.get(i).getName());
        holder.uDesignation.setText(userList.get(i).getAge());
        holder.uLocation.setText(userList.get(i).getLocation());
        return view;
    }

    static class ViewHolder {
        TextView uName;
        TextView uDesignation;
        TextView uLocation;
    }

}
