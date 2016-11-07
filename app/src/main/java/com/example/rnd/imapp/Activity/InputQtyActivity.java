package com.example.rnd.imapp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.rnd.imapp.R;
import com.example.rnd.imapp.app.AppController;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Objects;

public class InputQtyActivity extends AppCompatActivity {
    private ImageLoader imageLoader;
    private NetworkImageView networkImageView;
    private String imgUrl;
    private Button btnLanjut, btnSelesai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_qty);

        Intent intent = getIntent();
        String kode_barang = intent.getStringExtra("KODE_BARANG");
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
            imgUrl = "http://192.168.1.117/imapp_api/foto_barang/img_1.png";
        }
        if (kode_barang.equals("IDS-206/11")) {
            imgUrl = "http://192.168.1.117/imapp_api/foto_barang/img_2.png";
        }
        if (kode_barang.equals("IDS-208/11")) {
            imgUrl = "http://192.168.1.117/imapp_api/foto_barang/img_3.png";
        }
        networkImageView.setImageUrl(imgUrl, imageLoader);

        btnLanjut = (Button) findViewById(R.id.input_qty_next_button);
        btnSelesai = (Button) findViewById(R.id.input_qty_done_button);

        btnLanjut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                fm.popBackStack();
            }
        });

    }
}
