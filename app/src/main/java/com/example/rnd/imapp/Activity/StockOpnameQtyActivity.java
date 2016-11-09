package com.example.rnd.imapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.Orders;
import com.example.rnd.imapp.model.StockOpname;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StockOpnameQtyActivity extends AppCompatActivity {
    private TextView txtSample;
    private String sisaStok, kodeBarang, childBarang;
    private ListView listViewQty;
    DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/stockopname");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_opname_qty);

        Intent intent = getIntent();
        sisaStok = intent.getStringExtra("QUANTITY");
        kodeBarang = intent.getStringExtra("KODE_BARANG");

        txtSample = (TextView) findViewById(R.id.sampleTxt);
        listViewQty = (ListView) findViewById(R.id.qtyListView);

        if (kodeBarang.equals("CCC.901/15"))
            childBarang = "barang1";
        if (kodeBarang.equals("IDS-206/11"))
            childBarang = "barang2";
        if (kodeBarang.equals("IDS-208/11"))
            childBarang = "barang3";

        Log.i("Nama child: ", childBarang);

        DatabaseReference barangRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/stockopname");
        DatabaseReference myQuantityRef = myRootRef.child(childBarang).child("quantity");
        //FromUrl("https://imapp-99a05.firebaseio.com/barang");
        //StockOpname so = new StockOpname("IDS.208","GIRO PERSONALISASI (BLANKO) C/","0");
        //barangRef.push().setValue(so);

        myQuantityRef.setValue(sisaStok);

        // Read from the database
        myRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot soSnapshot: dataSnapshot.getChildren()) {
                    StockOpname soRekap = soSnapshot.getValue(StockOpname.class);
                    Log.i("Nama Barang: ", soRekap.getNama_barang()+ "Qty:  "+soRekap.getQuantity());
                }

               /* StockOpname value = dataSnapshot.getValue(StockOpname.class);
                //txtSample.setText(value.getNama_barang());
                Log.d("InputQTYAct", "Value is: " + value.getNama_barang());*/
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("InputQTYAct", "Failed to read value.", error.toException());
            }
        });



        FirebaseListAdapter<StockOpname> fireSisaStokList = new FirebaseListAdapter<StockOpname>(
                this, StockOpname.class, R.layout.list_qty_stock_opname, barangRef
        ) {
            @Override
            protected void populateView(View v, StockOpname soReview, int position) {
                ((TextView)v.findViewById(R.id.nama_barang_text)).setText(soReview.getNama_barang());
                ((TextView)v.findViewById(R.id.qty_text)).setText(soReview.getQuantity());
            }
        };

        listViewQty.setAdapter(fireSisaStokList);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
