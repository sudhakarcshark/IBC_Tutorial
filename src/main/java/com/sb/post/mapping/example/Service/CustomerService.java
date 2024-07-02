package com.sb.post.mapping.example.Service;

import com.sb.post.mapping.example.Request.CustomerRequest;
import com.sb.post.mapping.example.Response.APIResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    ResponseEntity<APIResponse> createCustomer(CustomerRequest request);
    ResponseEntity<APIResponse> getCustomers();
    ResponseEntity<APIResponse> getByCustomerId(long customerId);
    ResponseEntity<APIResponse> deleteByCustomerId(long customerId);
    ResponseEntity<APIResponse> upDateCustomerDetails(long customerId, CustomerRequest request);
}
