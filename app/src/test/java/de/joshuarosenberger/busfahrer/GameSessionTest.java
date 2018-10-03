package de.joshuarosenberger.busfahrer;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import de.joshuarosenberger.busfahrer.framework.GameSession;
import de.joshuarosenberger.busfahrer.framework.Player;
import de.joshuarosenberger.busfahrer.framework.Question;
import de.joshuarosenberger.busfahrer.framework.QuestionGenerator;

import static org.junit.Assert.*;

public class GameSessionTest {
    private ArrayList<Player> players;
    private ArrayList<Question> questions;

    @Before
    public void initialize(){
        players = new ArrayList<Player>();
        players.add(new Player("player1"));
        players.add(new Player("player2"));
        players.add(new Player("player3"));
        players.add(new Player("player4"));

        questions = new ArrayList<Question>();
        questions.add(new Question("Everybody do someting"));
        questions.add(new Question("@ACTIVEPLAYER do someting"));
        questions.add(new Question("@ACTIVEPLAYER drink with @PASSIVEPLAYER"));
        questions.add(new Question("@ACTIVEPLAYER drink with @PASSIVEPLAYER and @PASSIVEPLAYER"));
    }

    @Test
    public void createGame(){
        GameSession game = new GameSession(players, questions);
        assertEquals(0, game.getCurrentQuestionIndex());
    }

    @Test
    public void createEmptyPlayerGame(){
        GameSession game = new GameSession(new ArrayList<Player>(), questions);
    }

    @Test
    public void createEmptyQuestionsGame(){
        GameSession game = new GameSession(players, new ArrayList<Question>());
    }

    @Test
    public void setupOneQuestion(){
        GameSession game = new GameSession(players, questions);
        Question question = game.nextQuestion();
        assertEquals("Everybody do someting", question.getText());
    }

    @Test
    public void nextPlayersTurnTest() {
        GameSession game = new GameSession(players, questions);
        game.nextPlayersTurn();
        assertEquals("player2", game.getPlayers().get(0).getName());
        assertEquals("player3", game.getPlayers().get(1).getName());
        assertEquals("player4", game.getPlayers().get(2).getName());
        assertEquals("player1", game.getPlayers().get(3).getName());
        game.nextPlayersTurn();
        assertEquals("player3", game.getPlayers().get(0).getName());
        assertEquals("player4", game.getPlayers().get(1).getName());
        assertEquals("player1", game.getPlayers().get(2).getName());
        assertEquals("player2", game.getPlayers().get(3).getName());
        game.nextPlayersTurn();
        assertEquals("player4", game.getPlayers().get(0).getName());
        assertEquals("player1", game.getPlayers().get(1).getName());
        assertEquals("player2", game.getPlayers().get(2).getName());
        assertEquals("player3", game.getPlayers().get(3).getName());
        game.nextPlayersTurn();
        assertEquals("player1", game.getPlayers().get(0).getName());
        assertEquals("player2", game.getPlayers().get(1).getName());
        assertEquals("player3", game.getPlayers().get(2).getName());
        assertEquals("player4", game.getPlayers().get(3).getName());
    }


    @Test
    public void playAllQuestions(){
        GameSession game = new GameSession(players, questions);

        while (!game.gameHasFinished()) {
            Question question = game.nextQuestion();

            System.out.println(question.getText());
        }
    }

    @Test
    public void playWithGeneratedQuestions() {

        GameSession game = new GameSession(players,
                new QuestionGenerator(players.size(),1).getQuestions());

        while (!game.gameHasFinished()) {
            Question question = game.nextQuestion();

            System.out.println(question.getText());
        }
    }
}
