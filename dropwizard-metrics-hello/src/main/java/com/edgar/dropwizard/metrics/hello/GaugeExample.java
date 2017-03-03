package com.edgar.dropwizard.metrics.hello;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.Meter;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by Edgar on 2017/3/3.
 *
 * @author Edgar  Date 2017/3/3
 */
public class GaugeExample {

  private static final MetricRegistry metrics = new MetricRegistry();

  public static void main(String[] args) {
    startReport();
    Queue queue = new QueueManager(metrics, "job").getQueue();
    Runnable r = () -> {
      for (int i = 0; i < 3; i ++) {
        queue.offer(i);
        wait1Seconds();
      }
    };
    new Thread(r).start();
    wait5Seconds();
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
