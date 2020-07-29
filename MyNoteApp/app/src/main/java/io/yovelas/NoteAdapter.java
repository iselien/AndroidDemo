package io.yovelas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private List<Note> notes;
    private LayoutInflater layoutInflater;

    public NoteAdapter(Context aContext, List<Note> notes) {
        this.notes = notes;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int i) {
        return notes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder holder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.view_note_item, null);
            holder = new ViewHolder();
            holder.title = (TextView) view.findViewById(R.id.note_title);
            holder.content = (TextView) view.findViewById(R.id.note_content);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.title.setText(notes.get(i).getTitle());
        holder.content.setText(notes.get(i).getContent());
        return view;
    }

    static class ViewHolder {
        TextView title;
        TextView content;
    }












//
//    private List<Note> notes;
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        return null;
//    }
//
//    // Provide a reference to the views for each data item
//    // Complex data items may need more than one view per item, and
//    // you provide access to all the views for a data item in a view holder
//    public static class MyViewHolder extends RecyclerView.ViewHolder {
//        // each data item is just a string in this case
//        public View view;
//        public MyViewHolder(View v) {
//            super(v);
//            view = v;
//        }
//    }
//
//    // Provide a suitable constructor (depends on the kind of dataset)
//    public NoteAdapter(List<Note> notes) {
//        this.notes = notes;
//    }
//
//    // Create new views (invoked by the layout manager)
//    @Override
//    public NoteAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        // create a new view
//        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.view_note_item, parent, false);
//
//        MyViewHolder vh = new MyViewHolder(v);
//        return vh;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//        TextView title = holder.itemView.findViewById(R.id.note_title);
//        title.setText(notes.get(position).getTitle());
//
//        TextView content = holder.itemView.findViewById(R.id.note_content);
//        content.setText(notes.get(position).getContent());
//    }
//
////    // Replace the contents of a view (invoked by the layout manager)
////    @Override
////    public void onBindViewHolder(MyViewHolder holder, int position) {
////        // - get element from your dataset at this position
////        // - replace the contents of the view with that element
////        TextView title = holder.view.findViewById(R.id.note_title);
////        title.setText(notes[position].getTitle());
////
////        TextView content = holder.view.findViewById(R.id.note_content);
////        content.setText(notes[position].getContent());
////
////    }
//
//    // Return the size of your dataset (invoked by the layout manager)
//    @Override
//    public int getItemCount() {
//        return notes.size();
//    }
//
//
//
//
//

//
//    @NonNull
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
}
