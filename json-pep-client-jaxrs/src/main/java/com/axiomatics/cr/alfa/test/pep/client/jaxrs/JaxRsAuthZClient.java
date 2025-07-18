package com.axiomatics.cr.alfa.test.pep.client.jaxrs;

import com.axiomatics.cr.alfa.test.pep.client.AuthZClient;
import com.axiomatics.cr.alfa.test.pep.client.ClientConfiguration;
import com.axiomatics.cr.alfa.test.pep.model.Request;
import com.axiomatics.cr.alfa.test.pep.model.Response;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;

import java.util.Objects;

import static com.axiomatics.cr.alfa.test.pep.client.PDPConstants.CONTENT_TYPE;

/**
 * Builds a JAX-RS to invoke a Policy Decision Point.
 * It supports both the JSON Profile of XACML 1.0 (where the response could be either an Object or
 * an Array) and the JSON Profile of XACML 1.1 (where the response is always an array - to simplify
 * things)
 *
 * @author djob
 */
public class JaxRsAuthZClient implements AuthZClient {

    protected final Invocation.Builder requestInvocationBuilder;

    public JaxRsAuthZClient(Invocation.Builder requestInvocationBuilder) {
        this.requestInvocationBuilder = requestInvocationBuilder;
    }

    public JaxRsAuthZClient(ClientConfiguration clientConfiguration) {

        Objects.requireNonNull(clientConfiguration, "Client configuration must be non-null");
        Objects.requireNonNull(clientConfiguration.getAuthorizationServiceUrl(),
                "Client configuration must contain a non-null authorizationServiceUrl URL");

        ClientBuilder builder = ClientBuilder.newBuilder();
        if (clientConfiguration.getSSLContext() != null){
            builder.sslContext(clientConfiguration.getSSLContext());
        }

        Client client = builder.build();

        // Username (and Password) should be provided if PDP requires Basic Authentication
        if (null != clientConfiguration.getUsername()) {
            client.register(HttpAuthenticationFeature.basic(
                    clientConfiguration.getUsername(),
                    clientConfiguration.getPassword())
            );
        }
        this.requestInvocationBuilder = client
                .target(clientConfiguration.getAuthorizationServiceUrl())
                .request(CONTENT_TYPE);
    }

    /**
     * Sends the request object to the PDP and returns the response from PDP
     * <p>
     * The Response object is in the format of JSON Profile of XACML 1.1,
     * where the response contains an array of results.
     *
     * @param request the XACML request object
     * @return the response object
     */
    @Override
    public Response makeAuthorizationRequest(Request request) {
        return requestInvocationBuilder.post(Entity.entity(request, CONTENT_TYPE), Response.class);
    }
}
