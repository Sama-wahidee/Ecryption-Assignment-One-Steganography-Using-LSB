package application;

import javafx.application.Application;
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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Label title = new Label(" steganography program that uses the LSB");
			Button encodeButton = new Button("Encode the secret message ");
			Button decodeButton = new Button("Decode  the secret message");

			title.setLayoutX(300);
			title.setLayoutY(200);
			title.setTextFill(Color.THISTLE);
			title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
		
			encodeButton.setLayoutX(450);
			encodeButton.setLayoutY(350);
			encodeButton.setOpacity(1.0);
			encodeButton.setPrefSize(300, 50);
			encodeButton.setFont(new Font("Comic Sans MS", 20));
			encodeButton.setStyle("-fx-background-color: THISTLE");
			
			decodeButton.setLayoutX(800);
			decodeButton.setLayoutY(350);
			decodeButton.setOpacity(1.0);
			decodeButton.setPrefSize(300, 50);
			decodeButton.setFont(new Font("Comic Sans MS", 20));
			decodeButton.setStyle("-fx-background-color: THISTLE");
			
			Pane root = new Pane();
			root.getChildren().addAll(title, encodeButton, decodeButton);
			root.setBackground(Background());

			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setFullScreen(true);
			primaryStage.show();
			
			encodeButton.setOnAction(e -> {
				Pane encodeBorderPane = new BorderPane();
				encodeBorderPane=encode.encodeOpr();
				//encodeBorderPane.setBackground(Background());
				Scene name1 = new Scene(encodeBorderPane);
				primaryStage.setScene(name1);
				primaryStage.setFullScreen(true);

			});
			decodeButton.setOnAction(e -> {
				Pane decodeBorderPane = new BorderPane();
				decodeBorderPane=decode.decodeOpr();
				decodeBorderPane.setBackground(Background());
				Scene name1 = new Scene(decodeBorderPane);
				primaryStage.setScene(name1);
				primaryStage.setFullScreen(true);

			});	encode.back.setOnAction(e -> {
				primaryStage.setScene(scene);
				primaryStage.setFullScreen(true);
				primaryStage.show();

			});
			decode.back.setOnAction(e -> {
				primaryStage.setScene(scene);
				primaryStage.setFullScreen(true);
				primaryStage.show();

			});
		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Background Background() {
		Image image = new Image("Steganography.png");
		BackgroundSize backgroundSize = new BackgroundSize(1560, 855, false, false, false, false);
		BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT,
				BackgroundRepeat.NO_REPEAT, null, backgroundSize);
		Background background1 = new Background(backgroundImage);
		;
		return background1;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
