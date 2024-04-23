package com.Test.test.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Board {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;     // PK
    private String title;   // 제목
    private String content; // 내용
}
