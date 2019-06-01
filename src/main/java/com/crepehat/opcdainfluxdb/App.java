package com.crepehat.opcdainfluxdb;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

import com.hurence.opc.auth.NtlmCredentials;
import com.hurence.opc.da.OpcDaConnectionProfile;
import com.hurence.opc.da.OpcDaOperations;
import com.hurence.opc.da.OpcDaTemplate;

// import org.slf4j.Logger;

/**
 * Hello world!
 *
 */
public class App {
    // static final Logger logger = Logger.getLogger(App.class);
    public static void main(String[] args) throws URISyntaxException {
//         log4j.rootLogger=debug, stdout
// log4j.appender.stdout=org.apache.log4j.ConsoleAppender
// log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
// log4j.appender.stdout.layout.ConversionPattern=%-4r [%t] %-5p %c %x - %m%n
        System.out.println("Hello World!");
        // create a connection profile
        // OpcDaConnectionProfile
        OpcDaConnectionProfile connectionProfile = new OpcDaConnectionProfile()
                // change with the appropriate clsid
                .withComClsId("F8582CF2-88FB-11D0-B850-00C0F0104305")
                .withCredentials(new NtlmCredentials().withDomain("OPC-DOMAIN").withUser("OPC").withPassword("opc"))
                .withConnectionUri(new URI("opc.da://192.168.99.100"))
                .withSocketTimeout(Duration.of(5, ChronoUnit.SECONDS));

        // Create an instance of a da operations
        OpcDaOperations opcDaOperations = new OpcDaTemplate();
        // connect using our profile
        opcDaOperations.connect(connectionProfile).ignoreElement().blockingAwait();
        try {
            opcDaOperations.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
