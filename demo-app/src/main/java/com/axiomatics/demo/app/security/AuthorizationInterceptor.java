package com.axiomatics.demo.app.security;

import com.axiomatics.springboot.AxiomaticsSpringPep;
import com.axiomatics.springboot.annotation.AuthorizeWithAxiomaticsPDP;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class AuthorizationInterceptor implements HandlerInterceptor {

    @Autowired
    private AxiomaticsSpringPep pep;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("In filter");
        try {
            if (handler instanceof HandlerMethod handlerMethod) {
                AuthorizeWithAxiomaticsPDP annotation = AnnotationUtils.findAnnotation(handlerMethod.getMethod(), AuthorizeWithAxiomaticsPDP.class);
                if (annotation == null) {
                    annotation = AnnotationUtils.findAnnotation(handlerMethod.getBeanType(), AuthorizeWithAxiomaticsPDP.class);
                }
                if (annotation != null) {
                    log.info("Annotation " + annotation.annotationType() + " found on method " + handlerMethod.getMethod() + ", calling PEP");
                    boolean permit = pep.accept(handlerMethod, request, response);
                    if (!permit) {

                        log.info("Already commited: " + response.isCommitted());
                        if (response.isCommitted()) {
                            throw new RuntimeException("Response already committed");
                        }

                        response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access denied by PDP");
                        response.getWriter().write("Access denied by policy");
                        response.getWriter().flush();
                        return false; // 👈 Stop further processing
                    }
                } else {
                    log.info("Annotation " + AuthorizeWithAxiomaticsPDP.class.getSimpleName() + " not found on method " + handlerMethod.getMethod() + ", not calling PEP");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error in filter", e);
        }

        return true;  // ✅ Allow request to proceed
    }
}
