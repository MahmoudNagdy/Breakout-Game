package javafxapplication7;

import javafx.application.Application;
import javafx.animation.Timeline;
import javafx.animation.KeyFrame;
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
import javafx.scene.image.Image;

public class BreakoutGame extends Application {

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
    private double ballSpeedX = 5;
    private double ballSpeedY = -5;

    //creating dashboard 
    private int lives = 3;
    private int score = 0;
    private Label scoreLabel;
    private Label livesLabel;
    
    // start and exit declarition
    private Pane root;
    Button startButton = new Button("Start");
    Button ExitButton = new Button("Exit");
    Button pauseButton = new Button("⏵");
    Button resumeButton = new Button("⏸");
    
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(15), event -> {
        moveBall();
        checkCollisions();
    }));
    
    Button Level2 = new Button("Level2");
    Image image2 = new Image("E:\\Breakout\\src\\javafxapplication7/611250.jpg");
    BackgroundImage backgroundImage2 = new BackgroundImage(image2, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
    Background background2 = new Background(backgroundImage2);
    
    
    Image image3 = new Image("E:\\Breakout\\src\\javafxapplication7/WhatsApp Image 2023-05-22 at 23.49.26.jpg");
    BackgroundImage backgroundImage3 = new BackgroundImage(image3, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, new BackgroundSize(1.0, 1.0, true, true, false, false));
    Background background3 = new Background(backgroundImage3);
    @Override
    public void start(Stage primaryStage) {
        Leveltwo l = new Leveltwo();
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
        
        Level2.setFont(Font.font("Times New Roman", 20));
        Level2.setLayoutX((BOARD_WIDTH / 2 - startButton.getPrefWidth() / 2) + 110);
        Level2.setLayoutY((BOARD_HEIGHT / 2 - startButton.getPrefHeight() / 2) + 20);
        Level2.setPrefWidth(100);
        Level2.setOnAction(e -> {
            l.start(primaryStage);
        });
        
        
        root.getChildren().addAll(startButton,ExitButton);
        
        
        
        
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
        // Add pause button
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
    //root.requestFocus();
}
        
    private void createBlocks() {
        for (int row = 0; row < BLOCK_ROWS; row++) {
            for (int col = 0; col < BLOCK_COLUMNS; col++) {
                Rectangle block = new Rectangle(col * BLOCK_WIDTH, row * BLOCK_HEIGHT + 30, BLOCK_WIDTH, BLOCK_HEIGHT);
                block.setFill(BLOCK_COLORS[row]);
                block.setStroke(Color.BLACK);
                blocks[row][col] = block;
                root.getChildren().add(block);
            }
        }
    }

    private void createPaddle() {
        //creating paddle properties
        paddle = new Rectangle(BOARD_WIDTH / 2 - 50, BOARD_HEIGHT - 50, 100, 25);
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
        scoreLabel = new Label("Score: " + score);
        scoreLabel.setFont(Font.font("Times New Roman", 20));
        scoreLabel.setTextFill(Color.WHITE);
        scoreLabel.setAlignment(Pos.CENTER_LEFT);
        scoreLabel.setPrefWidth(100);
        scoreLabel.setLayoutX(10);
        scoreLabel.setLayoutY(BOARD_HEIGHT - 30);
        //create liveslabel properties
        livesLabel = new Label("Lives: " + lives);
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
    resumeButton.setVisible(false);

    // Create score label
    scoreLabel = new Label("Score: " + score);
    scoreLabel.setFont(Font.font("Times New Roman", 20));
    scoreLabel.setTextFill(Color.WHITE);
    scoreLabel.setAlignment(Pos.CENTER_LEFT);
    scoreLabel.setPrefWidth(100);
    scoreLabel.setLayoutX(10);
    scoreLabel.setLayoutY(BOARD_HEIGHT - 30);

    // Create lives label
    livesLabel = new Label("Lives: " +lives);
    livesLabel.setFont(Font.font("Times New Roman", 20));
    livesLabel.setTextFill(Color.WHITE);
    livesLabel.setAlignment(Pos.CENTER_RIGHT);
    livesLabel.setPrefWidth(100);
    livesLabel.setLayoutX(BOARD_WIDTH - livesLabel.getPrefWidth() - 10);
    livesLabel.setLayoutY(BOARD_HEIGHT - 30);

    // Add buttons and labels to root pane
    root.getChildren().addAll(pauseButton, resumeButton, scoreLabel, livesLabel);
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
        for (int row = 0; row < BLOCK_ROWS; row++) {
            for (int col = 0; col < BLOCK_COLUMNS; col++) {
                Rectangle block = blocks[row][col];
                if (block != null && ball.getBoundsInParent().intersects(block.getBoundsInParent())) {
                    // Remove block and update score
                    root.getChildren().remove(block);
                    blocks[row][col] = null;
                    score += 10;
                    scoreLabel.setText("Score: " + score);
                    
                    //check Win the game by final score
                    if (score == 500) {
                    endGame(true);
                    }
                    // Reverse vertical direction of ball
                    ballSpeedY *= -1;
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
        lives--;
        livesLabel.setText("Lives: " + lives);
        if (lives == 0) {
            endGame(false);
        } else {
            resetBall();
        }
    }
    
    private void endGame(boolean isWin) {
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
            ExitButton.setPrefWidth(100);
            ExitButton.setLayoutX((BOARD_WIDTH / 2 - startButton.getPrefWidth() / 2) - 50);
            ExitButton.setLayoutY((BOARD_HEIGHT / 2 - startButton.getPrefHeight() / 2)+ 50);
            
            
            Level2.setLayoutX((BOARD_WIDTH / 2 - startButton.getPrefWidth() / 2) + 60);
            Level2.setLayoutY((BOARD_HEIGHT / 2 - startButton.getPrefHeight() / 2) + 50);
            
            root.getChildren().addAll(endLabel,ExitButton, Level2); 
        } else {
            //endLabel.setText("Game Over");
            root.setBackground(background2);
            ExitButton.setLayoutX((BOARD_WIDTH / 2 - startButton.getPrefWidth() / 2));
            ExitButton.setLayoutY((BOARD_HEIGHT / 2 - startButton.getPrefHeight() / 2)+ 50);
            
            root.getChildren().addAll(endLabel,ExitButton);
        }
    }
    
    private void resetBall() {
        ball.setCenterX(paddle.getX() + paddle.getWidth() / 2);
        ball.setCenterY(paddle.getY() - 10);
    }
        
    public static void main(String[] args) {
        launch(args);
    }
}
