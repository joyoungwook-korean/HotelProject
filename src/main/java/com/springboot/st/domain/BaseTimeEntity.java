package com.springboot.st.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass // JPA Entity 클래스들이 BaseTimeEntity를 상속 할 경우 자동으로 인식
@EntityListeners(AuditingEntityListener.class) // Auditing기능을 포함
public abstract class BaseTimeEntity {

    @CreatedDate // 생성 될 때 자동으로 저장
    private LocalDateTime createdDate;

    @LastModifiedDate //변경 될 때 자동으로 저장
    private LocalDateTime modifiedDate;
}
