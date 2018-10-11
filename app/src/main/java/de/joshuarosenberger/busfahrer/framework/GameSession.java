package de.joshuarosenberger.busfahrer.framework;

import java.util.ArrayList;
import java.util.Collections;


public class GameSession {
    private ArrayList<Player> players;
    private ArrayList<Question> questions;
    private int currentQuestionIndex;
    private int maxGameLength;

    /**
     * Constructs a new GameSession with some players and questions
     */
    public GameSession(ArrayList<String> players, ArrayList<Question> questions) {
        this.players = createPlayerList(players);
        this.questions = questions;
        //Sets the amount of questions to the max. by default
        maxGameLength = questions.size();
    }

    public GameSession(ArrayList<Question> questions) {
        this.players = new ArrayList<Player>();
        this.questions = questions;
        //Sets the amount of questions to the max. by default
        maxGameLength = questions.size();
    }

    public void addPlayer(String name) {
        this.players.add(new Player(name));
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public int getCurrentQuestionIndex() {
        return this.currentQuestionIndex;
    }

    public void setMaxGameLength(int amount) {
        this.maxGameLength = amount;
    }

    public int getMaxGameLength() {
        return this.maxGameLength;
    }

    public Question nextQuestion() {
        //TODO find a logical way to signal the end of the questions
        if (gameHasFinished()){
            return new Question("Runde zu Ende!");
        }
        Question thisQuestion = questions.get(currentQuestionIndex);
        currentQuestionIndex++;

        ArrayList<Player> passivePlayers = getPassivePlayers(thisQuestion.getNumberOfActivePlaceholders());
        Collections.shuffle(passivePlayers);
        thisQuestion.replacePlaceholderNames(players, Question.ACTIVE_PLACEHOLDER);
        thisQuestion.replacePlaceholderNames(passivePlayers,Question.PASSIVE_PLACEHOLDER);

        nextPlayersTurn();
        return thisQuestion;
    }

    public void nextPlayersTurn() {
        ArrayList<Player> newPlayerSorting = new ArrayList<Player>();
        for (int i = 0; i < players.size(); i++) {
            newPlayerSorting.add(players.get((i+1)%(players.size())));

        }
        this.players = newPlayerSorting;
    }

    public ArrayList<Player> getPassivePlayers(int activePlayers){

        return new ArrayList<Player>(players.subList(activePlayers, players.size()-1));
    }

    public Boolean gameHasFinished() {
        return (getCurrentQuestionIndex() == questions.size())
                || (currentQuestionIndex == maxGameLength);
    }

    public Player whosTurnIsIt() {
        return players.get(0);
    }

    private ArrayList<Player> createPlayerList(ArrayList<String> names) {
        ArrayList<Player> players = new ArrayList<Player>();
        for (String name:
             names) {
            players.add(new Player(name));
        }
        return players;
    }

}
