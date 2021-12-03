package com.sniffer.throttling;

import java.util.Timer;
import java.util.TimerTask;

public class ThrottleImpl implements Throttler {

    private final int throughput;
    private final Handler handler;
    private final Tenant tenant;

    public ThrottleImpl(int throughput, Tenant tenant, Handler handler) {
        this.throughput = throughput;
        this.handler = handler;
        this.tenant = tenant;
    }

    @Override
    public void start() {

        new Timer(true).schedule(new TimerTask() {

            @Override
            public void run() {
                handler.reset(tenant.getName());
            }
        }, 1000, throughput);
    }
}
