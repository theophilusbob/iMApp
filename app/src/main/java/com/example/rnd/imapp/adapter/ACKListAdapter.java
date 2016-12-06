package com.example.rnd.imapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.ACK;

import java.util.ArrayList;

/*
 * Created by RND BCA on 11/17/2016.
 */

public class ACKListAdapter extends ArrayAdapter<ACK> {
    private ArrayList<ACK> ackArrayList;
    private final Activity context;

    public ACKListAdapter(Activity context, int resource, ArrayList<ACK> ackArrayList) {
        super(context, resource, ackArrayList);
        this.context = context;
        this.ackArrayList = ackArrayList;
    }

    private class ViewHolder {
        TextView name;
        TextView satuan;
        CheckBox checkBox;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        Log.v("ConvertView", String.valueOf(position));

        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ack_list, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.namaBarangACK);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBoxAck);
            holder.satuan = (TextView) convertView.findViewById(R.id.satuanACK);
            convertView.setTag(holder);

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                }
            });

            ACK ack = ackArrayList.get(position);
            holder.name.setText(" ( " + ack.getKode_barang() + " ) ");
            holder.checkBox.setText(ack.getNama_barang());
            holder.checkBox.setChecked(ack.isSelected());
            holder.satuan.setText(ack.getJml_order());
        }

        return convertView;
    }
}
