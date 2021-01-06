package farkle;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import static farkle.Main.createHSpacer;

public class PlayerBoard extends HBox{

    final int MAX_DICE = 6;
    final String initialWord = "FARKLE"; //must be same size as MAX_DICE
    Dice[] dice = new Dice[MAX_DICE];
    //Pos dicePos;

    PlayerBoard(boolean active){
        setDisable(!active);
        setAlignment(Pos.CENTER);
        setupDice(active);

    }

    void setupDice(boolean enabled){
        getChildren().add(createHSpacer());
        for(int i = 0; i < MAX_DICE; i++){
            dice[i] = new Dice(initialWord.charAt(i), enabled);
            getChildren().addAll(dice[i],createHSpacer());
        }
    }

    void takeTurn(){
        //score current dice and roll again
        scoreDice();//do something

        for(int i = 0; i < MAX_DICE; i++){
            if(dice[i].isActive){
                dice[i].roll();
            }
        }
    }
    void endTurn(){
        //score last hand (or take away)
        //adjust game score, reset turnScore
        //set dice and
        // playerboard into inactive state (call functioN)
        setEnable(false);
    }

    int scoreDice(){
        return 0;
    }

    void setEnable(boolean enable) {
        if(enable){
            setDisable(false);
            //toFront();
            for(int i = 0; i < MAX_DICE; i++){
                dice[i].reset(initialWord.charAt(i));
            }
        }
        else{
            setDisable(true);
            //toBack();
            for(int i = 0; i < MAX_DICE; i++){
                dice[i].endTurn();
            }
        }

    }
}
