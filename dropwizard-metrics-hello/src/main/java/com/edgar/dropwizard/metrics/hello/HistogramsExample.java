package com.edgar.dropwizard.metrics.hello;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Histogram;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * Created by Edgar on 2017/3/3.
 *
 * @author Edgar  Date 2017/3/3
 */
public class HistogramsExample {

  private static final MetricRegistry metrics = new MetricRegistry();
  private static final Histogram
          responseSizes = metrics.histogram(
          MetricRegistry.name("com.edgar.requestHandler",
                                                                "response-sizes"));

  public static void handleRequest() {
    // etc
    responseSizes.update(randomString().length());
  }

  public static void main(String[] args) {
    startReport();
    Runnable r = () -> {
      for (int i = 0; i < 30; i ++) {
        handleRequest();
        wait1Seconds();
      }
    };
    new Thread(r).start();
    wait5Seconds();
  }

  public static String randomString() {
    int len = new Random().nextInt(100);
    StringBuilder sb = new StringBuilder();
    Random random = ThreadLocalRandom.current();
    String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    int range = base.length();
    for (int i = 0; i < len; i++) {
      sb.append(base.charAt(random.nextInt(range)));
    }
    return sb.toString();
  }

  static void startReport() {
    ConsoleReporter reporter = ConsoleReporter.forRegistry(metrics)
            .convertRatesTo(TimeUnit.SECONDS)
            .convertDurationsTo(TimeUnit.MILLISECONDS)
            .build();
    reporter.start(1, TimeUnit.SECONDS);
  }

  static void wait1Seconds() {
    try {
      Thread.sleep(1*1000);
    }
    catch(InterruptedException e) {}
  }

  static void wait2Seconds() {
    try {
      Thread.sleep(2*1000);
    }
    catch(InterruptedException e) {}
  }

  static void wait5Seconds() {
    try {
      Thread.sleep(5*1000);
    }
    catch(InterruptedException e) {}
  }
}
