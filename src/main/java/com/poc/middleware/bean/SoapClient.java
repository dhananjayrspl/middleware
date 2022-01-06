package com.poc.middleware.bean;

import org.springframework.boot.webservices.client.HttpWebServiceMessageSenderBuilder;
import org.springframework.boot.webservices.client.WebServiceTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

@Configuration
public class SoapClient extends WebServiceGatewaySupport {

    @Bean
    public Jaxb2Marshaller marshaller() {
	Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	String[] packagesToScan = { "com.soap.obj.countries" };
	marshaller.setPackagesToScan(packagesToScan);
	return marshaller;
    }


     
    @Bean(name = "WebServiceTemplate")
    public WebServiceTemplate webServiceTemplate(WebServiceTemplateBuilder builder) {
	Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	String[] packagesToScan = { "com.soap.obj.countries" };
	marshaller.setPackagesToScan(packagesToScan);
	builder.setMarshaller(marshaller);
	return builder.messageSenders(new HttpWebServiceMessageSenderBuilder().build()).build();
    }

}
