package com.goodteam.springdemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

	// add mapping for POST /customers - add new customer
	@PostMapping("/customers")
	public Customer addCustomer(@RequestBody Customer theCustomer) {
		// to save a new item instead update - set id to 0
		theCustomer.setId(0);
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}

	// add mapping for PUT /customers - update existing customer
	@PutMapping("/customers")
	public Customer updateCustomer(@RequestBody Customer theCustomer) {
		// to save a new item instead update - set id to 0
		customerService.saveCustomer(theCustomer);
		return theCustomer;
	}

	// add mapping for DELETE /customers/{custumerId} - delete customer
	@DeleteMapping("/customers/{custumerId}")
	public String deleteCustomer(@PathVariable int custumerId) {
		Customer tempCustomer = customerService.getCustomer(custumerId);

		if (tempCustomer == null) {
			throw new CustomerNotFoundException("Customer id not found: " + custumerId);
		}
		customerService.deleteCustomer(custumerId);

		return "Deleted cuustomer, id: " + custumerId;
	}

}
