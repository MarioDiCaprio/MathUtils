package mathUtils.dev;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ComingSoon {

    String doc() default
            "This feature is not officially released" +
            "yet and will be available in a newer version." +
            "Do not use.";

}
