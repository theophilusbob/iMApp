package com.example.rnd.imapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.HistoryOrder;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryPeriodsActivity extends AppCompatActivity {
    protected ListView listHistoryPeriods;

    // Firebase database references
    DatabaseReference myHistoryByPeriodRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/orders");
    DatabaseReference mySCMHistory = myHistoryByPeriodRef.child("SCM");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_periods);

        listHistoryPeriods = (ListView) findViewById(R.id.historyPeriodsList);

        FirebaseListAdapter<HistoryOrder> historyByOrderList = new FirebaseListAdapter<HistoryOrder>(
                this, HistoryOrder.class, R.layout.list_row, mySCMHistory
        ) {
            @Override
            protected void populateView(View v, HistoryOrder historyValue, int position) {
                ((TextView)v.findViewById(R.id.nama_barang)).setText(historyValue.getNama_barang());
                ((TextView)v.findViewById(R.id.kode_barang)).setText(historyValue.getKode_barang());
                ((TextView)v.findViewById(R.id.qty)).setText(historyValue.getQuantity());
                ((TextView)v.findViewById(R.id.satuan_pack)).setText(historyValue.getSatuan());
            }
        };

        listHistoryPeriods.setAdapter(historyByOrderList);
    }
}
