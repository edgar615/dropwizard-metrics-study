package com.edgar.dropwizard.metrics.hello;

import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueManager {
  private final Queue queue;

  public QueueManager(MetricRegistry metrics, String name) {
    this.queue = new LinkedBlockingQueue();
    //metric的名称：com.example.QueueManager.${name}.size"
    metrics.register(MetricRegistry.name(QueueManager.class, name, "size"),
                     new Gauge<Integer>() {
                       @Override
                       public Integer getValue() {
                         return queue.size();
                       }
                     });
  }

  public Queue getQueue() {
    return queue;
  }
}