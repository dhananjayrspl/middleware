package com.poc.middleware.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Source;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.poc.moddleware.pojo.Country;
import com.soap.obj.countries.GetCountryRequest;
import com.soap.obj.countries.GetCountryResponse;


@RestController
public class MiddlewareController {
    
    @Autowired
    WebServiceTemplate webServiceTemplate;
    
    @Autowired
    Jaxb2Marshaller jaxb2Marshaller;
    
    @GetMapping("/test")
    public List<String> all() {
	List<String> dj = new ArrayList<>();
	dj.add("Dhananjay Chauhan");
	dj.add("Hitesh G");
	return dj;
    }

    @GetMapping("/countryCode/{code}")
    public Country getCountryInfo(@PathVariable String code) {
	GetCountryRequest req = new GetCountryRequest();
	req.setName(code);
	webServiceTemplate.setMarshaller(jaxb2Marshaller);
	webServiceTemplate.setUnmarshaller(jaxb2Marshaller);
	GetCountryResponse response = (GetCountryResponse) webServiceTemplate.marshalSendAndReceive("http://localhost:8081/service/countries-details",req);
	final com.soap.obj.countries.Country country = response.getCountry();
	return new Country(country.getName(),Long.valueOf(country.getPopulation()),country.getCapital(),country.getCurrency().name());
    }

}
