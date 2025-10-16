package com.cybernauts.backend.controller;


import com.cybernauts.backend.User.User;
import com.cybernauts.backend.service.Service;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/home")
public class UserController {

    //1st
    @Autowired
    Service service;
    @GetMapping("api/User/")
   public List<User> fetchAllUser(@PathVariable User user){
       return service.findAlll();
   }
   @GetMapping("id/{myid}")
    public ResponseEntity<Optional<User>> getUserById(@PathVariable ObjectId id){
        Optional<Object> user =service.findByID(id);
        if (user.isPresent()){
            return new ResponseEntity<>(service.getUser(id),HttpStatus.OK);
        }
       return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }

   //2nd
   @PostMapping("saveUser/")
    public ResponseEntity<User> registerNewUser(@RequestBody User user){
        try{
            user.setDate(LocalDate.now());
            service.saveUser(user);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }catch(Exception e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
   }

   //3rd
   @PutMapping("home/updated/{id}")
   public ResponseEntity<User> updateUser(@PathVariable ObjectId id, @RequestBody User newEntry){
            User old= (User) service.findByID(id).orElse(null);
        if(old!= null && newEntry!=null){

            //old.setId(newEntry!=null && newEntry.getId().equals()? newEntry.getId():old.getId());
            if (newEntry.getUsername() != null && !newEntry.getUsername().isEmpty()) {
                old.setUsername(newEntry.getUsername());
            }

            old.setAge(newEntry.getAge() !=0? newEntry.getAge() : old.getAge());
            if (newEntry.getHobbies() != null && newEntry.getHobbies().length > 0) {
                old.setHobbies(newEntry.getHobbies());
            }

            if (newEntry.getFriends() != null && newEntry.getFriends().isEmpty()) {
                old.setFriends(newEntry.getFriends());
            }

            old.setPopularityScore(service.calculatePopularity(id));
           // return service.saveUser(old);
        }
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

       return null;
   }

   //4th
   @DeleteMapping("/api/users/:id")
   public ResponseEntity<?> deleteUser(@PathVariable ObjectId id){
       service.deleteById(id);
        return  new ResponseEntity<>(HttpStatus.ACCEPTED);
   }

   //5th
    @PostMapping
    public String  createRelation(@PathVariable String id,@RequestBody User user){
        service.createRelation();
        return "created relation";
    }

    //6th
    @DeleteMapping
    public String deleteRelation(@PathVariable String id,@RequestBody User user){
        return "broke relation between them";
    }

    //7th
    @GetMapping
    public void showingAllData(){}



}
