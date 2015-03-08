/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package oneday;

import java.net.MalformedURLException;
import java.net.URL;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

/**
 *
 * @author tim
 */
public class OneDay extends Application {
    
        
    //private final static String PATH_TO_SPLASH_SCREEN="file:///C:\\Users\\tim\\Desktop\\images\\header.jpg";
    private final static String PATH_TO_SPLASH_SCREEN="/images/Title.jpg";
    private final static String PATH_TO_SOUND="/sound/sound1.mp3";
    private final static String DEFAULT_TITLE="WELCOME";
    private final static int HSIZE = 500;
    private final static int VSIZE = 400;
    
    private SoundManager soundManager;
    
    private final BorderPane root = new BorderPane();
    
    @Override
    public void start(Stage primaryStage) {
          this.soundManager = new SoundManager();
        

        URL res = this.getClass().getResource(PATH_TO_SOUND);
        soundManager.loadSound(res);

        
        this.soundManager.playSound();
        
        createTitleBox();
        root.setBottom(createButtonBar());
        root.setCenter(createSplashScreenImage());

        
        Scene scene = new Scene(root, HSIZE, VSIZE);
        
        primaryStage.setTitle("Title Screen");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
     private ImageView createSplashScreenImage() {
        Image image = new Image(this.getClass().getResourceAsStream(PATH_TO_SPLASH_SCREEN));
        ImageView iv = new ImageView();
        iv.setImage(image);
        
        return iv;
    }
    
    private void shutdown() {
        
        this.soundManager.shutdown();
    }
    
    private HBox createButtonBar() {
        HBox hbox = new HBox();
        
        Button startButton = new Button();
        startButton.setText("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                createTitleBox("Welcome to fnah");
            }
        });
        
        Button helpButton = new Button();
        helpButton.setText("Help");
        
        Button exitButton = new Button();
        exitButton.setText("Exit");
        
        exitButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Shutting Down");
                shutdown();
                System.exit(0);
            }
        });
        
        
        hbox.getChildren().addAll(startButton, helpButton, exitButton);
        
        return hbox;
    }
    
    private HBox createTitleBox() {
        
        return createTitleBox(DEFAULT_TITLE);
    }
    
    private HBox createTitleBox(String str) {
        HBox hbox = new HBox();
        
        Text t = new Text(10,VSIZE, str);
        t.setFont(new Font(20));
        t.textAlignmentProperty().set(TextAlignment.CENTER);
        hbox.setAlignment(Pos.CENTER);
        
        hbox.getChildren().add(t);
        
        root.setTop(hbox);
        return hbox;

    }
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
