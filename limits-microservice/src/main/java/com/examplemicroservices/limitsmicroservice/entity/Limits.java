package com.examplemicroservices.limitsmicroservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Limits {
    private int minimum;
    private int maximum;
}
