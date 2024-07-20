package rodriguez.manuel.tictactoegame;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddPlayers extends AppCompatActivity {

    private EditText playerOne, playerTwo;
    private String getPlayerOneName = "playerOne";
    private String getPlayerTwoName = "playerTwo";

    Button startGameBtn;

    /**
     * Called when the activity is starting. This is where most initialization should go.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        playerOne = findViewById(R.id.playerOneName);
        playerTwo = findViewById(R.id.playerTwoName);
        startGameBtn = findViewById(R.id.startGameButton);


        startGameBtn.setOnClickListener(new View.OnClickListener() {
            /**
             * Called when the start game button is clicked. Validates the player names
             * and starts the MainActivity.
             *
             * @param v The view that was clicked.
             */
            @Override
            public void onClick(View v) {
                getPlayerOneName = playerOne.getText().toString().toLowerCase();
                getPlayerTwoName = playerTwo.getText().toString().toLowerCase();

                // Check it has a valid name
                if(getPlayerOneName.isEmpty() || getPlayerTwoName.isEmpty()){
                    Toast.makeText(AddPlayers.this, "Please enter player name", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(AddPlayers.this, MainActivity.class);
                    intent.putExtra("playerOne", getPlayerOneName);
                    intent.putExtra("playerTwo", getPlayerTwoName);
                    startActivity(intent);
                }
            }
        });

    }

    /**
     * Called to retrieve per-instance from an activity before being killed
     * so that the state can be restored in onCreate(Bundle)
     *
     * @param outState Bundle in which to place your saved state.
     *
     */
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("playerOne_name", playerOne.getText().toString());
        outState.putString("playerTwo_name", playerTwo.getText().toString());
    }
}