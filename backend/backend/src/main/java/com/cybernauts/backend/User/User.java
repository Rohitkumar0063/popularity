package com.cybernauts.backend.User;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Setter
@Getter
@Document
public class User {

  @Id
  private ObjectId id;
  private String username;
  private int age;
  private String[] hobbies;
  private List<ObjectId> friends= new ArrayList<>();
  private LocalDate Date;
  private int popularityScore;


}
