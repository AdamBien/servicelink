package com.airhacks.servicelink.boundary;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

/**
 * A mandatory qualifier to inject containers within a user-defined network. For
 * legacy-links use the annotation {@link LegacyLink}. Injection of
 * <code>String</code> and <code>WebTarget</code> is supported equally well.
 *
 * @author airhacks.com
 */
@Qualifier
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Link {

    /**
     *
     * An optional name of the container link
     * (<code>--link container-name:<b>name</b></code>) If empty, the name is
     * derived from the uppercase field name
     *
     * @return the configured name of the link
     */
    @Nonbinding
    String name() default "";

    /**
     *
     * @return the number of the exposed port, regardless of its binding.
     */
    @Nonbinding
    int portNumber() default 8080;

    /**
     * Fully optional path, which is going to be added to the generated URI.
     *
     * @return application-specific path (e.g. REST resource)
     */
    @Nonbinding
    String path() default "";
}
