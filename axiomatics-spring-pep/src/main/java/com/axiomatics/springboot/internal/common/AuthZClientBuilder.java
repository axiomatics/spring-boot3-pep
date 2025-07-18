
package com.axiomatics.springboot.internal.common;

import com.axiomatics.cr.alfa.test.pep.client.AuthZClient;
import com.axiomatics.cr.alfa.test.pep.client.DefaultClientConfiguration;
import com.axiomatics.cr.alfa.test.pep.client.jaxrs.JaxRsAuthZClient;
import com.axiomatics.springboot.AxiomaticsPepSettings;

public class AuthZClientBuilder {

    public AuthZClient from(AxiomaticsPepSettings config) {

        String url = config.getUrl();
        String username = config.getUsername();
        String password = config.getPassword();

        DefaultClientConfiguration.DefaultClientConfigurationBuilder builder = DefaultClientConfiguration.builder()
                .authorizationServiceUrl(url)
                .username(username)
                .password(password);
        DefaultClientConfiguration clientConfiguration = builder.build();

        return new JaxRsAuthZClient(clientConfiguration);
    }
}

