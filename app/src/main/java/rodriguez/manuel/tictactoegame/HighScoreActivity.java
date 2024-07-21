package rodriguez.manuel.tictactoegame;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Activity to display the high scores of the players.
 */
public class HighScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);

        ListView listView = findViewById(R.id.listViewHighScores);
        Button buttonRestartGame = findViewById(R.id.buttonRestartGame);

        // Get the data from the intent
        Intent intent = getIntent();
        ArrayList<String> playerNames = intent.getStringArrayListExtra("playerNames");
        ArrayList<Integer> playerScores = intent.getIntegerArrayListExtra("playerScores");

        if (playerNames != null && playerScores != null) {
            Log.d("ArrayList", "" + playerNames);

            // Prepare the high score list
            List<HighScore> highScores = new ArrayList<>();
            for (int i = 0; i < playerNames.size(); i++) {
                highScores.add(new HighScore(playerNames.get(i), playerScores.get(i)));
            }

            // Sort the high score list by score in descending order
            Collections.sort(highScores, new Comparator<HighScore>() {
                @Override
                public int compare(HighScore hs1, HighScore hs2) {
                    return Integer.compare(hs2.getScore(), hs1.getScore());
                }
            });

            // Set the adapter
            HighScoreAdapter adapter = new HighScoreAdapter(this, highScores);
            listView.setAdapter(adapter);
        } else {
            Log.e("HighScoreActivity", "playerNames or playerScores is null");
        }


        // Set restart button
        buttonRestartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent restartIntent = new Intent(HighScoreActivity.this, AddPlayers.class);
                restartIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

                // Pass player names back to MainActivity
                restartIntent.putStringArrayListExtra("playerNames", playerNames);

                startActivity(restartIntent);
                finish(); // Finish HighScoreActivity to remove it from the back stack
            }
        });


    }
}
