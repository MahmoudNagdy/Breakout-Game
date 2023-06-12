package javafxapplication7;



import java.io.File;
import javafx.application.Application;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
import static javafx.application.Application.launch;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;
import javafx.geometry.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Leveltwo extends Application {

    BreakoutGame b = new BreakoutGame();
    //creating window properties 
    private static final int BOARD_WIDTH = 600;
    private static final int BOARD_HEIGHT = 400;

    //creating bricks properties
    private static final int BLOCK_ROWS = 5;
    private static final int BLOCK_COLUMNS = 10;
    private static final int BLOCK_WIDTH = BOARD_WIDTH / BLOCK_COLUMNS;
    private static final int BLOCK_HEIGHT = 20;
    private static final Color[] BLOCK_COLORS = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE};
    private final Rectangle[][] blocks = new Rectangle[BLOCK_ROWS][BLOCK_COLUMNS]; 
    
    //creating paddle properties
    private Rectangle paddle;
    
    //creating ball properties
    private Circle ball;
    private double ballSpeedX = 7;
    private double ballSpeedY = -7;

    //creating dashboard 
    private int lives2 = 3;
    private int score2 = 0;
    private Label scoreLabel;
    private Label livesLabel;
    
    // start and exit declarition
    private Pane root;
    Button startButton = new Button("Start");
    Button ExitButton = new Button("Exit");
    Button settingsButton = new Button("⚙");
    Button BackButton = new Button("←");
    Button pauseButton = new Button("⏵");
    Button resumeButton = new Button("⏸");
    Button muteButton = new Button("🔇");
    Button unmuteButton = new Button("♫");
    
    Button arabicButton = new Button("AR");
    Button englishButton = new Button("EN");
    
    Button musicOneButton = new Button("1");
    Button musicTwoButton = new Button("2");
    
    
    //Background Music
    String musicFile = "E:\\Breakout\\src\\javafxapplication7\\music/y2mate.com - Pursuit Dramatic Music  Vanoss Gaming Background Music HD.mp3";
    Media sound = new Media(new File(musicFile).toURI().toString());
    MediaPlayer mediaPlayer = new MediaPlayer(sound);

    
    //Background Music
    String musicFile2 = "E:\\Breakout\\src\\javafxapplication7\\music/y2mate.com - Scheming Weasel Faster Kevin Macleod  Background Music HD.mp3";
    Media sound2 = new Media(new File(musicFile2).toURI().toString());
    MediaPlayer mediaPlayer2= new MediaPlayer(sound2);
    
    
    
    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(15), event -> {
        moveBall();
        checkCollisions();
    }));
    
    
    Image image2 = new Image("E:\\Breakout\\src\\javafxapplication7/611250.jpg");
    BackgroundImage backgroundImage2 = new BackgroundImage(image2, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
    Background background2 = new Background(backgroundImage2);
    
    
    Image image3 = new Image("E:\\Breakout\\src\\javafxapplication7/WhatsApp Image 2023-05-22 at 23.49.26.jpg");
    BackgroundImage backgroundImage3 = new BackgroundImage(image3, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
    Background background3 = new Background(backgroundImage3);
    
    
    Image settingImage = new Image("E:\\Breakout\\src\\javafxapplication7/Screenshot 2023-05-26 221720.png");
    BackgroundImage backgroundSetting = new BackgroundImage(settingImage, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
    Background background4 = new Background(backgroundSetting);
    
    @Override
    public void start(Stage primaryStage) {
        //creating pane properties
        root = new Pane();
        Image image = new Image("E:\\Breakout\\src\\javafxapplication7/Background2.png");
        BackgroundImage backgroundImage = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
        Background background = new Background(backgroundImage);
        
        
        Image image1 = new Image("E:\\Breakout\\src\\javafxapplication7/WhatsApp Image 2023-05-22 at 23.00.22.jpg");
        BackgroundImage backgroundImage1 = new BackgroundImage(image1, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
        Background background1 = new Background(backgroundImage1);
        Scene scene = new Scene(root, BOARD_WIDTH, BOARD_HEIGHT);
        root.setBackground(background1);
        
        // Create start button
        startButton.setFont(Font.font("Times New Roman", 20));
        startButton.setPrefWidth(100);
        startButton.setLayoutX((BOARD_WIDTH / 2 - startButton.getPrefWidth() / 2) - 70);
        startButton.setLayoutY(BOARD_HEIGHT / 2 - startButton.getPrefHeight() / 2);
        startButton.setOnAction(event -> {
            root.setBackground(background);
            mediaPlayer.setVolume(0.1);
            startGame();
        });
        // Create exit button
        
        ExitButton.setFont(Font.font("Times New Roman", 20));
        ExitButton.setPrefWidth(100);
        ExitButton.setLayoutX((BOARD_WIDTH / 2 - startButton.getPrefWidth() / 2) + 50);
        ExitButton.setLayoutY(BOARD_HEIGHT / 2 - startButton.getPrefHeight() / 2);
        ExitButton.setOnAction(e -> {
            primaryStage.close();
        });
        
        BackButton.setPrefSize(50, 30);
        BackButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: Arial;");
        BackButton.setOnAction(event -> {
            b.start(primaryStage);
        });
        
        muteButton.setPrefSize(50, 50);
        muteButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: Arial;");
        muteButton.setLayoutX(370);
        muteButton.setLayoutY(120);
        muteButton.setOnAction(e->{
            muteGame();
            muteButton.setVisible(false);
            unmuteButton.setVisible(true);
        });
        
        unmuteButton.setPrefSize(50, 50);
        unmuteButton.setLayoutX(370);
        unmuteButton.setLayoutY(120);
        unmuteButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: Arial;");
        unmuteButton.setOnAction(e->{
            unmuteGame();
            unmuteButton.setVisible(false);
            muteButton.setVisible(true);
        });
        
        
        
        arabicButton.setPrefSize(70, 50);
        arabicButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: Arial;");
        arabicButton.setLayoutX(350);
        arabicButton.setLayoutY(200);
        arabicButton.setOnAction(e->{
            startButton.setText("ابدأ");
            ExitButton.setText("خروج");
            arabicButton.setVisible(false);
            englishButton.setVisible(true);
        });
        
        englishButton.setPrefSize(70, 50);
        englishButton.setLayoutX(350);
        englishButton.setLayoutY(200);
        englishButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: Arial;");
        englishButton.setOnAction(e->{
            startButton.setText("Start");
            ExitButton.setText("Exit");
            englishButton.setVisible(false);
            arabicButton.setVisible(true);
        });
        
        
        musicOneButton.setPrefSize(50, 50);
        musicOneButton.setStyle("-fx-background-color: #7b241c; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: Arial;");
        musicOneButton.setLayoutX(300);
        musicOneButton.setLayoutY(120);
        musicOneButton.setOnAction(e->{
            mediaPlayer.play();
            mediaPlayer2.stop();
            musicOneButton.setVisible(false);
            musicTwoButton.setVisible(true);
        });
        
        musicTwoButton.setPrefSize(50, 50);
        musicTwoButton.setLayoutX(300);
        musicTwoButton.setLayoutY(120);
        musicTwoButton.setStyle("-fx-background-color: #f1c40f; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: Arial;");
        musicTwoButton.setOnAction(e->{
            mediaPlayer2.play();
            mediaPlayer.stop();
            musicTwoButton.setVisible(false);
            musicOneButton.setVisible(true);
        });
        
        settingsButton.setPrefSize(50, 50);
        settingsButton.setStyle("-fx-background-color: #FFA500; -fx-text-fill: white; -fx-font-size: 25px; -fx-font-family: Arial;");
        settingsButton.setLayoutX(552);
        settingsButton.setLayoutY(0);
        settingsButton.setOnAction(e -> openSettingsWindow());
        
        root.getChildren().addAll(startButton,ExitButton, BackButton, settingsButton);
        
        
        
        // Background Music1
        // Set the volume of the music
        mediaPlayer.setVolume(0.5);

        // Set the music to loop indefinitely
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        // Play the music
        mediaPlayer.play();
        
        
        // Background Music2
        // Set the volume of the music
        mediaPlayer2.setVolume(0.5);

        // Set the music to loop indefinitely
        mediaPlayer2.setCycleCount(MediaPlayer.INDEFINITE);

        
        
        
        
        
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Breakout Game");
        primaryStage.show();
    }
    
    
/*
    private void startGame() {
        // remove start & exit button
        root.getChildren().clear();

        //creating the game map
        createBlocks();
        createPaddle();
        createBall();
        createScoreboard();
        
        //set FPS 'Timeline properties'
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(15), event -> {
            moveBall();
            checkCollisions();
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        
        root.requestFocus();
    }

*/
    
    private void startGame() {
    // Remove start & exit buttons
    root.getChildren().clear();

    // Create game map, paddle, ball, and scoreboard
    createBlocks();
    createPaddle();
    createBall();
    createScoreboard();

    // Set FPS 'Timeline' properties

    timeline.setCycleCount(Timeline.INDEFINITE);
    timeline.play();

    // Add pause and resume buttons

    // Set focus to the root pane
    root.requestFocus();
}
    
private void muteGame(){
     if (mediaPlayer.isMute()) {
        mediaPlayer.setMute(false);
    } else {
        mediaPlayer.setMute(true);
    }
     
     
    if (mediaPlayer2.isMute()) {
        mediaPlayer2.setMute(false);
    } else {
        mediaPlayer2.setMute(true);
    }
}

private void unmuteGame(){
     if (mediaPlayer.isMute()) {
        mediaPlayer.setMute(false);
    } else {
        mediaPlayer.setMute(true);
    }
     
    if (mediaPlayer2.isMute()) {
        mediaPlayer2.setMute(false);
    } else {
        mediaPlayer2.setMute(true);
    }
}

private void createBlocks() {
    int x = 50;
    int y = 50;
    for (int row = 0; row < BLOCK_ROWS; row++) {
        for (int col = 0; col < BLOCK_COLUMNS; col++) {
            Rectangle block = new Rectangle(x, y, BLOCK_WIDTH, BLOCK_HEIGHT);
            block.setStroke(Color.BLACK);
            block.setFill(Color.RED);
            block.getProperties().put("hits", 2); // initial hits required to break the block
            blocks[row][col] = block;
            root.getChildren().add(block);
            x += BLOCK_WIDTH + 20; // add horizontal spacing between blocks
        }
        x = 50;
        y += BLOCK_HEIGHT + 20; // add vertical spacing between rows
    }
    }

    private void createPaddle() {
        //creating paddle properties
        paddle = new Rectangle(BOARD_WIDTH / 2 - 50, BOARD_HEIGHT - 50, 100, 25);
        //ِArc a = new Arc(0, 0, 5, 5, 45, 5);
        paddle.setFill(Color.WHITE);
        paddle.setArcHeight(30);
        paddle.setArcWidth(30);
        root.getChildren().add(paddle);

        // Add mouse event listeners to move the paddle
        root.setOnMouseMoved(event -> {
            //mouse X cordinates
            double x = event.getX();
            //left move condition
            if (x < paddle.getWidth() / 2) {
                x = paddle.getWidth() / 2;
            }
            //right move condition
            else if (x > BOARD_WIDTH - paddle.getWidth() / 2) {
                x = BOARD_WIDTH - paddle.getWidth() / 2;
            }
            //move paddle line
            paddle.setX(x - paddle.getWidth() / 2);
        });
        //Add Keyboard event listeners to move the paddle
        root.setOnKeyPressed(event -> {
            //left move condition
            if(event.getCode() == KeyCode.LEFT){
                //left limit
                if(paddle.getX() > 0){
                    paddle.setX(paddle.getX() - 15);
                }
            }
            //right move condition
            else if(event.getCode() == KeyCode.RIGHT){
                //right limit
                if(paddle.getX() < BOARD_WIDTH - paddle.getWidth()){
                    paddle.setX(paddle.getX() + 15);
                }
            }
        });
    }

    private void createBall() {
        ball = new Circle(paddle.getX() + paddle.getWidth() / 2, paddle.getY() - 10, 10);
        ball.setFill(Color.WHITE);
        root.getChildren().add(ball);
    }
/*
    private void createScoreboard() {
        //create scorcelabel properties
        scoreLabel = new Label("Score: " + score2);
        scoreLabel.setFont(Font.font("Times New Roman", 20));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setAlignment(Pos.CENTER_LEFT);
        scoreLabel.setPrefWidth(100);
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(BOARD_HEIGHT - 30);
        //create liveslabel properties
        livesLabel = new Label("Lives: " + lives2);
        livesLabel.setFont(Font.font("Times New Roman", 20));
        livesLabel.setTextFill(Color.WHITE);
        livesLabel.setAlignment(Pos.CENTER_RIGHT);
        livesLabel.setPrefWidth(100);
        livesLabel.setLayoutX(BOARD_WIDTH - livesLabel.getPrefWidth() - 10);
        livesLabel.setLayoutY(BOARD_HEIGHT - 30);

        root.getChildren().addAll(scoreLabel, livesLabel);
    }
*/
    
    private void createScoreboard() {
    // Create pause button
    pauseButton.setPrefSize(50, 30);
    pauseButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: Arial;");
    pauseButton.setOnAction(event -> pauseGame());

    // Create resume button
    resumeButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-family: Arial;");
    resumeButton.setPrefSize(50, 30);
    resumeButton.setOnAction(event -> resumeGame());

    // Create score label
    scoreLabel = new Label("Score: " + score2);
    scoreLabel.setFont(Font.font("Times New Roman", 20));
    scoreLabel.setTextFill(Color.WHITE);
    scoreLabel.setAlignment(Pos.CENTER_LEFT);
    scoreLabel.setPrefWidth(100);
    scoreLabel.setLayoutX(10);
    scoreLabel.setLayoutY(BOARD_HEIGHT - 30);

    // Create lives label
    livesLabel = new Label("Lives: " +lives2);
    livesLabel.setFont(Font.font("Times New Roman", 20));
    livesLabel.setTextFill(Color.WHITE);
    livesLabel.setAlignment(Pos.CENTER_RIGHT);
    livesLabel.setPrefWidth(100);
    livesLabel.setLayoutX(BOARD_WIDTH - livesLabel.getPrefWidth() - 10);
    livesLabel.setLayoutY(BOARD_HEIGHT - 30);

    // Add buttons and labels to root pane
    root.getChildren().addAll(resumeButton, pauseButton, scoreLabel, livesLabel);
}
    
    private void pauseGame() {
    if (timeline != null) {
        timeline.pause();
    }
    pauseButton.setVisible(false);
    resumeButton.setVisible(true);
}

private void resumeGame() {
    if (timeline != null) {
        timeline.play();
    }
    pauseButton.setVisible(true);
    resumeButton.setVisible(false);
}
    private void moveBall() {
        //start move
        ball.setCenterX(ball.getCenterX() + ballSpeedX);
        ball.setCenterY(ball.getCenterY() + ballSpeedY);
        //right and left impact
        if (ball.getCenterX() < 0 || ball.getCenterX() > BOARD_WIDTH) {
            ballSpeedX *= -1;
        }
        //up impact
        if (ball.getCenterY() < 0) {
            ballSpeedY *= -1;
        }
        //fall ball
        if (ball.getCenterY() > BOARD_HEIGHT) {
            loseLife();
        }
    }
    
    private void checkCollisions() {
        // Check for collisions with blocks
        int numHits = 0;
        for (int row = 0; row < BLOCK_ROWS; row++) {
            for (int col = 0; col < BLOCK_COLUMNS; col++) {
                Rectangle block = blocks[row][col];
                if (block != null && ball.getBoundsInParent().intersects(block.getBoundsInParent())) {
                    int hits = (int) block.getProperties().get("hits");
                    
                    if (hits == 1) {
                    // Remove block and update score
                    root.getChildren().remove(block);
                    blocks[row][col] = null;
                    score2 += 10;
                    scoreLabel.setText("Score: " +score2);
                    //check Win the game by final score
                    if (score2 == 350) {
                        endGame2(true);
                    }
                    // Reverse vertical direction of ball
                    ballSpeedY *= -1;
                    
                } else {
                    // Decrement hits property of block
                    block.getProperties().put("hits", hits - 1);
                    block.setFill(Color.BISQUE);
                    // Reverse vertical direction of ball
                    ballSpeedY *= -1;
                }
                }
            }
        }
        
        
        // Check for collisions with paddle
        if (ball.getBoundsInParent().intersects(paddle.getBoundsInParent())) {
            // Reverse vertical direction of ball
            ballSpeedY *= -1;
            // Change horizontal direction of ball based on where it hits the paddle
            double ballPositionRelativeToPaddle = ball.getCenterX() - paddle.getX();
            double paddleWidth = paddle.getWidth();
            double horizontalFactor = (ballPositionRelativeToPaddle - paddleWidth / 2) / (paddleWidth / 2);
            ballSpeedX = horizontalFactor * 5;
        }
    }
    
    
    
    
    private void loseLife() {
        lives2--;
        livesLabel.setText("Lives: " + lives2);
        if (lives2 == 0) {
            endGame2(false);
        } else {
            resetBall();
        }
    }
    
    private void endGame2(boolean isWin) {
        //remove map
        root.getChildren().clear();
        //creat game status label & add exit button
        Label endLabel = new Label();
        endLabel.setFont(Font.font("Times New Roman", 30));
        endLabel.setTextFill(Color.WHITE);
        endLabel.setAlignment(Pos.CENTER);
        endLabel.setPrefWidth(BOARD_WIDTH);
        endLabel.setPrefHeight(BOARD_HEIGHT);
        endLabel.setLayoutX(0);
        endLabel.setLayoutY(0);
        if (isWin) {
            root.setBackground(background3);
            ExitButton.setLayoutX(BOARD_WIDTH / 2 - startButton.getPrefWidth() / 2);
            ExitButton.setLayoutY((BOARD_HEIGHT / 2 - startButton.getPrefHeight() / 2)+ 50);
            
            root.getChildren().addAll(ExitButton, BackButton); 
        } else {
            root.setBackground(background2);
            mediaPlayer.stop();
            mediaPlayer2.stop();
            ExitButton.setLayoutX(BOARD_WIDTH / 2 - startButton.getPrefWidth() / 2);
            ExitButton.setLayoutY((BOARD_HEIGHT / 2 - startButton.getPrefHeight() / 2)+ 50);
            root.getChildren().addAll(ExitButton, BackButton); 
        }
        
    }
    
    private void resetBall() {
        ball.setCenterX(paddle.getX() + paddle.getWidth() / 2);
        ball.setCenterY(paddle.getY() - 10);
    }
    
    private void openSettingsWindow() {
        // Create a new window for game settings
        Stage settingsWindow = new Stage();
        settingsWindow.setTitle("Game Settings");
        settingsWindow.setWidth(BOARD_WIDTH);
        settingsWindow.setHeight(BOARD_HEIGHT);
        Pane roots = new Pane();
        Scene s = new Scene(roots);
        
        
        Label sound = new Label("Sound");
        sound.setStyle("-fx-font-size: 28px; -fx-text-fill: #fff; -fx-padding: 10px; -fx-border-radius: 5px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);");
        sound.setLayoutX(170);
        sound.setLayoutY(115);
        
        
        Label Language = new Label("Language");
        Language.setStyle("-fx-font-size: 28px; -fx-text-fill: #fff; -fx-padding: 10px; -fx-border-radius: 5px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.4), 10, 0, 0, 0);");
        Language.setLayoutX(170);
        Language.setLayoutY(190);
        
        
        roots.setBackground(background4);
        
        roots.getChildren().addAll(muteButton, unmuteButton, sound, englishButton,arabicButton, Language, musicOneButton,musicTwoButton);

        // Show the settings window
        settingsWindow.setResizable(false);
        settingsWindow.setScene(s);
        settingsWindow.show();
    }
        
    public static void main(String[] args) {
        launch(args);
    }
}

