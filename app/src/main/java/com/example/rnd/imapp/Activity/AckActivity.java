package com.example.rnd.imapp.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.adapter.ACKListAdapter;
import com.example.rnd.imapp.model.ACK;

import java.util.ArrayList;

public class AckActivity extends AppCompatActivity {
    private ACKListAdapter ackListAdapter = null;
    private ArrayList<ACK> ackList = new ArrayList<ACK>();
    private Button btnKonfirmasiACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ack);

        // Sample data
        ACK ack = new ACK("2","CCC.901/15", "Struk Thermal Paper EDC", "PAK", false);
        ackList.add(ack);
        ack = new ACK("0","IDS.134/99", "Buku Tahapan BCA", "PAK", false);
        ackList.add(ack);
        ack = new ACK("4","IDS.203/12", "Buku Tahapan Gold", "PAK", false);
        ackList.add(ack);

        ackListAdapter = new ACKListAdapter(this, R.layout.ack_list, ackList);
        ListView ackListView = (ListView) findViewById(R.id.ackListView);
        ackListView.setAdapter(ackListAdapter);

        ackListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ACK ack = (ACK) adapterView.getItemAtPosition(i);
                Toast.makeText(getApplicationContext(), "Barang yang dipilih: " + ack.getNama_barang(), Toast.LENGTH_LONG).show();
            }
        });

        btnKonfirmasiACK = (Button) findViewById(R.id.konfirmasiACKButton);

        btnKonfirmasiACK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(AckActivity.this);
                builder.setTitle("Konfirmasi ACK")
                        .setMessage("Apakah Anda yakin untuk melakukan ACK?")
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // Back to home and display last orders
                                Intent backToHomeIntent = new Intent(AckActivity.this, ViewPagerActivity.class);
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
}
