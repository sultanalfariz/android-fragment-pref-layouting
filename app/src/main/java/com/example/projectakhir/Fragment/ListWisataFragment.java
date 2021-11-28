package com.example.projectakhir.Fragment;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectakhir.Activity.SettingActivity;
import com.example.projectakhir.Adapter.ListDestinationAdapter;
import com.example.projectakhir.Model.WisataModel;
import com.example.projectakhir.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListWisataFragment extends Fragment {

    List<WisataModel> listWisata;
    Context context;

    private ImageView btnSetting;
    private TextView txtView;

    public ListWisataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_wisata, container, false);
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

        listWisata = new ArrayList<>();
        listWisata.add(new WisataModel("Mount Semeru", "Malang, East Java",
                "Semeru Mountain, lies at the southern end of a volcanic massif extending north to the Tengger caldera. Semeru has become the most favourite mountain trekking destination by now. It is the highest volcano in Java with 3.676 meters above sea level high, makes it one of Indonesia’s seven summit and one of the most active. Mount Semeru is a part of Bromo-Tengger-Semeru National Park (TNBTR). With the nearest cities are Malang and Lumajang. Best time to hike Semeru is between May until September, because on December until March is rainy season in Indonesia with the frequent storms and landslides. National park are also closed during some of these times for natural rehabilitation.",
                R.drawable.rakum));
        listWisata.add(new WisataModel("Bunaken", "Manado, North Sulawesi",
                "Bunaken is an island, formerly part of Manado bay, is norther part of Sulawesi province, Indonesia. The visitors can reach Bunaken by speed boat abour 30 minutes from Manado sea port. Around the Bunaken Island, there is Bunaken Sea Garden which is part of Bunaken National Park. Overall, Bunaken is covers an area of 75,265 hectares with five islands within, which is the island of Manado Tua, Bunaken Island, Siladen Island, Mantehage Island island and the branch of it, Naen Island.\n" +
                        "\n" + "Bunaken marine park has 20 points dive spot with varying depth of up to 1344 meters. From those 20 diving points, the 12 points among them are around is Bunaken Island. Those twelve point dive are the most frequently visited by the divers or tourist who want to enjoy the beauty of the underwater scenery.",
                R.drawable.bunaken));
        listWisata.add(new WisataModel("Raja Ampat", "West Papua",
                "Far from the view-blocking skyscrapers, dense and hectic concrete jungles, congested traffics, flickering electric billboards, endless annoying noises, and all the nuisances of modern cities, you will find a pristine paradise where Mother Nature and warm friendly people welcome you with all the exceptional wonders in Raja Ampat, the islands-regency in West Papua Province. With all the spectacular wonders above and beyond its waters, as well as on land and amidst the thick jungles, this is truly the place where words such as beautiful, enchanting, magnificent, and fascinating get its true physical meaning.",
                R.drawable.rajaampat));
        listWisata.add(new WisataModel("Madakaripura Waterfall", "Probolinggo, East Java",
                "The best way to get to Madakaripura Waterfall is to rent a car from Surabaya or Malang, or include it in the tour package along with Mount Bromo. From Surabaya, capital of East Java, the trip will take a little over three hours, by taking the intercity route to Sidoarjo-Porong-Pasuruan-Probolinggo. At the entrance, you will need to pay about 20,000 rupiah entrance fee. With all of the parking, moto-taxi and entrance fee it all adds up to about 45,000 total per person, which is getting quite steep comparatively. But it’s worth it.\n" +
                        "\n" + "The trail is paved the whole way until you reach the falls. Then lots of men will approach you trying to sell you a poncho and iPhone covers etc. You don’t need them but you will get quite wet. So it’s up to you. You are only under the falling water for less than a few seconds so if you move quick your gear and clothes don’t get destroyed. We had our camera gear and just walked through quickly with our bag covers on.",
                R.drawable.madakaripura));
        listWisata.add(new WisataModel("Mount Rinjani", "Lombok, NTB",
                "The mighty Rinjani mountain of Gunung Rinjani is a massive volcano which towers over the island of Lombok. A climb to the top is one of the most exhilarating experiences you can have in Indonesia. At 3,726 meters tall, Gunung Rinjani is the second highest mountain in Indonesia. The climb to the top may not be easy but it’s worth it, and is widely regarded as one of the best views in the country.\n" +
                        "\n" + "Part of the famous ‘ring of fire’ this mountain also holds spiritual significance for the local people. It’s thought that the name Rinjani comes from an old Javanese term for ‘God’.\n" +
                        "\n" + "Around the slopes of Rinjani there are lush forests sprinkled with waterfalls and surrounded by stunning scenery.",
                R.drawable.rinjani));
        listWisata.add(new WisataModel("Ranu Kumbolo", "Semeru National Park, East Java",
                "When you’ll arrive at Ranu Kumbolo, a definite fatigue seemed to break even with a charm and beauty and you’ll feel. The blend of evergreen trees, shrubs, blue sky becomes a reflection of its own and offers a calm and peace in the area. The beautiful Ranu Kumbolo as a refreshing oasis, natural blend of fresh green and lake views, guaranteed to remove any kind of sense of fatigue, and splendid cool atmosphere, airy conditions.\n" +
                        "Ranu Kumbolo Lake truly deserves to be a perfect place for relaxation, especially the weather at night the temperature reaches -10 degrees Celsius.",
                R.drawable.rakum2));
        listWisata.add(new WisataModel("Nusa Penida", "Bali",
                "Nusa Penida is a relatively undeveloped island south-east of mainland Bali. You won’t find the snazzy beach clubs, organic cafes or trendy restaurants here, but Nusa Penida is still worthy of a visit. It houses some of the most photogenic sights in Bali.",
                R.drawable.nusapenida));
        listWisata.add(new WisataModel("Gili Trawangan", "Lombok",
                "Gili Trawangan is a tropical playground of global renown, ranking alongside Bali and Borobudur as one of Indonesia's top destinations. Trawangan's heaving main drag, busy with bikes, cidomos and mobs of scantily clad visitors, can surprise those expecting a languid island retreat.",
                R.drawable.trawangan));
        listWisata.add(new WisataModel("Balangan Beach", "Bali",
                "Balangan Beach is one of Bali’s most scenic spots, featuring a gorgeous half-kilometre stretch of golden sand between vegetated limestone cliffs. Locally referred to as Pantai Balangan, there’s a reef just off the coast that creates one of the longest left-hander breaks on the island.",
                R.drawable.balangan));
        listWisata.add(new WisataModel("Wurung Crater", "Bondowoso, East Java",
                "Wurung Crater is a hilly area that has a stretch of green grassland (savanna) with a crater-shaped shape like a mountain crater. The hills are surrounded by a giant hole like the caldera.",
                R.drawable.wurung));
        listWisata.add(new WisataModel("Mount Bromo", "East Java",
                "Located some 4 hours drive from Surabaya, the capital of East Java, Mount Bromo is a part of the Bromo Tengger Semeru National Park that covers a massive area of 800 square km. While it may be small when measured against other volcanoes in Indonesia, the magnificent Mt Bromo will not disappoint with its spectacular views and dramatic landscapes.",
                R.drawable.bromo));
        listWisata.add(new WisataModel("Wakatobi", "Southeast Sulawesi",
                "Located in the Asia-Pacific World Coral Triangle, in the province of Southeast Sulawesi, the Wakatobi Islands offer clear waters and a rich bio-diverse underwater life. Wakatobi hosts 942 fish species and 750 coral reef species (of 850 globally), versus 50 in the Caribbean and 300 in the Red Sea.",
                R.drawable.wakatobi));

        RecyclerView myrv = (RecyclerView) getActivity().findViewById(R.id.recyclerview_id);
        ListDestinationAdapter myAdapter = new ListDestinationAdapter(context, listWisata);
        myrv.setLayoutManager(new GridLayoutManager(context,2));
        myrv.setAdapter(myAdapter);

        txtView = getActivity().findViewById(R.id.textView2);

        btnSetting = getActivity().findViewById(R.id.btn_setting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void enableDarkMode(View decorView) {
        //set background color
        decorView.setBackgroundResource(R.color.darkModeBackground);

        txtView = (TextView) getActivity().findViewById(R.id.textView2);
        txtView.setTextColor(Color.WHITE);
    }

    private void disableDarkMode(View decorView) {
        //set background color
        decorView.setBackgroundResource(R.color.lightModeBackground);

        txtView = (TextView) getActivity().findViewById(R.id.textView2);
        txtView.setTextColor(Color.BLACK);
    }
}
