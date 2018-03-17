package com.kashifirshad.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class AddProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("Project hai:", "ONE 72" );
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);
        Log.e("Project hai:", "ONE 72" );


        Button btnAddProj = (Button) findViewById(R.id.btnSaveProj);
        btnAddProj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editProjTitle = (EditText) findViewById(R.id.editProjTitle);
                EditText editHours = (EditText) findViewById(R.id.editHours);
                EditText editCost = (EditText) findViewById(R.id.editCost);
//                editProjTitle.getText(), editHours.getText(), editCost.getText()
                Log.e("Project Id haii:", "" );
                String storeyText = editProjTitle.getText().toString();
                Log.e("storyText", storeyText);
                if(storeyText.length() == 0){
                     Toast.makeText(getApplicationContext(),
                     "Story Text Can not be blank " ,
                     Toast.LENGTH_LONG).show();
                }else{
                    DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
                    List<Project> projList = dh.getAllProjectsByStorey(storeyText);
                    Log.e("ProjectSize***",Integer.toString(projList.size()));
                    if(projList.size() > 0){
                        Toast.makeText(getApplicationContext(), "A project with same name already Exists ", Toast.LENGTH_LONG).show();
                        Log.e("ProjectAlreadyExists***","Project Already Exists");
                    }else {
                        Project proj = new Project(editProjTitle.getText().toString(), editHours.getText().toString(), editCost.getText().toString(), null,0,0,0,0,0,0  );
                        long projId= dh.createProject(proj);
                        Log.e("Project Id hai:", Long.toString(projId) );
                        Toast.makeText(getApplicationContext(), "Project Saved Successfully", Toast.LENGTH_LONG).show();
                    }
                    dh.closeDB();
                }
            }
        });
    }
}
