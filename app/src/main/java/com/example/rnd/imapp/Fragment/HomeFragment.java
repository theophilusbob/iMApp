package com.example.rnd.imapp.Fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rnd.imapp.Activity.LoginActivity;
import com.example.rnd.imapp.Activity.ViewPagerActivity;
import com.example.rnd.imapp.R;
import com.example.rnd.imapp.model.Barang;
import com.example.rnd.imapp.model.StockOpname;
import com.example.rnd.imapp.model.User;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
    protected TextView tabSCM, tabVMI, txtHalo, txtNoOrder;
    FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private FirebaseAuth.AuthStateListener mAuthListener;
    private String getCurrentUser, kodeCabang;

    DatabaseReference myRootRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-443a6.firebaseio.com/orders");
    DatabaseReference myUserRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-443a6.firebaseio.com/users");
    DatabaseReference myStockRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://imapp-443a6.firebaseio.com/stockopname");
    DatabaseReference mySCMLastOrderRef = myRootRef.child("SCM");
    DatabaseReference myVMILastOrderRef = myRootRef.child("VMI");
    //private FirebaseUser user;

    // Orders JSON Url
    //private static String url_scm = "http://192.168.1.117/imapp_api/getLastOrderSCM.php";
    //private static String url_vmi = "http://192.168.1.117/imapp_api/getLastOrderVMI.php";
    private ProgressDialog pDialog;
    //private List<Orders> ordersList = new ArrayList<Orders>();
    //private List<Orders> ordersListVMI = new ArrayList<Orders>();
    //private List<cobaLastOrder> list_coba_order_scm = new ArrayList<>();
    private ListView list_view_last_order_scm, list_view_last_order_vmi;
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
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        txtHalo = (TextView) v.findViewById(R.id.haloTxt);
        //txtNoOrder = (TextView)  v.findViewById(R.id.txtNoOrder);
        //infoTab = (LinearLayout) v.findViewById(R.id.infoTab);
        tabSCM = (TextView) v.findViewById(R.id.tabSCM);
        tabVMI = (TextView) v.findViewById(R.id.tabVMI);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            txtHalo.setText("Halo, " + user.getEmail());
            getCurrentUser = user.getEmail();
        } else {
            txtHalo.setText("Halo, " + "no user found!");
        }

        /*mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                final FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // User is signed in
                    // Retrieve nama cabang
                    myUserRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot userSnapshot: dataSnapshot.getChildren()) {
                                User userValue= userSnapshot.getValue(User.class);
                                if (user.getEmail().equals(userValue.getEmail_cabang())) {
                                    // User is signed in
                                    getCurrentUser = user.getEmail();
                                    txtHalo.setText("Halo, " + userValue.getNama_cabang());
                                }
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                        }
                    });
                } else {
                    // No user is signed in
                    txtHalo.setText("Halo, " + "No user!");
                }
                // ...
            }
        };*/



        /*pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading your last order. Click anywhere to dismiss.");
        pDialog.show();*/

        tabSCM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*String no_order = myRootRef
                                        .orderByChild("no_order")
                                        .limitToFirst(1);*/

                //infoTab.setBackgroundResource(R.color.blue);
                list_view_last_order_vmi.setVisibility(View.INVISIBLE);
                list_view_last_order_scm.setVisibility(View.VISIBLE);
                //txtNoOrder.setText("A0/16/04/3079");
            }
        });

        tabVMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //infoTab.setBackgroundResource(R.color.lightBlue);
                list_view_last_order_scm.setVisibility(View.INVISIBLE);
                list_view_last_order_vmi.setVisibility(View.VISIBLE);
            }
        });

        // Firebase List

        list_view_last_order_scm = (ListView) v.findViewById(R.id.lastOrderSCMList);
        list_view_last_order_vmi = (ListView) v.findViewById(R.id.lastOrderVMIList) ;

        if ( getCurrentUser.equals("bna@imapp.com"))
            kodeCabang = "5270";
        if ( getCurrentUser.equals("cdb@imapp.com"))
            kodeCabang = "0397";
        if ( getCurrentUser.equals("kms@imapp.com"))
            kodeCabang = "5500";
        if ( getCurrentUser.equals("kst@imapp.com"))
            kodeCabang = "5260";
        if ( getCurrentUser.equals("mdr@imapp.com"))
            kodeCabang = "0398";
        if ( getCurrentUser.equals("psp@imapp.com"))
            kodeCabang = "0229";
        if ( getCurrentUser.equals("tmg@imapp.com"))
            kodeCabang = "0310";
        if ( getCurrentUser.equals("wbp@imapp.com"))
            kodeCabang = "5435";
        if ( getCurrentUser.equals("wsl@imapp.com"))
            kodeCabang = "0482";
        if ( getCurrentUser.equals("wsa@imapp.com"))
            kodeCabang = "0084";

        FirebaseListAdapter<StockOpname> lastOrderFireList = new FirebaseListAdapter<StockOpname>(
                getActivity(), StockOpname.class, R.layout.list_row, myStockRef.child(kodeCabang).orderByChild("id_jenis_barang").equalTo("1")) {

            @Override
            protected void populateView(View v, StockOpname stockOpnameSCM, int position) {

                    ((TextView)v.findViewById(R.id.nama_barang)).setText(stockOpnameSCM.getNama_barang());
                    ((TextView)v.findViewById(R.id.kode_barang)).setText(stockOpnameSCM.getKode_barang());
                    ((TextView)v.findViewById(R.id.qty)).setText(stockOpnameSCM.getQuantity());
                    ((TextView)v.findViewById(R.id.satuan_pack)).setText(stockOpnameSCM.getSatuan());

            }
        };

        FirebaseListAdapter<StockOpname> lastOrderVMIFireList = new FirebaseListAdapter<StockOpname>(
                getActivity(), StockOpname.class, R.layout.list_row,  myStockRef.child(kodeCabang).orderByChild("id_jenis_barang").equalTo("2")) {

            @Override
            protected void populateView(View v, final StockOpname stockOpnameVMI, int position) {

                    ((TextView)v.findViewById(R.id.nama_barang)).setText(stockOpnameVMI.getNama_barang());
                    ((TextView)v.findViewById(R.id.kode_barang)).setText(stockOpnameVMI.getKode_barang());
                    ((TextView)v.findViewById(R.id.qty)).setText(stockOpnameVMI.getQuantity());
                    ((TextView)v.findViewById(R.id.satuan_pack)).setText(stockOpnameVMI.getSatuan());

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

    @Override
    public void onStart() {
        super.onStart();

    }
}
