package com.springboot.st.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 기본 생성자 생성
public class PostsUpdateRequestDto {
    private String title;
    private String content;

    //외부에서 사용해서 수정할 값을 입력 그리고 Service에서 사용
    @Builder
    public PostsUpdateRequestDto(String title, String content){
        this.title = title;
        this.content = content;
    }

}
