package com.kashifirshad.listview;

//import android.support.v7.app.AppCompatActivity;
//import android.os.Bundle;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ExpandableListView.OnGroupClickListener;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.Toast;

public class MainActivity extends Activity{

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<Project> listDataHeader;
    HashMap<Project, List<Project>> listDataChild;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnAddProj = (Button) findViewById(R.id.addProj);
//        Button btnAddStory = (Button) findViewById(R.id.btnAddStory);
//        btnAddStory.setOnClickListener(this);
        btnAddProj.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddProjectActivity.class);
                startActivity(intent);
                finish();
            }
        });






        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(MainActivity.this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
//                 Toast.makeText(getApplicationContext(),
//                 "Group Clicked*** " + listDataHeader.get(groupPosition),
//                 Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
//                Toast.makeText(MainActivity.this,
//                        listDataHeader.get(groupPosition) + " Expanded ***",
//                        Toast.LENGTH_SHORT).show();
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
//                Toast.makeText(MainActivity.this,
//                        listDataHeader.get(groupPosition) + " Collapsed",
//                        Toast.LENGTH_SHORT).show();

            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                // TODO Auto-generated method stub
//                Toast.makeText(
//                        getApplicationContext(),
//                        listDataHeader.get(groupPosition)
//                                + " : "
//                                + listDataChild.get(
//                                listDataHeader.get(groupPosition)).get(
//                                childPosition), Toast.LENGTH_SHORT)
//                        .show();
                Project story = (Project) listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition);
                Project proj = (Project)listDataHeader.get(groupPosition);

                Intent intent = new Intent(getApplicationContext(), AddStoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("projId",proj.getId());
                bundle.putString("projTitle", proj.getStory());
                bundle.putInt("storyId",story.getId());
                bundle.putString("story", story.getStory());
                bundle.putString("devHrs",story.getEstimatedHrs());
                bundle.putString("devCost",story.getEstimateCost());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

//                listDataHeader.add("kk..");
//                List<String> comingSoon = new ArrayList<String>();
//                comingSoon.add("2 Guns");
//                comingSoon.add("The Smurfs 2");
//                comingSoon.add("The Spectacular Now");
//                comingSoon.add("The Canyons");
//                comingSoon.add("Europa Report");
//
//                listDataChild.put(listDataHeader.get(3), comingSoon); // Header, Child data
//
//                listAdapter.setNewItems(listDataHeader, listDataChild);
                return false;
            }
        });
//        listAdapter.IsFirstRun = 0;


    }



    public void onClickStory(View v) {
        Log.e("***","On Click pressed");
        switch (v.getId()) {

            case R.id.btnAddStory:
                Project proj = (Project) v.getTag();
                Intent intent = new Intent(getApplicationContext(), AddStoryActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("projId",proj.getId());
                bundle.putString("projTitle", proj.getStory());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                break;
            default:
                break;
        }

    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        DatabaseHelper dh = new DatabaseHelper(getApplicationContext());
        listDataHeader = dh.getChildProjects(0);
        Log.e("ListDataHeader****", listDataHeader.toString());
        listDataChild = new HashMap<Project, List<Project>>();
        for(int i=0; i< listDataHeader.size(); i++){
                Log.e("i***",Integer.toString(i));
                Log.e("ithDataHeader***",listDataHeader.get(i).toString());
                List<Project> childProjects = dh.getChildProjects(listDataHeader.get(i).getId());
                Log.e("ith children****",childProjects.toString());
                childProjects.add( new Project(999999,"Add New Story","","","",0,0,0,listDataHeader.get(i).getId(),0,0));
                Log.e("Default Child***",childProjects.toString() );
                listDataChild.put((Project) listDataHeader.get(i),childProjects);
        }
    }
}