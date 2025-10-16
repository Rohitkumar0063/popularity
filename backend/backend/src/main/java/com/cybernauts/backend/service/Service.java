package com.cybernauts.backend.service;


import java.util.List;
import java.util.Optional;

import jakarta.validation.constraints.NotNull;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cybernauts.backend.User.User;
import com.cybernauts.backend.repository.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.stereotype.Service



@Component
public class Service {

  @Autowired 
  private User user;

  @Autowired
  private Repository repository;
  
  public void saveUser(User user)
  {

    repository.save(user);
  }

  public Optional<User> getUser(@NotNull ObjectId id){
    return repository.findById(id);
  }

  public List<User> findAlll() {

    return repository.findAll();
  }

  //public User put(ObjectId id, User user) {
  //}

  public void deleteById(ObjectId id) {
    User user = repository.findById(id).orElseThrow();
    if(!user.getFriends().isEmpty()){
      System.out.println("Fierst need to unlink it .....unlinking");
      for(ObjectId friendId: user.getFriends()){
        User friend = repository.findById(friendId).orElse(null);//storing the value after looping through for each loop
        if(friend != null){
          friend.getFriends().remove(id);
          repository.save(user);
          }
        }
      user.getFriends().clear();
      repository.save(user);
    }
    else {
      repository.deleteById(id);//currently empty
    }
    // repository.deleteById(id);
  }

  public void createRelation() {
  }

  public int countSharedHobbies(User user){
    int sharedHobbies=0;
    for()


  }

  public int calculatePopularity(ObjectId id) {

    User user=repository.findById(id).orElseThrow();
    int numOfFriends= user.getFriends().size();
  }

  public Optional<Object> findByID(ObjectId id) {
  }
}
