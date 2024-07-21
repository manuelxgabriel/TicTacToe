package rodriguez.manuel.tictactoegame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * ArrayAdapter to handle the display of HighScore objects in a ListView.
 */
public class HighScoreAdapter extends ArrayAdapter<HighScore> {

    /**
     * Constructor for HighScoreAdapter.
     *
     * @param context     The current context.
     * @param highScores  The list of high scores to display.
     */
    public HighScoreAdapter(Context context, List<HighScore> highScores) {
        super(context, 0, highScores);
    }

    /**
     * Provides a view for an AdapterView (ListView) for each high score item.
     *
     * @param position     The position of the item within the adapter's data set.
     * @param convertView  The old view to reuse, if possible.
     * @param parent       The parent view that this view will be attached to.
     * @return             A View corresponding to the data at the specified position.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        HighScore highScore = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_high_score, parent, false);
        }

        // Lookup view for data population
        TextView playerName = convertView.findViewById(R.id.playerName);
        TextView playerScore = convertView.findViewById(R.id.playerScore);

        // Populate the data into the template view using the data object
        playerName.setText(highScore.getPlayerName());
        playerScore.setText(String.valueOf(highScore.getScore()));

        // Return the completed view to render on screen
        return convertView;
    }
}
