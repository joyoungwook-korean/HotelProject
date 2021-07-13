package com.springboot.st.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
//Dto란 뷰에서 서비스 영역으로의 계층간의 데이터 교환을 위한 객체

@Getter
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;

}
