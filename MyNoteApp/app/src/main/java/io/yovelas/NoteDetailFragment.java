package io.yovelas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class NoteDetailFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.view_note_detail, container, false);

        final EditText title = view.findViewById(R.id.et_title);
        final EditText content = view.findViewById(R.id.et_content);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                System.out.println("asdfdswwwwwf");
                System.out.println(title.getText().toString());
                System.out.println(content.getText().toString());
                if(!title.getText().toString().equals("") && !content.getText().toString().equals("")){
                    Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.homeFragment);

                    RelativeLayout relativeLayout = view.findViewById(R.id.rl_btn);
                    relativeLayout.addView(view.findViewById(R.id.addButton));
                }else if(!title.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "内容不能为空", Toast.LENGTH_SHORT).show();
                }else if(!content.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"标题不能为空",Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }

}