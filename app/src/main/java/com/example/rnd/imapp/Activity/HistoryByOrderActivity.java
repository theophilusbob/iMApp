package com.example.rnd.imapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.HistoryOrder;
import com.example.rnd.imapp.model.StockOpname;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryByOrderActivity extends AppCompatActivity {
    private ListView listHistoryPeriods;
    DatabaseReference historyRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/historybyorder");
    DatabaseReference historyOneYearRef = historyRef.child("1tahun");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_by_order);
/*
        listHistoryPeriods = (ListView) findViewById(R.id.historyPeriodsList);

        FirebaseListAdapter<HistoryOrder> fireHistoryList = new FirebaseListAdapter<HistoryOrder>(
                this, HistoryOrder.class, R.layout.list_qty_stock_opname, historyOneYearRef
        ) {
            @Override
            protected void populateView(View v, HistoryOrder hoReview, int position) {
                ((TextView)v.findViewById(R.id.nama_barang_text)).setText(hoReview.getKode_barang());
                ((TextView)v.findViewById(R.id.qty_text)).setText(hoReview.getQty());

            }
        };

        listHistoryPeriods.setAdapter(fireHistoryList);*/

    }
}
