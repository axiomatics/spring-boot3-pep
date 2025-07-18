package com.axiomatics.springboot;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Data
@ConfigurationProperties("axiomatics.pep.pdp")
public class AxiomaticsPepSettings {

    private String url;
    private String username;
    private String password;

}
