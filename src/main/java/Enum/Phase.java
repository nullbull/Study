package Enum;

import java.util.EnumMap;
import java.util.Map;

public enum Phase {
    SOLID, LIQUID, GAS;
    public enum Transition {
        ;
        private final Phase src;
        private final Phase dst;

        private Transition(Phase src, Phase dst) {
            this.src = src;
            this.dst = dst;
        }
        public static final Map<Phase, EnumMap<Phase, Transition>> m = new EnumMap<Phase, EnumMap<Phase, Transition>>(Phase.class);
        static {
            for (Phase p : Phase.values()) {
                m.put(p, new EnumMap<Phase, Transition>(Phase.class));
            }
            for (Transition t : Transition.values()) {
                m.get(t.src).put(t.dst, t);
            }
        }
    }
}
