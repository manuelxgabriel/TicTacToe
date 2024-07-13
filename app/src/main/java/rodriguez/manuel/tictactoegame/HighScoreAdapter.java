package rodriguez.manuel.tictactoegame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class HighScoreAdapter extends ArrayAdapter<HighScore> {
    public HighScoreAdapter(Context context, List<HighScore> highScores) {
        super(context, 0, highScores);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HighScore highScore = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_high_score, parent, false);
        }
        TextView playerName = convertView.findViewById(R.id.playerName);
        TextView playerScore = convertView.findViewById(R.id.playerScore);

        playerName.setText(highScore.getPlayerName());
        playerScore.setText(String.valueOf(highScore.getScore()));

        return convertView;
    }
}
