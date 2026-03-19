package com.example.dietService.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailEvent {

    public String to;
    public String subject;
    public String body;
}
