package com.example.ha.ui.extracurriculars;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.ha.R;
import com.example.ha.databinding.FragmentExtracurricularsBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;


public class ExtracurricularsFragment extends Fragment {

    private FragmentExtracurricularsBinding binding;
    ListView listView;
    ArrayList<String> extracurriculars;
    ArrayAdapter<String> adapter;
    EditText input;
    ImageView enter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ExtracurricularsViewModel extracurricularsViewModel =
                new ViewModelProvider(this).get(ExtracurricularsViewModel.class);

        binding = FragmentExtracurricularsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Items on the layout
        listView = root.findViewById(R.id.listview2);
        input = root.findViewById(R.id.input2);
        enter = root.findViewById(R.id.add2);
        //Listview things
        extracurriculars = new ArrayList<>();
        adapter = new ArrayAdapter<>(getActivity(), R.layout.listviewtext, extracurriculars);
        listView.setAdapter(adapter);
        //checks if the input is valid and adds it
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0;i<extracurriculars.size();i++){
                    if(extracurriculars.get(i).equals("")|| extracurriculars.get(i) == null){
                        extracurriculars.remove(i);
                    }
                }
                String text = input.getText().toString();
                if(text.length() == 0){
                    makeToast("Enter an extracurricular");
                } else{
                    addExtracurriculars(text);
                    input.setText("");
                    makeToast("Added " + text);
                }
            }
        });
        //removes extracurricular with a long press
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                makeToast("Removed " + extracurriculars.get(position));
                removeExtracurriculars(position);
                return false;
            }
        });
        final TextView textView = binding.textExtracurriculars;
        extracurricularsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        saveExtracurriculars();
        return root;
    }
    //adds extracurricular to list
    public void addExtracurriculars(String extracurricular){
        extracurriculars.add(extracurricular);
        adapter.notifyDataSetChanged();
    }
    // removes extracurricular from list
    public void removeExtracurriculars(int remove){
        extracurriculars.remove(remove);
        adapter.notifyDataSetChanged();
    }

    Toast toast;
    //This function creates a toast message with a certain string from the parameter
    public void makeToast(String s){
        if (toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getActivity(),s, Toast.LENGTH_SHORT);
        toast.show();
    }
    //access saved extracurriculars from file
    public void saveExtracurriculars(){

        File path = getActivity().getFilesDir();
        File readFrom = new File(path, "extracurricular.txt");
        byte[] content = new byte[(int) readFrom.length()];
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(readFrom);
            stream.read(content);
            String s = new String(content);
            s = s.substring(1, s.length() -1);
            String split[] = s.split(", ");
            extracurriculars = new ArrayList<>(Arrays.asList(split));
            adapter = new ArrayAdapter<>(getActivity(),R.layout.listviewtext, extracurriculars);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    //when changed or "destroyed", saves the extracurriculars list data into a file
    public void onDestroyView() {
        File path = getActivity().getFilesDir();
        try{
            FileOutputStream writer = new FileOutputStream(new File(path, "extracurricular.txt"));
            writer.write(extracurriculars.toString().getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroyView();
        binding = null;
    }
}