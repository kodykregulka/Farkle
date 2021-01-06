package farkle;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import static farkle.Main.createHSpacer;
import static farkle.Main.scoreBoard;

public class Player extends HBox{

    final int MAX_DICE = 6;
    final String initialWord = "FARKLE"; //must be same size as MAX_DICE
    Dice[] dice = new Dice[MAX_DICE];
    String name = new String("");

    int gameScore = 0;
    int roundScore = 0;
    boolean firstToss = true;

    Player(boolean active){
        setDisable(!active);
        setAlignment(Pos.CENTER);
        setupDice(active);

    }

    void setupDice(boolean isFirst){
        getChildren().add(createHSpacer());
        for(int i = 0; i < MAX_DICE; i++){
            dice[i] = new Dice(initialWord.charAt(i), isFirst);
            getChildren().addAll(dice[i],createHSpacer());
        }
    }

    void takeTurn(){
        //score current dice and roll again
        if(!firstToss) {
            int selectedScore = scoreDice();
            for(int i = 0; i < MAX_DICE; i++){
                if(dice[i].isSelected){
                    dice[i].isSelected = false;
                    dice[i].isEnabled = false;
                }
            }
            if (selectedScore == 0)
                endTurn();
            roundScore += selectedScore;
            scoreBoard.setRoundScore(roundScore);
        }
        else{
            firstToss = false;
        }

        //roll all enabled dice
        for(int i = 0; i < MAX_DICE; i++){
            if(dice[i].isEnabled){
                dice[i].roll();
            }
        }
    }
    void endTurn(){
        //score last hand (or take away)
        //adjust game score, reset turnScore
        //set dice and
        // playerboard into inactive state

        int selectedScore = scoreDice();
        for(int i = 0; i < MAX_DICE; i++){
            if(dice[i].isSelected){
                dice[i].isSelected = false;
                dice[i].isEnabled = false;
            }
        }
        if(selectedScore == 0)
            roundScore = 0;
        else
            roundScore+=selectedScore;
        gameScore += (roundScore);
        roundScore = 0;

        scoreBoard.updateGameScores();

        setEnable(false);
    }

    int scoreDice(){
        int totalScore = 0;
        boolean used[] = new boolean[MAX_DICE];
        int count[] = new int [MAX_DICE];
        for(int i = 0; i < MAX_DICE; i++){
            used[i]=false;
            count[i]=0;
        }
        //3 of a kind
        for(int i = 0; i < MAX_DICE; i++){
            if(dice[i].isSelected && !used[i]) {
                if ((++count[dice[i].value]) == 3) {
                    count[dice[i].value] = 0;
                    if(dice[i].value == 1)
                        totalScore += 1000;
                    else
                        totalScore += 100 * dice[i].value;
                    //remove all members of 3 of a kind
                    for (int j = 0, removedCount = 0; j < MAX_DICE && removedCount < 3; j++) {
                        if (dice[j].value == dice[i].value) {
                            used[j] = true;
                            removedCount++;
                        }
                    }
                }
            }
        }

        //1s and 5s
        for(int i = 0; i < MAX_DICE; i++){
            if(dice[i].isSelected && !used[i]) {
                if(dice[i].value == 1){
                    totalScore += 100;
                }
                else if(dice[i].value == 5){
                    totalScore += 50;
                }
            }
        }

        return totalScore;
    }

    void setEnable(boolean enable) {
        if(enable){
            setDisable(false);
            firstToss = true;
            for(int i = 0; i < MAX_DICE; i++){
                dice[i].reset(initialWord.charAt(i));
            }
        }
        else{
            setDisable(true);
            for(int i = 0; i < MAX_DICE; i++){
                dice[i].shelfDice();
            }
        }
    }
}
