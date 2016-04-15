package com.beginners.hangdroid;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;


public class GameOverActivity extends ActionBarActivity {

    int mPoints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        int points = getIntent().getIntExtra("POINTS_IDENTIFIER", 0);
        TextView textViewPoints = (TextView) findViewById(R.id.textViewPoints);
        textViewPoints.setText(String.valueOf(points));
        mPoints = points;
        Toast.makeText(this,"Game Over! Enter Score", Toast.LENGTH_SHORT).show();
    }

        public void saveScore(View v){

            SharedPreferences preferences = getSharedPreferences("MYPREFERENCES", Context.MODE_PRIVATE); //preferences are private for this application
            //name of user, points, date
            //create  basic structure
            //Name X Points mm/dd/yyyy ln Name2 Y Points mm/dd/yyyy
            //use the ln as a sorting element
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String currentDateandTime = sdf.format(new Date());
            String year = currentDateandTime.substring(0,4);
            String month = currentDateandTime.substring(4,6);
            String day = currentDateandTime.substring(6,8);
            String hour = currentDateandTime.substring(9,11);
            String minute = currentDateandTime.substring(11,13);
            String second = currentDateandTime.substring(13,15);
            EditText editText = (EditText) findViewById(R.id.editTextName);
            String name = editText.getText().toString();
            SharedPreferences.Editor editor = preferences.edit();
            String previousScores = preferences.getString("SCORES",""); // get scores from before in addition to this score
            editor.putString("SCORES", name + " " + mPoints + " POINTS " + month + "/" + day + "/" + year + " at " + hour + ":" + minute + ":" + second + "\n" + previousScores);
            editor.commit();
            finish();

        }


}
