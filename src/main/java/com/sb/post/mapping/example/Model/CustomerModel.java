package com.sb.post.mapping.example.Model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
public class CustomerModel {
        private long customerId;
        private String customerName;
        private int customerAge;
        private String customerMobileNumber;
        private String customerEmailAddress;
        private String customerAddress;
}
