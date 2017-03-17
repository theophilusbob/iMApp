package com.example.rnd.imapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
            else if (kodeBarang.equals("KK0048"))
                childBarang = "barang2";
            else if (kodeBarang.equals("094-KK0051"))
                childBarang = "barang3";
            else if (kodeBarang.equals("IDS.220/15"))
                childBarang = "barang4";
            else if (kodeBarang.equals("IDS.221/15"))
                childBarang = "barang5";
            else if (kodeBarang.equals("IDS.207/11"))
                childBarang = "barang6";
            else if (kodeBarang.equals("IDS.209/11"))
                childBarang = "barang7";
            else if (kodeBarang.equals("IDS.601/94"))
                childBarang = "barang8";
            else if (kodeBarang.equals("IDS.226/10"))
                childBarang = "barang9";
            else if (kodeBarang.equals("IDS.229/10"))
                childBarang = "barang10";
            else if (kodeBarang.equals("IDS.401/94"))
                childBarang = "barang11";
            else if (kodeBarang.equals("ITS.501/15"))
                childBarang = "barang12";
            else if (kodeBarang.equals("IDS-206/11"))
                childBarang = "barang13";
            else if (kodeBarang.equals("IDS-208/11"))
                childBarang = "barang14";
            else if (kodeBarang.equals("UMM.742/07"))
                childBarang = "barang15";
            else if (kodeBarang.equals("UMM.744/07"))
                childBarang = "barang16";
            else if (kodeBarang.equals("UMM.749/07"))
                childBarang = "barang17";
            else if (kodeBarang.equals("UMM.751/07"))
                childBarang = "barang18";
            else if (kodeBarang.equals("UMM913A/06"))
                childBarang = "barang19";
            else if (kodeBarang.equals("IDS.175/05"))
                childBarang = "barang20";
            else if (kodeBarang.equals("IDS.176/05"))
                childBarang = "barang21";
            else if (kodeBarang.equals("IDS.177/05"))
                childBarang = "barang22";
            else if (kodeBarang.equals("IDS.178/05"))
                childBarang = "barang23";
            else if (kodeBarang.equals("IDS.179/05"))
                childBarang = "barang24";
            else if (kodeBarang.equals("IDS.202/11"))
                childBarang = "barang25";
            else if (kodeBarang.equals("IDS.219/09"))
                childBarang = "barang26";
            else if (kodeBarang.equals("IDS135A/01"))
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

                /*if (childBarang.equals("unknownItem")) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ScanStockOpnameActivity.this);
                    builder.setMessage("Barcode tidak ditemukan. Silahkan melakukan scan ulang.")
                            .setCancelable(false)
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //do things
                                    dialog.dismiss();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }*/
                //else {
                    intent.putExtra("KODE_BARANG", rawResult.getContents());
                    startActivity(intent);
                    finish();
                //}
            }
        }, 2000);
    }
}
