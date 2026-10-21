/*
    Jonathon Walmsley
    10/25/2026
    Module 1.3
    Description: Display 4 randomly selected cards from a 52-card deck using JavaFX.
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AssignmentCards extends Application {

    private BorderPane rootPane;
    private HBox cardContainer;

    @Override
    public void start(Stage primaryStage) {
        

        // --- 1. TOP REGION: Application Header --- //
        
        // Create Root Pane with a light background color
        rootPane = new BorderPane();
        rootPane.setStyle("-fx-background-color: #f1f5f9;"); 

        // Create a header with padding and background color
        HBox header = new HBox();
        header.setPadding(new Insets(16, 24, 16, 24));
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: #1e293b;");

        // Add a title label to the header
        Label appTitle = new Label("Random Card Dealer");
        appTitle.setStyle("-fx-text-fill: #ffffff; -fx-font-size: 17px; -fx-font-weight: bold;");
        header.getChildren().add(appTitle);

        // Set the header in the top of the root pane
        rootPane.setTop(header);


        // --- 2. CENTER REGION: Horizontal Card Display --- //

        // Create a container for the cards with spacing and center alignment
        cardContainer = new HBox(18); 
        cardContainer.setAlignment(Pos.CENTER);
        cardContainer.setPadding(new Insets(24));

        // Deal the initial set of 4 cards
        dealCards();
        
        // Set the card container in the center of the root pane
        rootPane.setCenter(cardContainer);


        // --- 3. BOTTOM REGION: Action Bar with Refresh Button --- //

        // Create a bottom bar with center alignment and padding
        HBox bottomBar = new HBox();
        bottomBar.setAlignment(Pos.CENTER);
        bottomBar.setPadding(new Insets(0, 0, 24, 0));

        // Create button to deal new cards
        Button btnDeal = new Button("Deal New Cards");
        btnDeal.setStyle(
            "-fx-background-color: #2563eb; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 14px; " +
            "-fx-font-weight: bold; " +
            "-fx-padding: 10 24 10 24; " +
            "-fx-background-radius: 6; " +
            "-fx-cursor: hand;"
        );

        // Add Hover effect to button
        btnDeal.setOnMouseEntered(e -> btnDeal.setStyle(
            "-fx-background-color: #1d4ed8; -fx-text-fill: white; -fx-font-size: 14px; " +
            "-fx-font-weight: bold; -fx-padding: 10 24 10 24; -fx-background-radius: 6; -fx-cursor: hand;"
        ));
        btnDeal.setOnMouseExited(e -> btnDeal.setStyle(
            "-fx-background-color: #2563eb; -fx-text-fill: white; -fx-font-size: 14px; " +
            "-fx-font-weight: bold; -fx-padding: 10 24 10 24; -fx-background-radius: 6; -fx-cursor: hand;"
        ));

        // Add action to deal new cards when button is clicked
        btnDeal.setOnAction(e -> dealCards());
        bottomBar.getChildren().add(btnDeal);

        // Set the bottom bar in the root pane
        rootPane.setBottom(bottomBar);


        // --- 4. Setup Scene & Stage --- //

        Scene scene = new Scene(rootPane, 620, 360);
        primaryStage.setTitle("Module 1.3 - Random Card Dealer");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    // Shuffles a deck of 52 cards, draws the first 4, and renders them in the center container.
    private void dealCards() {
        cardContainer.getChildren().clear();

        // Create a deck indexed from 1 to 52
        List<Integer> deck = new ArrayList<>();
        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }

        // Shuffle to randomize deck
        Collections.shuffle(deck);

        // Create a shadow effect for the cards
        DropShadow shadow = new DropShadow();
        shadow.setColor(Color.rgb(0, 0, 0, 0.25));
        shadow.setRadius(8);
        shadow.setOffsetY(3);

        // Draw the first 4 cards
        for (int i = 0; i < 4; i++) {
            int cardNum = deck.get(i);

            try {

                // Loads images from the 'cards' folder
                Image cardImage = new Image(getClass().getResourceAsStream("cards/" + cardNum + ".png"));
                ImageView imageView = new ImageView(cardImage);

                // Apply consistent style to cards
                imageView.setFitWidth(110);
                imageView.setPreserveRatio(true);
                imageView.setSmooth(true);
                imageView.setEffect(shadow);

                // Add the card image to the container
                cardContainer.getChildren().add(imageView);

            } catch (Exception ex) {

                // Add Error Handling if Image fails to load
                Label errorLabel = new Label("Card " + cardNum);
                errorLabel.setStyle("-fx-border-color: #cbd5e1; -fx-padding: 40 10; -fx-background-color: white;");
                cardContainer.getChildren().add(errorLabel);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}