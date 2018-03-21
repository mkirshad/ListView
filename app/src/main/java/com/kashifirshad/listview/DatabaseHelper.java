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
    private static final int DATABASE_VERSION = 6;

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
            "EmailAddress TEXT, " +
            "SkypeId TEXT, " +
            "WatsAppNo TEXT, " +
            "AddressLine1 TEXT, " +
            "AddressLine2 TEXT, " +
            "City TEXT, " +
            "State TEXT, " +
            "Country TEXT, " +
            "CreatedAt DATETIME, " +
            "UpdatedAt DATETIME, " +
            "IsSynched INTEGER, " +
            "ServerId INTEGER" +
            ")";

    private static final String CREATE_TABLE_PROJECT = "CREATE TABLE IF NOT EXISTS " + TABLE_PROJECT + " ( " +
            "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
            "Story TEXT, " +
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
        values.put("EstimatedHrs", proj.getEstimatedHrs());
        values.put("EstimateCost", proj.getEstimateCost());
        values.put("DeliveryDate", proj.getDeliveryDate());
        values.put("CreatedAt", getDateTime());
        values.put("UpdatedAt", getDateTime());
        values.put("UserId", proj.getUserId());
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

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Project td = new Project();
        td.setId(c.getInt(c.getColumnIndex("Id")));
        td.setStory(c.getString(c.getColumnIndex("Story")));
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

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c != null)
        if (c.moveToFirst()) {
            do {
                Project td = new Project();
                td.setId(c.getInt(c.getColumnIndex("Id")));
                td.setStory(c.getString(c.getColumnIndex("Story")));
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

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c != null)
            if (c.moveToFirst()) {
                do {
                    Project td = new Project();
                    td.setId(c.getInt(c.getColumnIndex("Id")));
                    td.setStory(c.getString(c.getColumnIndex("Story")));
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

    public HashMap<Project, List<Project>> getProjectHierarchy(){
        HashMap<Project, List<Project>> projectHierarchy = new HashMap<Project, List<Project>>();
        List<Project> parentProjects = getChildProjects(0);

        if(parentProjects.size() > 0){
            for (ListIterator<Project> iter = parentProjects.listIterator(); iter.hasNext(); ) {
                Project proj = iter.next();
                List<Project> childProjects = getChildProjects(proj.getId());
                childProjects.add( new Project(999999,"Add Story to this project","","","",0,0,0,proj.getId(),0,0));
                Log.e("Default Child***",Integer.toString(childProjects.size()));
                projectHierarchy.put(proj,childProjects);
            }
        }

        return projectHierarchy;
    }


    /*
* getting all Projects
* */
    public List<Project> getAllProjectsByStory(String storey) {
        List<Project> projects = new ArrayList<Project>();
        String selectQuery = "SELECT  * FROM " + TABLE_PROJECT + " WHERE Story = '" + storey.replace("'","\'") +"'";

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c != null)
        if (c.moveToFirst()) {
            do {

                Log.e("Id***",Integer.toString(c.getColumnIndex("Id")));
                Log.e("Story***",Integer.toString(c.getColumnIndex("Story")));
                Log.e("EstimatedHrs***",Integer.toString(c.getColumnIndex("EstimatedHrs")));
                Log.e("EstimateCost***",Integer.toString(c.getColumnIndex("EstimateCost")));
                Log.e("DeliveryDate***",Integer.toString(c.getColumnIndex("DeliveryDate")));
                Log.e("CreatedAt***",Integer.toString(c.getColumnIndex("CreatedAt")));
                Log.e("UpdatedAt***",Integer.toString(c.getColumnIndex("UpdatedAt")));
                Log.e("IsSynched***",Integer.toString(c.getColumnIndex("IsSynched")));
                Log.e("ServerUserId***",Integer.toString(c.getColumnIndex("ServerUserId")));
                Log.e("ServerUserId***",Integer.toString(c.getColumnIndex("ServerUserId")));
                Log.e("ParentId***",Integer.toString(c.getColumnIndex("ParentId")));
                Log.e("ServerId***",Integer.toString(c.getColumnIndex("ServerId")));
                Log.e("ServerParentId***",Integer.toString(c.getColumnIndex("ServerParentId")));

                Project td = new Project();
                td.setId(c.getInt(c.getColumnIndex("Id")));
                td.setStory(c.getString(c.getColumnIndex("Story")));
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
        values.put("EstimatedHrs", proj.getEstimatedHrs());
        values.put("EstimateCost", proj.getEstimateCost());
        values.put("DeliveryDate", proj.getDeliveryDate());
//        values.put("CreatedAt", getDateTime());
        values.put("UpdatedAt", getDateTime());
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

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }

    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
