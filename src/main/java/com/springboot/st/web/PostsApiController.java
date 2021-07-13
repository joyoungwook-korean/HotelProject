package com.springboot.st.web;

import com.springboot.st.service.posts.PostsService;
import com.springboot.st.web.dto.PostsResponseDto;
import com.springboot.st.web.dto.PostsSaveRequestDto;
import com.springboot.st.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor // private final로 생성된 모든 변수를 생성자로 생성 (Autowired랑 같은 원리)
@RestController
public class PostsApiController {

    private final PostsService postsService;

    //바디로 post된 View단의 Dto를 받아서 Service로 직접 호출 조회 기능
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }



}
