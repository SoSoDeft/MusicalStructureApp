package com.example.android.musicalstructureapp;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailPageActivity extends AppCompatActivity {

    // Fields for detail Artist Name, Song Title, Song Image and Song Audio
    String detailArtistName;
    String detailSongTitle;
    int detailSongImage;
    int detailSongAudio;
    // Fields initialization for ListView activity intent and bundle
    Intent intent;
    Bundle bundle;
    // Utilize ButterKnife View binding
    @BindView(R.id.detail_artist_name)
    TextView detailArtistNameView;
    @BindView(R.id.detail_song_title)
    TextView detailSongTitleView;
    @BindView(R.id.detail_album_image)
    ImageView detailSongImageView;
    @BindView(R.id.play_icon)
    ImageView play;
    // Handles playback of all audio files
    private MediaPlayer mMediaPlayer;
    // Handles audio focus when playing audio file
    private AudioManager mAudioManager;
    private AudioManager.OnAudioFocusChangeListener mOnAudioFocusChangeListener = new
            AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        //If audio focus loss is temporary or other audio interrupts...
                        //audio is paused
                        mMediaPlayer.pause();

                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {

                        mMediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {

                        releaseMediaPlayer();
                    }

                }
            };
    // On completion of audio file, resources are released
    public MediaPlayer.OnCompletionListener mCompletionListener = new
            MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    releaseMediaPlayer();
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.detail_page);
        ButterKnife.bind(this); // << Be sure to include this to bind views...

        // Create AudioManager to request audio focus
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        // get intent and song data from ListView activity
        intent = getIntent();
        bundle = intent.getBundleExtra("songBundle");


        // Retrieve data from bundle to populate views
        detailSongImage = bundle.getInt("songImage");
        detailArtistName = bundle.getString("artistName");
        detailSongTitle = bundle.getString("songTitle");
        detailSongAudio = bundle.getInt("songAudio");


        // Set data from bundle to respective view
        detailSongImageView.setImageResource(detailSongImage);
        detailSongTitleView.setText(detailSongTitle);
        detailArtistNameView.setText(detailArtistName);

        mMediaPlayer = MediaPlayer.create(this, detailSongAudio);
        mMediaPlayer.start();


        // Set onclick listeners for buttons
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!mMediaPlayer.isPlaying()) {
                    mMediaPlayer.start();
                    play.setImageResource(R.drawable.baseline_pause_black_48dp);

                } else {
                    mMediaPlayer.pause();
                    play.setImageResource(R.drawable.outline_play_arrow_black_48dp);
                }
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();

        // On activity stop, release app MediaPlayer resources
        releaseMediaPlayer();
    }

    // Learned from implementing MiWok app
    private void releaseMediaPlayer() {
        // If the media player is not empty...
        if (mMediaPlayer != null) {
            // It is recommended to release app resources
            mMediaPlayer.release();

            // Setting item to null tells that no current resources are in use.
            mMediaPlayer = null;

            // When set to null, audio focus should be removed as well.
            mAudioManager.abandonAudioFocus(mOnAudioFocusChangeListener);
        }
    }


}
