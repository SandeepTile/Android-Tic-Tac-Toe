package com.example.sandy.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int activePlayer=0;
    //0 = one ,  1 = two
    boolean gameIsActive=true;
    int[] gameState ={2,2,2,2,2,2,2,2,2};

    int[][] winningPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    public void dropIn(View view)
    {
        ImageView counter = (ImageView) view;



        int tappedCounter=Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter]==2&&gameIsActive) {

            gameState[tappedCounter]=activePlayer;

            counter.setTranslationY(-1000f);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.one);

                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.two);

                activePlayer = 0;
            }

            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for(int[] winningPostions :winningPositions){
                if(gameState[winningPostions[0]]==gameState[winningPostions[1]] &&
                        gameState[winningPostions[1]]==gameState[winningPostions[2]]&&
                        gameState[winningPostions[0]]!=2){

                    //someone has won

                    gameIsActive=false;

                    String winner="circle";

                    if(gameState[winningPostions[0]]==0)

                    {
                             winner="Cross";
                    }

                    TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);

                    winnerMessage.setText(winner +"Has Won!!!");
                   LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);

                   layout.setVisibility(View.VISIBLE);
                }else {

                    boolean gameIsOver=true;

                    for(int counterState:gameState){

                        if(counterState==2) gameIsOver=false;
                    }
                    if(gameIsOver){
                        TextView winnerMessage=(TextView)findViewById(R.id.winnerMessage);

                        winnerMessage.setText("It's a Drow");
                        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);

                        layout.setVisibility(View.VISIBLE);

                    }
                }

            }
        }
    }

    public void playAgain(View view)
    {
        gameIsActive=true;

        LinearLayout layout=(LinearLayout)findViewById(R.id.playAgainLayout);

        layout.setVisibility(View.INVISIBLE );

        activePlayer=0;

        for(int i=0;i<  gameState.length;i++)
        {
            gameState[i]=2;
        }

        GridLayout gridLayout=(GridLayout)findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
