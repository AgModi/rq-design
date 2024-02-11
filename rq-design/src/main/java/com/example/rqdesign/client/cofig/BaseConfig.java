package com.example.rqdesign.client.cofig;

import lombok.Data;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@Data
public class BaseConfig {

    @NotBlank
    public String scheme;

    @NotBlank
    public String host;

    @NotBlank
    public String port;
}
