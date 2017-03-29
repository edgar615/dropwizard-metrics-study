package com.edgar.dropwizard.metrics.hello;

import com.codahale.metrics.Meter;
import com.codahale.metrics.RatioGauge;
import com.codahale.metrics.Timer;

public class CacheHitRatio extends RatioGauge {
    private final Meter hits;
    private final Timer calls;

    public CacheHitRatio(Meter hits, Timer calls) {
        this.hits = hits;
        this.calls = calls;
    }

    @Override
    public Ratio getRatio() {
        return Ratio.of(hits.getOneMinuteRate(),
                        calls.getOneMinuteRate());
    }
}