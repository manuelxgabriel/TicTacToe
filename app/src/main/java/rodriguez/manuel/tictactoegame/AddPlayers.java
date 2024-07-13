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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);

        playerOne = findViewById(R.id.playerOneName);
        playerTwo = findViewById(R.id.playerTwoName);
        final Button startGameBtn = findViewById(R.id.startGameButton);


        startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String getPlayerOneName = playerOne.getText().toString().toLowerCase();
                final String getPlayerTwoName = playerTwo.getText().toString().toLowerCase();

//                savePlayerData(getPlayerOneName, 0);
//                savePlayerData(getPlayerTwoName, 0);


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



//    private void savePlayerData(String playerName, int wins){
//        SharedPreferences pref = getApplication().getSharedPreferences("GamePreferences",MODE_PRIVATE);
//        SharedPreferences.Editor editor = pref.edit();
//
//        editor.putString(playerName + "_name", playerName);
//        editor.putInt(playerName + "_wins", wins);
//        editor.commit();
//    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("playerOne_name", playerOne.getText().toString());
        outState.putString("playerTwo_name", playerTwo.getText().toString());
    }
}