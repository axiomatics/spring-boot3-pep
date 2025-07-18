package com.axiomatics.cr.alfa.test.pep.client;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.net.ssl.SSLContext;


@Getter @Setter @Builder
public class DefaultClientConfiguration implements ClientConfiguration {

    String authorizationServiceUrl;
    String username;
    String password;
    SSLContext SSLContext;

    public static DefaultClientConfigurationBuilder builder() {
        return new DefaultClientConfigurationBuilder();
    }
}
