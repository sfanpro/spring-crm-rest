package com.goodteam.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goodteam.springdemo.entity.Customer;
import com.goodteam.springdemo.service.CustomerService;

@RestController
@RequestMapping("/api")
public class CustomerRestController {
	// autowire the CustomerService
	@Autowired
	private CustomerService customerService;

	// add mapping to get customers
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}

	// add mapping for GET /customers/{custumerId}
	@GetMapping("/customers/{custumerId}")
	public Customer getCustomer(@PathVariable int custumerId) {
		Customer theCustomer = customerService.getCustomer(custumerId);
		if (theCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found - " + custumerId);
		}
		return theCustomer;
	}
}
