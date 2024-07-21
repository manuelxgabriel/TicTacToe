package rodriguez.manuel.tictactoegame;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

/**
 * Custom dialog to display a win message and provide options to restart the game,
 * view the high scores, or view the game rules.
 */
public class WinDialog extends Dialog {

    private final String message;
    private final MainActivity mainActivity;
    TextView messageText;
    Button startAgainBtn;
    Button viewHighScoreBtn;
    Button rulesBtn;

    /**
     * Constructor for WinDialog.
     *
     * @param context      The current context.
     * @param message      The win message to display.
     * @param mainActivity The MainActivity instance to handle restart and navigation actions.
     */
    public WinDialog(@NonNull Context context, String message, MainActivity mainActivity) {
        super(context);
        this.message = message;
        this.mainActivity = mainActivity;
    }

    /**
     * Called when the dialog is starting. Sets up the dialog's UI and button click handlers.
     *
     * @param savedInstanceState If the dialog is being re-initialized after previously being shut down,
     *                           this contains the data it most recently supplied. Otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.win_dialog_layout);

        messageText = findViewById(R.id.messageText);
        startAgainBtn = findViewById(R.id.startAgainBtn);
        viewHighScoreBtn = findViewById(R.id.viewHighScoreButton);
        rulesBtn = findViewById(R.id.rulesButton);

        messageText.setText(message);

        startAgainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.restartMatch();
                dismiss();
            }
        });

        viewHighScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent highScoreBoardIntent = new Intent(mainActivity, HighScoreActivity.class);
                mainActivity.sendHighScoresToActivity();
                // mainActivity.startActivity(highScoreBoardIntent);
            }
        });

        rulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                Intent rulesIntent = new Intent(mainActivity, RulesActivity.class);
                mainActivity.startActivity(rulesIntent);
            }
        });
    }
}
