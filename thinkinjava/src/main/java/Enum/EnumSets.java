package Enum;

import java.util.EnumSet;

enum AlarmPoints {
    Stair1, Stair2, Lobby, Office1, Office2, Office3,
    Office4, BathRoom, Utilty, Kitchen
}
public class EnumSets {
    public static void main(String[] args) {
        EnumSet<AlarmPoints> points = EnumSet.noneOf(AlarmPoints.class);
        points.add(AlarmPoints.BathRoom);
        System.out.println(points);
        points.addAll(EnumSet.of(AlarmPoints.Stair1, AlarmPoints.Stair2));
        System.out.println(points);
        points.addAll(EnumSet.allOf(AlarmPoints.class));
        System.out.println(points);
        points.removeAll(EnumSet.of(AlarmPoints.Stair1, AlarmPoints.Stair2));
        System.out.println(points);
        points = EnumSet.complementOf(points);
        System.out.println(points);
    }
}
