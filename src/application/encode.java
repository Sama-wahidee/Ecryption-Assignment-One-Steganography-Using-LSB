package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class encode {
    static Button back = new Button("Back");

    public static VBox encodeOpr() {
        Label title1 = new Label("Enter the Secret Message:");
        title1.setFont(Font.font("Calibri", FontWeight.BOLD, 30));

        Button encodeButton = new Button("Encode");
        encodeButton.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        back.setFont(Font.font("Calibri", FontWeight.BOLD, 20));
        TextArea secretMessage = new TextArea();
        secretMessage.setFont(Font.font("Calibri", 20));
        encodeButton.setStyle("-fx-background-color: #112244; -fx-text-fill: white;");
        back.setStyle("-fx-background-color: #112244; -fx-text-fill: white;");

        VBox beforeBox = new VBox(10);
        VBox afterBox = new VBox(10);
        HBox imageContainer = new HBox(20); 

        secretMessage.setMaxWidth(500);
        secretMessage.setMaxHeight(100);
        secretMessage.setWrapText(true);

        encodeButton.setPrefSize(100, 30);
        encodeButton.setAlignment(Pos.CENTER);

        Label beforeLabel = new Label("Before");
        beforeLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 70));
        
        try {
            Image image1 = new Image("image.png");
            ImageView imageView1 = new ImageView(image1);
            imageView1.setFitHeight(400);
            imageView1.setFitWidth(300);
            
            VBox beforeImageContainer = new VBox(5, beforeLabel, imageView1);
            beforeImageContainer.setAlignment(Pos.CENTER);
            beforeBox.getChildren().add(beforeImageContainer);
        } catch (Exception e) {
            System.out.println("Error loading the image: " + e.getMessage());
        }

        Label afterLabel = new Label("After");
        afterLabel.setFont(Font.font("Calibri", FontWeight.BOLD, 70));
        
        ImageView imageView2 = new ImageView("Empty.png");
        imageView2.setFitHeight(400);
        imageView2.setFitWidth(300);
        VBox afterImageContainer = new VBox(5, afterLabel, imageView2);
        afterImageContainer.setAlignment(Pos.CENTER);
        afterBox.getChildren().add(afterImageContainer);

        back.setPrefSize(80, 30);
        Image arrow = new Image("arrow.png");
        ImageView arrowView1 = new ImageView(arrow);
        arrowView1.setFitHeight(200);
        arrowView1.setFitWidth(200);
        
        imageContainer.setAlignment(Pos.CENTER); 
        imageContainer.getChildren().addAll(beforeBox, arrowView1, afterBox);
        imageContainer.setMaxWidth(840);
        imageContainer.setMaxHeight(500);
        imageContainer.setPadding(new Insets(20, 20, 20, 20));
        imageContainer.setStyle("-fx-border-color: black; -fx-border-width: 1;");
        VBox encodePane = new VBox(10); 
        encodePane.setStyle("-fx-background-color: lightblue;");
        encodePane.setAlignment(Pos.CENTER); 
        encodePane.setPadding(new Insets(10)); 
        HBox buttons= new HBox(back,encodeButton);
        buttons.setSpacing(80);
        buttons.setAlignment(Pos.CENTER);
        encodePane.getChildren().addAll(imageContainer, title1, secretMessage,buttons );

        encodeButton.setOnAction(e -> {
            String message = secretMessage.getText();
            try {
                File inputFile = new File("C:\\Users\\sama6\\OneDrive\\Desktop\\image.png");
                BufferedImage bufferedImage = ImageIO.read(inputFile);
                hideMessage(bufferedImage, message);

                File outputFile = new File("output_image.png");
                ImageIO.write(bufferedImage, "png", outputFile);
                Image encodedImage = new Image(outputFile.toURI().toString());
                imageView2.setImage(encodedImage);

                System.out.println("Message hidden successfully!");
            } catch (IOException ioException) {
                System.out.println("Error encoding image: " + ioException.getMessage());
            }
        });

        return encodePane;
    }

    private static void hideMessage(BufferedImage image, String message) {
        StringBuilder shiftedMessage = new StringBuilder();
        for (char c : message.toCharArray()) {
            shiftedMessage.append((char) (c + 2));
        }

        StringBuilder binaryMessage = new StringBuilder();
        for (char c : shiftedMessage.toString().toCharArray()) {
            String binaryChar = String.format("%08d", Integer.parseInt(Integer.toBinaryString(c)));
            binaryMessage.append(binaryChar);
        }
        binaryMessage.append("11111111"); 

        int width = image.getWidth();
        int height = image.getHeight();
        int sectionHeight = height / 3;
        int messageIndex = 0;

        int xRed = 0, yRed = 0; 
        int xGreen = width - 1, yGreen = sectionHeight;
        int xBlue = 0, yBlue = 2 * sectionHeight; 

        while (messageIndex < binaryMessage.length()) {
            if (messageIndex < binaryMessage.length()) {
                int pixel = image.getRGB(xRed, yRed);
                pixel = setLSB(pixel, binaryMessage.charAt(messageIndex++), "red");
                image.setRGB(xRed, yRed, pixel);
                xRed++;
                if (xRed >= width) { 
                    xRed = 0;
                    yRed++;
                }
            }

            if (messageIndex < binaryMessage.length()) {
                int pixel = image.getRGB(xGreen, yGreen);
                pixel = setLSB(pixel, binaryMessage.charAt(messageIndex++), "green");

                image.setRGB(xGreen, yGreen, pixel);

                xGreen--;
                if (xGreen < 0) { 
                    xGreen = width - 1;
                    yGreen++;
                }
            }

            if (messageIndex < binaryMessage.length()) {
                int pixel = image.getRGB(xBlue, yBlue);
                pixel = setLSB(pixel, binaryMessage.charAt(messageIndex++), "blue");
                image.setRGB(xBlue, yBlue, pixel);

                yBlue++;
                if (yBlue >= height) { 
                    yBlue = 2 * sectionHeight;
                    xBlue++;
                }
            }
        }
    }




    private static int setLSB(int pixel, char bit, String channel) {
        int originalLSB = 0;
        int color = pixel;

        switch (channel) {
            case "red":
                originalLSB = (pixel >> 16) & 1;
                if (((bit - '0') ^ originalLSB) == 1) { 
                    color = pixel | 0x010000; 
                } else {
                    color = pixel & 0xFEFFFF; 
                }
                break;
            case "green":
                originalLSB = (pixel >> 8) & 1;
                if (((bit - '0') ^ originalLSB) == 1) { 
                    color = pixel | 0x000100; 
                } else {
                    color = pixel & 0xFFFEFF; 
                }
                break;
            case "blue":
                originalLSB = pixel & 1;
                if (((bit - '0') ^ originalLSB) == 1) {
                    color = pixel | 0x000001; 
                } else {
                    color = pixel & 0xFFFFFE; 
                }
                break;
        }
        return color;
    }

}