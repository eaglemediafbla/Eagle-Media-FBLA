package com.example.ha.ui.mail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.ha.R;
import com.example.ha.databinding.FragmentMailBinding;


public class MailFragment extends Fragment {

    private FragmentMailBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MailViewModel mailViewModel =
                new ViewModelProvider(this).get(MailViewModel.class);

        binding = FragmentMailBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        final Button send = (Button) root.findViewById(R.id.Send);
        final TextView body = (TextView) root.findViewById(R.id.textView2);
        final Spinner spinner = (Spinner) root.findViewById(R.id.emaildropdown);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),R.array.emails, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        //Clears the body text and sends the mail
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                body.setText("");
                makeToast("Mail Sent");
            }

        });

        //spinner choice control
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final TextView textView = binding.textMail;
        mailViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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