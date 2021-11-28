package com.example.projectakhir.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectakhir.Activity.MainActivity;
import com.example.projectakhir.Activity.SettingActivity;
import com.example.projectakhir.Adapter.ListFavAdapter;
import com.example.projectakhir.Model.FavoriteModel;
import com.example.projectakhir.Model.WisataModel;
import com.example.projectakhir.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFavoritFragment extends Fragment {

    public static String MY_PREF = "my_Preference";
    public static String JSON_KEY = "myData";

    private Context context;
    private ArrayList<FavoriteModel> saveFav;
    private ListFavAdapter adapter;
    private RecyclerView recyclerView;
    private ImageView btnClear;

    public ListFavoritFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_favorit, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        context = getContext();

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(getContext().getApplicationContext());
        Boolean switchPref = sharedPreferences
                .getBoolean(SettingActivity.KEY_PREF_DARKMODE_SWITCH, false);
        if(switchPref){
            enableDarkMode(view);
        } else {
            disableDarkMode(view);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SharedPreferences pref = getActivity().getSharedPreferences(MY_PREF, MODE_PRIVATE);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<FavoriteModel>>(){}.getType();
        String json = pref.getString(JSON_KEY, null);
        saveFav = gson.fromJson(json, type);
        if (saveFav == null){
            saveFav = new ArrayList<>();
        }

        recyclerView = getActivity().findViewById(R.id.recyclerView);
        adapter = new ListFavAdapter(getContext(), saveFav);

        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter.notifyDataSetChanged();

        RelativeLayout rel = getActivity().findViewById(R.id.konten_1);
        btnClear = getActivity().findViewById(R.id.btn_delete);

        if (saveFav.size() == 0){
            rel.setVisibility(View.VISIBLE);
            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Data is Empty", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            rel.setVisibility(View.GONE);
            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("Attention")
                            .setContentText("Do you want to delete data?")
                            .setConfirmText("Yes")
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {

                                    ((MainActivity)getActivity()).remove();

                                    Intent intent = new Intent(getContext(), MainActivity.class);
                                    intent.putExtra("remove_pref", "true");
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            })
                            .setCancelButton("Cancel", new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sDialog) {
                                    sDialog.dismissWithAnimation();
                                }
                            })
                            .show();
                }
            });
        }
    }

    private void enableDarkMode(View decorView) {
        //set background color
        decorView.setBackgroundResource(R.color.darkModeBackground);
    }

    private void disableDarkMode(View decorView) {
        //set background color
        decorView.setBackgroundResource(R.color.lightModeBackground);
    }
}
