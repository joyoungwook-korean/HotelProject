package com.springboot.st.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

public class HelloResponseDtoTest {

    @Test
    public void lombok_Test(){
        String name="test";
        int amount= 1000;
         HelloResponseDto dto = new HelloResponseDto(name,amount);

         Assertions.assertThat(dto.getName()).isEqualTo(name);
         Assertions.assertThat(dto.getAmount()).isEqualTo(amount);

    }
}
