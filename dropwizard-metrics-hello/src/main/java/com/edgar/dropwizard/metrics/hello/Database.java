package com.edgar.dropwizard.metrics.hello;

import java.util.Random;

/**
 * Created by Edgar on 2017/3/3.
 *
 * @author Edgar  Date 2017/3/3
 */
public class Database {
  //可以使用SELECT 1来ping数据库
  public boolean ping() {
    return new Random().nextInt(100) % 2 == 0;
  }
}
