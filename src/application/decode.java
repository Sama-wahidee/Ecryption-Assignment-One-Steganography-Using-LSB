package application;



import java.io.File;
import java.util.Scanner;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;

public class decode {
	static Button back = new Button("back");
	public static Pane decodeOpr() {


		Label title1 = new Label("  decode the");
		Label title2 = new Label("secret message");
		Button decodeButton = new Button("decode");
		Button chooseFile = new Button("choose file");
		Pane beforPane = new Pane();
		Pane afterPane = new Pane();
		Pane contant = new Pane();

		title1.setLayoutY(20);
		title1.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 30));
		title1.setStyle("-fx-font-color: rgb(216,191,216, 1.5);");
		
		title2.setLayoutY(50);
		title2.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 28));
		title2.setStyle("-fx-font-color: rgb(216,191,216, 1.5);");

		chooseFile.setLayoutX(10);
		chooseFile.setLayoutY(120);
		chooseFile.setPrefWidth(195);
		chooseFile.setFont(Font.font("Comic Sans MS", FontWeight.BOLD, 15));

		decodeButton.setLayoutX(70);
		decodeButton.setLayoutY(200);
		decodeButton.setOpacity(1.0);
		decodeButton.setPrefSize(80, 20);
		decodeButton.setFont(new Font("Comic Sans MS", 15));
		decodeButton.setStyle("-fx-background-color:rgb(0,0,0, 0.5);");

		beforPane.setLayoutX(10);
		beforPane.setLayoutY(20);

		Image image1 = new Image("image.png");
		ImageView imageView1 = new ImageView(image1);
		beforPane.getChildren().add(imageView1);


		afterPane.setLayoutX(889);
		afterPane.setLayoutY(20);
		afterPane.setPrefWidth(620);
		afterPane.setPrefHeight(830);
		Label plainText = new Label();
		plainText.setFont(new Font("Comic Sans MS", 20));
		plainText.setTextFill(Color.THISTLE);
		afterPane.setStyle("-fx-background-color:rgb(59,64,67); -fx-background-radius: 20;");//THISTLE
		afterPane.getChildren().add(plainText);

		back.setLayoutX(730);
		back.setLayoutY(810);
		back.setOpacity(1.0);
		back.setPrefSize(80, 20);
		back.setFont(new Font("Comic Sans MS", 15));
		back.setStyle("-fx-background-color: THISTLE");

		contant.setStyle("-fx-background-color: rgb(216,191,216); -fx-background-radius: 20;");// THISTLE
		contant.setLayoutX(660);
		contant.setLayoutY(80);
		contant.setPrefSize(220, 250);
		contant.getChildren().addAll(title1,title2, chooseFile, decodeButton);

		Pane decodePane = new Pane();
		decodePane.setStyle("-fx-background-color:  rgb(232,234,235);");//THISTLE

		 decodePane.getChildren().addAll(beforPane, contant, back);
		
		 decodeButton.setOnAction(e -> {
			decodePane.getChildren().clear();
	        plainText.setText("   \n       the plain Text");
			decodePane.getChildren().addAll(beforPane, contant,afterPane, back);
	
		});
		 chooseFile.setOnAction(e -> {
			 FileChooser fileChooserOrder = new FileChooser();
				new FileChooser.ExtensionFilter("file", "*.png");
				fileChooserOrder.setTitle("open your file please");
				File fileOrder = fileChooserOrder.showOpenDialog(null);
				Scanner scanner;
				try {
					scanner = new Scanner(fileOrder);
					 String fileName = fileOrder.getName();
				       if(fileName.toLowerCase().endsWith(".png")) {
				    	 
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setTitle("INFORMATION");
							alert.setContentText("The file has been upladed successfully!");
							alert.show();
						
					}
				       else {
				    	   Alert alert = new Alert(AlertType.ERROR);
							alert.setTitle("ERROR");
							alert.setContentText("cho0se png file");
							alert.showAndWait();
				       }
				       
					
				} catch (Exception e1) {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("ERROR");
					alert.setContentText("The file has not been upladed successfully!");
					alert.showAndWait();
				}
		
		
			});
		 
		 
		
		 

		return decodePane;
	}
}		
