package com.example.cmsc191.projectupa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
    }

    public void homeClick(View view ){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void payableClick(View view){
        Intent intent = new Intent(this, UnitListActivity.class);
        startActivity(intent);
    }
}
