package com.example.hemamostafa.loginwithroom;

import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hemamostafa.loginwithroom.DataBase.MyDatabase;
import com.example.hemamostafa.loginwithroom.Model.UserTable;

import java.util.List;

public class Register extends AppCompatActivity {

    EditText emailEditText,passworEditText,usernameEditText;
    TextInputLayout emailLayout,passwordLayout,usernameLayout;
    Button btnRegister;
    List<UserTable> users_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();
        initTextViewLogin();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()){
                    String email = emailEditText.getText().toString();
                    String password = passworEditText.getText().toString();
                    String username = usernameEditText.getText().toString();
                    UserTable userTable = new UserTable(username,password,email);

                    // Get Data From DataBase

                   users_List= MyDatabase.getInstance(getApplicationContext())
                            .userTableDao()
                            .getAllUsers();

                    if (users_List == null){
                        Snackbar.make(btnRegister, "No Users in DataBase", Snackbar.LENGTH_LONG).show();
                        MyDatabase.getInstance(getApplicationContext())
                                .userTableDao()
                                .inserUser(userTable);
                        Snackbar.make(btnRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();
                        return;

                    }

                    for (int i = 0 ; i < users_List.size();i++){
                        if (email.equals(users_List.get(i).getEmail().toString())) {
                            Snackbar.make(btnRegister, "User already exists with same email ,failed Registeration ", Snackbar.LENGTH_LONG).show();
                            return;
                        }
                    }

                    MyDatabase.getInstance(getApplicationContext())
                            .userTableDao()
                            .inserUser(userTable);
                    Snackbar.make(btnRegister, "User created successfully! Please Login ", Snackbar.LENGTH_LONG).show();



                }
            }
        });
    }

    private void initViews() {
        emailEditText = (EditText) findViewById(R.id.editTextEmail);
        passworEditText = (EditText) findViewById(R.id.EditTextPassword);
        usernameEditText=(EditText)findViewById(R.id.editTextUserName);
        emailLayout = (TextInputLayout) findViewById(R.id.InputTextEmail);
        passwordLayout = (TextInputLayout) findViewById(R.id.InputTextPassword);
        usernameLayout = (TextInputLayout) findViewById(R.id.InputTextUserName);
        btnRegister = (Button) findViewById(R.id.btn_register);

    }
    private  void initTextViewLogin(){
        TextView backToLogin_textView= (TextView)findViewById(R.id.textViewBackLogin);
        backToLogin_textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private boolean validate() {
        boolean valid = false;

        // get The Value From EditText

        String email = emailEditText.getText().toString();
        String password = passworEditText.getText().toString();
        String username = usernameEditText.getText().toString();


        if (username.isEmpty()) {
            valid = false;
            usernameLayout.setError("Please enter valid username!");
        } else {
            if (username.length() > 5) {
                valid = true;
                usernameLayout.setError(null);
            } else {
                valid = false;
                usernameLayout.setError("Username is to short!");
            }
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){

            valid=false;
            emailLayout.setError(" Please Enter A valid E-mail");
            return  false;

        }
        else {

            valid = true;
            emailLayout.setError(null);

        }


        if(password.isEmpty()){
            valid=false;
            passwordLayout.setError(" Password IS Empty");
            return valid;

        }
        else {
            if(password.length() <= 5){
                valid=false;
                passwordLayout.setError(" Enter More Than 5 Letter");
                return valid;
            }
            else{
                valid=true;
                passwordLayout.setError(null);
            }


        }

        return  valid;
    }




}
