package farkle;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.Random;

public class Dice extends VBox {
    StackPane stackPane = new StackPane();
    Rectangle shape = new Rectangle();
    Text display = new Text();

    final int MAX_VALUE = 6;
    int value;
    boolean isActive, isEnabled;
    Random rand = new Random();

    Dice(char input, boolean enabled){
        setActive(enabled);

        value = 0; //invalid value
        setPadding(new Insets(80,20,80,20));

        setText(input);
        display.setFont(new Font(50));

        shape.setWidth(100);
        shape.setHeight(100);
        shape.setArcWidth(20);
        shape.setArcHeight(20);
        shape.setFill(Color.WHITE);

        stackPane.getChildren().addAll(shape, display);
        getChildren().add(stackPane);

        stackPane.setOnMouseClicked(new EventHandler<MouseEvent>()
        {
            @Override
            public void handle(MouseEvent t) {
                setActive(!isActive);
            }
        });
    }
    void setText(char input) {
        //sets text and invalidates the value
        display.setText(""+input);
        value = 0;
    }
    int roll(){
        //desired range 1 -> MAX Value
        value = rand.nextInt(MAX_VALUE-1)+1;
        display.setText(""+value);
        return value;
    }
    void setActive(boolean active){
        //whether or not dice is in play
        isActive = active;
        if(isActive){
            setAlignment(Pos.TOP_CENTER);
        }
        else{
            setAlignment(Pos.BOTTOM_CENTER);
        }
    }
    void endTurn(){
        //shelf dice
        if(isActive){
            setAlignment(Pos.BOTTOM_CENTER);
        }
        else{
            setAlignment(Pos.TOP_CENTER);
        }
    }
    void reset(char input){
        //start new turn
        isActive = true;
        setAlignment(Pos.TOP_CENTER);
        setText(input);
    }

}
