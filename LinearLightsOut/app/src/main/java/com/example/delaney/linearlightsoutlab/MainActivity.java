package com.example.delaney.linearlightsoutlab;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LightsOutGame mGame;
    private TextView mGameStateTextView;
    private Button[] mButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGame = new LightsOutGame();
//mGameStateTextView = findViewById(R.id.game_state_text_view);
    //    mButtons = new Button[LightsOutGame.MIN_NUM_BUTTONS];
    //    mButtons[0]= findViewById(R.id.button0);
     //   mButtons[1]= findViewById(R.id.button1);
     //   mButtons[2]= findViewById(R.id.button2);
     //   mButtons[3]= findViewById(R.id.button3);
     //   mButtons[4]= findViewById(R.id.button4);
     //   mButtons[5]= findViewById(R.id.button5);
     //   mButtons[6]= findViewById(R.id.button6);



    }


   public void pressedSquare (View view){
        String tagAsString = view.getTag().toString();
        int tagAsInt = Integer.parseInt(tagAsString);

        // Quick Toast Test to see if buttons are working
        //   Log.d("TTT", "You pressed index" + tagAsInt);
        //  Toast.makeText(this, "You pressed index" + tagAsInt, Toast.LENGTH_SHORT).show();

    }

    public void pressedNewGame (View view){

        Toast.makeText(this, "You pressed New Game" , Toast.LENGTH_SHORT).show();

    }
}
