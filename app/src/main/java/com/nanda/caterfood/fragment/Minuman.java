package com.nanda.caterfood.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.constraint.solver.widgets.Helper;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.nanda.caterfood.activity.Login;
import com.nanda.caterfood.activity.Scanner;
import com.nanda.caterfood.adapter.ImageAdapter;
import com.nanda.caterfood.konfigurasi.Config;
import com.nanda.caterfood.model.MenuModel;
import com.nanda.caterfood.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Minuman extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private static final String TAG = "RecyclerViewFragment";
    private RecyclerView mRecyclerView;
    private ArrayList<MenuModel> mList;
    private ImageAdapter mAdapter;

    public Minuman() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment F_dua.
     */
    // TODO: Rename and change types and number of parameters
    public static Minuman newInstance(String param1, String param2) {
        Minuman fragment = new Minuman();
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
        View rootView = inflater.inflate(R.layout.first, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
        mList = new ArrayList<>();
//        ProgressDialog pd = new ProgressDialog(getContext());
//        pd.setIndeterminate(true);
//        pd.setCancelable(false);
//        pd.setInverseBackgroundForced(false);
//        pd.setCanceledOnTouchOutside(false);
//        pd.setTitle("Info");
//        pd.setMessage("Mengambil data");
//        pd.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Config.DAFTAR_MINUMAN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            Log.e("response ", response);
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("data");
                            for (int a = 0; a < jsonArray.length(); a++) {
                                JSONObject object1 = jsonArray.getJSONObject(a);
                                MenuModel makanan = new MenuModel();
                                makanan.setmGambar(object1.getString("gambar"));
                                makanan.setmDesk(object1.getString("deskripsi"));
                                makanan.setmNamaMenu(object1.getString("nama_menu"));
                                makanan.setmHarga(object1.getString("harga"));
                                mList.add(makanan);
                            }

                        }catch (JSONException e){
                            Config.pesan(getContext(),"Error Conver Data To JSON"+e.toString());
                            ProgressDialog pd = new ProgressDialog(getContext());
                            pd.dismiss();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //You can handle error here if you want
                        Log.e("error",error.toString());

                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                //Adding parameters to request

                //returning parameter
                return params;
            }
        };

        //Adding the string request to the queue
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
//        mList.add(new MenuModel(String.valueOf(R.drawable.caterfood_icon), "Buket 1", " Rp.75.000 ", "Bisa Desain Rangkaian Bunga Sendiri."));
//        mList.add(new MenuModel(String.valueOf(R.drawable.caterfood_icon), "Buket 2", " Rp.100.000 ", "Bisa Desain Rangkaian Bunga Sendiri."));
//        mList.add(new MenuModel(String.valueOf(R.drawable.caterfood_icon), "Buket 3", " Rp.200.000 ", "Bisa Desain Rangkaian Bunga Sendiri."));
//        mList.add(new MenuModel(String.valueOf(R.drawable.caterfood_icon), "Buket 4", " Rp.90.000 ", "Bisa Desain Rangkaian Bunga Sendiri."));
//        mList.add(new MenuModel(String.valueOf(R.drawable.caterfood_icon), "Buket 5", " Rp.150.000 ", "Bisa Desain Rangkaian Bunga Sendiri."));
//        mList.add(new MenuModel(String.valueOf(R.drawable.caterfood_icon), "Buket 6", " Rp.125.000 ", "Bisa Desain Rangkaian Bunga Sendiri."));

        mAdapter = new ImageAdapter(mList,getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);

        // Inflate the layout for this fragment
        return rootView;

    }
}
