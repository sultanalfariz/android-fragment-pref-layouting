package com.example.projectakhir.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.projectakhir.Fragment.ListFavoritFragment;
import com.example.projectakhir.Fragment.ListWisataFragment;
import com.example.projectakhir.Model.FavoriteModel;
import com.example.projectakhir.Model.WisataModel;
import com.example.projectakhir.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;

    public static String MY_PREF = "my_Preference";
    public static String JSON_KEY = "myData";

    RelativeLayout btnListDestination, btnListFavorite;
    ImageView icDestination, icFavorite;
    LinearLayout bottomBar;
    View viewBar;

    private List<FavoriteModel> listFav =  new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnListDestination = findViewById(R.id.btn_list);
        btnListFavorite = findViewById(R.id.btn_favorite);

        icDestination = findViewById(R.id.icon_destination);
        icFavorite = findViewById(R.id.icon_favorite);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new ListWisataFragment()).commit();
        }

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Boolean switchPref = sharedPreferences.getBoolean(SettingActivity.KEY_PREF_DARKMODE_SWITCH, false);
        if (switchPref){
            enableDarkMode(this.getWindow().getDecorView());
        } else {
            disableDarkMode(this.getWindow().getDecorView());
        }

        String delPref = getIntent().getStringExtra("remove_pref");
        if (delPref == "true"){
            onRestart();
        }
    }

    public void click_item(View view) {
        switch (view.getId()){
            case R.id.btn_list:
                loadFragment(new ListWisataFragment());
                icDestination.setImageResource(R.drawable.ic_photos);
                icFavorite.setImageResource(R.drawable.ic_favorite_border);
                break;
            case R.id.btn_favorite:
                loadFragment(new ListFavoritFragment());
                icDestination.setImageResource(R.drawable.ic_list_border);
                icFavorite.setImageResource(R.drawable.ic_favorite);
                break;
        }
    }

    public void loadFragment(Fragment fragment){

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();

        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void enableDarkMode(View decorView) {
        //set background color
        decorView.setBackgroundResource(R.color.darkModeBackground);

        bottomBar = (LinearLayout) findViewById(R.id.bottom_bar);
        bottomBar.setBackgroundResource(R.color.darkBottomBar);

        viewBar = (View) findViewById(R.id.view);
        viewBar.setBackgroundResource(R.color.darkBottomBar);
    }

    private void disableDarkMode(View decorView) {
        //set background color
        decorView.setBackgroundResource(R.color.lightModeBackground);

        bottomBar = (LinearLayout) findViewById(R.id.bottom_bar);
        bottomBar.setBackgroundResource(R.color.colorWhite);

        viewBar = (View) findViewById(R.id.view);
        viewBar.setBackgroundResource(R.color.colorView);
    }

    public void addNotification(String Title) {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this.getApplicationContext(), "notify_001");
        Intent ii = new Intent(this.getApplicationContext(), MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, ii, 0);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.setBigContentTitle("You have successfully added, "+Title);

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.drawable.logokuy);
        mBuilder.setContentTitle(Title+", added to your favorite place");
        mBuilder.setContentText("You can see your favorite places on the favorite page");
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigText);

        mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, mBuilder.build());
    }

    public void saveArrayList(){
        SharedPreferences prefs = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        Gson gson = new Gson();
        String json = gson.toJson(listFav);
        editor.putString(JSON_KEY, json);
        editor.apply();
    }

    public void remove(){
        SharedPreferences prefs = getSharedPreferences(MY_PREF, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public void insertItem(String title, String place, int img){
        listFav.add(new FavoriteModel(title, place, img));
        //mAdapter.notifyItemInserted(0);
        Log.d("dataaaaaa", "BerhasilInsert :" + listFav.size());
    }

    public void setItemEnable(){
        icDestination.setImageResource(R.drawable.ic_list_border);
        icFavorite.setImageResource(R.drawable.ic_favorite);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }
}
