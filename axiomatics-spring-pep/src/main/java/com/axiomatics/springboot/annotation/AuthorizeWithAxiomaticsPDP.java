package com.axiomatics.springboot.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorizeWithAxiomaticsPDP {
}
