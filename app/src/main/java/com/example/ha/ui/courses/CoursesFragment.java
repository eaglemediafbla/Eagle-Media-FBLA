package com.example.ha.ui.courses;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;


import com.example.ha.R;
import com.example.ha.databinding.FragmentCoursesBinding;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;


public class CoursesFragment extends Fragment {

    private FragmentCoursesBinding binding;
    ListView listView;
    ArrayList<String> courses;
    ArrayAdapter adapter;
    EditText input;
    ImageView enter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CoursesViewModel coursesViewModel =
                new ViewModelProvider(this).get(CoursesViewModel.class);
        binding = FragmentCoursesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Items on the layout
        listView = root.findViewById(R.id.listview);
        input = root.findViewById(R.id.input);
        enter = root.findViewById(R.id.add);
        //Listview things
        courses = new ArrayList<>();
        adapter = new ArrayAdapter<>(getActivity(), R.layout.listviewtext, courses);
        listView.setAdapter(adapter);
        //checks if the input is valid and adds it
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0;i<courses.size();i++){
                    if(courses.get(i).equals("")|| courses.get(i) == null){
                        courses.remove(i);
                    }
                }
                String text = input.getText().toString();
                if(text.length() == 0){
                    makeToast("Enter a course");
                } else{
                    addCourse(text);
                    input.setText("");
                    makeToast("Added " + text);
                }
            }
        });
        //removes course with a long press
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                makeToast("Removed " + courses.get(position));
                removeCourse(position);
                return false;
            }
        });
        final TextView textView = binding.textCourses;
        coursesViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        saveCourses();
        return root;
    }
    //adds course to list
    public void addCourse(String course){
        courses.add(course);
        adapter.notifyDataSetChanged();
    }
    //removes course from list
    public void removeCourse(int remove){
        courses.remove(remove);
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
    //access saved courses from file
    public void saveCourses(){
        File path = getActivity().getFilesDir();
        File readFrom = new File(path, "course.txt");
        byte[] content = new byte[(int) readFrom.length()];
        FileInputStream stream = null;
        try {
            stream = new FileInputStream(readFrom);
            stream.read(content);
            String s = new String(content);
            s = s.substring(1, s.length() -1);
            String split[] = s.split(", ");
            courses = new ArrayList<>(Arrays.asList(split));
            adapter = new ArrayAdapter<>(getActivity(),R.layout.listviewtext, courses);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Override
    //when changed or "destroyed", saves the courses list data into a file
    public void onDestroyView() {
        File path = getActivity().getFilesDir();
        try{
            FileOutputStream writer = new FileOutputStream(new File(path, "course.txt"));
            writer.write(courses.toString().getBytes());
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onDestroyView();
        binding = null;
    }

}