package com.springboot.st.web.dto;

import com.springboot.st.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }



    //Posts의 DB Layer를 생성자로 생성하는 메소드 View단에서 사용하고 직접 DB와는 접근하지 않음
    public Posts toEntity(){
        return Posts.builder().title(title).content(content).author(author).build();
    }

}
