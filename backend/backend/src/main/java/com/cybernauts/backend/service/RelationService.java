package com.cybernauts.backend.service;

import com.cybernauts.backend.User.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

public class RelationService {

    @Autowired
    private User user;

    @Autowired
    private Service service;

    public void linkUser(@PathVariable ObjectId id1, @RequestBody ObjectId id2){
        User user1= (User) service.findByID(id1).orElseThrow();
        User user2 = (User) service.findByID(id2).orElseThrow();
        if(id1!=null){
            if(id1.equals(id2)){
                System.out.println("cannot link to same person");
            }


        } else if (user1.getFriends().contains(user2.getId())
            && user2.getFriends().contains(user1.getId())) {
            System.out.println("Already in relation");
        }

        user1.getFriends().add(user2.getId());
        user2.getFriends().add(user1.getId());
        System.out.println("created link ");

    }

    //Unlinking method
    public String unlinking(@PathVariable ObjectId id1 ,@RequestBody ObjectId id2){
        User user1= (User) service.findByID(id1).orElseThrow();
        User user2= (User) service.findByID(id2).orElseThrow();

        if(id1!=null){
            if(user1.getFriends().contains(user2.getId()))
            {
                user1.getFriends().remove(id2);
                user2.getFriends().remove(id1);
            }

        }
        return  "Unlinked relation successfully";
    }

    public void del

}


