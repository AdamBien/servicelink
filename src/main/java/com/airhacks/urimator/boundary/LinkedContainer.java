package com.airhacks.urimator.boundary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 *
 * @author airhacks.com
 */
@Qualifier
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface LinkedContainer {

    @Nonbinding
    String linkName() default "";

    @Nonbinding
    int portNumber() default 8080;

    @Nonbinding
    String resource() default "";
}
