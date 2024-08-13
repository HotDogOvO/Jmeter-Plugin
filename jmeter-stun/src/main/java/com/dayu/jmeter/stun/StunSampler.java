package com.dayu.jmeter.stun;

import com.dayu.jmeter.stun.client.StunClient;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

public class StunSampler extends AbstractJavaSamplerClient {

    @Override
    public Arguments getDefaultParameters() {
        Arguments arguments = new Arguments();
        return arguments;
    }

    @Override
    public SampleResult runTest(JavaSamplerContext javaSamplerContext) {
        SampleResult result = new SampleResult();
        try{
            StunClient.send();
            result.setSuccessful(true);
        } catch (Exception e){
            result.setSuccessful(false);
        }
        return result;
    }


}
