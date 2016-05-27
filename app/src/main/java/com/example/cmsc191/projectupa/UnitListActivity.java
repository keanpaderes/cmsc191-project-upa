package com.example.cmsc191.projectupa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Crissa on 5/26/2016.
 */
public class UnitListActivity extends AppCompatActivity{
    UserDatabaseAdapter unitListAdapter;
    Button btnAddUnit;
    ArrayList<String> unitItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    SessionManager session;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_list);

        unitListAdapter = new UserDatabaseAdapter(this);
        unitListAdapter = unitListAdapter.open();
        //unitItems = unitListAdapter.selectAll(session.getCurrentUser());
        unitItems = unitListAdapter.selectAll();

        ListView listview = (ListView) findViewById(R.id.units_listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, unitItems);
        listview.setAdapter(adapter);

        AdapterView.OnItemClickListener itemClickListener =
                new AdapterView.OnItemClickListener(){
                  public void onItemClick(AdapterView<?> listView, View v, int position, long id){
                      Intent i = new Intent(getApplicationContext(), PayableActivity.class);
                      i.putExtra(PayableActivity.EXTRA_POSITION, position);
                      startActivity(i);
                  }
                };

        listview.setOnItemClickListener(itemClickListener);

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

    /*public void onListItemClick(ListView listview, View itemView, int position, long id) {
        Intent intent = new Intent(getApplicationContext(), PayableActivity.class);
        startActivity(intent);
    }*/

    public void profileClick(View view ){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    public void homeClick(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
