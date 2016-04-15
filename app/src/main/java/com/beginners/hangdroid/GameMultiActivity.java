package com.beginners.hangdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


// Can take the longest streak if desired to scorekeeping
// Keep track of and display who the word is being entered FOR, and display on the multiplayer activity.
// Score format for multiplayer: ____ beat ____ on mm/dd/yyyy at hh:mm:ss
// Get rid of multiplayer activity, scores generated automatically

public class GameMultiActivity extends ActionBarActivity {

    String mword;
    int mFailCounter = 0;
    int mGuessedLetters = 0;
    int mPoints = 0;
    String mprevious = "";
    String mGuessed = "";
    String mCheckLetter = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    boolean mIsLetter = false;
    boolean mAlreadyGuessed = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__multi_game);
        String wordSent = getIntent().getStringExtra("WORD_IDENTIFIER");
        String playerName1 = getIntent().getStringExtra("WORD_IDENTIFIER1");
        String playerName2 = getIntent().getStringExtra("WORD_IDENTIFIER2");
        Log.d("MYLOG",wordSent);
        mword = wordSent.toUpperCase();
        createTextViews(wordSent);
    }


    /**
     * Retrieving the letter introduced on the editText
     * @param v (button click)
     */
    public void introduceLetter(View v){
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);
            //The (EditText) is called 'Casting' in Java. The findView returns a view, but this is not
            //a view, it is an EditText and so we 'cast' it.
        String letter = myEditText.getText().toString();
        String letterUpper = letter.toUpperCase();
        myEditText.setText("");
        Log.d("MYLOG","The letter introduced is "+letterUpper);
        for(int j = 0; j < mCheckLetter.length();j++){
            if(mCheckLetter.charAt(j) == letterUpper.charAt(0)){
                mIsLetter = true;
            }
        }
        for(int j = 0; j < mGuessed.length();j++){
            if(mGuessed.charAt(j) == letterUpper.charAt(0)){
                mAlreadyGuessed = true;
            }
        }

        Log.d("STATUS", "" + mIsLetter);
        Log.d("STATUS", "" + mAlreadyGuessed);

        if(mIsLetter){
            if(!mAlreadyGuessed){
                if(letterUpper.length() == 1){
                    checkLetter(letterUpper);
                }
            }
            else{

                Toast.makeText(this,"You already guessed that letter!",Toast.LENGTH_SHORT).show();

            }
        }


        else{
           Toast.makeText(this,"Please introduce a letter",Toast.LENGTH_SHORT).show();
        }
        mGuessed = mGuessed + letterUpper;
        mIsLetter = false; //reset the boolean because the next character may not be a letter.
        mAlreadyGuessed = false; //reset the boolean because the next character may be a repeat.
    }

    /**
     * This method is checking if the letter introduced matches any letter in the word to guess.
     * @param introducedLetter, is the letter introduced by the player.
     */
    public void checkLetter(String introducedLetter){
        char charIntroduced = introducedLetter.charAt(0);
        boolean letterGuessed = false;
        for(int i = 0; i < mword.length(); i++){
            char charFromTheWord = mword.charAt(i);
            Log.d("MYLOG","There letter we are checking is " + charFromTheWord);
            if (charFromTheWord == charIntroduced){
                Log.d("MYLOG","There was one match");
                letterGuessed = true;
                showLettersAtIndex(i,charFromTheWord);
                mGuessedLetters++;
            }
        }

        if(letterGuessed == false){
            letterFailed(Character.toString(charIntroduced));
        }

        if(mGuessedLetters == mword.length()){
            //mPoints++;
            //clearScreen();
            finish();
        }

    }




    /**
     * This is the function that happens when
     */
    public void letterFailed(String charIntroduced){
        mFailCounter++;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if(mFailCounter == 1){
            imageView.setImageResource(R.drawable.hangdroid_1);
            indexFailedLetter(charIntroduced);
        }
        if(mFailCounter == 2){
            imageView.setImageResource(R.drawable.hangdroid_2);
            indexFailedLetter(charIntroduced);
        }
        if(mFailCounter == 3){
            imageView.setImageResource(R.drawable.hangdroid_3);
            indexFailedLetter(charIntroduced);
        }
        if(mFailCounter == 4){
            imageView.setImageResource(R.drawable.hangdroid_4);
            indexFailedLetter(charIntroduced);
        }
        if(mFailCounter == 5){
            imageView.setImageResource(R.drawable.hangdroid_5);
            indexFailedLetter(charIntroduced);
        }
        if(mFailCounter == 6){
            Intent gameOverIntent = new Intent(this,GameOverActivity.class);
            gameOverIntent.putExtra("POINTS_IDENTIFIER",mPoints);
            startActivity(gameOverIntent);
        }

    }

    /**
     * This puts the failed guesses in the red text one at a time.
     * @param letterGuessed, the letter introduced by the user.
     */
    public void indexFailedLetter(String letterGuessed){
        TextView fails = (TextView) findViewById(R.id.fails);
        fails.setText(mprevious + letterGuessed);
        mprevious = fails.getText().toString();
    }

    /**
     * Displaying a letter guessed by the user
     * @param position, position of the letter
     * @param letterGuessed
     */
    public void showLettersAtIndex(int position, char letterGuessed){
        LinearLayout layoutLetter = (LinearLayout) findViewById(R.id.layoutLetters);
        TextView textView = (TextView) layoutLetter.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));
    }

    public void createTextViews(String word){
        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        for (int i = 0; i < word.length(); i++){
            TextView newTextView = (TextView) getLayoutInflater().inflate(R.layout.textview,null);
            layoutLetters.addView(newTextView);
        }
    }


    public void clearScreen(){
        TextView fails = (TextView) findViewById(R.id.fails);
        fails.setText("");

        mGuessedLetters = 0;
        mFailCounter = 0;

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);
        for(int i=0; i<layoutLetters.getChildCount();i++){
            TextView currentTextView = (TextView) layoutLetters.getChildAt(i);
            currentTextView.setText("_");
        }

        fails.setText("");
        mGuessed = "";
        mprevious = "";

        //can just say finish(); and get back to the word entry

        //ImageView imageView = (ImageView) findViewById(R.id.imageView);
        //imageView.setImageResource(R.drawable.hangdroid_0);
        Toast.makeText(this,"Word Guessed! +1 Point",Toast.LENGTH_SHORT).show();
    }


}
