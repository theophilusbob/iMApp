package com.example.rnd.imapp.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.rnd.imapp.R;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class ScanAckActivity extends BaseScannerActivity implements ZBarScannerView.ResultHandler {
    private ZBarScannerView mScannerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_ack);

        ViewGroup contentFrameACK = (ViewGroup) findViewById(R.id.content_frame_ack);
        mScannerView = new ZBarScannerView(this);
        contentFrameACK.addView(mScannerView);
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
        Toast.makeText(this, "Kode Surat Jalan = " + rawResult.getContents() +
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
                Intent intent = new Intent(ScanAckActivity.this, AckActivity.class);
                intent.putExtra("KODE_SURAT_JALAN", rawResult.getContents());
                startActivity(intent);
            }
        }, 2000);
    }
}
