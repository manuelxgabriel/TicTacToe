package rodriguez.manuel.tictactoegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import rodriguez.manuel.tictactoegame.databinding.ActivityMainBinding;


/*
*
* MainActivity class for the Tic Tac Toe game.
* Handles the game logic, UI interactions, and state management.
* */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    public static final String KEY_BOX_POSITION = "box_position";
    public static final String KEY_PLAYER_TURN = "player_turn";

    private final List<int[]> combinationList = new ArrayList<>();
    private int[] boxPosition = {0,0,0,0,0,0,0,0,0};
    private int playerTurn = 1;
    private int totalSelectedBoxes = 1;
    private LinearLayout playerOneLayout, playerTwoLayout;
    private TextView playerOneName, playerTwoName;
    private ArrayList<String> playerNames;
    private ImageView image1, image2, image3, image4, image5,
            image6, image7, image8, image9;


/**
* Called when the activity is first created.
* @param savedInstanceState If the activity is being re-initialized after
 *                           previously being shut doen then this Bundle contains the
 *                          data it most recently supplied in onSaveInstanceState(Bundle).
*/
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
            playerTurn = savedInstanceState.getInt(KEY_PLAYER_TURN);
            restoreGameState();
        }

        // Get player names from the intent if available
        Intent intent = getIntent();
        playerNames = intent.getStringArrayListExtra("playerNames");

        if(playerNames != null && !playerNames.isEmpty()){
            Toast.makeText(this ,"Player names loaded", Toast.LENGTH_SHORT).show();
            initializeGame(playerNames); // restarts the game
        } else {
            playerNames = new ArrayList<>();
            // Add default player names or handle accordingly
            playerNames.add("Player 1");
            playerNames.add("Player 2");
        }


    }


    /**
     * Called to save the current dynamic state of the activity.
     * @param outState Bundle in which to place your saved state.
     * */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putIntArray(KEY_BOX_POSITION, boxPosition);
        outState.putInt(KEY_PLAYER_TURN, playerTurn);
    }


    /**
     * Restores the game state from the saved instance state.
     */
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

    /**
     * Sets the image resource for a given box
     *
     * @param index Index of the box
     * @param resId Resource ID of the image of set
     */
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

    /**
     * Perform an action when a box is selected.
     *
     * @param imageView Imageview of the selected box
     * @param selectedBoxPosition Position of the selected box
     */
    private void performAction(ImageView imageView, int selectedBoxPosition){
        boxPosition[selectedBoxPosition] = playerTurn;

        if(playerTurn == 1){
            imageView.setImageResource(R.drawable.x_letter);

            if(checkPlayerWin()){
                savePlayerScore(playerOneName.getText().toString(), 1);
                //sendHighScoresToActivity(); // Navigate to HighScoreActivity with the high score
                // Display the Win Window
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
                savePlayerScore(playerTwoName.getText().toString(), 1);
                //sendHighScoresToActivity();
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

    /**
     * Changes the player's turn
     *
     * @param currentPlayerTurn Current player's turn.
     */
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

    /**
     * Checks if the current player has won
     * @return True if the player has won, false otherwise.
     */
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

    /**
     * Checks if a box is already selected.
     *
     * @param position Position of the box
     * @return True if the box is selected, false otherwise
     */
    private boolean isBoxSelected(int position){
        boolean response = false;

        if(this.boxPosition[position] == 0){
            response = true;
        }

        return response;
        //return boxPosition[position] == 0;
    }


    /**
     * Restarts the match by resetting the game state and clearing the board
     * Resets the player turn and total selected boxes.
     */
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

    /**
     * 
     * @param playerNames
     */
    public void initializeGame(ArrayList<String> playerNames){
        playerOneName.setText(playerNames.get(0));
        playerTwoName.setText(playerNames.get(1));

        restartMatch();
    }

    /**
     * Saves the player's sscore to the shared preference.
     * Updates the existing score if the player already exists, otherwise creates a new entry.
     *
     * @param playerName the name of the player.
     * @param point the points to be added to the player's score.
     */
    public void savePlayerScore(String playerName, int point){
        SharedPreferences sharedPreferences = getApplication().getSharedPreferences("HighScores", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Find the player in the shared preferences
        if( sharedPreferences.contains(playerName)){
            String keyPlayer = sharedPreferences.getString(playerName, null);
            if(keyPlayer != null){
                String[] parts = keyPlayer.split(",");
                int playerScore = Integer.parseInt(parts[1]);
                Log.d("PlayerScore", "Existing Score: " + playerScore);

                // Add new point to the existing point
                playerScore += point;
                editor.putString(playerName, playerName + "," + playerScore);
            }
        } else {
            // Save the new player's name and point
            editor.putString(playerName, playerName + "," + point );
        }
        editor.apply();
        editor.commit();
    }

    /**
     * Retrieves all player scores from the shared preferences.
     * @return a list of HighScore objects representing the player scores.
     */
    public List<HighScore> getAllPlayerScore(){
        SharedPreferences sharedPreferences = getSharedPreferences("HighScores", MODE_PRIVATE);
        List<HighScore> highScores = new ArrayList<>();

        Map<String, ?> allEntries = sharedPreferences.getAll();
        for(Map.Entry<String, ? > entry: allEntries.entrySet()){
            String[] parts = entry.getValue().toString().split(",");
            String playerName = parts[0];
            int point = Integer.parseInt((parts[1]));
            highScores.add(new HighScore(playerName, point));
        }

        return highScores;
    }

    /**
     * Sends the high scores to the HighScoreActivity.
     *
     * Retrieves all player scores, prepares the data,
     * and starts the HighScoreAActivity with the data
     */
    public void sendHighScoresToActivity(){
        List<HighScore> highScores = getAllPlayerScore();
        Log.d("sendHighScoreToActivity", "" + highScores);

        Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
        ArrayList<String> playerNames = new ArrayList<>();
        ArrayList<Integer> playerScores = new ArrayList<>();

        for(HighScore highScore : highScores){
            playerNames.add(highScore.getPlayerName());
            playerScores.add(highScore.getScore());
        }

        intent.putStringArrayListExtra("playerNames", playerNames);
        intent.putIntegerArrayListExtra("playerScores", playerScores);
        Log.d("sendIntent", "" + intent);
        startActivity(intent);

    }


}