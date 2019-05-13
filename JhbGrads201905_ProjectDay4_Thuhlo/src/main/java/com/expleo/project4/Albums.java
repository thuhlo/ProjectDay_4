package com.expleo.project4;

public class Albums
{
    int albumID;
    String title;
    int artistsId;

    public Albums(int albumID, String title, int artistsId)
    {
        setAlbumID(albumID);
        setTitle(title);
        setArtistsId(artistsId);
    }

    public int getAlbumID() {
        return albumID;
    }

    public void setAlbumID(int albumID) {
        this.albumID = albumID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getArtistsId() {
        return artistsId;
    }

    public void setArtistsId(int artistsId) {
        this.artistsId = artistsId;
    }
}
