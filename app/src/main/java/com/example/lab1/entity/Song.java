package com.example.lab1.entity;

import android.net.Uri;
/**
 * Сущность mp3 трек
 */
public class Song {
    private String audioTitle;
    private String audioDuration;
    private String audioArtist;
    private Uri audioUri;

    /**
     * Возвращает название трека
     *
     * @return название
     */
    public String getAudioTitle() {
        return audioTitle;
    }

    /**
     * Устанавливает название трека
     *
     * @param audioTitle название
     */
    public void setAudioTitle(String audioTitle) {
        this.audioTitle = audioTitle;
    }

    /**
     * Возвращает длительность трека
     *
     * @return длительность
     */
    public String getAudioDuration() {
        return audioDuration;
    }

    /**
     * Устанавливает длительность трека
     *
     * @param audioDuration длительность
     */
    public void setAudioDuration(String audioDuration) {
        this.audioDuration = audioDuration;
    }

    /**
     * Возвращает исполнителя
     *
     * @return исполнитель
     */
    public String getAudioArtist() {
        return audioArtist;
    }

    /**
     * Устанавливает исполнителя
     *
     * @param audioArtist исполнитель
     */
    public void setAudioArtist(String audioArtist) {
        this.audioArtist = audioArtist;
    }
    /**
     * Возвращает путь до трека
     *
     * @return путь
     */
    public Uri getAudioUri() {
        return audioUri;
    }
    /**
     * Устанавливает путь расположения трека
     *
     * @param audioUri путь
     */
    public void setAudioUri(Uri audioUri) {
        this.audioUri = audioUri;
    }
    @Override
    public String toString() {
        return "Song{" +
                "audioTitle='" + audioTitle + '\'' +
                ", audioDuration='" + audioDuration + '\'' +
                ", audioArtist='" + audioArtist + '\'' +
                ", audioUri=" + audioUri +
                '}';
    }
}