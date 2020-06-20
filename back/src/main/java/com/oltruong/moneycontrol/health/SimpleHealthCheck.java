package com.oltruong.moneycontrol.health;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;

@Liveness
@ApplicationScoped
public class SimpleHealthCheck implements HealthCheck {

    @ConfigProperty(name = "quarkus.mongodb.database")
    String database;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse.named("Health check")
                                  .up()
                                  .withData("database", database)
                                  .build();

    }
}
