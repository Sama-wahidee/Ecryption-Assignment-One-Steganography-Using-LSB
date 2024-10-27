package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class encode {
	static Button back = new Button("back");

	public static Pane encodeOpr() {

//		Label title = new Label("Encode the secret message ");
//		Label befor = new Label("befor");
//		Label after = new Label("after");
//		Button encodeButton = new Button("Encode");
//		TextField secretMessage = new TextField();
//		Pane beforPane = new Pane();
//		Pane afterPane = new Pane();
//
//		title.setLayoutX(450);
//		title.setLayoutY(10);
//		title.setTextFill(Color.THISTLE);
//		title.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 40));
//
//		secretMessage.setLayoutX(450);
//		secretMessage.setLayoutY(70);
//		secretMessage.setPrefSize(430, 20);
//		secretMessage.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 15));
//
//		encodeButton.setLayoutX(900);
//		encodeButton.setLayoutY(70);
//		encodeButton.setOpacity(1.0);
//		encodeButton.setPrefSize(80, 20);
//		encodeButton.setFont(new Font("Comic Sans MS", 15));
//		encodeButton.setStyle("-fx-background-color: THISTLE");
//
//		befor.setLayoutX(250);
//		befor.setLayoutY(85);
//		befor.setTextFill(Color.THISTLE);
//		befor.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
//
//		after.setLayoutX(1150);
//		after.setLayoutY(85);
//		after.setTextFill(Color.THISTLE);
//		after.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));

		Label title1 = new Label("   Encode the");
		Label title2 = new Label("secret message");
		Label befor = new Label("befor");
		Label after = new Label("after");
		Button encodeButton = new Button("Encode");
		TextArea secretMessage = new TextArea();
		Pane beforPane = new Pane();
		Pane afterPane = new Pane();
		Pane contant = new Pane();

//		title1.setLayoutX(660);
		title1.setLayoutY(20);
		title1.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
		title1.setStyle("-fx-font-color: rgb(216,191,216, 1.5);");
		
//		title2.setLayoutX(660);
		title2.setLayoutY(50);
		title2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 28));
		title2.setStyle("-fx-font-color: rgb(216,191,216, 1.5);");

		secretMessage.setLayoutX(10);
		secretMessage.setLayoutY(120);
		secretMessage.setWrapText(true);
		secretMessage.setPrefWidth(195);
		secretMessage.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 15));

		encodeButton.setLayoutX(70);
		encodeButton.setLayoutY(370);
		encodeButton.setOpacity(1.0);
		encodeButton.setPrefSize(80, 20);
		encodeButton.setFont(new Font("Comic Sans MS", 15));
		encodeButton.setStyle("-fx-background-color:rgb(0,0,0, 0.5);");

		beforPane.setLayoutX(10);
		beforPane.setLayoutY(20);
//		beforPane.setPrefWidth(1000);
//		beforPane.setPrefHeight(200);
		Image image1 = new Image("image.png");
		ImageView imageView1 = new ImageView(image1);
		beforPane.getChildren().add(imageView1);
//		 beforPane.getChildren().add(new Image("Steganography.png"));
//		 beforPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");//THISTLE
//		 beforPane.setStyle("-fx-background-color:THISTLE;");//THISTLE

		afterPane.setLayoutX(889);
		afterPane.setLayoutY(20);
//		beforPane.setPrefWidth(1000);
//		beforPane.setPrefHeight(200);
		Image image2 = new Image("image.png");
		ImageView imageView2 = new ImageView(image2);
		afterPane.getChildren().add(imageView2);
//		 beforPane.getChildren().add(new Image("Steganography.png"));
//		 beforPane.setStyle("-fx-background-color: rgba(0, 0, 0, 0.5);");//THISTLE
//		 beforPane.setStyle("-fx-background-color:THISTLE;");//THISTLE

		back.setLayoutX(730);
		back.setLayoutY(810);
		back.setOpacity(1.0);
		back.setPrefSize(80, 20);
		back.setFont(new Font("Comic Sans MS", 15));
		back.setStyle("-fx-background-color: THISTLE");

		contant.setStyle("-fx-background-color: rgb(216,191,216); -fx-background-radius: 20;");// THISTLE
		contant.setLayoutX(660);
		contant.setLayoutY(80);
		contant.setPrefSize(220, 450);
		contant.getChildren().addAll(title1,title2, secretMessage, encodeButton);

		Pane encodePane = new Pane();
//		 encodePane.setStyle("-fx-background-color: rgb(216,191,216, 0.5);");//THISTLE
		 encodePane.setStyle("-fx-background-color:  rgb(232,234,235);");//THISTLE

//		encodePane.getChildren().addAll(title, secretMessage, beforPane, afterPane, encodeButton, back);
		encodePane.getChildren().addAll(beforPane, contant, back);
		
		encodeButton.setOnAction(e -> {
			encodePane.getChildren().clear();
			encodePane.getChildren().addAll(beforPane, contant,afterPane, back);
	
		});

		return encodePane;
	}

}
