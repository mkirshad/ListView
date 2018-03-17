package com.kashifirshad.listview;

import java.util.Date;

/**
 * Created by biome on 3/17/2018.
 */

public class User {

    int Id;
    String FirstName;
    String MiddleName;
    String LastName;
    String EmailAddress;
    String SkypeId;
    String WatsAppNo;
    String AddressLine1;
    String AddressLine2;
    String City;
    String State;
    String Country;
    String CreatedAt;
    String UpdatedAt;
    Boolean IsSynched;
    int ServerId;

    public User(){

    }
    public User(int id, String firstName, String middleName, String lastName, String emailAddress, String skypeId, String watsAppNo, String addressLine1, String addressLine2, String city, String state, String country, String createdAt, String updatedAt, Boolean isSynched, int serverId) {
        Id = id;
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
        EmailAddress = emailAddress;
        SkypeId = skypeId;
        WatsAppNo = watsAppNo;
        AddressLine1 = addressLine1;
        AddressLine2 = addressLine2;
        City = city;
        State = state;
        Country = country;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        IsSynched = isSynched;
        ServerId = serverId;
    }

    public User(String firstName, String middleName, String lastName, String emailAddress, String skypeId, String watsAppNo, String addressLine1, String addressLine2, String city, String state, String country, String createdAt, String updatedAt, Boolean isSynched, int serverId) {
        FirstName = firstName;
        MiddleName = middleName;
        LastName = lastName;
        EmailAddress = emailAddress;
        SkypeId = skypeId;
        WatsAppNo = watsAppNo;
        AddressLine1 = addressLine1;
        AddressLine2 = addressLine2;
        City = city;
        State = state;
        Country = country;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        IsSynched = isSynched;
        ServerId = serverId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getMiddleName() {
        return MiddleName;
    }

    public void setMiddleName(String middleName) {
        MiddleName = middleName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getSkypeId() {
        return SkypeId;
    }

    public void setSkypeId(String skypeId) {
        SkypeId = skypeId;
    }

    public String getWatsAppNo() {
        return WatsAppNo;
    }

    public void setWatsAppNo(String watsAppNo) {
        WatsAppNo = watsAppNo;
    }

    public String getAddressLine1() {
        return AddressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        AddressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return AddressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        AddressLine2 = addressLine2;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getState() {
        return State;
    }

    public void setState(String state) {
        State = state;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public String getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        UpdatedAt = updatedAt;
    }

    public Boolean getSynched() {
        return IsSynched;
    }

    public void setSynched(Boolean synched) {
        IsSynched = synched;
    }

    public int getServerId() {
        return ServerId;
    }

    public void setServerId(int serverId) {
        ServerId = serverId;
    }
}