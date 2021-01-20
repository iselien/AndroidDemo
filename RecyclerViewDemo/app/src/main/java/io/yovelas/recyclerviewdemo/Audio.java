package io.yovelas.recyclerviewdemo;

public class Audio {

    private String id;
    private String display_name;
    private String artist;
    private String duration;
    private String size;

    public Audio() {
    }

    public Audio(String id, String display_name, String artist, String duration, String size) {
        this.id = id;
        this.display_name = display_name;
        this.artist = artist;
        this.duration = duration;
        this.size = size;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "id='" + id + '\'' +
                ", display_name='" + display_name + '\'' +
                ", artist='" + artist + '\'' +
                ", duration='" + duration + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
