package com.example.khoildm;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) // keep annotation at runtime.
@Target(ElementType.TYPE) //can use in class only.
public @interface Table {    
    // should contain the name of the table in the database
    String name() default "";
}