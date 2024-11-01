package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class decode {
	static Button backk = new Button("Back");

	public static Pane decodeOpr() {
		Button decodeButton = new Button("Decode");
		Button chooseEncodedFile = new Button("Upload Encoded Image");

		String buttonStyle = "-fx-background-color: #112244; -fx-text-fill: white;";
		Font buttonFont = Font.font("Calibri", FontWeight.BOLD, 20);

		decodeButton.setStyle(buttonStyle);
		decodeButton.setFont(buttonFont);
		chooseEncodedFile.setStyle(buttonStyle);
		chooseEncodedFile.setFont(buttonFont);

		backk.setStyle(buttonStyle);
		backk.setFont(buttonFont);

		Label plainText = new Label();
		plainText.setFont(new Font("Calibri", 20));
		plainText.setTextFill(Color.BLACK);
		plainText.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-padding: 10;");
		plainText.setPrefSize(300, 50);

		ImageView beforeImageView = new ImageView("Empty.png");
		beforeImageView.setFitHeight(400);
		beforeImageView.setFitWidth(300);
		beforeImageView.setPreserveRatio(true);

		StackPane imageViewContainer = new StackPane(beforeImageView);
		imageViewContainer.setStyle("-fx-border-color: black; -fx-border-width: 2;");
		imageViewContainer.setPadding(new Insets(5));
		imageViewContainer.setMaxHeight(400);
		imageViewContainer.setMaxWidth(300);

		Label secretMessageLabel = new Label("Secret Message:");
		secretMessageLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 30));
		secretMessageLabel.setTextFill(Color.BLACK);
		Label l = new Label("Encoded Image:");
		l.setFont(Font.font("Calibri", FontWeight.BOLD, 30));

		VBox imageContainer = new VBox(10, l, imageViewContainer);
		imageContainer.setAlignment(Pos.CENTER);
		imageContainer.setTranslateY(20);

		HBox actionButtonBox = new HBox(20, backk, decodeButton);
		actionButtonBox.setAlignment(Pos.CENTER);

		VBox buttonBox = new VBox(20, chooseEncodedFile, actionButtonBox);
		buttonBox.setAlignment(Pos.CENTER);

		VBox contentBox = new VBox(20, buttonBox, secretMessageLabel, plainText);
		contentBox.setAlignment(Pos.CENTER);
		contentBox.setTranslateY(10);

		BorderPane mainContainer = new BorderPane();
		mainContainer.setCenter(contentBox);
		mainContainer.setTop(imageContainer);
		mainContainer.setStyle("-fx-background-color: lightblue;");
		BorderPane.setAlignment(imageContainer, Pos.CENTER);

		final File[] selectedEncodedFile = new File[1];

		chooseEncodedFile.setOnAction(e -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png"));
			selectedEncodedFile[0] = fileChooser.showOpenDialog(null);

			if (selectedEncodedFile[0] != null) {
				try {
					Image image = new Image(selectedEncodedFile[0].toURI().toString());
					beforeImageView.setImage(image);
				} catch (Exception ex) {
					new Alert(AlertType.ERROR, "Error loading the image: " + ex.getMessage()).show();
				}
			} else {
				new Alert(AlertType.ERROR, "Please choose a valid PNG file.").show();
			}
		});

		decodeButton.setOnAction(e -> {
			if (selectedEncodedFile[0] != null) {
				try {
					BufferedImage encodedImage = ImageIO.read(selectedEncodedFile[0]);
					File inputFile = new File("C:\\Users\\sama6\\OneDrive\\Desktop\\image.png");
					BufferedImage originalImage = ImageIO.read(inputFile);
					String message = revealMessage(encodedImage, originalImage);
					plainText.setText(message);
				} catch (IOException ioException) {
					new Alert(AlertType.ERROR, "Error decoding the image: " + ioException.getMessage()).show();
				}
			} else {
				new Alert(AlertType.WARNING, "Please select both encoded and original images.").show();
			}
		});

		return mainContainer;
	}

	private static String revealMessage(BufferedImage encodedImage, BufferedImage originalImage) {
		StringBuilder binaryMessage = new StringBuilder();
		int width = encodedImage.getWidth();
		int height = encodedImage.getHeight();
		int sectionHeight = height / 3;

		int xRed = 0, yRed = 0; 
		int xGreen = width - 1, yGreen = sectionHeight; 
		int xBlue = 0, yBlue = 2 * sectionHeight; 

		while (true) {
			int encodedPixel = encodedImage.getRGB(xRed, yRed);
			int originalPixel = originalImage.getRGB(xRed, yRed);
			binaryMessage.append(getLSBXOR(encodedPixel, originalPixel, "red"));

			xRed++;
			if (xRed >= width) {
				xRed = 0;
				yRed++;
			}

			if (yGreen < height) {
				encodedPixel = encodedImage.getRGB(xGreen, yGreen);
				originalPixel = originalImage.getRGB(xGreen, yGreen);
				binaryMessage.append(getLSBXOR(encodedPixel, originalPixel, "green"));

				xGreen--;
				if (xGreen < 0) {
					xGreen = width - 1;
					yGreen++;
				}
			}

			if (yBlue < height) {
				encodedPixel = encodedImage.getRGB(xBlue, yBlue);
				originalPixel = originalImage.getRGB(xBlue, yBlue);
				binaryMessage.append(getLSBXOR(encodedPixel, originalPixel, "blue"));

				yBlue++;
				if (yBlue >= height) {
					yBlue = 2 * sectionHeight;
					xBlue++;
				}
			}

			if (binaryMessage.length() >= 8 && binaryMessage.substring(binaryMessage.length() - 8).equals("11111111")) {
				break;
			}
		}

		StringBuilder message = new StringBuilder();
		for (int i = 0; i < binaryMessage.length(); i += 8) {
			String byteStr = binaryMessage.substring(i, Math.min(i + 8, binaryMessage.length()));
			char c = (char) Integer.parseInt(byteStr, 2);
			message.append((char) (c - 2)); 
		}

		return message.toString().substring(0, message.length() - 1);
	}

	private static char getLSBXOR(int encodedPixel, int originalPixel, String channel) {
		int encodedLSB = 0;
		int originalLSB = 0;

		switch (channel) {
		case "red":
			encodedLSB = (encodedPixel >> 16) & 1;
			originalLSB = (originalPixel >> 16) & 1;
			break;
		case "green":
			encodedLSB = (encodedPixel >> 8) & 1;
			originalLSB = (originalPixel >> 8) & 1;
			break;
		case "blue":
			encodedLSB = encodedPixel & 1;
			originalLSB = originalPixel & 1;
			break;
		}

		
		return (encodedLSB != originalLSB) ? '1' : '0';
	}
}