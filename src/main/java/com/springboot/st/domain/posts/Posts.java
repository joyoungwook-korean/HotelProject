package com.springboot.st.domain.posts;

import com.springboot.st.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 게터 메소드 자동 생성 lombok
@NoArgsConstructor // 기본 생성자 자동 추가 lombok
@Entity // Entity 클래스 실제 DB와 연결  될 클래스
public class Posts extends BaseTimeEntity {

    @Id // PK를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK 생성 규칙 IDENTITY 클래스를 추가 해야만 auto됨
    private Long id;

    @Column(length = 500, nullable = false) // 컬럼을 나타냄
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    @Builder //해당 클래스의 빌더 패턴 클래스를 생성
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content =content;
    }
}
