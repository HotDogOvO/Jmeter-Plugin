package com.dayu.jmeter.stun.client;

import org.ice4j.ResponseCollector;
import org.ice4j.StunResponseEvent;
import org.ice4j.StunTimeoutEvent;

public class Response implements ResponseCollector {
    @Override
    public void processResponse(StunResponseEvent event) {
        System.out.println("Received STUN response:");
        System.out.println(event);
    }

    @Override
    public void processTimeout(StunTimeoutEvent event) {
        System.out.println("STUN request timed out.");

    }
}
