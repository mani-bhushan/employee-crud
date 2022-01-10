package com.apps.org.custom.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mapstruct.Qualifier;


@Retention(RetentionPolicy.CLASS)
@Qualifier
@Target(ElementType.METHOD)
public @interface DesignationMapper {
}
