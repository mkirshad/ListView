package com.kashifirshad.listview;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class AddStoryActivity extends AppCompatActivity {
    private static final int PICK_FILE_REQUEST = 1;
    ProgressDialog dialog;
    private String selectedFilePath;
    TextView tvFileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_story);
        tvFileName = (TextView) findViewById(R.id.tv_file_name);



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
            if(bundle.containsKey("filePaths")){
                tvFileName.setText( bundle.getString("filePaths"));
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
                String StoryText = editStory.getText().toString();
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
                        Toast.makeText(getApplicationContext(), "A Story with same name already Exists ", Toast.LENGTH_LONG).show();
                    }else {

                        TextView projTV = (TextView) findViewById(R.id.projectId2);
                        int projId = Integer.parseInt(projTV.getText().toString());

                        if(projIdNew == 0){
                            Project proj = new Project(StoryText,tvFileName.getText().toString(), editHours.getText().toString(), editCost.getText().toString(), null,0,0,projId,0,0, MainActivity.user  );
                            long storyId= dh.createProject(proj);
                            vId.setText(Long.toString(storyId));
                        }else{
                            Project proj = new Project(projIdNew,StoryText,tvFileName.getText().toString(), editHours.getText().toString(), editCost.getText().toString(), null,0,0,projId,0,0 , MainActivity.user );
                            dh.updateProject(proj);
                        }
                        Toast.makeText(getApplicationContext(), "Project Saved Successfully", Toast.LENGTH_LONG).show();

                    }
                    dh.closeDB();
                }
            }
        });

        Button checkBoxFile = (Button) findViewById(R.id.btnUploadFile);
        checkBoxFile.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                    showFileChooser();
            }
        });


    }


    private void showFileChooser() {
        Intent intent = new Intent();
        //sets the select file to all types of files
        intent.setType("*/*");
        //allows to select data and return it
        intent.setAction(Intent.ACTION_GET_CONTENT);
        //starts new activity to select file and return data
        startActivityForResult(Intent.createChooser(intent,"Choose File to Upload.."),PICK_FILE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == PICK_FILE_REQUEST){
                if(data == null){
                    //no data present
                    return;
                }


                Uri selectedFileUri = data.getData();
                selectedFilePath = FilePath.getPath(this,selectedFileUri);
//                Log.i(TAG,"Selected File Path:" + selectedFilePath);

                if(selectedFilePath != null && !selectedFilePath.equals("")){
                    String filePaths = tvFileName.getText().toString();
                    if(filePaths != null && !filePaths.equals("") ){
                        if(!filePaths.contains(selectedFilePath))
                            filePaths= filePaths +", "+selectedFilePath;
                    }else{
                        filePaths = selectedFilePath;
                    }

                    tvFileName.setText(filePaths);
                }else{
                    Toast.makeText(this,"Cannot upload file to server",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(),
                "Press Home button to go back!" ,
                Toast.LENGTH_LONG).show();
    }
}
