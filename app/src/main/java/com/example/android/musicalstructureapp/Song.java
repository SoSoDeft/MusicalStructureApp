package com.example.android.musicalstructureapp;


public class Song {


    private String songName;
    private String songTitle;
    private int songImage;
    private int songAudio;

    // Default Constructor
    Song() {

    }

    // Constructor
    Song(String songName, String songTitle, int songImage, int songAudio) {

        this.songName = songName;
        this.songTitle = songTitle;
        this.songImage = songImage;
        this.songAudio = songAudio;
    }


    //Getter methods for constructor

    public String getSongName() {
        return songName;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public int getSongImage() {
        return songImage;
    }

    public int getSongAudio() {
        return songAudio;
    }


}
