package com.kashifirshad.listview;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by biome on 3/17/2018.
 */

public class User {

    long Id;
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
    int SyncDuration;
    int ShowUnreadStoriesOnly;
    String CreatedAt;
    String UpdatedAt;
    int IsSynched;
    int ServerId;

    public User(){

    }
    public User(long id, String firstName, String middleName, String lastName, String emailAddress, String skypeId, String watsAppNo, String addressLine1,
                String addressLine2, String city, String state, String country, int syncDuration, int showUnreadStoriesOnly,
                int isSynched, int serverId) {
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
        SyncDuration = syncDuration;
        ShowUnreadStoriesOnly = showUnreadStoriesOnly;
        CreatedAt = getDateTime();
        UpdatedAt = getDateTime();
        IsSynched = isSynched;
        ServerId = serverId;
    }

    public User(String firstName, String middleName, String lastName, String emailAddress, String skypeId, String watsAppNo, String addressLine1,
                String addressLine2, String city, String state, String country, int syncDuration, int showUnreadStoriesOnly,
                int isSynched, int serverId) {
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
        SyncDuration = syncDuration;
        ShowUnreadStoriesOnly = showUnreadStoriesOnly;
        UpdatedAt = getDateTime();
        IsSynched = isSynched;
        ServerId = serverId;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
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

    public int getSynched() {
        return IsSynched;
    }

    public void setSynched(int synched) {
        IsSynched = synched;
    }

    public int getServerId() {
        return ServerId;
    }

    public void setServerId(int serverId) {
        ServerId = serverId;
    }


    public int getSyncDuration(){
        return SyncDuration;
    }

    public void setSyncDuration(int synchDuration){
         SyncDuration = synchDuration;
    }

    public int getShowUnreadStoriesOnly(){
        return ShowUnreadStoriesOnly;
    }
    public void setShowUnreadStoriesOnly(int showUnreadStoriesOnly){
        ShowUnreadStoriesOnly = showUnreadStoriesOnly;
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}