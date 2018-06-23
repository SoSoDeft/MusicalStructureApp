package com.example.android.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Since setOnClickListener contains inner class, must include final access modifier
        final ArrayList<Song> songs = new ArrayList<Song>();

        // Song object contains Artist Name, SongTitle,image and raw audio resource file
        songs.add(new Song("Andy Mineo", getString(R.string.andy_mineo_track),
                R.drawable.andy_mineo_ucantstopme, R.raw.andy_mineo_you_cant_stop_me));
        songs.add(new Song("Cool Breeze", getString(R.string.cool_breeze_track), R.drawable.cool_breeze_gangstapartna,
                R.raw.cool_breeze_gangsta_partna));
        songs.add(new Song("No Doubt", getString(R.string.no_doubt_track), R.drawable.no_doubt_dontspeak,
                R.raw.no_doubt_dont_speak));
        songs.add(new Song("Santana", getString(R.string.santana_track), R.drawable.santana_mirage,
                R.raw.carlos_santana_mirage));
        songs.add(new Song("Sevin", getString(R.string.sevin_track), R.drawable.sevin_champion,
                R.raw.sevin_ft_angie_rose_champion));
        songs.add(new Song("Signtist", getString(R.string.signtist_track), R.drawable.signtist_magnumopus,
                R.raw.signtist_magnum_opus));
        songs.add(new Song("WuTang Clan", getString(R.string.wutang_track), R.drawable.wutangclan_triumph,
                R.raw.wutang_clan_triumph_instrumental));


        SongAdapter adapter = new SongAdapter(this, songs);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);

        //Setup onItemClick Listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                // Get song by position clicked...
                Song song = songs.get(position);
                Bundle sendBund = new Bundle(); // Create Bundle to send with intent
                // Create intent to send from MainActivity to DetailPageActivity
                Intent intent = new Intent(MainActivity.this, DetailPageActivity.class);
                String artist = song.getSongName();
                String title = song.getSongTitle();
                int image = song.getSongImage();
                int audio = song.getSongAudio();

                // Place intent date into bundle
                sendBund.putInt("songImage", image);
                sendBund.putString("artistName", artist);
                sendBund.putString("songTitle", title);
                sendBund.putInt("songAudio", audio);


                //Place bundle as extra into intent
                intent.putExtra("songBundle", sendBund);


                startActivity(intent);
            }
        });
    }
}
