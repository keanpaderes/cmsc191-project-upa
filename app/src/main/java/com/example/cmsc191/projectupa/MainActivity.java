package com.example.cmsc191.projectupa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SessionManager session;
    Button btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        session = new SessionManager(getApplicationContext());

        // Button logout
        btnLogout = (Button) findViewById(R.id.btn_logout);

        //Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        /**
         * Call this function whenever you want to check user login
         * This will redirect user to LoginActivity is he is not
         * logged in
         * */
        if(!session.isLoggedIn()){
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        else{
            setContentView(R.layout.activity_main);
        }

        // get user data from session
//        HashMap<String, String> user = session.getUserDetails();
//
//        // name
//        String name = user.get(SessionManager.KEY_NAME);
//
//        // displaying user data
//        lblName.setText(Html.fromHtml("Name: <b>" + name + "</b>"));

        /**
         * Logout button click event
         * */
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Clear the session data
                // This will clear all session data and
                // redirect user to LoginActivity
                session.logoutUser();
            }
        });


    }

    //onBackPressed
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
        System.exit(0);
    }

    public void profileClick(View view ){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void payableClick(View view){
        Intent intent = new Intent(this, UnitListActivity.class);
        startActivity(intent);
    }
}