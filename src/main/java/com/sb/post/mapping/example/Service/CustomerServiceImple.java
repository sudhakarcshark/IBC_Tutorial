package com.sb.post.mapping.example.Service;


import com.sb.post.mapping.example.Model.CustomerModel;
import com.sb.post.mapping.example.Request.CustomerRequest;
import com.sb.post.mapping.example.Response.APIResponse;
import com.sb.post.mapping.example.Response.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerServiceImple implements CustomerService {

   private static List<CustomerModel> customers = new ArrayList<>();

   private AtomicInteger at = new AtomicInteger();


    @Override
    public ResponseEntity<APIResponse> createCustomer(CustomerRequest request) {

        customers.add(requesttoModelMapper(request));

        return ResponseEntity.ok(
                APIResponse.builder()
                        .errorCode(0)
                        .data( customers.stream().map(customerModel -> modeltoResponse(customerModel)).toList())
                        .build()

        );
    }

    @Override
    public ResponseEntity<APIResponse> getCustomers() {

        return ResponseEntity.ok(
                APIResponse.builder()
                        .errorCode(000)
                        .data(customers.stream().map(customerModel -> modeltoResponse(customerModel)).toList())
                        .build()
        );
    }

    private CustomerResponse modeltoResponse(CustomerModel model){

        return CustomerResponse.builder()
                .customerId(model.getCustomerId())
                .customerName(model.getCustomerName())
                .customerAge(model.getCustomerAge())
                .customerMobileNumber(model.getCustomerMobileNumber())
                .customerEmailAddress(model.getCustomerEmailAddress())
                .customerAddress(model.getCustomerAddress())
                .build();

    }

    private CustomerModel requesttoModelMapper(CustomerRequest request){

        return CustomerModel.builder()
                .customerId(at.getAndIncrement())
                .customerName(request.getCustomerName())
                .customerAge(request.getCustomerAge())
                .customerMobileNumber(request.getCustomerMobileNumber())
                .customerEmailAddress(request.getCustomerEmailAddress())
                .customerAddress(request.getCustomerAddress())
                .build();
    }


}
