package com.ConstructorHelper.ConstructorHeper.util;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginResponse {
   private String message;
   private boolean state;
}
