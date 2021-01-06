package farkle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {
    //public static Pane root = new Pane();
    Button menuButton = new Button();
    Button rollButton = new Button("Roll");
    Button stopButton = new Button("Score & Stop");

    ScoreBoard scoreBoard = new ScoreBoard();

    SplitPane gamePane = new SplitPane();
    Player player1 = new Player(true);
    Player player2 = new Player(false);
    Player currentPlayer = player1;

    public static final int toWin = 4000;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //start dialog
        setupDialog();

        //setup
        StackPane root = new StackPane();
        //root.setStyle("-fx-background-color: #369c2f");

        //overlay
        BorderPane overlayPane = new BorderPane();
        setupOverlay(overlayPane);

        //gamePane
        gamePane.setOrientation(Orientation.VERTICAL);
        gamePane.setStyle("-fx-background-color: #369c2f");

        //nextPlayer.setEnable(false);
        gamePane.getItems().addAll(player2,player1);

        root.getChildren().addAll(gamePane, overlayPane);

        //stage
        primaryStage.setTitle("Farkle: Roll the Dice");
        primaryStage.setScene(new Scene(root));
        primaryStage.setMaximized(true);
        primaryStage.show();

        //event handlers
        //menuButton
        //rollButton
        rollButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                currentPlayer.takeTurn();
                rollButton.setText("Hold & Roll Again");
            }
        });

        //stopButton
        stopButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                currentPlayer.endTurn();
                if(currentPlayer == player1)
                    currentPlayer = player2;
                else
                    currentPlayer = player2;
                currentPlayer.setEnable(true);
                gamePane.getItems().remove(currentPlayer);
                gamePane.getItems().add(currentPlayer);
                rollButton.setText("Roll");
            }
        });
    }

    public void setupDialog(){
        TextInputDialog startDialog = new TextInputDialog("");
        startDialog.setTitle("Welcome to Farkle");

        startDialog.setHeaderText("Player 1 enter a name:");
        startDialog.showAndWait();

        player1.name = startDialog.getEditor().getText();
        scoreBoard.setPlayer1NameLabel(player1.name);
        startDialog.getEditor().setText("");

        startDialog.setHeaderText("Player 2 enter a name:");
        startDialog.showAndWait();

        player2.name = startDialog.getEditor().getText();
        scoreBoard.setPlayer2NameLabel(player2.name);
        startDialog.getEditor().setText("");
    }

    public void setupOverlay(BorderPane overlayPane){
        overlayPane.setPickOnBounds(false);

        //Rectangle scoreBox = new Rectangle(400,200, Color.WHITE); //make this
        VBox scorePane = new VBox();
        scorePane.setAlignment(Pos.TOP_LEFT);
        scorePane.getChildren().add(scoreBoard);
        scorePane.setPickOnBounds(false);
        overlayPane.setLeft(scorePane);

        menuButton.setMaxSize(30,30);
        ImageView gearIcon = new ImageView(new Image("farkle/settings_gear.png"));
        gearIcon.setFitHeight(30);
        gearIcon.setPreserveRatio(true);
        menuButton.setGraphic(gearIcon);
        VBox menuPane = new VBox();
        menuPane.setAlignment(Pos.TOP_RIGHT);
        menuPane.getChildren().add(menuButton);
        overlayPane.setRight(menuPane);

        HBox controlPane = new HBox();
        controlPane.setAlignment(Pos.BOTTOM_CENTER);
        Font controlFont = new Font(30);
        controlPane.getChildren().add(createHSpacer());
        rollButton.setFont(controlFont);
        controlPane.getChildren().addAll(rollButton, createHSpacer());
        stopButton.setFont(controlFont);
        controlPane.getChildren().addAll(stopButton, createHSpacer());

        overlayPane.setBottom(controlPane);
    }
    public void swapPlayers(){

    }


    public static Node createHSpacer() {
        //https://stackoverflow.com/questions/40883858/how-to-evenly-distribute-elements-of-a-javafx-vbox
        final Region spacer = new Region();
        // Make it always grow or shrink according to the available space
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
    public static Node createVSpacer() {
        //https://stackoverflow.com/questions/40883858/how-to-evenly-distribute-elements-of-a-javafx-vbox
        final Region spacer = new Region();
        // Make it always grow or shrink according to the available space
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
