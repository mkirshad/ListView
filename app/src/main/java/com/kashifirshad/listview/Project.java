package com.kashifirshad.listview;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by biome on 3/17/2018.
 */

public class Project {

    int Id;
    String Story;
    String FilePaths;
    String EstimatedHrs;
    String EstimateCost;
    String DeliveryDate;
    String CreatedAt;
    String UpdatedAt;
    Long UserId;
    int IsSynched;
    int ServerUserId;
    int ParentId;
    int ServerId;
    int ServerParentId;
    User User;

    public Project(){

    }

    public Project(int id, String story, String filePaths, String estimatedHrs, String estimateCost, String deliveryDate, int isSynched, int serverUserId, int parentId, int serverId, int serverParentId, User user) {
        Id = id;
        FilePaths = filePaths;
        Story = story;
        EstimatedHrs = estimatedHrs;
        EstimateCost = estimateCost;
        DeliveryDate = deliveryDate;
        CreatedAt = getDateTime();
        UpdatedAt = getDateTime();
        UserId = user.getId();
        IsSynched = isSynched;
        ServerUserId = serverUserId;
        ParentId = parentId;
        ServerId = serverId;
        ServerParentId = serverParentId;
        User = user;
    }

    public Project(String story, String filePaths, String estimatedHrs, String estimateCost, String deliveryDate, int isSynched, int serverUserId, int parentId, int serverId, int serverParentId, User user) {


        Story = story;
        FilePaths = filePaths;
        EstimatedHrs = estimatedHrs;
        EstimateCost = estimateCost;
        DeliveryDate = deliveryDate;
        UpdatedAt = getDateTime(); ;
        UserId = user.getId();
        IsSynched = isSynched;
        ServerUserId = serverUserId;
        ParentId = parentId;
        ServerId = serverId;
        ServerParentId = serverParentId;
        User = user;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getStory() {
        return Story;
    }

    public void setStory(String story) {
        Story = story;
    }

    public String getFilePaths() {
        return FilePaths;
    }

    public void setFilePaths(String filePaths) {
        FilePaths = filePaths;
    }

    public String getEstimatedHrs() {
        return EstimatedHrs;
    }

    public void setEstimatedHrs(String estimatedHrs) {
        EstimatedHrs = estimatedHrs;
    }

    public String getEstimateCost() {
        return EstimateCost;
    }

    public void setEstimateCost(String estimateCost) {
        EstimateCost = estimateCost;
    }

    public String getDeliveryDate() {
        return DeliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        DeliveryDate = deliveryDate;
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

    public long getUserId() {
        return UserId;
    }

    public void setUserId(long userId) {
        UserId = userId;
    }

    public int getIsSynched() {
        return IsSynched;
    }

    public void setIsSynched(int synched) {
        IsSynched = synched;
    }

    public int getServerUserId() {
        return ServerUserId;
    }

    public void setServerUserId(int serverUserId) {
        ServerUserId = serverUserId;
    }

    public int getParentId() {
        return ParentId;
    }

    public void setParentId(int parentId) {
        ParentId = parentId;
    }

    public int getServerId() {
        return ServerId;
    }

    public void setServerId(int serverId) {
        ServerId = serverId;
    }

    public int getServerParentId() {
        return ServerParentId;
    }

    public void setServerParentId(int serverParentId) {
        ServerParentId = serverParentId;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        this.User = user;
        this.setUserId(user.getId());
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}
