package com.mysite.extraclass.notice;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Notice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Integer id; 

	
    @Column(length = 200) 
    private String title; 

    
    @Column(columnDefinition = "TEXT") 
    private String content; 
    
    
    @Column(columnDefinition = "TEXT") 
    private String content1;
    
    
    @Column(columnDefinition = "TEXT") 
    private String content2;

    
    private LocalDateTime date; 
    
    
    private String image1;
    
    
    private String image2;
    
    
    private String image3;
    
    // 공지글은 여러개의 댓글을 가질 수 있으니까
    @OneToMany(mappedBy = "notice", cascade = CascadeType.REMOVE) 
    private List<ReviewComment1> commentList;
    
    
    
    
    
    private String author;
}