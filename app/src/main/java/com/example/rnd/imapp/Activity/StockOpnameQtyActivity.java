package com.example.rnd.imapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.Barang;
import com.example.rnd.imapp.model.Rerata;
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
    private Button btnRescan,btnCalculate;
    private StockOpname[] stockArray = new StockOpname[27];

    // Firebase database reference
    DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/stockopname");
    DatabaseReference myAverageRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/average");
    DatabaseReference myBarangRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/barang");
    DatabaseReference myOrderRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/orders");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_opname_qty);

        Intent intent = getIntent();
        sisaStok = intent.getStringExtra("QUANTITY");
        kodeBarang = intent.getStringExtra("KODE_BARANG");

        listViewQty = (ListView) findViewById(R.id.qtyListView);

        if (kodeBarang.equals("CCC.901/15"))
            childBarang = "barang1";
        if (kodeBarang.equals("IDS-206/11"))
            childBarang = "barang2";
        if (kodeBarang.equals("IDS-208/11"))
            childBarang = "barang3";
        if (kodeBarang.equals("IDS.134/99"))
            childBarang = "barang6";
        if (kodeBarang.equals("IDS.203/12"))
            childBarang = "barang7";

        //Log.i("Nama child: ", childBarang);

        DatabaseReference barangRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/stockopname");
        DatabaseReference myQuantityRef = myRootRef.child(childBarang).child("quantity");

        myQuantityRef.setValue(sisaStok);

        // Read from the stock opname database
        myRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                int i = 0;

                for (DataSnapshot soSnapshot: dataSnapshot.getChildren()) {
                    StockOpname soRekap = soSnapshot.getValue(StockOpname.class);
                    //Log.i("Nama Barang: ", soRekap.getNama_barang()+ "Qty:  "+soRekap.getQuantity());

                    i++;
                    stockArray[i] = new StockOpname();
                    stockArray[i].setId_jenis_barang(soRekap.getId_jenis_barang());
                    stockArray[i].setNama_barang(soRekap.getNama_barang());
                    stockArray[i].setKode_barang(soRekap.getKode_barang());
                    stockArray[i].setQuantity(soRekap.getQuantity());
                    //Log.i("Quantity: ", stockArray[i].getQuantity());
                }
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

                /*if (soReview.getQuantity().equals("0")) {
                    ((TextView)v.findViewById(R.id.nama_barang_text)).setTextColor(Color.parseColor("#C62828"));
                    ((TextView)v.findViewById(R.id.qty_text)).setTextColor(Color.parseColor("#C62828"));
                }*/
            }
        };

        listViewQty.setAdapter(fireSisaStokList);

        btnRescan = (Button) findViewById(R.id.rescanButton);
        btnCalculate = (Button) findViewById(R.id.calculateButton);

        btnRescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToScanIntent = new Intent(StockOpnameQtyActivity.this, ScanStockOpnameActivity.class);
                startActivity(backToScanIntent);
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(StockOpnameQtyActivity.this);
                builder.setTitle("Konfirmasi Order")
                    .setMessage("Apakah Anda yakin ingin melakukan order sesuai stock opname yang telah Anda masukkan?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                // CALCULATION
                                // Read from the average database
                                myAverageRef.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        int i = 0;
                                        double qty_order = 0;

                                        for (DataSnapshot avgSnapshot: dataSnapshot.getChildren()) {
                                            Rerata rerataRekap = avgSnapshot.getValue(Rerata.class);

                                            i++;
                                            if (rerataRekap.getKode_barang().equals(stockArray[i].getKode_barang())) {
                                                qty_order = (((8.0 / 3.0)*Double.parseDouble(rerataRekap.getrerata())) - Integer.parseInt(stockArray[i].getQuantity()));

                                                if (qty_order < 0){
                                                    qty_order = 0;
                                                }
                                                System.out.println(qty_order);

                                                DatabaseReference mySCMOrder = myOrderRef.child("SCM");
                                                mySCMOrder.push().setValue(stockArray[i]);

                                                // WRITE TO DATABASE
                                                /*myBarangRef.addValueEventListener(new ValueEventListener() {
                                                    @Override
                                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                                        int j = 0;
                                                        DatabaseReference mySCMOrder = myOrderRef.child("SCM");
                                                        DatabaseReference myVMIOrder = myOrderRef.child("VMI");


                                                        for (DataSnapshot barangSnapshot : dataSnapshot.getChildren()) {
                                                            Barang barangRekap = barangSnapshot.getValue(Barang.class);

                                                            for (int i = 0; i < 27; i++) {
                                                                System.out.println(stockArray[i].getKode_barang());
                                                            }
                                                        }
                                                    }

                                                    @Override
                                                    public void onCancelled(DatabaseError databaseError) {

                                                    }
                                                });*/
                                            }
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError error) {
                                        // Failed to read value
                                        Log.w("InputQTYAct", "Failed to read value.", error.toException());
                                    }
                                });
                                // Back to home and display last orders
                                Intent backToHomeIntent = new Intent(StockOpnameQtyActivity.this, ViewPagerActivity.class);
                                startActivity(backToHomeIntent);
                            }
                        })
                    .setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                builder.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
}
