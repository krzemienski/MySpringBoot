package com.harishkannarao.jdbc.controller;

import com.harishkannarao.jdbc.dao.CustomerDao;
import com.harishkannarao.jdbc.domain.CreateCustomerRequestDto;
import com.harishkannarao.jdbc.domain.Customer;
import com.harishkannarao.jdbc.domain.DeleteCustomerRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/customers", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomersRestController extends AbstractBaseController {

    private final CustomerDao customerDao;

    @Autowired
    public CustomersRestController(@Qualifier("myCustomerDao") CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> createCustomer(
            @RequestBody CreateCustomerRequestDto requestDto
    ) {
        customerDao.createCustomer(requestDto.getFirstName(), requestDto.getLastName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Void> createCustomerWithTransaction(
            @RequestBody CreateCustomerRequestDto requestDto
    ) {
        customerDao.createCustomer(requestDto.getFirstName(), requestDto.getLastName());
        throw new RuntimeException("Bang Bang");
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCustomer(
            @RequestBody DeleteCustomerRequestDto requestDto
    ) {
        customerDao.deleteCustomer(requestDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
