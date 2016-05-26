package com.example.cmsc191.projectupa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    UserDatabaseAdapter registrationAdapter;
    TextView loginLink;
    EditText regFullname, regUsername, regPassword,
            regConfirmPassword, regAddress;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registrationAdapter = new UserDatabaseAdapter(this);
        registrationAdapter = registrationAdapter.open();

        regFullname = (EditText) findViewById(R.id.register_fullname_edittext);
        regUsername = (EditText) findViewById(R.id.register_username_edittext);
        regPassword = (EditText) findViewById(R.id.register_password_edittext);
        regConfirmPassword = (EditText) findViewById(R.id.register_confirm_password_edittext);
        regAddress = (EditText) findViewById(R.id.register_address_edittext);

        loginLink = (TextView) this.findViewById(R.id.link_login_textview);
        loginLink.setMovementMethod(LinkMovementMethod.getInstance());
        Spannable spans = (Spannable) loginLink.getText();
        ClickableSpan clickSpan = new ClickableSpan() {
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        };
        spans.setSpan(clickSpan, 0, spans.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        btnRegister = (Button) findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullname = regFullname.getText().toString();
                String username = regUsername.getText().toString();
                String password = regPassword.getText().toString();
                String confirmPassword =
                        regConfirmPassword.getText().toString();
                String address = regAddress.getText().toString();

                if(fullname.equals("") || username.equals("")
                        || password.equals("") || confirmPassword.equals("")
                        || address.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "There are incomplete fields!",
                            Toast.LENGTH_LONG).show();
                }
                if(!password.equals(confirmPassword)) {
                    Toast.makeText(getApplicationContext(),
                            "Password does not match.",
                            Toast.LENGTH_LONG).show();
                }
                if(!registrationAdapter.getSingleEntry(username).equals("NOT EXIST")) {
                    Toast.makeText(getApplicationContext(),
                            "Username already exists!",
                            Toast.LENGTH_LONG).show();
                } else {
                    registrationAdapter.insertEntry(fullname,username,password,address);
                    Toast.makeText(getApplicationContext(),
                            "Account successfully created!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        registrationAdapter.close();
    }
}
