package com.example.rnd.imapp.Fragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rnd.imapp.Activity.HistoryByItemActivity;
import com.example.rnd.imapp.Activity.HistoryByOrderActivity;
import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.cobaOrder;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HistoryOrderFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HistoryOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HistoryOrderFragment extends Fragment {
    private ListView listViewLastOrder;
    private TextView kode_barang, nama_barang, qty, satuan;
    private Button by_order_button, by_item_button;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HistoryOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HistoryOrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HistoryOrderFragment newInstance(String param1, String param2) {
        HistoryOrderFragment fragment = new HistoryOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_history_order, container, false);

        /*listViewLastOrder = (ListView) v.findViewById(R.id.lastOrderListView);
        kode_barang = (TextView) v.findViewById(R.id.kode_barang);
        nama_barang = (TextView) v.findViewById(R.id.nama_barang);
        qty = (TextView) v.findViewById(R.id.qty);
        satuan = (TextView) v.findViewById(R.id.satuan_pack);

        final DatabaseReference lastOrderRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/orders");

        // Read from the database
        lastOrderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                for (DataSnapshot soSnapshot: dataSnapshot.getChildren()) {
                    cobaOrder lastOrder = soSnapshot.getValue(cobaOrder.class);
                    Log.i("Nama Barang: ", lastOrder.getNama_barang() + "Qty:  " + lastOrder.getQty());
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("InputQTYAct", "Failed to read value.", error.toException());
            }
        });

        FirebaseListAdapter<cobaOrder> fireSisaStokList = new FirebaseListAdapter<cobaOrder>(
                getActivity(), cobaOrder.class, R.layout.list_qty_stock_opname, lastOrderRef
        ) {
            @Override
            protected void populateView(View v, cobaOrder lastOrders, int position) {
                ((TextView)v.findViewById(R.id.nama_barang_text)).setText(lastOrders.getNama_barang());
                ((TextView)v.findViewById(R.id.qty_text)).setText(lastOrders.getQty());

                *//*if (soReview.getQuantity().equals("0")) {
                    ((TextView)v.findViewById(R.id.nama_barang_text)).setTextColor(Color.parseColor("#C62828"));
                    ((TextView)v.findViewById(R.id.qty_text)).setTextColor(Color.parseColor("#C62828"));
                }*//*
            }
        };

        listViewLastOrder.setAdapter(fireSisaStokList);*/

        by_order_button = (Button) v.findViewById(R.id.btnByOrder);
        by_item_button = (Button) v.findViewById(R.id.btnByItem);

        by_order_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HistoryByOrderActivity.class);
                startActivity(intent);
            }
        });

        by_item_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HistoryByItemActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
         //   throw new RuntimeException(context.toString()
           //         + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
