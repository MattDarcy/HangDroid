package com.beginners.hangdroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MultiplayerActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multiplayer);
    }

    public void startMultiGame(View v){

            EditText editText = (EditText) findViewById(R.id.editTextWord);
            String wordToGuess = editText.getText().toString();

            EditText editText1 = (EditText) findViewById(R.id.editP1);
            String player1 = editText.getText().toString();

            EditText editText2 = (EditText) findViewById(R.id.editP2);
            String player2 = editText.getText().toString();

            if((editText1.length() < 1) || (editText2.length() < 1)){
                Toast.makeText(this, "Player names must be at least 1 character.", Toast.LENGTH_SHORT).show();
            }
            else if (wordToGuess.length() < 3){
                Toast.makeText(this, "Word must be between 3 and 10 letters.", Toast.LENGTH_SHORT).show();
             }


            else {
                editText.setText("");
                editText1.setText("");
                editText2.setText("");
                Intent myIntent = new Intent(this, GameMultiActivity.class);
                myIntent.putExtra("WORD_IDENTIFIER", wordToGuess);
                myIntent.putExtra("WORD_IDENTIFIER1", player1);
                myIntent.putExtra("WORD_IDENTIFIER2", player2);
                startActivity(myIntent);
            }

    }



}
