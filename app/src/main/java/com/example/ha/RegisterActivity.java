package com.example.ha;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.ha.ui.login.LoginActivity;

public class RegisterActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register();
    }


    //This function gets the user input and changes the screen back to the login page.
    public void register(){
        Button register2 = (Button) findViewById(R.id.register2);
        EditText nmaybe = (EditText) findViewById(R.id.namemaybe);
        EditText emaybe = (EditText) findViewById(R.id.emailmaybe);
        EditText user2 = (EditText) findViewById(R.id.username2);
        EditText pass2 = (EditText) findViewById(R.id.password2);
        EditText re = (EditText) findViewById(R.id.retype);
        register2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String n = nmaybe.getText().toString();
                String e = emaybe.getText().toString();
                String u = user2.getText().toString();
                String p = pass2.getText().toString();
                String r = re.getText().toString();
                if(n.equals("")||e.equals("")||u.equals("")||p.equals("")||r.equals("")){
                    makeToast("Please enter all the details");
                } else if (!p.equals(r)){
                    makeToast("Please enter the same password");
                } else {
                    makeToast("You have been registered. Please login with the same username and password.");
                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                }
            }
        });
    }


    Toast toast;
    //This function creates a toast message with a certain string from the parameter
    private void makeToast(String s){
        if (toast != null){
            toast.cancel();
        }
        toast = Toast.makeText(getApplicationContext(),s, Toast.LENGTH_SHORT);
        toast.show();
    }


}