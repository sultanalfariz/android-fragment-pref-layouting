package com.example.projectakhir.Fragment;


import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.projectakhir.Activity.MainActivity;
import com.example.projectakhir.Activity.SettingActivity;
import com.example.projectakhir.R;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public static String KEY_ACTIVITY1 = "msg_activity1";
    public static String KEY_ACTIVITY2 = "msg_activity2";
    public static String KEY_ACTIVITY3 = "msg_activity3";
    public static String KEY_ACTIVITY4 = "msg_activity4";

    private TextView tvtitle, tvdescription, tvplace;
    private ImageView img, btnBack;
    private RelativeLayout btnAddFavorite;

    public DetailFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        tvtitle = getActivity().findViewById (R.id.txttitle);
        tvdescription = getActivity().findViewById (R.id.txtdesc);
        tvplace = getActivity().findViewById (R.id.txtloct);
        img = getActivity().findViewById (R.id.wisataimg_id);
        btnAddFavorite = getActivity().findViewById(R.id.btn_add_favorite);
        btnBack = getActivity().findViewById(R.id.btn_back);

        String Title = getArguments().getString(KEY_ACTIVITY1);
        String Description = getArguments().getString(KEY_ACTIVITY2);
        String Place = getArguments().getString(KEY_ACTIVITY3);
        int Thumbnail = getArguments().getInt(KEY_ACTIVITY4);

        tvtitle.setText(Title);
        tvdescription.setText(Description);
        tvplace.setText(Place);
        img.setImageResource(Thumbnail);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListWisataFragment frg = new ListWisataFragment();

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = manager
                        .beginTransaction()
                        .replace(R.id.fragment_container, frg, ListWisataFragment.class.getSimpleName());
                mFragmentTransaction.addToBackStack(null).commit();
            }
        });

        btnAddFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Title = getArguments().getString(KEY_ACTIVITY1);
                String Description = getArguments().getString(KEY_ACTIVITY2);
                String Place = getArguments().getString(KEY_ACTIVITY3);
                int Thumbnail = getArguments().getInt(KEY_ACTIVITY4);

                ((MainActivity)getActivity()).insertItem(Title, Place, Thumbnail);
                ((MainActivity)getActivity()).saveArrayList();
                ((MainActivity)getActivity()).addNotification(Title);
                ((MainActivity)getActivity()).setItemEnable();

                ListFavoritFragment frg = new ListFavoritFragment();

                FragmentManager manager = getActivity().getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = manager
                        .beginTransaction()
                        .replace(R.id.fragment_container, frg, ListFavoritFragment.class.getSimpleName());
                mFragmentTransaction.addToBackStack(null).commit();
            }
        });
    }

    private void enableDarkMode(View decorView) {
        //set background color
        decorView.setBackgroundResource(R.color.darkModeBackground);

        tvtitle = (TextView) getActivity().findViewById(R.id.txttitle);
        tvtitle.setTextColor(Color.WHITE);

        tvdescription = (TextView) getActivity().findViewById (R.id.txtdesc);
        tvdescription.setTextColor(Color.WHITE);

        RelativeLayout relHead = getActivity().findViewById(R.id.konten_1);
        relHead.setBackgroundResource(R.color.darkBottomBar);

        View view = getActivity().findViewById(R.id.view);
        view.setBackgroundResource(R.color.darkBottomBar);
    }

    private void disableDarkMode(View decorView) {
        //set background color
        decorView.setBackgroundResource(R.color.lightModeBackground);

        tvtitle = (TextView) getActivity().findViewById(R.id.txttitle);
        tvtitle.setTextColor(Color.BLACK);

        tvdescription = (TextView) getActivity().findViewById (R.id.txtdesc);
        tvdescription.setTextColor(Color.BLACK);

        RelativeLayout relHead = getActivity().findViewById(R.id.konten_1);
        relHead.setBackgroundResource(R.color.colorWhite);

        View view = getActivity().findViewById(R.id.view);
        view.setBackgroundResource(R.color.colorView);
    }
}
