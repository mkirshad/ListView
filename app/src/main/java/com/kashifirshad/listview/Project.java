package com.kashifirshad.listview;

/**
 * Created by biome on 3/17/2018.
 */

public class Project {

    int Id;
    String Story;
    String EstimatedHrs;
    String EstimateCost;
    String DeliveryDate;
    String CreatedAt;
    String UpdatedAt;
    int UserId;
    int IsSynched;
    int ServerUserId;
    int ParentId;
    int ServerId;
    int ServerParentId;

    public Project(){

    }

    public Project(int id, String storey, String estimatedHrs, String estimateCost, String deliveryDate, int userId, int isSynched, int serverUserId, int parentId, int serverId, int serverParentId) {
        Id = id;
        Story = storey;
        EstimatedHrs = estimatedHrs;
        EstimateCost = estimateCost;
        DeliveryDate = deliveryDate;
//        CreatedAt = createdAt;
//        UpdatedAt = updatedAt;
        UserId = userId;
        IsSynched = isSynched;
        ServerUserId = serverUserId;
        ParentId = parentId;
        ServerId = serverId;
        ServerParentId = serverParentId;
    }

    public Project(String storey, String estimatedHrs, String estimateCost, String deliveryDate, int userId, int isSynched, int serverUserId, int parentId, int serverId, int serverParentId) {
        Story = storey;
        EstimatedHrs = estimatedHrs;
        EstimateCost = estimateCost;
        DeliveryDate = deliveryDate;
//        CreatedAt = createdAt;
//        UpdatedAt = updatedAt;
        UserId = userId;
        IsSynched = isSynched;
        ServerUserId = serverUserId;
        ParentId = parentId;
        ServerId = serverId;
        ServerParentId = serverParentId;
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

    public void setStory(String storey) {
        Story = storey;
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

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
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

    public Project getAddStoryProj(){
        return new Project(999999,"Add Story to this project","","","",0,0,0,0,0,0);
    }
}
