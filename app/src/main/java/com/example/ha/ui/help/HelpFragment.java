package com.example.ha.ui.help;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ha.R;
import com.example.ha.databinding.FragmentHelpBinding;

public class HelpFragment extends Fragment {


    private FragmentHelpBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HelpViewModel helpViewModel =
                new ViewModelProvider(this).get(HelpViewModel.class);

        binding = FragmentHelpBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //Items on the layout
        final Button send = (Button) root.findViewById(R.id.Send24);
        final TextView body = (TextView) root.findViewById(R.id.textView24);
        //This button click clears the body text in the mail and makes a toast message appear.
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                body.setText("");
                makeToast("Bug Report Sent");
            }

        });

        final TextView textView = binding.textHelp;
        helpViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
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