package com.luv2code.springdemo.rest;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("/customers/{customerId}")
    public Customer getCustomer(@PathVariable int customerId) {

        Customer customer = customerService.getCustomer(customerId);
        if(customer == null) {
            throw new CustomerNotFoundException(String.format("Customer with id %d not found.", customerId));
        }
        return customer;
    }

    @PostMapping("/customers")
    public HttpStatus addCustomer(@RequestBody Customer theCustomer) {
        theCustomer.setId(0);

        customerService.saveCustomer(theCustomer);

        return HttpStatus.CREATED;
    }

    @PutMapping("/customers")
    public HttpStatus updateCustomer(@RequestBody Customer customer) {

        boolean doesCustomerExist = customerService.getCustomer(customer.getId()) != null;
        if(doesCustomerExist) {
            customerService.saveCustomer(customer);
        } else {
            throw new CustomerNotFoundException(String.format("Customer with id %d not found", customer.getId()));
        }
        return HttpStatus.OK;
    }

    @DeleteMapping("/customers/{customerId}")
    public HttpStatus deleteCustomer(@PathVariable int customerId) {
        boolean doesCustomerExist = customerService.getCustomer(customerId) != null;
        if(doesCustomerExist) {
            customerService.deleteCustomer(customerId);
        } else {
            throw new CustomerNotFoundException(String.format("Customer with id %d not found", customerId));
        }
        return HttpStatus.OK;
    }

}
