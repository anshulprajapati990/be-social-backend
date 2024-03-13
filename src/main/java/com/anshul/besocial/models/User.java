package com.anshul.besocial.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer id;
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public String gender;
    public List<Integer> followers=new ArrayList<>();
    public List<Integer> followings=new ArrayList<>();
}
