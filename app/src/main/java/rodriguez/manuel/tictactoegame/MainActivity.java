package rodriguez.manuel.tictactoegame;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import rodriguez.manuel.tictactoegame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    public static final String KEY_BOX_POSITION = "box_position";

    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxPosition = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;
    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName, playerTwoName;
    private ImageView image1, image2, image3, image4, image5,
            image6, image7, image8, image9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        EdgeToEdge.enable(this);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });

        playerOneName = findViewById(R.id.playerOneName);
        playerTwoName = findViewById(R.id.playerTwoName);

        playerOneLayout = findViewById(R.id.playerOneLayout);
        playerTwoLayout = findViewById(R.id.playerTwoLayout);

        image1 = findViewById(R.id.image1);
        image2 = findViewById(R.id.image2);
        image3 = findViewById(R.id.image3);
        image4 = findViewById(R.id.image4);
        image5 = findViewById(R.id.image5);
        image6 = findViewById(R.id.image6);
        image7 = findViewById(R.id.image7);
        image8 = findViewById(R.id.image8);
        image9 = findViewById(R.id.image9);

        combinationList.add(new int[]{0,1,2}); // Horizontal Combination
        combinationList.add(new int[]{3,4,5});
        combinationList.add(new int[]{6,7,8});

        combinationList.add(new int[]{0,3,6}); // Vertical Combination
        combinationList.add(new int[]{1,4,7});
        combinationList.add(new int[]{2,5,8});

        combinationList.add(new int[]{0,4,8}); // Angle Combination
        combinationList.add(new int[]{2,4,6});

        final String getPlayerOneName = getIntent().getStringExtra("playerOne");
        final String getPlayerTwoName = getIntent().getStringExtra("playerTwo");

        playerOneName.setText(getPlayerOneName);
        playerTwoName.setText(getPlayerTwoName);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelected(0)){
                    performAction((ImageView)v, 0);
                }
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isBoxSelected(1)) {
                    performAction((ImageView)v, 1 );
                }
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelected(2)){
                    performAction((ImageView)v, 2);
                }
            }
        });

        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelected(3)){
                    performAction((ImageView)v, 3 );
                }
            }
        });

        image5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelected(4)){
                    performAction((ImageView) v, 4);
                }
            }
        });

        image6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelected( 5)){
                    performAction((ImageView)v, 5);
                }
            }
        });

        image7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelected(6)){
                    performAction((ImageView)v, 6);
                }
            }
        });

        image8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelected(7)){
                    performAction((ImageView)v, 7);
                }
            }
        });

        image9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isBoxSelected(8)){
                    performAction((ImageView)v, 8);
                }
            }
        });

        if(savedInstanceState != null){
            boxPosition = savedInstanceState.getIntArray(KEY_BOX_POSITION);
            restoreGameState();
        }


    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray(KEY_BOX_POSITION, boxPosition);
    }


    private void restoreGameState(){
        for(int i =0; i < boxPosition.length; i++){
            if(boxPosition[i] == 1){
                setImageResource(i, R.drawable.x_letter);
            } else if(boxPosition[i] == 2){
                setImageResource(i, R.drawable.o_letter);
            }
        }
        changePlayerTurn(playerTurn);
    }

    private void setImageResource(int index, int resId){

        switch(index){
            case 0:
                image1.setImageResource(resId);
                break;
            case 1:
                image2.setImageResource(resId);
                break;
            case 2:
                image3.setImageResource(resId);
                break;
            case 3:
                image4.setImageResource(resId);
                break;
            case 4:
                image5.setImageResource(resId);
                break;
            case 5:
                image6.setImageResource(resId);
                break;
            case 6:
                image7.setImageResource(resId);
                break;
            case 7:
                image8.setImageResource(resId);
                break;
            case 8:
                image9.setImageResource(resId);
                break;
        }
    }

    private void performAction(ImageView imageView, int selectedBoxPosition){
        boxPosition[selectedBoxPosition] = playerTurn;

        if(playerTurn == 1){
            imageView.setImageResource(R.drawable.x_letter);

            if(checkPlayerWin()){
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        playerOneName.getText().toString() + " has won the match",
                        MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();

            } else if(totalSelectedBoxes == 9){
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        "It is a draw!",
                        MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else {
                changePlayerTurn(2);
                totalSelectedBoxes++;
            }
        }
        else {
            imageView.setImageResource(R.drawable.o_letter);
            if(checkPlayerWin()){
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        playerTwoName.getText().toString() + " has won the match",
                        MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            }
            else if(selectedBoxPosition == 9){
                WinDialog winDialog = new WinDialog(MainActivity.this,
                        "It is a draw!",
                        MainActivity.this);
                winDialog.setCancelable(false);
                winDialog.show();
            } else {
                changePlayerTurn(1);
                totalSelectedBoxes++;
            }
        }
    }

    private void changePlayerTurn(int currentPlayerTurn){
        playerTurn = currentPlayerTurn;

        if(playerTurn == 1){
            playerOneLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        } else {
            playerTwoLayout.setBackgroundResource(R.drawable.round_back_blue_border);
            playerOneLayout.setBackgroundResource(R.drawable.round_back_dark_blue);
        }
    }


    private boolean checkPlayerWin(){
        boolean response = false;
        for(int i =0; i < combinationList.size(); i++){
            final int[] combination = combinationList.get(i);
            if(boxPosition[combination[0]] == playerTurn
                    && boxPosition[combination[1]] == playerTurn
                    && boxPosition[combination[2]] == playerTurn){
                response = true;
            }
        }
        return response;
    }

    private boolean isBoxSelected(int position){
        return boxPosition[position] == 0;
    }

    public void restartMatch(){
        boxPosition = new int[] {0,0,0,0,0,0,0,0,0};
        playerTurn = 1;

        totalSelectedBoxes = 1;
        image1.setImageResource(R.color.dark_blue);
        image2.setImageResource(R.color.dark_blue);
        image3.setImageResource(R.color.dark_blue);
        image4.setImageResource(R.color.dark_blue);
        image5.setImageResource(R.color.dark_blue);
        image6.setImageResource(R.color.dark_blue);
        image7.setImageResource(R.color.dark_blue);
        image8.setImageResource(R.color.dark_blue);
        image9.setImageResource(R.color.dark_blue);
    }



}