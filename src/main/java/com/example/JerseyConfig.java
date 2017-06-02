package com.example;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * Created by csdaniel on 2016/7/28.
 */
@Component
@Configuration
public class JerseyConfig extends ResourceConfig{

    public JerseyConfig() {
        packages(true, "com.example");
    }
}
