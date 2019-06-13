package com.example.hemamostafa.loginwithroom;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Html;

import android.widget.TextView;

 /*import java.util.regex.Pattern; */

import com.example.hemamostafa.loginwithroom.DataBase.MyDatabase;
import com.example.hemamostafa.loginwithroom.Model.UserTable;

import java.util.List;

import static android.text.Html.fromHtml;

public class login extends AppCompatActivity {

     EditText emailEditText,passworEditText;
    TextInputLayout emailLayout,passwordLayout;
    Button btnLogin;
    List<UserTable> users_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initViews();
        initCreateAccountTextView();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (validate()){
                    String email = emailEditText.getText().toString();
                    String password = passworEditText.getText().toString();
                    // get Date From Data Base


                   users_List=null;
                   users_List= MyDatabase.getInstance(getApplicationContext())
                           .userTableDao()
                           .getAllUsers();

                        if (users_List == null){
                            Snackbar.make(btnLogin, "No Users in DataBase", Snackbar.LENGTH_LONG).show();
                            return;
                        }

                   for (int i = 0 ;i < users_List.size();i++){
                       if (email.equals(users_List.get(i).getEmail().toString()) &&password.equals( users_List.get(i).getPassword()))
                       {
                           Snackbar.make(btnLogin, "Successfully Logged in!", Snackbar.LENGTH_LONG).show();
                           Log.e("mail",users_List.get(i).getEmail().toString());
                           Log.e("mail",email);
                           break;

                       }

                   }

                }
                else
                    Snackbar.make(btnLogin, "Failed to log in , please try again", Snackbar.LENGTH_LONG).show();

            }
        });


    }
    private void initViews() {
        emailEditText = (EditText) findViewById(R.id.EditTextEmail);
        passworEditText = (EditText) findViewById(R.id.EditTextPassword);
        emailLayout = (TextInputLayout) findViewById(R.id.TextInputLayoutEmail);
        passwordLayout = (TextInputLayout) findViewById(R.id.TextInputLayoutPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);

    }

    private void initCreateAccountTextView(){
        TextView textViewCreateAccount = (TextView)findViewById(R.id.textViewCreateAccount);

        /* fromHTml ? */
        textViewCreateAccount.setText(fromHtml("<font color='#ffffff'>I don't have account yet. </font>"
                + "<font color='#0c0099'>create one</font>"));

        textViewCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(login.this,Register.class));
            }
        });

    }
    //This method is for handling fromHtml method deprecation
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html) {
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    private boolean validate() {
        boolean valid = false;

        // get The Value From EditText

        String email = emailEditText.getText().toString();
        String password = passworEditText.getText().toString();


        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            valid=false;
            emailLayout.setError(" Please Enter A valid E-mail");
            return valid;

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
