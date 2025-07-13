package com.maid.service.provider.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Service;

@Document
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Admin {

    @Id
    private String id;

    private String username;

    private String password;

}
