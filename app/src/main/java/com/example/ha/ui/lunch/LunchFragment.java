package com.example.ha.ui.lunch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.ha.R;

import com.example.ha.databinding.FragmentLunchBinding;

import java.util.ArrayList;
import java.util.Random;


public class LunchFragment extends Fragment {
    private ArrayList<String> lunches = new ArrayList<>();
    private ArrayList<String> noLunch = new ArrayList<>();
    private FragmentLunchBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        LunchViewModel lunchViewModel =
                new ViewModelProvider(this).get(LunchViewModel.class);

        binding = FragmentLunchBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Items on the layout
        final EditText monthText = (EditText) root.findViewById(R.id.month2);
        final EditText dateText = (EditText) root.findViewById(R.id.day2);
        final Button button2 = (Button) root.findViewById(R.id.lunchButton);
        final TextView output = (TextView) root.findViewById(R.id.lunchOutput);
        Random random = new Random();
        //List of lunch options
        lunches.add("Chicken Noodle Soup, Green beans, Apples/Oranges, Bread, & Strawberry Milk");
        lunches.add("Chicken Alfredo, Blueberries, Raspberries, Cucumbers, Chocolate Pudding, & Water ");
        lunches.add("Cheese Quesadilla, Guacamole, Salsa, Tortilla Chips, Strawberries, & Chocolate Milk");
        lunches.add("Bagel, Cream Cheese, Mangoes, Pretzels, & Capri Suns");
        lunches.add("Mac & Cheese, Grapes, Carrots, Cookies, Smoothie");
        noLunch.add("No school lunch; Enjoy your break");
        //This button click checks the month and date and returns the an appropriate lunch option
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monthU = monthText.getText().toString();
                String da = dateText.getText().toString();
                if(monthU.equals("")|| da.equals("")){
                    makeToast("Please enter a month and/or day");
                } else {
                    int dateU = Integer.parseInt((dateText.getText().toString()));
                    int val = random.nextInt(5);
                    if (monthU.equals("June") || monthU.equals("July") || monthU.equals("june") || monthU.equals("july")) {
                        output.setText(noLunch.get(0));
                    } else if ((monthU.equals("December") || monthU.equals("december")) && (dateU > 22)) {
                        output.setText(noLunch.get(0));
                    } else if ((monthU.equals("January") || monthU.equals("january")) && (dateU < 4)) {
                        output.setText(noLunch.get(0));
                    } else {
                        output.setText(lunches.get(val));
                    }
                }
            }


        });
        final TextView textView = binding.textLunch;
        lunchViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    Toast toast;
    //This function creates a toast message with a certain string from the parameter
    private void makeToast(String s){
        if (toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getActivity(),s, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}