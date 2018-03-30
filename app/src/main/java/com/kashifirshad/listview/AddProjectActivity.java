package com.kashifirshad.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class AddProjectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_project);

        Button btnAddProj = (Button) findViewById(R.id.btnSaveProj);
        btnAddProj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editProjTitle = (EditText) findViewById(R.id.editProjTitle);
                EditText editHours = (EditText) findViewById(R.id.editHours);
                EditText editCost = (EditText) findViewById(R.id.editCost);
                String StoryText = editProjTitle.getText().toString();
                if(StoryText.length() == 0){
                     Toast.makeText(getApplicationContext(),
                     "Story Text Can not be blank " ,
                     Toast.LENGTH_LONG).show();

                }
                else if(StoryText == "Add New Story"){
                    Toast.makeText(getApplicationContext(),
                            "Story with this text can not be added" ,
                            Toast.LENGTH_LONG).show();
                }

                else{
                    DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
                    TextView vId = (TextView) findViewById(R.id.textId2);
                    int projIdNew = Integer.parseInt(vId.getText().toString());
                    int count = dh.getOtherProjectCount(StoryText, projIdNew);

                    if(count > 0){
                        Toast.makeText(getApplicationContext(), "A project with same name already Exists ", Toast.LENGTH_LONG).show();
                    }else {



                        if(projIdNew == 0){
                            Project proj = new Project(editProjTitle.getText().toString(), null, editHours.getText().toString(), editCost.getText().toString(), null,0,0,0,0,0,MainActivity.user  );
                            long projId= dh.createProject(proj);
                            vId.setText(Long.toString(projId));
                        }else{
                            Project proj = new Project(projIdNew, editProjTitle.getText().toString(),null, editHours.getText().toString(), editCost.getText().toString(), null,0,0,0,0,0 ,MainActivity.user );
                            dh.updateProject(proj);
                        }
                        Toast.makeText(getApplicationContext(), "Project Saved Successfully", Toast.LENGTH_LONG).show();

                    }
                    dh.closeDB();
                }
            }
        });

        Button btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),
                "Press Home button to go back!" ,
                Toast.LENGTH_LONG).show();
    }
}
