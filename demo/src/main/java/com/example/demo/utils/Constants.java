package com.example.demo.utils;

import lombok.Data;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Data
@Component
public class Constants implements EnvironmentAware {

    private static ApplicationContext applicationContext = new GenericApplicationContext();

    private static Environment environment = applicationContext.getEnvironment();

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

   // @Value("${checkavailable.uri: Not assigned}")
   public static final String CHECK_AVAILABLE_URI = environment.getProperty("checkavailable.uri");
}
