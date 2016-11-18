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
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.rnd.imapp.R;
import com.example.rnd.imapp.app.AppController;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

public class InputQtyActivity extends AppCompatActivity {
    private ImageLoader imageLoader;
    private NetworkImageView networkImageView;
    private String imgUrl;
    private Button btnLanjut, btnSelesai;
    private EditText fieldQty;
    private StorageReference mStorageRef;

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_qty);

        Intent intent = getIntent();
        final String kode_barang = intent.getStringExtra("KODE_BARANG");
        Log.i("Kode Barang", kode_barang);

       /* Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority("192.168.1.117")
                .appendPath("imapp_api")
                .appendPath("getImageUrl.php")
                .appendQueryParameter("kodeBarang",kode_barang.toString());
        String imageUrl = builder.build().toString();

        Log.i("API url:", imageUrl);
*/
        networkImageView = (NetworkImageView) findViewById(R.id.productImageContainer);

        imageLoader = AppController.getInstance().getImageLoader();

        if (kode_barang.equals("CCC.901/15")) {
            imgUrl = "https://firebasestorage.googleapis.com/v0/b/imapp-99a05.appspot.com/o/foto_barang%2Fimg_1.png?alt=media&token=726488f5-ab1b-4837-ad71-71189e8c782f";
        }
        if (kode_barang.equals("IDS.134/99")) {
            imgUrl = "https://firebasestorage.googleapis.com/v0/b/imapp-99a05.appspot.com/o/foto_barang%2Fimg_6.png?alt=media&token=bfb049c5-31e2-4383-8f28-42ebcbe399e0";
        }
        if (kode_barang.equals("IDS-208/11")) {
            imgUrl = "http://192.168.1.117/imapp_api/foto_barang/img_3.png";
        }
        if (kode_barang.equals("IDS.203/12")) {
            imgUrl = "https://firebasestorage.googleapis.com/v0/b/imapp-99a05.appspot.com/o/foto_barang%2Fimg_7.png?alt=media&token=e1e8cda2-5b7e-4734-a7db-81582ef1797f";
        }

        /*// Firebase Storage
        mStorageRef = FirebaseStorage.getInstance().getReference();

        File localFile = null;
        try {
            localFile = File.createTempFile("images", "jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }
        mStorageRef.getFile(localFile)
                .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                        // Successfully downloaded data to local file
                        // ...
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle failed download
                // ...
            }
        });*/

        networkImageView.setImageUrl(imgUrl, imageLoader);

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
