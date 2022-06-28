package com.example.ha.ui.calendar;

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
import com.example.ha.databinding.FragmentCalendarBinding;

import java.util.ArrayList;
import java.util.Random;


public class CalendarFragment extends Fragment {
    private ArrayList<String> events = new ArrayList<>();
    private ArrayList<String> noevent = new ArrayList<>();
    private FragmentCalendarBinding binding;
    private Toast toast;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        CalendarViewModel calendarViewModel =
                new ViewModelProvider(this).get(CalendarViewModel.class);

        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Items on the layout
        final EditText monthText = (EditText) root.findViewById(R.id.month);
        final EditText dateText = (EditText) root.findViewById(R.id.day);
        final Button button = (Button) root.findViewById(R.id.calButton);
        final TextView output = (TextView) root.findViewById(R.id.calOutput);
        Random random = new Random();
        //List of events
        events.add("Track Practice");
        events.add("Parent-Teacher Conferences");
        events.add("Tennis Practice");
        events.add("Away Track Meet");
        events.add("Tennis Home Matches");
        noevent.add("No School Events; Enjoy your break");
        //This button click checks the month and date and returns the an appropriate event
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String monthU = monthText.getText().toString();
                String da = dateText.getText().toString();
                if (monthU.equals("")|| da.equals("")) {
                    makeToast("Please enter a month and/or day");
                } else {
                    int dateU = Integer.parseInt((dateText.getText().toString()));
                    int val = random.nextInt(5);
                    if (monthU.equals("June") || monthU.equals("July") || monthU.equals("june") || monthU.equals("july")) {
                        output.setText(noevent.get(0));
                    } else if ((monthU.equals("December") || monthU.equals("december")) && (dateU > 22)) {
                        output.setText(noevent.get(0));
                    } else if ((monthU.equals("January") || monthU.equals("january")) && (dateU < 4)) {
                        output.setText(noevent.get(0));
                    } else {
                        output.setText(events.get(val));
                    }
                }
            }
        });
        final TextView textView = binding.textCalendar;
        calendarViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

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