package com.example.cmsc191.projectupa;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Crissa on 5/26/2016.
 */
public class AddUnitFormActivity extends AppCompatActivity {
    SessionManager session;
    UserDatabaseAdapter addUnitFormAdapter;
    EditText addCodename, addTenants, addWaterBill, addElectricBill, addRent;
    Button submitBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_add_form);

        session = new SessionManager(getApplicationContext());

        addUnitFormAdapter = new UserDatabaseAdapter(this);
        addUnitFormAdapter = addUnitFormAdapter.open();

        addCodename = (EditText) findViewById(R.id.codename_editText);
        addTenants = (EditText) findViewById(R.id.tenants_editText);
        addWaterBill = (EditText) findViewById(R.id.waterbill_editText);
        addElectricBill = (EditText) findViewById(R.id.electricbill_editText);
        addRent = (EditText) findViewById(R.id.rent_editText);

        submitBtn = (Button) findViewById(R.id.addUnitSubmitbutton);
        submitBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String codename = addCodename.getText().toString();
                String tenants = addTenants.getText().toString();
                Float waterBill, electricBill, rent;

                try {
                    waterBill = Float.parseFloat(addWaterBill.getText().toString());
                }catch(NumberFormatException e){
                    waterBill = (float) 0;
                }

                try{
                    electricBill = Float.parseFloat(addElectricBill.getText().toString());
                }catch(NumberFormatException e){
                    electricBill = (float) 0;
                }

                try{
                    rent = Float.parseFloat(addRent.getText().toString());
                }catch(NumberFormatException e){
                    rent = (float) 0;
                }

                if(codename.equals("") || tenants.equals("")){
                    Toast.makeText(getApplicationContext(), "There are incomplete fields!", Toast.LENGTH_LONG).show();
                }else{
                    addUnitFormAdapter.insertUnitEntry(session.getCurrentUser(), codename, waterBill, electricBill, rent, tenants);
                    setContentView(R.layout.activity_unit_list);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addUnitFormAdapter.close();
    }
}
