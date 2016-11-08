package com.example.rnd.imapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rnd.imapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StockOpnameQtyActivity extends AppCompatActivity {
    private TextView txtSample;
    private String sisaStok;
    private ListView listViewQty;
    DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference myQuantityRef = myRootRef.child("Barang");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_opname_qty);

        Intent intent = getIntent();
        sisaStok = intent.getStringExtra("QUANTITY");

        txtSample = (TextView) findViewById(R.id.sampleTxt);
        listViewQty = (ListView) findViewById(R.id.qtyListView);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/Barang");
    }

    @Override
    protected void onStart() {
        super.onStart();
        myQuantityRef.setValue(sisaStok);

        // Read from the database
        myQuantityRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                txtSample.setText(value);
                Log.d("InputQTYAct", "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("InputQTYAct", "Failed to read value.", error.toException());
            }
        });
    }
}
