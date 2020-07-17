package Spring.soundSystem;

import java.util.List;

public class BlankDisc implements CompactDisc {

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public List<String> getTracks() {
        return tracks;
    }

    public void setTracks(List<String> tracks) {
        this.tracks = tracks;
    }

    private String title;
    private String artist;
    private List<String> tracks;
//    public BlankDisc(String title, String artist, List<String> tracks){
//        this.artist = artist;
//        this.title = title;
//        this.tracks = tracks;
//    }
    @Override
    public void play() {
        System.out.println("Playing " + title + " by " + artist);
    }

    public void playTrack(int i) {
        System.out.println("Playing " + tracks.get(i) + " by " + artist + " from " + title);
    }
}
