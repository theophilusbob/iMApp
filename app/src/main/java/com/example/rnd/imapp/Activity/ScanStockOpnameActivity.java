package com.example.rnd.imapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rnd.imapp.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanStockOpnameActivity extends BaseScannerActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;
    private String sisaStok, kodeBarang, childBarang;
    DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/stockopname");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scan_stock_opname);

        ViewGroup contentFrame = (ViewGroup) findViewById(R.id.content_frame);
        mScannerView = new ZBarScannerView(this);
        contentFrame.addView(mScannerView);

        Intent intent = getIntent();
        sisaStok = intent.getStringExtra("QUANTITY");
        kodeBarang = intent.getStringExtra("KODE_BARANG");

        if (kodeBarang != null && !kodeBarang.isEmpty()) {
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

            DatabaseReference myQuantityRef = myRootRef.child(childBarang).child("quantity");

            if ( sisaStok == null && sisaStok.isEmpty()) {
                myQuantityRef.setValue("0");
            }
            else {
                myQuantityRef.setValue(sisaStok);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult(final Result rawResult) {
        Toast.makeText(this, "Kode Barang = " + rawResult.getContents() +
                ", Format = " + rawResult.getBarcodeFormat().getName(), Toast.LENGTH_SHORT).show();
        // Note:
        // * Wait 2 seconds to resume the preview.
        // * On older devices continuously stopping and resuming camera preview can result in freezing the app.
        // * I don't know why this is the case but I don't have the time to figure out.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mScannerView.stopCameraPreview();
                //mScannerView.resumeCameraPreview(SOFragment.this);
                Intent intent = new Intent(ScanStockOpnameActivity.this, InputQtyActivity.class);

                intent.putExtra("KODE_BARANG", rawResult.getContents());
                startActivity(intent);
            }
        }, 2000);
    }
}
