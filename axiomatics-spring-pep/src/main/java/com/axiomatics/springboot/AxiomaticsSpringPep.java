
package com.axiomatics.springboot;

import com.axiomatics.cr.alfa.test.pep.client.AuthZClient;
import com.axiomatics.cr.alfa.test.pep.model.Category;
import com.axiomatics.cr.alfa.test.pep.model.PDPDecision;
import com.axiomatics.cr.alfa.test.pep.model.Request;
import com.axiomatics.cr.alfa.test.pep.model.Response;
import com.axiomatics.springboot.internal.common.AuthZClientBuilder;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@Component
@Slf4j
public class AxiomaticsSpringPep {

    @Autowired
    AxiomaticsPepSettings config;

    public boolean accept(HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) {
        log.info("Creating authorization request");

        AuthZClient authZClient = new AuthZClientBuilder().from(config);
        Request authzRequest = buildRequest(handlerMethod, request, response);
        log.info("Authorization request created " + authzRequest.toString());
        Response authZResponse = authZClient.makeAuthorizationRequest(authzRequest);
        log.info("Authorization response: {}", authZResponse);
        boolean deny = authZResponse.getResults().stream().filter(
                result -> result.getDecision() != PDPDecision.PERMIT).findAny().isPresent();
        return !deny;
    }

    private static Request buildRequest(HandlerMethod handlerMethod, HttpServletRequest request, HttpServletResponse response) {
        //This builds an Authorization request from the spring context. It is very generic and for demo purposes.
        //You should tailor this to your needs.

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String user = auth.getName();
        WebAuthenticationDetails details = (WebAuthenticationDetails) auth.getDetails();
        Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
        String method = handlerMethod.getMethod().getName();
        Map<String, String[]> parameterMap = request.getParameterMap();
        Map<String, Object> attributes = new HashMap<>();
        request.getAttributeNames().asIterator().forEachRemaining(n -> attributes.put(n, request.getAttribute(n)));

        Request authRequest = new Request();
        Category subjects = new Category("AccessSubject");
        authRequest.addAccessSubjectCategory(subjects);
        subjects.addAttribute("user", user);
        subjects.addAttribute("user.source-ip", details.getRemoteAddress());

        roles.forEach(role -> subjects.addAttribute("role", role.getAuthority()));

        Category actions = new Category("Action");
        actions.addAttribute("action", request.getMethod());
        authRequest.addActionCategory(actions);

        Category resources = new Category("Resource");
        authRequest.addResourceCategory(resources);

        resources.addAttribute("method", method);
        parameterMap.entrySet().forEach(entry -> {
            String key = entry.getKey();
            if (entry.getValue() != null) {
                Arrays.stream(entry.getValue()).forEach(value -> resources.addAttribute(key, value));
            }
        });
        attributes.entrySet().forEach(entry -> {
            String key = entry.getKey();
            resources.addAttribute(key, entry.getValue().toString());
        });

        return authRequest;
    }
}


