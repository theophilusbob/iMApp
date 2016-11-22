package com.example.rnd.imapp.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import com.example.rnd.imapp.R;
import com.example.rnd.imapp.adapter.CustomListAdapter;
import com.example.rnd.imapp.app.AppController;
import com.example.rnd.imapp.model.Orders;
import com.example.rnd.imapp.model.StockOpname;
import com.example.rnd.imapp.model.cobaLastOrder;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    private LinearLayout infoTab;
    private TextView tabSCM, tabVMI, txtHalo, txtNoOrder;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

    DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-99a05.firebaseio.com/orders");
    DatabaseReference mySCMLastOrderRef = myRootRef.child("SCM");
    DatabaseReference myVMILastOrderRef = myRootRef.child("VMI");

    // Orders JSON Url
    private static String url_scm = "http://192.168.1.117/imapp_api/getLastOrderSCM.php";
    private static String url_vmi = "http://192.168.1.117/imapp_api/getLastOrderVMI.php";
    private ProgressDialog pDialog;
    private List<Orders> ordersList = new ArrayList<Orders>();
    private List<Orders> ordersListVMI = new ArrayList<Orders>();
    private List<cobaLastOrder> list_coba_order_scm = new ArrayList<>();
    private ListView list_view_last_order_scm, list_view_last_order_vmi, ordersListView,ordersListViewVMI;
    private CustomListAdapter cobaLastOrderSCM, customListAdapter, customListAdapterVMI;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        /*// Creating volley request obj
        JsonArrayRequest lastOrderReqSCM = new JsonArrayRequest(url_scm,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Last order scm", response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Orders lastOrders = new Orders();
                                lastOrders.setNama_barang(obj.getString("nama_barang"));
                                lastOrders.setKode_barang(obj.getString("kode_barang"));
                                lastOrders.setQty(obj.getInt("qty"));
                                lastOrders.setSatuan_pack(obj.getString("satuan"));

                                // adding order to order array
                                ordersList.add(lastOrders);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        customListAdapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Home Fragment", "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(lastOrderReqSCM);

        // Creating volley request obj
        JsonArrayRequest lastOrderReqVMI = new JsonArrayRequest(url_vmi,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d("Last order VMI", response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = response.getJSONObject(i);
                                Orders lastOrders = new Orders();
                                lastOrders.setNama_barang(obj.getString("nama_barang"));
                                lastOrders.setKode_barang(obj.getString("kode_barang"));
                                lastOrders.setQty(obj.getInt("qty"));
                                lastOrders.setSatuan_pack(obj.getString("satuan"));

                                // adding order to order array
                                ordersListVMI.add(lastOrders);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        customListAdapterVMI.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("Home Fragment", "Error: " + error.getMessage());
                hidePDialog();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(lastOrderReqVMI);*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        txtHalo = (TextView) v.findViewById(R.id.haloTxt);
        txtNoOrder = (TextView)  v.findViewById(R.id.txtNoOrder);
        infoTab = (LinearLayout) v.findViewById(R.id.infoTab);
        tabSCM = (TextView) v.findViewById(R.id.tabSCM);
        tabVMI = (TextView) v.findViewById(R.id.tabVMI);
        //ordersListView = (ListView) v.findViewById(R.id.ordersList);
        //ordersListViewVMI = (ListView) v.findViewById(R.id.ordersListVMI);

        txtHalo.setText("Halo, " + firebaseAuth.getCurrentUser().getEmail());

        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading your last order. Click anywhere to dismiss.");
        pDialog.show();

        //customListAdapter = new CustomListAdapter(getActivity(), ordersList);
        //customListAdapterVMI =  new CustomListAdapter(getActivity(), ordersListVMI);
       // ordersListViewVMI.setAdapter(customListAdapterVMI);
        //ordersListView.setAdapter(customListAdapter);

        tabSCM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* String no_order = myRootRef
                                        .orderByChild("no_order")
                                        .limitToFirst(1);*/

                infoTab.setBackgroundResource(R.color.blue);
                list_view_last_order_vmi.setVisibility(View.INVISIBLE);
                list_view_last_order_scm.setVisibility(View.VISIBLE);
                txtNoOrder.setText("A0/16/04/3079");
            }
        });

        tabVMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoTab.setBackgroundResource(R.color.lightBlue);
                list_view_last_order_scm.setVisibility(View.INVISIBLE);
                list_view_last_order_vmi.setVisibility(View.VISIBLE);
            }
        });

        // Firebase List

        list_view_last_order_scm = (ListView) v.findViewById(R.id.lastOrderSCMList);
        list_view_last_order_vmi = (ListView) v.findViewById(R.id.lastOrderVMIList) ;

        FirebaseListAdapter<cobaLastOrder> lastOrderFireList = new FirebaseListAdapter<cobaLastOrder>(
                getActivity(), cobaLastOrder.class, R.layout.list_row, mySCMLastOrderRef
        ) {
            @Override
            protected void populateView(View v, cobaLastOrder cobaLastOrderSCM, int position) {
                ((TextView)v.findViewById(R.id.nama_barang)).setText(cobaLastOrderSCM.getNama_barang());
                ((TextView)v.findViewById(R.id.kode_barang)).setText(cobaLastOrderSCM.getKode_barang());
                ((TextView)v.findViewById(R.id.qty)).setText(cobaLastOrderSCM.getQty());
                ((TextView)v.findViewById(R.id.satuan_pack)).setText(cobaLastOrderSCM.getSatuan());
            }
        };

        FirebaseListAdapter<cobaLastOrder> lastOrderVMIFireList = new FirebaseListAdapter<cobaLastOrder>(
                getActivity(), cobaLastOrder.class, R.layout.list_row, myVMILastOrderRef
        ) {
            @Override
            protected void populateView(View v, cobaLastOrder cobaLastOrderVMI, int position) {
                ((TextView)v.findViewById(R.id.nama_barang)).setText(cobaLastOrderVMI.getNama_barang());
                ((TextView)v.findViewById(R.id.kode_barang)).setText(cobaLastOrderVMI.getKode_barang());
                ((TextView)v.findViewById(R.id.qty)).setText(cobaLastOrderVMI.getQty());
                ((TextView)v.findViewById(R.id.satuan_pack)).setText(cobaLastOrderVMI.getSatuan());
            }
        };

        list_view_last_order_scm.setAdapter(lastOrderFireList);
        list_view_last_order_vmi.setAdapter(lastOrderVMIFireList);

        return v;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
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
            //throw new RuntimeException(context.toString()
              //      + " must implement OnFragmentInteractionListener");
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
