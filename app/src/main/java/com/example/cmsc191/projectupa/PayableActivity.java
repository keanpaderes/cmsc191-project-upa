package com.example.cmsc191.projectupa;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Crissa on 5/27/2016.
 */
public class PayableActivity extends AppCompatActivity {
    //int unitIndex = (Integer) getIntent().getExtras().get("index");
    public static final String EXTRA_POSITION = "positionNo";
    UserDatabaseAdapter payableAdapter;
    ArrayList<String> unitDetails = new ArrayList<String>();
    TextView unitName, tenants, rent, ebill, wbill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payable);

        payableAdapter = new UserDatabaseAdapter(this);
        payableAdapter = payableAdapter.open();

        int positionNo = (Integer) getIntent().getExtras().get(EXTRA_POSITION);
        unitDetails = payableAdapter.getUnitDetails(positionNo+1);

        unitName = (TextView) findViewById(R.id.list_unit);
        unitName.setText(unitDetails.get(0));

        tenants = (TextView) findViewById(R.id.tenants_textview);
        tenants.setText(unitDetails.get(4));

        rent = (TextView) findViewById(R.id.amount_rent_textview);
        rent.setText(unitDetails.get(3));

        ebill = (TextView) findViewById(R.id.amount_electricity_textview);
        ebill.setText(unitDetails.get(2));

        wbill = (TextView) findViewById(R.id.amount_water_textview);
        wbill.setText(unitDetails.get(1));

    }

}
