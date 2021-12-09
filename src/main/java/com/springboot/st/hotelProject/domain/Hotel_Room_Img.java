package com.springboot.st.hotelProject.domain;

import com.springboot.st.domain.BaseTimeEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
public class Hotel_Room_Img extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String img_Name;

    private String img_Server_Name;

    private String img_Server_Path;

    private String img_UUID;
}
