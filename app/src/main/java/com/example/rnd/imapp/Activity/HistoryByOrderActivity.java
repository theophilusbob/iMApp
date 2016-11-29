package com.example.rnd.imapp.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.rnd.imapp.R;

public class HistoryByOrderActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_by_order);

        Button btn3bulan = (Button) findViewById(R.id.btn3bulan);
        Button btn6bulan = (Button) findViewById(R.id.btn6bulan);
        Button btn12bulan = (Button) findViewById(R.id.btn12bulan);

        btn3bulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HistoryByOrderActivity.this, HistoryPeriodsActivity.class);
                startActivity(intent);
            }
        });

        btn6bulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn12bulan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }
}
