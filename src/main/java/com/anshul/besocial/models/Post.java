package com.anshul.besocial.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer id;
    private String caption;
    private String image;
    private String video;


    @ManyToOne
    private User user;
    private LocalDateTime createdAt;

    @OneToMany
    private List<User> liked=new ArrayList<>();

}
