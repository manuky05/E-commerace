package com.mantu.electronic.store.ElectronicStore.exceptions;

import lombok.*;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class ApiResponseMessage {
    private String message;
    private boolean success;
    private HttpStatus status;
}
