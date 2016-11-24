package com.example.rnd.imapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.ACK;

import java.util.ArrayList;

/**
 * Created by RND on 11/17/2016.
 */

public class ACKListAdapter extends ArrayAdapter<ACK> {
    private ArrayList<ACK> ackArrayList;
    private LayoutInflater layoutInflater;
    private final Activity context;

    public ACKListAdapter(Activity context, int resource, ArrayList<ACK> ackArrayList) {
        super(context, resource, ackArrayList);
        this.context = context;
        this.ackArrayList = ackArrayList;
        //this.ackArrayList.addAll(ackArrayList);
    }

    private class ViewHolder {
        TextView name;
        TextView satuan;
        CheckBox checkBox;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;
        Log.v("ConvertView", String.valueOf(position));

        if (convertView == null) {
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.ack_list, null);

            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.namaBarangACK);
            holder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBoxAck);
            holder.satuan = (TextView) convertView.findViewById(R.id.satuanACK);
            convertView.setTag(holder);

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    ACK ack = (ACK) cb.getTag();
                    ack.setSelected(cb.isChecked());
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
