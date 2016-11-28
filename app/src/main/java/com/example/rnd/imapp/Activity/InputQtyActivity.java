package com.example.rnd.imapp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.bumptech.glide.Glide;
import com.example.rnd.imapp.R;
import com.example.rnd.imapp.app.AppController;
import com.example.rnd.imapp.model.Barang;
import com.example.rnd.imapp.model.StockData;
import com.example.rnd.imapp.util.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class InputQtyActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView txtSatuan;
    private String imgChild;
    private Button btnLanjut, btnSelesai;
    private EditText fieldQty;

    DatabaseReference myStockDataRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com");
    DatabaseReference myBarangRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/barang");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_qty);

        Intent intent = getIntent();
        final String kode_barang = intent.getStringExtra("KODE_BARANG");

        imageView = (ImageView) findViewById(R.id.productImageContainer);
        txtSatuan = (TextView) findViewById(R.id.satuanTextView);

        StorageReference storageReference = FirebaseStorage.getInstance().getReferenceFromUrl("gs://imapp-99a05.appspot.com/");

        if (kode_barang.equals("CCC.901/15")) {
            imgChild = "foto_barang/img_1.png";
        }
        if (kode_barang.equals("IDS.134/99")) {
            imgChild = "foto_barang/img_6.png";
        }
        if (kode_barang.equals("IDS-208/11")) {
            imgChild = "foto_barang/img_3.png";
        }
        if (kode_barang.equals("IDS.203/12")) {
            imgChild = "foto_barang/img_7.png";
        }

        StorageReference imageRef = storageReference.child(imgChild);

        Glide.with(this)
                .using(new FirebaseImageLoader())
                .load(imageRef)
                .into(imageView);


        myBarangRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot barangSnapshot: dataSnapshot.getChildren()) {
                    Barang barangValue= barangSnapshot.getValue(Barang.class);

                    if (kode_barang.equals(barangValue.getKode_barang()))
                        txtSatuan.setText(barangValue.getSatuan());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        btnLanjut = (Button) findViewById(R.id.input_qty_next_button);
        btnSelesai = (Button) findViewById(R.id.input_qty_done_button);
        fieldQty = (EditText) findViewById(R.id.qtyField);

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(InputQtyActivity.this, "Kode Barang: " + kode_barang + "Qty: " +fieldQty.getText().toString(), Toast.LENGTH_SHORT).show();
                Intent backToScanIntent = new Intent(InputQtyActivity.this, ScanStockOpnameActivity.class);
                backToScanIntent.putExtra("QUANTITY",fieldQty.getText().toString());
                backToScanIntent.putExtra("KODE_BARANG", kode_barang);
                startActivity(backToScanIntent);

            }
        });

        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InputQtyActivity.this, StockOpnameQtyActivity.class);
                intent.putExtra("QUANTITY",fieldQty.getText().toString());
                intent.putExtra("KODE_BARANG", kode_barang);
                startActivity(intent);
            }
        });

        Log.i("Path: ", getApplicationInfo().dataDir);
    }
}
