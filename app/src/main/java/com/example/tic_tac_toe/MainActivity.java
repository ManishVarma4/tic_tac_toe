package com.example.tic_tac_toe;

import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //0=x, 1=O;
    boolean active= true;
    int activePlayer=0;
    int[] gameState={2,2,2,2,2,2,2,2,2};  // 0-x, 1=O, 2= empty;
    int [][] winPos={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void playerTap(View view){
        ImageView img= (ImageView) view;

        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!active){
            gameReset( view);
        }
        if(gameState[tappedImage]==2 && active) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                activePlayer = 1;
                TextView status = findViewById((R.id.status));
                status.setText("O's Turn");
            } else {
                img.setImageResource(R.drawable.o);
                activePlayer = 0;
                TextView status = findViewById((R.id.status));
                status.setText("X's Turn");
            }

            img.animate().translationYBy(1000f).setDuration(300);
        }
        for(int [] wP: winPos){
            String winner;
            if(gameState[wP[0]]== gameState[wP[1]] && gameState[wP[1]]== gameState[wP[2]] &&gameState[wP[0]]!= 2){

                if(gameState[wP[0]]==0){
                    winner ="X has Won";
                    active=false;
                }
                else{
                    winner= "O has Won";
                    active=false;
                }
                TextView status = findViewById((R.id.status));
                status.setText(winner);

            }
//            else{
//                winner= "It's a Tie...";
//                active=false;
//
//            }

//            TextView status = findViewById((R.id.status));
//            status.setText(winner);
        }
        if(active){
            String winner;
            for(int i=0;i<gameState.length;i++){
                if(gameState[i]==2) break;
                if(i==gameState.length-1 && gameState[i]!=2){
                    winner= "It's a Tie...";
                    active=false;
                    TextView status = findViewById((R.id.status));
                    status.setText(winner);
                    break;
                }
            }
        }
    }
    public void gameReset(View view){
        active=true;
        activePlayer=0;
        Arrays.fill(gameState, 2);
        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}