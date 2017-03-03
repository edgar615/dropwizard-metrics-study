package com.edgar.dropwizard.metrics.hello;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.health.HealthCheck;
import com.codahale.metrics.health.HealthCheckRegistry;

import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by Edgar on 2017/3/3.
 *
 * @author Edgar  Date 2017/3/3
 */
public class HealthCheckExample {

  private static final HealthCheckRegistry healthChecks = new HealthCheckRegistry();

  public static void main(String[] args) {
    DatabaseHealthCheck healthCheck = new DatabaseHealthCheck(new Database());
    healthChecks.register("database", healthCheck);
    for (int i = 0; i < 10; i ++) {
      check();
    }
  }

  private static void check() {
    final Map<String, HealthCheck.Result> results = healthChecks.runHealthChecks();
    for (Map.Entry<String, HealthCheck.Result> entry : results.entrySet()) {
      if (entry.getValue().isHealthy()) {
        System.out.println(entry.getKey() + " is healthy");
      } else {
        System.err.println(entry.getKey() + " is UNHEALTHY: " + entry.getValue().getMessage());
        final Throwable e = entry.getValue().getError();
        if (e != null) {
          e.printStackTrace();
        }
      }
    }
  }
}
