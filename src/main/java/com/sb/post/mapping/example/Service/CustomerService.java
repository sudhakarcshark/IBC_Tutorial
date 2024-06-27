package com.sb.post.mapping.example.Service;

import com.sb.post.mapping.example.Request.CustomerRequest;
import com.sb.post.mapping.example.Response.APIResponse;
import org.springframework.http.ResponseEntity;

public interface CustomerService {

    ResponseEntity<APIResponse> createCustomer(CustomerRequest request);
}
