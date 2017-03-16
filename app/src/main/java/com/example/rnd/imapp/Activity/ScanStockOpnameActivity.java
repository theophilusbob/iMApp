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
        //mScannerView.setFlash(true);
        contentFrame.addView(mScannerView);

        Intent intent = getIntent();
        sisaStok = intent.getStringExtra("QUANTITY");
        kodeBarang = intent.getStringExtra("KODE_BARANG");

        if (kodeBarang != null && !kodeBarang.isEmpty()) {
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
        mScannerView.setFlash(true);
    }

    @Override
    public void onPause() {
        super.onPause();
        mScannerView.stopCamera();
        mScannerView.setFlash(false);
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
