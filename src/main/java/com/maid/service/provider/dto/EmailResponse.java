package com.maid.service.provider.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailResponse {
    private String message;
    private String recipient;
    private String subject;
    private String timestamp;
}
