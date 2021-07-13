package com.springboot.st.web.dto;

import com.springboot.st.domain.posts.Posts;
import lombok.Getter;

//web에서 어떤 정보를 전송할 지의 비즈니스 로직
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();

    }
}
