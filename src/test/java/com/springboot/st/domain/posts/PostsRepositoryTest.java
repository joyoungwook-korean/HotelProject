package com.springboot.st.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest // 자동으로 H2 데이터 베이스를 실행
public class PostsRepositoryTest {

    @Autowired
    PostsRepository postsRepository;

    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void write_load(){
        String title="테스트 게시글";
        String content="테스트 본문";

        //give
        //만약에 posts 테이블에 id 값이 있으면 update, 없으면 insert
        postsRepository.save(Posts.builder().title(title).content(content).author("joyoung@naver.com").build());

        //find
        List<Posts> postsList = postsRepository.findAll();

        //check
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }

}