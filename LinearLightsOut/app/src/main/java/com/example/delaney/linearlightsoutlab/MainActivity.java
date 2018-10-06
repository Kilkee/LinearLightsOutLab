package com.example.delaney.linearlightsoutlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LightsOutGame mGame;
    private TextView mGameStateTextView;

    private static final String GAME_BUTTONS_KEY = "GAME_BUTTONS";
    private static final String NUM_TURNS_KEY = "NUM_TURNS";
    private List<Button> mButtons;


    //private Button[] mButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Constructor call, that calls the LightsOutGame constructor in lightsOutGame.java
        mGame = new LightsOutGame();
        mGameStateTextView = findViewById(R.id.game_state_text_view);
     //   mButtons = new Button[LightsOutGame.MIN_NUM_BUTTONS];  // make a new array with 7 spots available
      //  mButtons[0]= findViewById(R.id.button0);
      //  mButtons[1]= findViewById(R.id.button1);
      //  mButtons[2]= findViewById(R.id.button2);
      //  mButtons[3]= findViewById(R.id.button3);
      //  mButtons[4]= findViewById(R.id.button4);
      //  mButtons[5]= findViewById(R.id.button5);
      //  mButtons[6]= findViewById(R.id.button6);

        mButtons = new ArrayList<>();
        mButtons.add((Button) findViewById(R.id.button0));
        mButtons.add((Button) findViewById(R.id.button1));
        mButtons.add((Button) findViewById(R.id.button2));
        mButtons.add((Button) findViewById(R.id.button3));
        mButtons.add((Button) findViewById(R.id.button4));
        mButtons.add((Button) findViewById(R.id.button5));
        mButtons.add((Button) findViewById(R.id.button6));

        restoreState(savedInstanceState);
        updateView();

    }

    private void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.getString(GAME_BUTTONS_KEY) != null) {
            // Note, getIntArray wasn't working so using a String (which is a pain)
            String gameString = savedInstanceState.getString(GAME_BUTTONS_KEY);
            //  Log.d(Constants.TAG, "Game String = " + gameString);
            int[] gameArray = new int[7];
            for (int i = 0; i < gameString.length(); i++) {
                gameArray[i] = gameString.charAt(i) == '1' ? 1 : 0;
            }
            mGame.setAllValues(gameArray);

            int numPresses = savedInstanceState.getInt(NUM_TURNS_KEY);
            mGame.setNumPresses(numPresses);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //  Log.d(Constants.TAG, "Saving " + mGame.toString());
        outState.putString(GAME_BUTTONS_KEY, mGame.toString());
        outState.putInt(NUM_TURNS_KEY, mGame.getNumPresses());
    }

// pressedSquare tells the model which button you pressed
   public void pressedSquare (View view){
       // String tagAsString = view.getTag().toString();
       // int tagAsInt = Integer.parseInt(tagAsString);

       int tag = Integer.parseInt(view.getTag().toString());
       mGame.pressedButtonAtIndex(tag);

        // Quick Toast Test to see if buttons are working
        //   Log.d("TTT", "You pressed index" + tagAsInt);
        //  Toast.makeText(this, "You pressed index" + tagAsInt, Toast.LENGTH_SHORT).show();

      // mGame.pressedButtonAtIndex(tag);



       updateView();  //Calls updateView method

    }

    private void updateView() {

        boolean didWin = mGame.checkForWin();
        for (int i = 0; i < mButtons.size(); ++i) {
            Button gameButton = mButtons.get(i);
            gameButton.setText(Integer.toString(mGame.getValueAtIndex(i)));
            gameButton.setEnabled(!didWin);
        }
        int turns = mGame.getNumPresses();
        String gameStr = getResources().getQuantityString(R.plurals.game_state_turns, turns, turns);
        if (didWin) {
            gameStr = getString(R.string.win);
        } else if (turns == 0) {
            gameStr = getString(R.string.label_start);
        }
        mGameStateTextView.setText(gameStr);

      // mGameStateTextView.setText(mGame.getValueAtIndex());



    }

    public void pressedNewGame (View view){

      //  Toast.makeText(this, "You pressed New Game" , Toast.LENGTH_SHORT).show();

        mGame = new LightsOutGame();
        updateView();
    }
}
