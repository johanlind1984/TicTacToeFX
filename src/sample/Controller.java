package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {

    @FXML
    Label player1Score;
    @FXML
    Label player2Score;
    @FXML
    Label labelDisplayInstructions;
    @FXML
    VBox chooseMarkTop;
    @FXML
    VBox chooseMarkBot;
    @FXML
    VBox cell1;
    @FXML
    VBox cell2;
    @FXML
    VBox cell3;
    @FXML
    VBox cell4;
    @FXML
    VBox cell5;
    @FXML
    VBox cell6;
    @FXML
    VBox cell7;
    @FXML
    VBox cell8;
    @FXML
    VBox cell9;
    @FXML
    Button buttonPlayAgain;

    PlayArea playField;
    String player1;
    String player2;
    int turn;
    boolean isWinner;
    FileInputStream crossPath;
    FileInputStream circlePath;
    Image imageCross;
    Image imageCircle;
    BackgroundImage backroundCross;
    BackgroundImage backroundCircle;
    Background hboxBackgroundX;
    Background hboxBackgroundO;


    public void initialize() {
        playField = new PlayArea();
        player1 = "";
        player2 = "";
        turn = 1;
        player1Score.setText("0");
        player2Score.setText("0");
        isWinner = false;

        try {
            crossPath = new FileInputStream("C:\\Users\\johan.lind\\IdeaProjects\\TicTacToeFX\\src\\sample\\images\\cross.jpg");
            circlePath = new FileInputStream("C:\\Users\\johan.lind\\IdeaProjects\\TicTacToeFX\\src\\sample\\images\\circle.jpg");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        imageCross = new Image(crossPath);
        imageCircle = new Image(circlePath);
        backroundCross = new BackgroundImage(imageCross, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        backroundCircle = new BackgroundImage(imageCircle, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        hboxBackgroundX = new Background(backroundCross);
        hboxBackgroundO = new Background(backroundCircle);
        chooseMarkTop.setBackground(hboxBackgroundX);
        chooseMarkBot.setBackground(hboxBackgroundO);
    }

    @FXML
    public void placeMarker(MouseEvent mouseEvent) {

        Pattern p = Pattern.compile("\\d+");
        Matcher m = p.matcher(mouseEvent.getSource().toString());
        System.out.println(m.find());
        int position = Integer.parseInt(m.group());
        String tempPlayer;

        if (turn % 2 != 0) {
            tempPlayer = player1;
        } else {
            tempPlayer = player2;
        }

        if (player1 != "" && !isWinner) {
            if (playField.placeMarker(tempPlayer, position)) {
                VBox temp = (VBox) mouseEvent.getSource();
                if (tempPlayer.equals("X")) {
                    temp.setBackground(hboxBackgroundX);
                } else {
                    temp.setBackground(hboxBackgroundO);
                }
                turn++;
                if (playField.isWinner(tempPlayer)) {
                    labelDisplayInstructions.setText("We got a Winner! \n Congrats " + tempPlayer);
                    if (tempPlayer.equals(player1)) {
                        int tempScore = Integer.parseInt(player1Score.getText()) + 1;
                        player1Score.setText("" + tempScore);

                    } else {
                        int tempScore = Integer.parseInt(player2Score.getText()) + 1;
                        player2Score.setText("" + tempScore);

                    }
                    isWinner = true;
                    buttonPlayAgain.setVisible(true);
                }

            } else {
                labelDisplayInstructions.setText("You can't pick a occupied spot");
            }
        } else {
            labelDisplayInstructions.setText("Choose marker first");

        }
    }

    @FXML
    public void chooseMarker(MouseEvent mouseEvent) {

        if (player1.equals("")) {
            if (mouseEvent.getSource().toString().equals("VBox[id=chooseMarkTop]")) {
                player1 = "X";
                player2 = "O";
                labelDisplayInstructions.setText("Player 1 is X \n Payer 2 is O \n\n Player 1 starts");
            } else {
                player1 = "O";
                player2 = "X";
                System.out.println("player 1" + player1 + " Player 2: " + player2);
                labelDisplayInstructions.setText("Player 1 is O \n Payer 2 is X \n\n Player 1 starts");
            }
        }
    }

    @FXML
    public void resetAll(ActionEvent event) {

        cell1.setBackground(null);
        cell2.setBackground(null);
        cell3.setBackground(null);
        cell4.setBackground(null);
        cell5.setBackground(null);
        cell6.setBackground(null);
        cell7.setBackground(null);
        cell8.setBackground(null);
        cell9.setBackground(null);
        playField = new PlayArea();
        isWinner = false;
        buttonPlayAgain.setVisible(false);
    }
}


