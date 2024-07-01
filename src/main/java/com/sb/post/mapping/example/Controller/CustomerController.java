package com.sb.post.mapping.example.Controller;

import com.sb.post.mapping.example.Request.CustomerRequest;
import com.sb.post.mapping.example.Service.CustomerService;
import com.sb.post.mapping.example.Response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse> createCustomer(@RequestBody CustomerRequest request){

        return customerService.createCustomer(request);

    }

    @GetMapping("/getAll")
    public ResponseEntity<APIResponse> getAllCustomers(){

        return customerService.getCustomers();
    }

    @GetMapping("getById/{customerId}")
    public ResponseEntity<APIResponse> getAllCustomers(@PathVariable long customerId){

        return customerService.getByCustomerId(customerId);

    }
    @DeleteMapping("deleteById/{customerId}")
    public ResponseEntity<APIResponse> deleteById(@PathVariable long customerId){

        return customerService.deleteByCustomerId(customerId);

    }
}




