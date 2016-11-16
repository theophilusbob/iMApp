package com.example.rnd.imapp.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.Orders;
import com.example.rnd.imapp.model.StockOpname;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HistoryByItemActivity extends AppCompatActivity {
    private ListView checkListView;
    DatabaseReference barangRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/stockopname");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_by_item);

        checkListView = (ListView) findViewById(R.id.checkBarangListView);

        FirebaseListAdapter<StockOpname> barangList = new FirebaseListAdapter<StockOpname>(
                this, StockOpname.class, R.layout.list_qty_stock_opname, barangRef
        ) {
            @Override
            protected void populateView(View v, StockOpname soReview, int position) {
                ((TextView)v.findViewById(R.id.nama_barang_text)).setText(soReview.getNama_barang());
            }
        };

        checkListView.setChoiceMode(checkListView.CHOICE_MODE_MULTIPLE);
        checkListView.setAdapter(barangList);
    }
}
