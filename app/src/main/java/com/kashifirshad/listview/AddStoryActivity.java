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

public class AddStoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if(!bundle.isEmpty()){
            if(bundle.containsKey("projId")){
                TextView vProjId = (TextView) findViewById(R.id.projectId2);
                vProjId.setText( Integer.toString(bundle.getInt("projId")));
            }
            if(bundle.containsKey("projTitle")){
                TextView vProjId = (TextView) findViewById(R.id.editProjTitle);
                vProjId.setText( bundle.getString("projTitle"));
            }

            if(bundle.containsKey("storyId")){
                TextView vProjId = (TextView) findViewById(R.id.textId2);
                vProjId.setText( Integer.toString(bundle.getInt("storyId")));
            }
            if(bundle.containsKey("story")){
                EditText vProjId = (EditText) findViewById(R.id.Story);
                vProjId.setText( bundle.getString("story"));
            }
            if(bundle.containsKey("devHrs")){
                EditText vProjId = (EditText) findViewById(R.id.editHours);
                vProjId.setText( bundle.getString("devHrs"));
            }
            if(bundle.containsKey("devCost")){
                EditText vProjId = (EditText) findViewById(R.id.editCost);
                vProjId.setText( bundle.getString("devCost"));
            }

        }


        Button btnHome = (Button) findViewById(R.id.btnHome);
        btnHome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        Button btnAddProj = (Button) findViewById(R.id.btnSaveProj);
        btnAddProj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText editStory = (EditText) findViewById(R.id.Story);
                EditText editHours = (EditText) findViewById(R.id.editHours);
                EditText editCost = (EditText) findViewById(R.id.editCost);
                String storeyText = editStory.getText().toString();
                if(storeyText.length() == 0){
                    Toast.makeText(getApplicationContext(),
                            "Story Text Can not be blank " ,
                            Toast.LENGTH_LONG).show();

                }
                else if(storeyText == "Add New Story"){
                    Toast.makeText(getApplicationContext(),
                            "Story with this text can not be added" ,
                            Toast.LENGTH_LONG).show();
                }

                else{
                    DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
                    TextView vId = (TextView) findViewById(R.id.textId2);
                    int projIdNew = Integer.parseInt(vId.getText().toString());
                    int count = dh.getOtherProjectCount(storeyText, projIdNew);

                    if(count > 0){
                        Toast.makeText(getApplicationContext(), "A Story with same name already Exists ", Toast.LENGTH_LONG).show();
                        Log.e("ProjectAlreadyExists***","Project Already Exists");
                    }else {

                        TextView projTV = (TextView) findViewById(R.id.projectId2);
                        int projId = Integer.parseInt(projTV.getText().toString());

                        if(projIdNew == 0){
                            Project proj = new Project(storeyText, editHours.getText().toString(), editCost.getText().toString(), null,0,0,0,projId,0,0  );
                            long storyId= dh.createProject(proj);
                            vId.setText(Long.toString(storyId));
                        }else{
                            Project proj = new Project(projIdNew,storeyText, editHours.getText().toString(), editCost.getText().toString(), null,0,0,0,projId,0,0  );
                            dh.updateProject(proj);
                        }
                        Toast.makeText(getApplicationContext(), "Project Saved Successfully", Toast.LENGTH_LONG).show();

                    }
                    dh.closeDB();
                }
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