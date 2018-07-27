//package Spring.soundSystem;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//@EnableAspectJAutoProxy
//public class TrackCounterConfig {
//    @Bean
//    public CompactDisc sgtPeppers() {
//        BlankDisc cd = new BlankDisc();
//        cd.setArtist("The Beatles");
//        cd.setTitle("Sgt. Pepper's Lonely Hearts Club band");
//        List<String> tracks = new ArrayList<>();
//        tracks.add("Sgt. Pepper's Lonely Hearts Club Band");
//        tracks.add("With a Little Help from My Friends");
//        tracks.add("Lucy in the Sky with Diamonds");
//        tracks.add("Getting Better");
//        tracks.add("Fixing a Hole");
//        cd.setTracks(tracks);
//        System.out.println("123");
//        return cd;
//    }
//    @Bean
//    public TrackCounter trackCounter() {
//        System.out.println("zzzz");
//        return new TrackCounter();
//    }
//}
