package de.joshuarosenberger.busfahrer.framework;

import java.util.ArrayList;

public class Question {
    public static final String ACTIVE_PLACEHOLDER = "@ACTIVEPLAYER";
    public static final String PASSIVE_PLACEHOLDER = "@PASSIVEPLAYER";
    private String text;

    /**
     * Constructs a Question with a text.
     * @param text
     */
    public Question(String text) {
        this.text = text;
    }

    public String getText() {
     return text;
    }

    public int getNumberOfActivePlaceholders(){
        String countingText = text;
        int i = 0;
        while (countingText.contains(ACTIVE_PLACEHOLDER)) {
            i++;
            countingText = countingText.replaceFirst(ACTIVE_PLACEHOLDER, "Replaced");
        }
        return i;
    }

    /**
     * Adds a list of player names to the questions placeholders in the text.
     * @param players ArrayList of Players
     * @param regex Regex of the placeholder
     */
    public void replacePlaceholderNames(ArrayList<Player> players, String regex){
        for (int i = 0; text.contains(regex); i++) {
            //prevents an ArrayOutOfBoundsException
            if (players.size() <= i)
                break;
            this.text = text.replaceFirst(regex, players.get(i).getName());
        }
    }
}
