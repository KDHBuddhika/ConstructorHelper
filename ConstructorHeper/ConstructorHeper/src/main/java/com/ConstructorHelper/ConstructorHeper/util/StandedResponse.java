package com.ConstructorHelper.ConstructorHeper.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StandedResponse {
    private int code;
    private String message;
    private Object data;
}
