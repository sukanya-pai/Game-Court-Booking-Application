package dev.sukanya.gamecourtbooking.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class VerificationToken {

    private static final int VALIDITY_TIME = 4*60; //in minutes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String token;

    @OneToOne(targetEntity = User.class)
    private User user;

    private Date expiryTime;

    public VerificationToken(){
        super();    //Object class
    }

    //to generate expiry time, we need to have constructor
    public VerificationToken(User user){
//        String token ="2163yghsau7183481"; //some string generate
        String token = generateRandomUniqueToken();
        this.user=user;

        this.expiryTime=calculateExpiryTime();

    }

    public void updateToken(){
        //TODO: generate new token String
        this.token = generateRandomUniqueToken();

        this.expiryTime = calculateExpiryTime();
        //TODO: Calculate new expiry date
    }

    public String generateRandomUniqueToken(){
        //we can use inbuilt given by java
        //UUID: Universally unique ID
        return UUID.randomUUID().toString();
    }

    public Date calculateExpiryTime(){
        Calendar calendar= Calendar.getInstance();

        Date currentTimeAndDate = new Date();

        calendar.setTimeInMillis(currentTimeAndDate.getTime());
        calendar.add(Calendar.MINUTE, VALIDITY_TIME);

        return new Date(calendar.getTime().getTime()); //calendar.getTime() is instance of Date class

        //date.getTime() --> Long in milliseconds
    }
}

//scaler.com
//create account
//you get verification token
//scaler.com/verify/somestring
//click on this link--> we need to know what user clicked
//token,, userid, expiryTime