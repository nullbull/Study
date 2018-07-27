package AT;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UseCaseTracker {
    public static void trackUseCases(List<Integer> useCases, Class<?> T) {
        for (Method m : T.getDeclaredMethods()) {
            UseCase useCase = m.getAnnotation(UseCase.class);
            if(useCase != null) {
                System.out.println("Found use Case: " + useCase.id() + " " + useCase.description());
                useCases.remove(new Integer(useCase.id()));
            }
        }
        for(int i : useCases) {
            System.out.println("Warning: Missing use case-" + i);
        }
    }

    public static void main(String[] args) {
        List<Integer> useCases = new ArrayList<>();
        Collections.addAll(useCases, 47, 48, 49, 50);
        trackUseCases(useCases, PasswordUtils.class);
    }
}
