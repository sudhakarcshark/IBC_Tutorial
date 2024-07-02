package com.sb.post.mapping.example.Service;


import com.sb.post.mapping.example.Model.CustomerModel;
import com.sb.post.mapping.example.Request.CustomerRequest;
import com.sb.post.mapping.example.Response.APIResponse;
import com.sb.post.mapping.example.Response.CustomerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class CustomerServiceImple implements CustomerService {

   private static List<CustomerModel> customers = new ArrayList<>();

   private static AtomicInteger at = new AtomicInteger();


   static {
       customers.add(new CustomerModel(at.getAndIncrement(),"sudhakar",35,"8553769931","sudhakarvisam26@gmail.com","Bangalore"));
       customers.add(new CustomerModel(at.getAndIncrement(),"sunder",25,"8553765531","sundervisam26@gmail.com","KGF"));
       customers.add(new CustomerModel(at.getAndIncrement(),"shruk",38,"8555543663","srksudhaa26@gmail.com","Chennai"));
       customers.add(new CustomerModel(at.getAndIncrement(),"pradeeep",34,"97363563839","supradeep6@gmail.com","Bangalore"));
       customers.add(new CustomerModel(at.getAndIncrement(),"arun",30,"85688393993","arunjackson6@gmail.com","Bangalore"));
   }



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

    @Override
    public ResponseEntity<APIResponse> getByCustomerId(long customerId) {

       Optional<CustomerResponse>  optional = customers.stream().filter(customerModel -> customerModel.getCustomerId() == customerId)
                .map(customerModel -> modeltoResponse(customerModel))
                .findFirst();

        if (optional.isPresent()){
            return ResponseEntity.ok(
                    APIResponse.builder()
                            .errorCode(0)
                            .data(optional.get())
                            .build()
            );
        }else {
            return ResponseEntity.ok(
                    APIResponse.builder()
                            .errorCode(500)
                            .data(List.of())
                            .build()
            );
        }

    }

    @Override
    public ResponseEntity<APIResponse> deleteByCustomerId(long customerId) {
       Boolean isRemoved = customers.removeIf(customerModel -> customerModel.getCustomerId() == customerId);
       if (isRemoved){

           return ResponseEntity.ok(
                   APIResponse.builder()
                           .errorCode(0)
                           .data("Successfully Delete")
                           .build()
           );
       } else {

           return  ResponseEntity.ok(
                   APIResponse.builder()
                           .errorCode(600)
                           .data("Datas are not found")
                           .build()
           );
       }
    }

    @Override
    public ResponseEntity<APIResponse> upDateCustomerDetails(long customerId, CustomerRequest request) {

      Optional<CustomerModel> customerModelOptional = customers.stream()
                   .filter(customerModel -> customerModel.getCustomerId() == customerId)
                   .map(customerModel -> {
                   customerModel.setCustomerName(request.getCustomerName());
                   customerModel.setCustomerAge(request.getCustomerAge());
                   customerModel.setCustomerMobileNumber(request.getCustomerMobileNumber());
                   customerModel.setCustomerEmailAddress(request.getCustomerEmailAddress());
                   customerModel.setCustomerAddress(request.getCustomerAddress());
                   return customerModel;
               })
              .findFirst();
        return customerModelOptional.map(customerModel -> ResponseEntity.ok(
                APIResponse.builder()
                        .errorCode(000)
                        .data(modeltoResponse(customerModel))
                        .build()
        )).orElseGet(() -> ResponseEntity.ok(
                APIResponse.builder()
                        .errorCode(999)
                        .data("Customer is not available")
                        .build()
        ));
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
