package com.edgar.dropwizard.metrics.hello;

import com.codahale.metrics.Counter;
import com.codahale.metrics.Gauge;
import com.codahale.metrics.MetricRegistry;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class CounterQueueManager {
  private final Queue queue;

  private final Counter pendingJobs;

  public CounterQueueManager(MetricRegistry metrics, String name) {
    this.queue = new LinkedBlockingQueue();
    //metric的名称：com.example.QueueManager.${name}.size"
    pendingJobs = metrics.counter(MetricRegistry.name(QueueManager.class, name));
  }

  public Queue getQueue() {
    return queue;
  }

  public void add(int i) {
    pendingJobs.inc();
    queue.add(i);
  }

  public Object take() {
    pendingJobs.dec();
    return queue.poll();
  }
}