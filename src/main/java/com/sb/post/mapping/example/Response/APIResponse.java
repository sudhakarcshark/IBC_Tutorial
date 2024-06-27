package com.sb.post.mapping.example.Response;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder(toBuilder = true)
public class APIResponse {

    private int errorCode;
    private Object data;
}
