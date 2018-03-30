package com.kashifirshad.listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;

/**
 * Created by biome on 3/17/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 9;

    // Database Name
    private static final String DATABASE_NAME = "ProjectsDB.db";

    // Table Names
    private static final String TABLE_USER = "Users";

    private static final String TABLE_PROJECT = "Projects";


    private static final String CREATE_TABLE_USER = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + " ( " +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "FirstName TEXT, " +
            "MiddleName TEXT, " +
            "LastName TEXT, " +
            "EmailAddress TEXT NOT NULL, " +
            "SkypeId TEXT, " +
            "WatsAppNo TEXT, " +
            "AddressLine1 TEXT, " +
            "AddressLine2 TEXT, " +
            "City TEXT, " +
            "State TEXT, " +
            "Country TEXT, " +
            "UnReadOnly INTEGER, " +
            "SyncDuration INTEGER, " +
            "CreatedAt DATETIME, " +
            "UpdatedAt DATETIME, " +
            "IsSynched INTEGER, " +
            "ServerId INTEGER," +
            "CONSTRAINT uq_email UNIQUE (EmailAddress) "+
            ")";

    private static final String CREATE_TABLE_PROJECT = "CREATE TABLE IF NOT EXISTS " + TABLE_PROJECT + " ( " +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Story TEXT, " +
            "FilePaths TEXT,"+
            "EstimatedHrs TEXT, " +
            "EstimateCost TEXT, " +
            "DeliveryDate DATETIME, " +
            "CreatedAt DATETIME, " +
            "UpdatedAt DATETIME, " +
            "UserId INTEGER, " +
            "ServerUserId INTEGER, " +
            "IsSynched INTEGER, " +
            "ParentId INTEGER, " +
            "ServerId INTEGER, " +
            "ServerParentId INTEGER" +
            ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_PROJECT);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROJECT);
        // create new tables
        onCreate(db);
    }

    /*
    * Creating a Project
    */
    public long createProject(Project proj) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Story", proj.getStory());
        values.put("FilePaths", proj.getFilePaths());
        values.put("EstimatedHrs", proj.getEstimatedHrs());
        values.put("EstimateCost", proj.getEstimateCost());
        values.put("DeliveryDate", proj.getDeliveryDate());
        values.put("CreatedAt", proj.getCreatedAt());
        values.put("UpdatedAt", proj.getUpdatedAt());
        values.put("UserId", proj.getUser().getId());
        values.put("IsSynched", proj.getIsSynched());
        values.put("ServerUserId", proj.getServerUserId());
        values.put("ParentId", proj.getParentId());
        values.put("ServerId", proj.getServerId());
        values.put("ServerParentId", proj.getServerParentId());

        // insert row
        long id = db.insert(TABLE_PROJECT, null, values);

        return id;
    }


    /*
    * get single Project
    */
    public Project getProject(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PROJECT +
                " WHERE Id   = " + id;

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Project td = new Project();
        td.setId(c.getInt(c.getColumnIndex("Id")));
        td.setStory(c.getString(c.getColumnIndex("Story")));
        td.setFilePaths(c.getString(c.getColumnIndex("FilePaths")));
        td.setEstimatedHrs(c.getString(c.getColumnIndex("EstimatedHrs")));
        td.setEstimateCost(c.getString(c.getColumnIndex("EstimateCost")));
        td.setDeliveryDate(c.getString(c.getColumnIndex("DeliveryDate")));
        td.setCreatedAt(c.getString(c.getColumnIndex("CreatedAt")));
        td.setUpdatedAt(c.getString(c.getColumnIndex("UpdatedAt")));
        td.setUserId(c.getInt(c.getColumnIndex("UserId")));
        td.setIsSynched(c.getInt(c.getColumnIndex("IsSynched")));
        td.setServerUserId(c.getInt(c.getColumnIndex("ServerUserId")));
        td.setParentId(c.getInt(c.getColumnIndex("ParentId")));
        td.setServerId(c.getInt(c.getColumnIndex("ServerId")));
        td.setServerParentId(c.getInt(c.getColumnIndex("ServerParentId")));

        return td;
    }

    /*
 * getting all Projects
 * */
    public List<Project> getAllProjects() {
        List<Project> projects = new ArrayList<Project>();
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c != null)
        if (c.moveToFirst()) {
            do {
                Project td = new Project();
                td.setId(c.getInt(c.getColumnIndex("Id")));
                td.setStory(c.getString(c.getColumnIndex("Story")));
                td.setFilePaths(c.getString(c.getColumnIndex("FilePaths")));
                td.setEstimatedHrs(c.getString(c.getColumnIndex("EstimatedHrs")));
                td.setEstimateCost(c.getString(c.getColumnIndex("EstimateCost")));
                td.setDeliveryDate(c.getString(c.getColumnIndex("DeliveryDate")));
                td.setCreatedAt(c.getString(c.getColumnIndex("CreatedAt")));
                td.setUpdatedAt(c.getString(c.getColumnIndex("UpdatedAt")));
                td.setUserId(c.getInt(c.getColumnIndex("UserId")));
                td.setIsSynched(c.getInt(c.getColumnIndex("IsSynched")));
                td.setServerUserId(c.getInt(c.getColumnIndex("ServerUserId")));
                td.setParentId(c.getInt(c.getColumnIndex("ParentId")));
                td.setServerId(c.getInt(c.getColumnIndex("ServerId")));
                td.setServerParentId(c.getInt(c.getColumnIndex("ServerParentId")));

                // adding to todo list
                projects.add(td);
            } while (c.moveToNext());
        }
        return projects;
    }



    /*
 * getting all Projects
 * */
    public List<Project> getChildProjects(int id) {
        List<Project> projects = new ArrayList<Project>();
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECT + " WHERE ParentId = " + Integer.toString(id) + " Order By Id";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c != null)
            if (c.moveToFirst()) {
                do {
                    Project td = new Project();
                    td.setId(c.getInt(c.getColumnIndex("Id")));
                    td.setStory(c.getString(c.getColumnIndex("Story")));
                    td.setFilePaths(c.getString(c.getColumnIndex("FilePaths")));
                    td.setEstimatedHrs(c.getString(c.getColumnIndex("EstimatedHrs")));
                    td.setEstimateCost(c.getString(c.getColumnIndex("EstimateCost")));
                    td.setDeliveryDate(c.getString(c.getColumnIndex("DeliveryDate")));
                    td.setCreatedAt(c.getString(c.getColumnIndex("CreatedAt")));
                    td.setUpdatedAt(c.getString(c.getColumnIndex("UpdatedAt")));
                    td.setUserId(c.getInt(c.getColumnIndex("UserId")));
                    td.setIsSynched(c.getInt(c.getColumnIndex("IsSynched")));
                    td.setServerUserId(c.getInt(c.getColumnIndex("ServerUserId")));
                    td.setParentId(c.getInt(c.getColumnIndex("ParentId")));
                    td.setServerId(c.getInt(c.getColumnIndex("ServerId")));
                    td.setServerParentId(c.getInt(c.getColumnIndex("ServerParentId")));
                    td.setUser(getUser(c.getInt(c.getColumnIndex("UserId"))));
                    // adding to todo list
                    projects.add(td);
                } while (c.moveToNext());
            }
        return projects;
    }


    public int getOtherProjectCount(String Story, int Id){
        int count = 0;
        String selectQuery = "SELECT COUNT(*) AS CountProject FROM " + TABLE_PROJECT + " WHERE Story = '" + Story.replace("'","\'") +"'"
                +" AND Id != " + Integer.toString(Id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
        {
            c.moveToFirst();
            count = (c.getInt(c.getColumnIndex("CountProject")));
        }
        return count;
    }

    /*
 * Updating a Project
 */
    public int updateProject(Project proj) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("Story", proj.getStory());
        values.put("FilePaths", proj.getFilePaths());
        values.put("EstimatedHrs", proj.getEstimatedHrs());
        values.put("EstimateCost", proj.getEstimateCost());
        values.put("DeliveryDate", proj.getDeliveryDate());
        values.put("CreatedAt", proj.getCreatedAt());
        values.put("UpdatedAt", proj.getUpdatedAt());
        values.put("UserId", proj.getUserId());
        values.put("IsSynched", proj.getIsSynched());
        values.put("ServerUserId", proj.getServerUserId());
        values.put("ParentId", proj.getParentId());
        values.put("ServerId", proj.getServerId());
        values.put("ServerParentId", proj.getServerParentId());

        // updating row
        return db.update(TABLE_PROJECT, values,   " Id = ?",
                new String[] { String.valueOf(proj.getId()) });
    }



    /*
 * Deleting a Project
 */
    public void deleteProjecto(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PROJECT,   " Id = ?",
                new String[] { String.valueOf(id) });
    }

 /* ************** User Funtions ******************** */
 public User getUser(String email){
     User usr = new User();
     String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE EmailAddress = '" + email.replace("'","\'") +"'";
     SQLiteDatabase db = this.getReadableDatabase();
     Cursor c = db.rawQuery(selectQuery, null);
     if (c != null && c.moveToFirst())
     {

         usr.setId(c.getInt(c.getColumnIndex("Id")));
         usr.setFirstName(c.getString(c.getColumnIndex("FirstName")));
         usr.setLastName(c.getString(c.getColumnIndex("LastName")));
         usr.setMiddleName(c.getString(c.getColumnIndex("MiddleName")));
         usr.setEmailAddress(c.getString(c.getColumnIndex("EmailAddress")));
         usr.setSkypeId(c.getString(c.getColumnIndex("SkypeId")));
         usr.setWatsAppNo(c.getString(c.getColumnIndex("WatsAppNo")));
         usr.setAddressLine1(c.getString(c.getColumnIndex("AddressLine1")));
         usr.setAddressLine2(c.getString(c.getColumnIndex("AddressLine2")));
         usr.setCity(c.getString(c.getColumnIndex("City")));
         usr.setState(c.getString(c.getColumnIndex("State")));
         usr.setCountry(c.getString(c.getColumnIndex("Country")));
         usr.setShowUnreadStoriesOnly(c.getInt(c.getColumnIndex("UnReadOnly")));
         usr.setSyncDuration(c.getInt(c.getColumnIndex("SyncDuration")));
         usr.setCreatedAt(c.getString(c.getColumnIndex("CreatedAt")));
         usr.setUpdatedAt(c.getString(c.getColumnIndex("UpdatedAt")));
         usr.setSynched(c.getInt(c.getColumnIndex("IsSynched")));
         usr.setServerId(c.getInt(c.getColumnIndex("ServerId")));
     }
     return usr;
 }



    public User getUser(int id){
        User usr = new User();
        String selectQuery = "SELECT * FROM " + TABLE_USER + " WHERE Id = '" + Integer.toString(id) +"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null && c.moveToFirst())
        {

            usr.setId(c.getInt(c.getColumnIndex("Id")));
            usr.setFirstName(c.getString(c.getColumnIndex("FirstName")));
            usr.setLastName(c.getString(c.getColumnIndex("LastName")));
            usr.setMiddleName(c.getString(c.getColumnIndex("MiddleName")));
            usr.setEmailAddress(c.getString(c.getColumnIndex("EmailAddress")));
            usr.setSkypeId(c.getString(c.getColumnIndex("SkypeId")));
            usr.setWatsAppNo(c.getString(c.getColumnIndex("WatsAppNo")));
            usr.setAddressLine1(c.getString(c.getColumnIndex("AddressLine1")));
            usr.setAddressLine2(c.getString(c.getColumnIndex("AddressLine2")));
            usr.setCity(c.getString(c.getColumnIndex("City")));
            usr.setState(c.getString(c.getColumnIndex("State")));
            usr.setCountry(c.getString(c.getColumnIndex("Country")));
            usr.setShowUnreadStoriesOnly(c.getInt(c.getColumnIndex("UnReadOnly")));
            usr.setSyncDuration(c.getInt(c.getColumnIndex("SyncDuration")));
            usr.setCreatedAt(c.getString(c.getColumnIndex("CreatedAt")));
            usr.setUpdatedAt(c.getString(c.getColumnIndex("UpdatedAt")));
            usr.setSynched(c.getInt(c.getColumnIndex("IsSynched")));
            usr.setServerId(c.getInt(c.getColumnIndex("ServerId")));
        }
        return usr;
    }


    public long createUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("FirstName", user.getFirstName());
        values.put("MiddleName", user.getMiddleName());
        values.put("LastName", user.getLastName());
        values.put("EmailAddress", user.getEmailAddress());
        values.put("SkypeId", user.getSkypeId());
        values.put("WatsAppNo", user.WatsAppNo);
        values.put("AddressLine1", user.getAddressLine1());
        values.put("AddressLine2", user.getAddressLine2());
        values.put("City", user.getCity());
        values.put("State", user.getState());
        values.put("Country", user.getCountry());
        values.put("UnReadOnly", user.getShowUnreadStoriesOnly());
        values.put("SyncDuration", user.getSyncDuration());
        values.put("CreatedAt", user.getCreatedAt());
        values.put("UpdatedAt", user.getUpdatedAt());
        values.put("IsSynched", user.getSynched());
        values.put("ServerId", user.getServerId());

        // insert row
        long id = db.insert(TABLE_USER, null, values);
        return id;
    }


    public long updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("FirstName", user.getFirstName());
        values.put("MiddleName", user.getMiddleName());
        values.put("LastName", user.getLastName());
        values.put("EmailAddress", user.getEmailAddress());
        values.put("SkypeId", user.getSkypeId());
        values.put("WatsAppNo", user.WatsAppNo);
        values.put("AddressLine1", user.getAddressLine1());
        values.put("AddressLine2", user.getAddressLine2());
        values.put("City", user.getCity());
        values.put("State", user.getState());
        values.put("Country", user.getCountry());
        values.put("UnReadOnly", user.getShowUnreadStoriesOnly());
        values.put("SyncDuration", user.getSyncDuration());
        values.put("UpdatedAt", user.getUpdatedAt());
        values.put("IsSynched", user.getSynched());
        values.put("ServerId", user.getServerId());

        // insert row
        return db.update(TABLE_USER, values,   " Id = ?",
                new String[] { String.valueOf(user.getId()) });
    }



    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
