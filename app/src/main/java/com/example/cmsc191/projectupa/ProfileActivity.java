package com.example.cmsc191.projectupa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    EditText fullname;
    EditText address;
    EditText username;
    EditText password;
    EditText repassword;

    UserDatabaseAdapter uda;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        uda = new UserDatabaseAdapter(this);
        session = new SessionManager(getApplicationContext());
        String user =session.getCurrentUser();

        uda = uda.open();
        String userEntries[] = uda.getUserEntries(user);
        uda.close();

        fullname = (EditText) findViewById(R.id.prof_fullname);
        address = (EditText) findViewById(R.id.prof_addr);
        username = (EditText) findViewById(R.id.prof_username);
        password = (EditText) findViewById(R.id.prof_pass);
        repassword = (EditText) findViewById(R.id.prof_repass);

        fullname.setText(userEntries[0]);
        address.setText(userEntries[1]);
        username.setText(userEntries[2]);
        password.setText(userEntries[3]);
        repassword.setText(userEntries[3]);
    }

    public void homeClick(View view ){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void payableClick(View view){
        Intent intent = new Intent(this, UnitListActivity.class);
        startActivity(intent);
    }

    public void updateProfile(View view){
        String fname = fullname.getText().toString();
        String addr = address.getText().toString();
        String uname = username.getText().toString();
        String pass = password.getText().toString();
        String repass = repassword.getText().toString();

        if(pass.compareTo(repass) == 0){
            String user =session.getCurrentUser();

            uda = uda.open();
            uda.updateEntry(user, uname, pass, addr, fname);
            uda.close();

            session.updateCurrentUser(uname);
        }else{
            Toast.makeText(getApplicationContext(),
                    "The passwords you typed do not match!",
                    Toast.LENGTH_LONG).show();
        }
    }
}
