package com.dropbeatzadmin.model;

import java.util.Date;

public class Song {
    private int id;
    private String title;
    private String artistName;
    private String album;
    private String duration;
    private Date releaseDate;

    public Song(int id, String title, String artistName, String album, String duration, Date releaseDate) {
        this.id = id;
        this.title = title;
        this.artistName = artistName;
        this.album = album;
        this.duration = duration;
        this.releaseDate = releaseDate;
    }

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getArtistName() {
		return artistName;
	}

	public String getAlbum() {
		return album;
	}

	public String getDuration() {
		return duration;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

}