package com.springboot.st.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
//Dao라고 불리는 DB Layer 접근자
//Entity 클래스와 ID 값을 상속한다
//기본으로 CRUD 메소드가 생성된다.
public interface PostsRepository extends JpaRepository<Posts,Long> {

}
