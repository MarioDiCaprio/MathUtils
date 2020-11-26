package mathUtils.dev;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Experimental {

    String doc() default
            "This feature is experimental;" +
            "use at own risk.";

}
