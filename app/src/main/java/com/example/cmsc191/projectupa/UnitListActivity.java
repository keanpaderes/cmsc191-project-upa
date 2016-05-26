package com.example.cmsc191.projectupa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Crissa on 5/26/2016.
 */
public class UnitListActivity extends AppCompatActivity{
    UserDatabaseAdapter unitListAdapter;
    Button btnAddUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_list);

        btnAddUnit = (Button) findViewById(R.id.add_unit_button);
        btnAddUnit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), AddUnitFormActivity.class);
                //i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                //i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });
    }
}
