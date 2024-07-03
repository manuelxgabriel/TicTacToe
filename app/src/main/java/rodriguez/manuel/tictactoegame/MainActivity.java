package rodriguez.manuel.tictactoegame;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import rodriguez.manuel.tictactoegame.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

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

                }
            }
        });

    }

    private void performAction(ImageView imageView, int selectedBox){
        boxPosition[selectedBox] = playerTurn;

        if(playerTurn == 1){
            imageView.setImageResource(R.drawable.x_letter);

            if(checkPlayerWin()){

            }
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



}