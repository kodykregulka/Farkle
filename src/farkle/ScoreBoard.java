package farkle;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static farkle.Main.*;
import static farkle.Main.createVSpacer;

public class ScoreBoard extends VBox {

    private Label toWinLabel = new Label("To Win: " + toWin);
    private Label player1NameLabel = new Label();
    private Label player1ScoreLabel = new Label("0");
    private Label player2NameLabel = new Label();
    private Label player2ScoreLabel = new Label("0");

    private Label roundScoreLabel = new Label("Round: 0");
    private Label selectedScoreLabel = new Label("Selected: 0");

    ScoreBoard(){
        setWidth(300);
        setHeight(100);
        setAlignment(Pos.BASELINE_CENTER);
        setPadding(new Insets(20,20,20,20));
        setStyle("-fx-background-color: #ffffff");

        Font font = new Font(20);
        toWinLabel.setFont(font);
        player1NameLabel.setFont(font);
        player1ScoreLabel.setFont(font);
        player2NameLabel.setFont(font);
        player2ScoreLabel.setFont(font);
        roundScoreLabel.setFont(font);
        selectedScoreLabel.setFont(font);

        getChildren().add(createVSpacer());
        getChildren().addAll(toWinLabel, createVSpacer());

        HBox totalScorePane = new HBox();
        VBox player1TotalScorePane = new VBox();
        player1TotalScorePane.getChildren().addAll(createVSpacer(), player1NameLabel, createVSpacer(), player1ScoreLabel, createVSpacer());
        VBox player2TotalScorePane = new VBox();
        player2TotalScorePane.getChildren().addAll(createVSpacer(), player2NameLabel, createVSpacer(), player2ScoreLabel, createVSpacer());
        totalScorePane.getChildren().addAll(createHSpacer(), player1TotalScorePane, createHSpacer(), player2TotalScorePane, createHSpacer());
        getChildren().addAll(totalScorePane, createVSpacer());

        getChildren().addAll(roundScoreLabel, createVSpacer());
        getChildren().addAll(selectedScoreLabel, createVSpacer());
    }

    public void setPlayer1NameLabel(String name){
        player1NameLabel.setText(name);
    }
    public void setPlayer2NameLabel(String name){
        player2NameLabel.setText(name);
    }
    public void setSelectedScore(int input) {
        selectedScoreLabel.setText("Selected: " + input);
    }
    public void setRoundScore(int input) {
        roundScoreLabel.setText("Round: " + input);
    }
    public void setPlayer1GameScore(int score){
        player1ScoreLabel.setText(Integer.toString(score));
    }
    public void setPlayer2GameScore(int score){
        player2ScoreLabel.setText(Integer.toString(score));
    }


}
