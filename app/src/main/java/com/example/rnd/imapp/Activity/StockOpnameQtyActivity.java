package com.example.rnd.imapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.Rerata;
import com.example.rnd.imapp.model.StockOpname;
import com.example.rnd.imapp.model.User;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StockOpnameQtyActivity extends AppCompatActivity {
    private String sisaStok, kodeBarang, childBarang;
    public String nama_cabang;
    private ListView listViewQty;
    private Button btnRescan,btnCalculate;
    private StockOpname[] stockArray = new StockOpname[31];
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String getCurrentUser;
    private String kodeCabang = "0000";

    // Firebase database reference
    DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-443a6.firebaseio.com/stockopname");
    DatabaseReference myUserRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-443a6.firebaseio.com/users");
    DatabaseReference myAverageRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-443a6.firebaseio.com/average");
    DatabaseReference myOrderRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-443a6.firebaseio.com/orders");
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_opname_qty);

        // Get intent from previous activity
        Intent intent = getIntent();
        sisaStok = intent.getStringExtra("QUANTITY");
        kodeBarang = intent.getStringExtra("KODE_BARANG");
        listViewQty = (ListView) findViewById(R.id.qtyListView);

        // [START initialize_auth]
        mAuth = FirebaseAuth.getInstance();
        // [END initialize_auth]

        // [START auth_state_listener]
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in

                    getCurrentUser = user.getEmail();
                    Log.i("User email",getCurrentUser);

                    // Assign kode barang

                    if (kodeBarang.equals("CCC.901/15"))
                        childBarang = "barang1";
                    if (kodeBarang.equals("KK0048"))
                        childBarang = "barang2";
                    if (kodeBarang.equals("094-KK0051"))
                        childBarang = "barang3";
                    if (kodeBarang.equals("IDS.220/15"))
                        childBarang = "barang4";
                    if (kodeBarang.equals("IDS.221/15"))
                        childBarang = "barang5";
                    if (kodeBarang.equals("IDS.207/11"))
                        childBarang = "barang6";
                    if (kodeBarang.equals("IDS.209/11"))
                        childBarang = "barang7";
                    if (kodeBarang.equals("IDS.601/94"))
                        childBarang = "barang8";
                    if (kodeBarang.equals("IDS.226/10"))
                        childBarang = "barang9";
                    if (kodeBarang.equals("IDS.229/10"))
                        childBarang = "barang10";
                    if (kodeBarang.equals("IDS.401/94"))
                        childBarang = "barang11";
                    if (kodeBarang.equals("ITS.501/15"))
                        childBarang = "barang12";
                    if (kodeBarang.equals("IDS-206/11"))
                        childBarang = "barang13";
                    if (kodeBarang.equals("IDS-208/11"))
                        childBarang = "barang14";
                    if (kodeBarang.equals("UMM.742/07"))
                        childBarang = "barang15";
                    if (kodeBarang.equals("UMM.744/07"))
                        childBarang = "barang16";
                    if (kodeBarang.equals("UMM.749/07"))
                        childBarang = "barang17";
                    if (kodeBarang.equals("UMM.751/07"))
                        childBarang = "barang18";
                    if (kodeBarang.equals("UMM913A/06"))
                        childBarang = "barang19";
                    if (kodeBarang.equals("IDS.175/05"))
                        childBarang = "barang20";
                    if (kodeBarang.equals("IDS.176/05"))
                        childBarang = "barang21";
                    if (kodeBarang.equals("IDS.177/05"))
                        childBarang = "barang22";
                    if (kodeBarang.equals("IDS.178/05"))
                        childBarang = "barang23";
                    if (kodeBarang.equals("IDS.179/05"))
                        childBarang = "barang24";
                    if (kodeBarang.equals("IDS.202/11"))
                        childBarang = "barang25";
                    if (kodeBarang.equals("IDS.219/09"))
                        childBarang = "barang26";
                    if (kodeBarang.equals("IDS135A/01"))
                        childBarang = "barang27";

                    // Read & write to stock opname database
                    DatabaseReference myStockRef = myRootRef.child(cekKodeCabang());
                    DatabaseReference myQuantityRef = myStockRef.child(childBarang).child("quantity");
                    myQuantityRef.setValue(sisaStok);

                    Log.i("Kodecabang", kodeCabang);

                    // Method to store temporary stock opname data to be used by calculation fx
                    //insertToStockArray();


                } else {
                    // User is signed out
                    //Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        // Firebase List View displaying rekapan stok opname
        FirebaseListAdapter<StockOpname> fireSisaStokList = new FirebaseListAdapter<StockOpname>(
                this, StockOpname.class, R.layout.list_qty_stock_opname, myRootRef.child(cekKodeCabang())
        ) {
            @Override
            protected void populateView(View v, StockOpname soReview, int position) {

                if (soReview.getQuantity().equals("0")) {
                    ((TextView)v.findViewById(R.id.nama_barang_text)).setText(soReview.getNama_barang());
                    ((TextView)v.findViewById(R.id.nama_barang_text)).setTextColor(Color.parseColor("#C62828"));
                    ((TextView)v.findViewById(R.id.qty_text)).setText(soReview.getQuantity());
                    ((TextView)v.findViewById(R.id.qty_text)).setTextColor(Color.parseColor("#C62828"));
                }
                else {
                    ((TextView)v.findViewById(R.id.nama_barang_text)).setText(soReview.getNama_barang());
                    ((TextView)v.findViewById(R.id.nama_barang_text)).setTextColor(Color.parseColor("#000000"));
                    ((TextView)v.findViewById(R.id.qty_text)).setText(soReview.getQuantity());
                    ((TextView)v.findViewById(R.id.qty_text)).setTextColor(Color.parseColor("#000000"));
                }
            }
        };

        listViewQty.setAdapter(fireSisaStokList);
        // End of firebase list view

        btnRescan = (Button) findViewById(R.id.rescanButton);
        btnCalculate = (Button) findViewById(R.id.calculateButton);

        btnRescan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backToScanIntent = new Intent(StockOpnameQtyActivity.this, ScanStockOpnameActivity.class);
                backToScanIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(backToScanIntent);
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(StockOpnameQtyActivity.this);
                builder.setTitle("Konfirmasi order")
                    .setMessage("Apakah Anda yakin data stock opname yang telah Anda masukkan sudah benar?" )
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                // Calling calculation formula method
                                //calculationFormula();

                                // Back to home and display last orders

                                Intent backToHomeIntent = new Intent(StockOpnameQtyActivity.this, ViewPagerActivity.class);
                                backToHomeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
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

    public void insertToStockArray() {
        myRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                int i = 0;

                for (DataSnapshot soSnapshot: dataSnapshot.getChildren()) {
                    StockOpname soRekap = soSnapshot.getValue(StockOpname.class);

                    // Input data to array

                    stockArray[i] = new StockOpname();
                    stockArray[i].setNama_cabang(user.getEmail());
                    stockArray[i].setId_jenis_barang(soRekap.getId_jenis_barang());
                    stockArray[i].setNama_barang(soRekap.getNama_barang());
                    stockArray[i].setKode_barang(soRekap.getKode_barang());
                    stockArray[i].setQuantity(soRekap.getQuantity());
                    stockArray[i].setSatuan(soRekap.getSatuan());
                    i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("InputQTYAct", "Failed to read value.", error.toException());
            }
        });
    }

    public String cekKodeCabang() {
        if ( user.getEmail().equals("bna@imapp.com"))
            kodeCabang = "5270";
        if (  user.getEmail().equals("cdb@imapp.com"))
            kodeCabang = "0397";
        if (  user.getEmail().equals("kms@imapp.com"))
            kodeCabang = "5500";
        if (  user.getEmail().equals("kst@imapp.com"))
            kodeCabang = "5260";
        if (  user.getEmail().equals("mdr@imapp.com"))
            kodeCabang = "0398";
        if (  user.getEmail().equals("psp@imapp.com"))
            kodeCabang = "0229";
        if (  user.getEmail().equals("tmg@imapp.com"))
            kodeCabang = "0310";
        if (  user.getEmail().equals("wbp@imapp.com"))
            kodeCabang = "5435";
        if (  user.getEmail().equals("wsl@imapp.com"))
            kodeCabang = "0482";
        if (  user.getEmail().equals("wsa@imapp.com"))
            kodeCabang = "0084";

        return kodeCabang;
    }

    public void calculationFormula() {

        //Log.i("Kode Cabang",kodeCabang);

        DatabaseReference myCabangAvg = myAverageRef.child(cekKodeCabang());

        myCabangAvg.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int i = 0;
                double qty_order;

                for (DataSnapshot avgSnapshot: dataSnapshot.getChildren()) {
                    Rerata rerataRekap = avgSnapshot.getValue(Rerata.class);

                    if (rerataRekap.getKode_barang().equals(stockArray[i].getKode_barang())) {
                        qty_order = ((6*Double.parseDouble(rerataRekap.getrerata())) - Integer.parseInt(stockArray[i].getQuantity()));

                        if (qty_order < 0){
                            qty_order = 0;
                        }

                        stockArray[i].setQuantity(String.valueOf(Math.round(qty_order)));

                        DatabaseReference mySCMOrder = myOrderRef.child("SCM");
                        DatabaseReference myVMIOrder = myOrderRef.child("VMI");

                        if (stockArray[i].getId_jenis_barang().equals("1"))
                            mySCMOrder.push().setValue(stockArray[i]);
                        else
                            myVMIOrder.push().setValue(stockArray[i]);
                    }
                    i++;
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("InputQTYAct", "Failed to read value.", error.toException());
            }
        });
    }

    /*public String getNamaCabang() {
        // Retrieve nama cabang
        myUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                    User userValue= userSnapshot.getValue(User.class);
                    if (user != null) {
                        if (user.getEmail().equals(userValue.getEmail_cabang())){
                            // User is signed in
                            nama_cabang = userValue.getNama_cabang();
                        }
                    } else {
                        // No user is signed in
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        return nama_cabang;
    }*/

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }
}
