package de.joshuarosenberger.suffkischte.framework;

import java.util.ArrayList;
import java.util.Collections;

public class QuestionGenerator {
    private int playerCount;
    private ArrayList<Question> questions;

    public QuestionGenerator(int playerCount, int duplicates) {
        this.playerCount = playerCount;
        questions = new ArrayList<Question>();

        generateDrinkWith(duplicates);
        generateDrinkYourself(duplicates);
        generateDrinkShare(duplicates);
        generateGeneralRules(duplicates);
        generateDrinkingPartners();
    }

    public ArrayList<Question> getQuestions() {
        Collections.shuffle(questions);
        return this.questions;
    }


    private void generateDrinkYourself(int duplicates){
        for (int i = 0; i <= duplicates; i++) {
            for (int j = 2; j <= 3; j++) {
                questions.add(new Question(Question.ACTIVE_PLACEHOLDER + " trink " + j
                        + " Schlücke!"));
            }
            questions.add(new Question(Question.ACTIVE_PLACEHOLDER + " trink 1 Schluck!"));
        }
    }

    private void generateDrinkWith(int duplicates){
        for (int i = 0; i <= duplicates; i++) {
            for (int j = 2; j <= 3; j++) {
                questions.add(new Question(Question.ACTIVE_PLACEHOLDER
                        + " trink " + j
                        + " Schlücke mit " + Question.PASSIVE_PLACEHOLDER + "!"));
            }
            questions.add(new Question(Question.ACTIVE_PLACEHOLDER
                    + " trink 1 Schluck mit "
                    + Question.PASSIVE_PLACEHOLDER + "!"));
        }
    }

    private void generateDrinkShare(int duplicates){
        for (int i = 0; i <= duplicates; i++) {
            for (int j = 1; j <= playerCount; j++) {
                questions.add(new Question(Question.ACTIVE_PLACEHOLDER
                        + " verteile " + j + " Schlücke!"));
            }
            questions.add(new Question(Question.ACTIVE_PLACEHOLDER
                    + " verteil 1 Schluck!"));
        }
    }

    private void generateGeneralRules(int duplicates){
        for (int i = 0; i <= duplicates; i++) {
            questions.add(new Question("Derjenige mit dem vollsten Glas trinkt 3 Schlücke!"));
            questions.add(new Question("Derjenige dessen Glas am leersten ist, verteilt 2 Schlücke!"));
            questions.add(new Question("Alle trinken!"));
            questions.add(new Question("Alle trinken 2!"));
            questions.add(new Question("Verteile so viele Schlücke wie Personen am Tisch"));
            questions.add(new Question("Alle Frauen trinken!"));
            questions.add(new Question("Alle Männer Trinken!"));
            questions.add(new Question(Question.ACTIVE_PLACEHOLDER + " mach jedem am Tisch ein Kompliment!"));
            questions.add(new Question("Wasserfall! " + Question.ACTIVE_PLACEHOLDER
                    + " beginnt aber " + Question.PASSIVE_PLACEHOLDER + " darf sich die Richtung aussuchen!"));

        }
    }

    private void generateDrinkingPartners() {
        for (int i = 0; i < playerCount -1; i++) {
            questions.add(new Question("Trinkpartner! Suche dir eine Person die ab sofort immer mit dir Trinkt!"));
            questions.add(new Question("Tischregel! Überlege dir eine Regel welche bis zum Ende des Spiels gilt!"));
        }
    }

}
