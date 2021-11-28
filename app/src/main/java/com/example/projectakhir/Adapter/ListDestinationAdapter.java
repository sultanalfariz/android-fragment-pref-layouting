package com.example.projectakhir.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectakhir.Fragment.DetailFragment;
import com.example.projectakhir.Model.WisataModel;
import com.example.projectakhir.R;

import java.util.List;

import static com.example.projectakhir.Fragment.DetailFragment.KEY_ACTIVITY1;
import static com.example.projectakhir.Fragment.DetailFragment.KEY_ACTIVITY2;
import static com.example.projectakhir.Fragment.DetailFragment.KEY_ACTIVITY3;
import static com.example.projectakhir.Fragment.DetailFragment.KEY_ACTIVITY4;

public class ListDestinationAdapter extends RecyclerView.Adapter<ListDestinationAdapter.MyViewHolder> {

    private Context context;
    private List<WisataModel> mData;

    public ListDestinationAdapter(Context context, List<WisataModel> mData) {
        this.context = context;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_wisata, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.tv_wisata_title.setText(mData.get(position).getTitle());
        holder.img_wisata.setImageResource(mData.get(position).getImage());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Title = mData.get(position).getTitle();
                String Description = mData.get(position).getDescription();
                String Place = mData.get(position).getPlace();
                int Thumbnail = mData.get(position).getImage();
                // Send a message to Fragment
                DetailFragment frg = new DetailFragment();
                Bundle mBundle = new Bundle();
                mBundle.putString(KEY_ACTIVITY1, Title);
                mBundle.putString(KEY_ACTIVITY2, Description);
                mBundle.putString(KEY_ACTIVITY3, Place);
                mBundle.putInt(KEY_ACTIVITY4, Thumbnail);
                frg.setArguments(mBundle);

                FragmentManager manager = ((AppCompatActivity)context).getSupportFragmentManager();
                FragmentTransaction mFragmentTransaction = manager
                        .beginTransaction()
                        .replace(R.id.fragment_container, frg, DetailFragment.class.getSimpleName());
                mFragmentTransaction.addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tv_wisata_title;
        ImageView img_wisata;
        CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv_wisata_title = (TextView) itemView.findViewById(R.id.wisata_title);
            img_wisata = (ImageView) itemView.findViewById(R.id.wisata_img);
            cardView = (CardView) itemView.findViewById(R.id.cardview_id);
        }
    }
}