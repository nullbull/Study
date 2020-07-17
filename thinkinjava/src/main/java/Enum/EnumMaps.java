package Enum;

import java.util.EnumMap;

interface Command { void action();}

public class EnumMaps {
    public static void main(String[] args) {
        EnumMap<AlarmPoints, Command> em = new EnumMap<AlarmPoints, Command>(AlarmPoints.class);
        em.put(AlarmPoints.Kitchen, new Command() {
            @Override
            public void action() {
                System.out.println("Kitchen fire!");
            }
        });
        em.put(AlarmPoints.BathRoom, new Command() {
            @Override
            public void action() {
                System.out.println("BathRoom alert");
            }
        });
        for(AlarmPoints entry: em.keySet()) {
            System.out.println(entry + ":");
            em.get(entry).action();
        }
    }
}
