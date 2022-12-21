package top.himenma.rinetdconfig.common.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
*   日志注解，用在controller层接口上
* */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LogHandler {
    @AliasFor("value")
    String title() default "";

    @AliasFor("title")
    String value() default "";
}
