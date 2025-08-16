package com.microservices.core.util.http;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Objects;

@Slf4j
@Component
public class ServiceUtil {

    @Value("${server.port}")
    private String port;

    private String serviceAddress;

    public String getAddress() {
        if(Objects.isNull(serviceAddress)) {
            serviceAddress = findMyHostName() + "/" + findMyIPAddress() + ":" + port;
        }

        return serviceAddress;
    }

    private String findMyHostName() {
        try {
            return InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException uhe) {
            log.warn("Unable to locate host: {}", uhe.getMessage());
            return "UNKNOWN HOST";
        }
    }

    private String findMyIPAddress() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        }catch (UnknownHostException uhe) {
            log.warn("Unable to locate IP: {}", uhe.getMessage());
            return "UNKNOWN IP";
        }
    }
}
