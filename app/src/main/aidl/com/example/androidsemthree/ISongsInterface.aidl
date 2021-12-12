// ISongsInterface.aidl
package com.example.androidsemthree;

interface ISongsInterface {
    void playPreviousSong();

    void playNextSong();

    void pauseSong();

    void playSong();

    void setSong(int id);

    void stop();
}
