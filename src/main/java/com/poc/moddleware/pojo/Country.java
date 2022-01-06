package com.poc.moddleware.pojo;

public class Country {
    private String name;
    private Long polulation;
    private String capital;
    private String currency;

    public Country(String name, Long polulation, String capital, String currency) {
	super();
	this.name = name;
	this.polulation = polulation;
	this.capital = capital;
	this.currency = currency;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Long getPolulation() {
	return polulation;
    }

    public void setPolulation(Long polulation) {
	this.polulation = polulation;
    }

    public String getCapital() {
	return capital;
    }

    public void setCapital(String capital) {
	this.capital = capital;
    }

    public String getCurrency() {
	return currency;
    }

    public void setCurrency(String currency) {
	this.currency = currency;
    }

}
