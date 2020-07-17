package AT;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DBTable {
    public String name() default "";
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface Constrains {
    boolean primaryKey() default false;
    boolean allowNull() default true;
    boolean unique() default false;
}
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLString {
    int value() default 0;
    String name() default "";
    Constrains contraints() default @Constrains;
}

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@interface SQLInteger {
    String name() default "";
    Constrains contraints() default @Constrains;
}