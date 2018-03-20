import java.lang.annotation.*;

@Target ({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)

public @interface Campo {
    int maxLength() default 0;
    boolean required() default false;
    String label();
}
