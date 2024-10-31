package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Create buttons
            Button encodeButton = new Button("Encode a Secret Message");
            Button decodeButton = new Button("Decode a Secret Message");

            // Set button styles
            encodeButton.setPrefSize(300, 50);
            encodeButton.setFont(new Font("Calibri", 20));
            encodeButton.setStyle("-fx-background-color: #556cf7; -fx-text-fill: white;");

            decodeButton.setPrefSize(300, 50);
            decodeButton.setFont(new Font("Calibri", 20));
            decodeButton.setStyle("-fx-background-color: #556cf7; -fx-text-fill: white;");

            // Create label with the text "Using LSB"
            Label lsbLabel = new Label("Using LSB");
            lsbLabel.setFont(new Font("Calibri", 50));
            lsbLabel.setStyle("-fx-background-color: white; -fx-text-fill: #fa820e;");
            lsbLabel.setPrefSize(300, 100);
            lsbLabel.setAlignment(Pos.CENTER);
            // Create VBox for the label, aligning it at the top
            VBox labelBox = new VBox(lsbLabel);
            labelBox.setStyle("-fx-alignment: top-center; -fx-padding: 50;");
            labelBox.setTranslateY(550);
            labelBox.setTranslateX(50);

            // Create VBox for the buttons, aligning them at the center of the bottom area
            VBox buttonBox = new VBox(20, encodeButton, decodeButton);
            buttonBox.setStyle("-fx-alignment: center; -fx-padding: 20;");

            BorderPane root = new BorderPane();
            root.setBackground(createBackground());

            root.setRight(labelBox);
            root.setBottom(buttonBox);

            // Set the scene
            Scene scene = new Scene(root, 800, 600); // Adjust dimensions as needed
            primaryStage.setScene(scene);
            primaryStage.setFullScreen(true);
            primaryStage.show();
            
            // Encode button action
            encodeButton.setOnAction(e -> {
                Pane encodeBorderPane = new BorderPane();
                encodeBorderPane = encode.encodeOpr();
                Scene encodeScene = new Scene(encodeBorderPane);
                primaryStage.setScene(encodeScene);
                primaryStage.setFullScreen(true);
            });

            // Decode button action
            decodeButton.setOnAction(e -> {
                Pane decodeBorderPane = new BorderPane();
                decodeBorderPane = decode.decodeOpr();
                Scene decodeScene = new Scene(decodeBorderPane);
                primaryStage.setScene(decodeScene);
                primaryStage.setFullScreen(true);
            });

            encode.back.setOnAction(e -> {
                primaryStage.setScene(scene);
                primaryStage.setFullScreen(true);
            });
            decode.backk.setOnAction(e -> {
            	primaryStage.setScene(scene);
                primaryStage.setFullScreen(true);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Background createBackground() {
        Image image = new Image("Steganography.png");
        BackgroundSize backgroundSize = new BackgroundSize(100, 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT, null, backgroundSize);
        return new Background(backgroundImage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}