package com.example.rnd.imapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.Orders;

import java.util.List;

/*
 * Created by RND on 11/1/2016.
 */

public class CustomListAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<Orders> orderItems;

    public CustomListAdapter(Activity activity, List<Orders> orderItems) {
        this.activity = activity;
        this.orderItems = orderItems;
    }

    @Override
    public int getCount() {
        return orderItems.size();
    }

    @Override
    public Object getItem(int location) {
        return orderItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (layoutInflater == null) {
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView ==  null) {
            convertView = layoutInflater.inflate(R.layout.list_row, null);
        }

        TextView nama_barang = (TextView) convertView.findViewById(R.id.nama_barang);
        TextView kode_barang = (TextView) convertView.findViewById(R.id.kode_barang);
        TextView qty = (TextView) convertView.findViewById(R.id.qty);
        TextView satuan_pack = (TextView) convertView.findViewById(R.id.satuan_pack);

        // Getting Order data for the row
        Orders  o = orderItems.get(position);

        // Nama Barang
        nama_barang.setText(o.getNama_barang());

        // Kode Barang
        kode_barang.setText(o.getKode_barang());

        // Quantity
        qty.setText(String.valueOf(o.getQty()));

        // Satuan
        satuan_pack.setText(o.getSatuan_pack());

        return convertView;
    }
}
