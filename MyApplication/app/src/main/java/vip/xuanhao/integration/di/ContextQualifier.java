package vip.xuanhao.integration.di;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Xuanhao on 2016/11/3.
 */


@Qualifier
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextQualifier {

    String value() default "Application";
}
