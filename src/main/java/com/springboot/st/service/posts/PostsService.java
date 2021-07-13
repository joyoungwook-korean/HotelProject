package com.springboot.st.service.posts;

import com.springboot.st.domain.posts.Posts;
import com.springboot.st.domain.posts.PostsRepository;
import com.springboot.st.web.dto.PostsResponseDto;
import com.springboot.st.web.dto.PostsSaveRequestDto;
import com.springboot.st.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    //JPA세이브 기능을 구현 사용은 View단의 Dto를 생성자 사용
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    // post를 받아와서 직접 업데이트 시전
    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다." + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    //어떤 값을 받아올지 db와 통신하여 그 값을 리턴
    public PostsResponseDto findById(Long id){
        Posts entity= postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다" + id));
        return  new PostsResponseDto(entity);
    }




}
