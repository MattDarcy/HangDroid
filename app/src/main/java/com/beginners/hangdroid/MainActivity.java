package com.beginners.hangdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //can be public or private
    //public can be called from outside the class
    //private can only be called from inside the class
    //.xml button starts this so it must be PUBLIC

    //Here the input parameter is a view, the thing we click
    public void startSinglePlayerGame(View v){
        Intent myIntent = new Intent(this, GameActivity.class);
        //Intent means an intent to do something, start an activity, send an email, open a browser
        startActivity(myIntent);
    }

    public void startMultiGame(View v){
        Intent myIntent = new Intent(this, MultiplayerActivity.class);
        startActivity(myIntent);
    }

    public void openScores (View v){
        Intent myIntent = new Intent(this, ScoresActivity.class);
        startActivity(myIntent);
    }

}
