package com.in28minutes.springboot.microservice.example.forex;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	ExchangeValue findByFromAndTo(String from, String to);
	ExchangeValue findByToAndFrom(String to, String from);
	List<ExchangeValue> findByTo(String to);
}