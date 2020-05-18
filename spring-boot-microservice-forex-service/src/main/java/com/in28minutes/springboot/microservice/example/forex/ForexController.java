package com.in28minutes.springboot.microservice.example.forex;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {

	@Autowired
	private Environment environment;

	@Autowired
	private ExchangeValueRepository repository;

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {

		System.out.println("ForexController:retrieveExchangeValue executed");
		
		ExchangeValue exchangeValue = repository.findByToAndFrom(to, from);

		exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

		return exchangeValue;
	}
	
	@GetMapping("/currency-exchange/from/{to}")
	public List<ExchangeValue> retrieveExchangeValueTo(@PathVariable String to) {
		
		System.out.println("ForexController:retrieveExchangeValueTo executed");
		
		List<ExchangeValue> exchanges = new ArrayList<ExchangeValue>();
		
		List<ExchangeValue> exchangeValue = repository.findByTo(to);
		for (Iterator<ExchangeValue> iterator = exchangeValue.iterator(); iterator.hasNext();) {
			ExchangeValue exchangeValue2 = (ExchangeValue) iterator.next();
			exchangeValue2.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
			exchanges.add(exchangeValue2);
		}

		return exchanges;
	}
}