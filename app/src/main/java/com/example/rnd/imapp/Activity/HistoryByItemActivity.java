package com.example.rnd.imapp.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.adapter.SingleCheckListAdapter;
import com.example.rnd.imapp.model.Barang;
import com.example.rnd.imapp.model.Orders;
import com.example.rnd.imapp.model.StockOpname;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HistoryByItemActivity extends AppCompatActivity {
    private ListView checkListView;
    DatabaseReference barangRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/stockopname");

    private ArrayList<Barang> barangArrayList;
    private String nama_barang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_by_item);

        checkListView = (ListView) findViewById(R.id.checkBarangListView);

        barangArrayList = new ArrayList<>();

        // Sample data
        Barang barang = new Barang("STRUK THERMAL PAPER EDC");
        barangArrayList.add(barang);
        barang = new Barang("CEK PERSONALISASI (BLANKO) C/F");
        barangArrayList.add(barang);
        barang = new Barang("GIRO PERSONALISASI (BLANKO)C/F");
        barangArrayList.add(barang);
        barang = new Barang("BUKU TAHAPAN BCA");
        barangArrayList.add(barang);
        barang = new Barang("BUKU TAHAPAN BCA (NEW)");
        barangArrayList.add(barang);
        barang = new Barang("BUKU TAHAPAN BCA GOLD (HVS)");
        barangArrayList.add(barang);
        barang = new Barang("BUKU TAHAPAN BCA GOLD (NEW)");
        barangArrayList.add(barang);
        barang = new Barang("GIRO-NON PERSONALISASI(BUKU)");
        barangArrayList.add(barang);
        barang = new Barang("DEPOSITO BERJANGKA");
        barangArrayList.add(barang);
        barang = new Barang("PAPER ATM NCR 5670 (7,9X21CM)");
        barangArrayList.add(barang);
        barang = new Barang("PAPER ATM NON TUNAI(7,9X18 CM)");
        barangArrayList.add(barang);
        barang = new Barang("PAPER ATM DIAMETER KECIL");
        barangArrayList.add(barang);
        barang = new Barang("AMPLOP COKLAT KECIL-ALMT WSA2");
        barangArrayList.add(barang);
        barang = new Barang("AMPLOP COKLAT BESAR-ALMT WSA2");
        barangArrayList.add(barang);
        barang = new Barang("AMPLOP COKLAT KECIL-ALMT GI");
        barangArrayList.add(barang);
        barang = new Barang("AMPLOP COKLAT BESAR-ALMT GI");
        barangArrayList.add(barang);
        barang = new Barang("BOX ARSIP BCA");
        barangArrayList.add(barang);

        SingleCheckListAdapter adapter = new SingleCheckListAdapter(this, R.layout.item_listview, barangArrayList);
        checkListView.setAdapter(adapter);

        checkListView.setOnItemClickListener(onItemClickListener());

        Button btnSelesai = (Button) findViewById(R.id.displayGraphicButton);
        btnSelesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryByItemActivity.this, DisplayGraphicsActivity.class);
                intent.putExtra("NAMA_BARANG", nama_barang);
                startActivity(intent);
            }
        });
    }

    private AdapterView.OnItemClickListener onItemClickListener() {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Dialog dialog = new Dialog(HistoryByItemActivity.this);
                dialog.setContentView(R.layout.layout_dialog);
                dialog.setTitle("Friend Details");
                dialog.setCancelable(true);

                TextView friendID = (TextView) dialog.findViewById(R.id.position);
                TextView friendName = (TextView) dialog.findViewById(R.id.name);

                friendID.setText("Position: " + (position + 1));
                friendName.setText("Name: " + ((Barang) parent.getItemAtPosition(position)).getNama_barang());
                nama_barang = ((Barang) parent.getItemAtPosition(position)).getNama_barang();
                dialog.show();
            }
        };
    }

}
