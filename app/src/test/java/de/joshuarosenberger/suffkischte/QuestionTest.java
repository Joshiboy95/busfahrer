package de.joshuarosenberger.suffkischte;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import de.joshuarosenberger.suffkischte.framework.Player;
import de.joshuarosenberger.suffkischte.framework.Question;
import static org.junit.Assert.*;

public class QuestionTest {
    private ArrayList<Player> activePlayers;
    private ArrayList<Player> passivePlayers;

     @Before
    public void initialize(){
         activePlayers = new ArrayList<Player>();
         activePlayers.add(new Player("act1"));
         activePlayers.add(new Player("act2"));
         activePlayers.add(new Player("act3"));

         passivePlayers = new ArrayList<Player>();
         passivePlayers.add(new Player("pas1"));
         passivePlayers.add(new Player("pas2"));
         passivePlayers.add(new Player("pas3"));
     }

     @After
     public void reset(){
         activePlayers = null;
         passivePlayers = null;
     }

     @Test
    public void getTextTest(){
         Question q1 = new Question("test");
        assertEquals("test", q1.getText());
     }

    @Test
    public void noQuestionTextTest(){
         Question noQuestion = new Question("");
        assertEquals("", noQuestion.getText());
    }

    @Test
    public void noActivePlayersTest(){
         activePlayers = new ArrayList<Player>();
         assertEquals(0, activePlayers.size());

        Question q1 = new Question("test");
    }

    @Test
    public void noPassivePlayersTest(){
        passivePlayers = new ArrayList<Player>();
        assertEquals(0, passivePlayers.size());

        Question q1 = new Question("test");
    }

    @Test
    public void replacePlaceholderNamesActiveTest(){
         Question q1 = new Question("Play @ACTIVEPLAYER and @ACTIVEPLAYER now!");
         q1.replacePlaceholderNames(activePlayers, Question.ACTIVE_PLACEHOLDER);
         assertEquals("Play act1 and act2 now!", q1.getText());
    }

    @Test
    public void replacePlaceholderNamesToManyActiveTest(){
        Question q1 = new Question("Play @ACTIVEPLAYER and @ACTIVEPLAYER and @ACTIVEPLAYER and @ACTIVEPLAYER!");
        q1.replacePlaceholderNames(activePlayers, Question.ACTIVE_PLACEHOLDER);
        assertEquals("Play act1 and act2 and act3 and @ACTIVEPLAYER!", q1.getText());
    }

    @Test
    public void replacePlaceholderNamesToManyPassiveTest(){
        Question q1 = new Question("Play @PASSIVEPLAYER and @PASSIVEPLAYER and @PASSIVEPLAYER and @PASSIVEPLAYER!");
        q1.replacePlaceholderNames(passivePlayers, Question.PASSIVE_PLACEHOLDER);
        assertEquals("Play pas1 and pas2 and pas3 and @PASSIVEPLAYER!", q1.getText());
    }

    @Test
    public void replacePlaceholderNamesActiveAndPassiveTest(){
        Question q1 = new Question("Play @ACTIVEPLAYER and @PASSIVEPLAYER and @ACTIVEPLAYER and @PASSIVEPLAYER!");
        q1.replacePlaceholderNames(activePlayers, Question.ACTIVE_PLACEHOLDER);
        q1.replacePlaceholderNames(passivePlayers, Question.PASSIVE_PLACEHOLDER);
        assertEquals("Play act1 and pas1 and act2 and pas2!", q1.getText());
    }
}
