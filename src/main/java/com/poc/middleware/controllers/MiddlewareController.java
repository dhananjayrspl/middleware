package com.poc.middleware.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.poc.middleware.bean.SOAPConnector;
import com.poc.moddleware.pojo.Country;
import com.soap.obj.countries.GetCountryRequest;
import com.soap.obj.countries.GetCountryResponse;

@RestController
public class MiddlewareController {

    @Autowired
    SOAPConnector soapConnector;

    @Autowired
    Jaxb2Marshaller jaxb2Marshaller;

    @GetMapping("/test")
    public List<String> all() {
	List<String> dj = new ArrayList<>();
	dj.add("Dhananjay Chauhan");
	return dj;
    }

    @GetMapping("/countryCode/{code}")
    public Country getCountryInfo(@PathVariable String code) {
	GetCountryRequest req = new GetCountryRequest();
	req.setName(code);
	GetCountryResponse response = (GetCountryResponse) soapConnector.callWebService(soapConnector.getDefaultUri(), req);
	final com.soap.obj.countries.Country country = response.getCountry();
	return new Country(country.getName(), Long.valueOf(country.getPopulation()), country.getCapital(), country.getCurrency().name());
    }

}
