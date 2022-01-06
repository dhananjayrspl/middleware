package com.poc.middleware.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class SoapConfig {

    @Bean
    public Jaxb2Marshaller marshaller() {
	Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	String[] packagesToScan = { "com.soap.obj.countries" };
	marshaller.setPackagesToScan(packagesToScan);
	return marshaller;
    }

    @Bean
    public SOAPConnector soapConnector(Jaxb2Marshaller marshaller) {
	SOAPConnector client = new SOAPConnector();
	client.setDefaultUri("http://localhost:8081/service/countries-details");
	client.setMarshaller(marshaller);
	client.setUnmarshaller(marshaller);
	return client;
    }

}
