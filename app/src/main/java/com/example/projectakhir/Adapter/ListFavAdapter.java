package com.example.projectakhir.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectakhir.Activity.SettingActivity;
import com.example.projectakhir.Model.FavoriteModel;
import com.example.projectakhir.R;
import java.util.List;

public class ListFavAdapter extends RecyclerView.Adapter<ListFavAdapter.ViewHolder> {

    private Context context;
    private List<FavoriteModel> saveFav;

    public ListFavAdapter(Context context, List<FavoriteModel> mData) {
        this.context = context;
        this.saveFav = mData;
    }

    @NonNull
    @Override
    public ListFavAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_favorite, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ListFavAdapter.ViewHolder holder, int position) {
        holder.textView.setText(saveFav.get(position).getTitle());
        holder.txtLoc.setText(saveFav.get(position).getPlace());
        holder.imageView.setImageResource(saveFav.get(position).getImage());

        SharedPreferences sharedPreferences = PreferenceManager
                .getDefaultSharedPreferences(context.getApplicationContext());
        Boolean switchPref = sharedPreferences
                .getBoolean(SettingActivity.KEY_PREF_DARKMODE_SWITCH, false);
        if(switchPref){
            holder.cardView.setBackgroundResource(R.color.darkBottomBar);
            holder.textView.setTextColor(Color.WHITE);
        } else {
            holder.cardView.setBackgroundResource(R.color.colorWhite);
            holder.textView.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return saveFav.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView, txtLoc;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.wisata_img);
            textView = itemView.findViewById(R.id.wisata_title);
            txtLoc = itemView.findViewById(R.id.txtloct);
            cardView = itemView.findViewById(R.id.cardview_id);
        }
    }
}
