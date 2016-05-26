package com.example.cmsc191.projectupa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    UserDatabaseAdapter loginAdapter;
    TextView registerLink;
    EditText logUsername, logPassword;
    Button btnLogin;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        session = new SessionManager(getApplicationContext());
        loginAdapter = new UserDatabaseAdapter(this);
        loginAdapter = loginAdapter.open();

        logUsername = (EditText) findViewById(R.id.register_username_edittext);
        logPassword = (EditText) findViewById(R.id.register_password_edittext);

        registerLink = (TextView) this.findViewById(R.id.link_register_textview);
        registerLink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin = (Button) findViewById(R.id.btn_register);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = logUsername.getText().toString();
                String password = logPassword.getText().toString();

                if(username.equals("") || password.equals("")) {
                    Toast.makeText(getApplicationContext(),
                            "There are incomplete fields!",
                            Toast.LENGTH_LONG).show();
                }
                String dbPassword = loginAdapter.getSingleEntry(username);
                if(dbPassword.equals("NOT EXIST")) {
                    Toast.makeText(getApplicationContext(),
                            "Username does not exist.",
                            Toast.LENGTH_LONG).show();
                } else {
                    if(!dbPassword.equals(password)){
                        Toast.makeText(getApplicationContext(),
                                "Wrong password!",
                                Toast.LENGTH_LONG).show();
                    }else {
                        session.createLoginSession(username);

                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();
                    }
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginAdapter.close();
    }
}
