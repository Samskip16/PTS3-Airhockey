/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package Airhockey.Main;

import Airhockey.Elements.Bat;
import Airhockey.Elements.Goal;
import Airhockey.Elements.LeftEnemyBat;
import Airhockey.Elements.Puck;
import Airhockey.Elements.RightEnemyBat;
import Airhockey.Elements.Triangle;
import Airhockey.Properties.PropertiesManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

/**
 *
 * @author Sam
 */
public class Renderer extends Application {

    private boolean canImpulsBall = true;
    private boolean batSideMovementLeft = false;
    private boolean batSideMovementRight = false;

    public static final int LEFT = 1;
    public static final int RIGHT = 2;

    private Puck puck;
    private Bat bat;
    private LeftEnemyBat leftEnemyBat;
    private RightEnemyBat rightEnemyBat;
    private Triangle triangle;
    
    private Goal redGoal;
    private Goal blueGoal;
    private Goal greenGoal;

    private Body batBody;

    private Button startButton;
    private final Group root = new Group();
    //private final Group root2 = new Group();

    private Label player1NameLabel;
    private Label player2NameLabel;
    private Label player3NameLabel;
    private Label player1ScoreLabel;
    private Label player2ScoreLabel;
    private Label player3ScoreLabel;

    private Game game;

    public Renderer() {
        game = new Game(this);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Airhockey");
        primaryStage.setFullScreen(false);
        primaryStage.setResizable(false);

        KeyListener keyListener = new KeyListener(this);
        PropertiesManager.saveProperty("LEB-Difficulty", "EASY");
        PropertiesManager.saveProperty("REB-Difficulty", "HARD");

        //Create a group for holding all objects on the screen
        final Scene scene = new Scene(root, Utils.WIDTH, Utils.HEIGHT, Color.GRAY);
        //final Scene scene2 = new Scene(root2, Utils.WIDTH, Utils.HEIGHT, Color.WHITE);

        Canvas canvas = new Canvas(Utils.WIDTH, Utils.HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        root.getChildren().add(canvas);

        addItems();
        addLabels();

        //Add ground to the application, this is where balls will land
        Utils.addGround(100, 10);

//        //Add left and right walls so balls will not move outside the viewing area.
        Utils.addWall(0, 100, 1, 100); //Left wall
        Utils.addWall(99, 100, 1, 100); //Right wall
        Utils.addWall(0, 90, 100, 1);
        /**
         * Set ActionEvent and duration to the KeyFrame. The ActionEvent is trigged when KeyFrame execution is over.
         */
        final Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        Duration duration = Duration.seconds(1.0 / 60.0); // Set duration for frame.
        MyHandler eventHandler = new MyHandler();
        KeyFrame frame = new KeyFrame(duration, eventHandler, null, null);
        timeline.getKeyFrames().add(frame);

        //Create button to start simulation.
        startButton = new Button();
        startButton.setLayoutX((Utils.WIDTH / 20));
        startButton.setLayoutY((30));
        startButton.setText("Start");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                timeline.playFromStart();
                startButton.setDisable(true);
                scene.setOnKeyPressed(keyListener);
                scene.setOnKeyReleased(keyListener);
//                primaryStage.setScene(scene2);
//                primaryStage.show();
            }
        });

        root.getChildren().add(startButton);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void addItems() {
        puck = new Puck(50, 50);
        bat = new Bat(1, 40, 20, Color.RED);
        leftEnemyBat = new LeftEnemyBat(2, 20, 50, Color.BLUE);
        rightEnemyBat = new RightEnemyBat(3, 80, 50, Color.GREEN);
        triangle = new Triangle(0);
        
        int goalLeftTopX = (int) Math.floor(triangle.getLeftBottomX() + (triangle.getWidth() * 0.3));
        redGoal = new Goal(Color.RED, goalLeftTopX, triangle.getBottomLineY(), triangle.getWidth());

        goalLeftTopX = (int) Math.floor(triangle.getLeftBottomX() - (triangle.getWidth() * 0.25));
        int goalLeftTopY = (int) Math.floor(triangle.getBottomLineY() - (triangle.getHeight() * 0.3));
        greenGoal = new Goal(Color.GREEN, goalLeftTopX, goalLeftTopY, triangle.getWidth());

        goalLeftTopX = (int) Math.floor(triangle.getLeftBottomX() + (triangle.getWidth() * 0.85));
        blueGoal = new Goal(Color.BLUE, goalLeftTopX, goalLeftTopY, triangle.getWidth());
        
        root.getChildren().add(puck.node);
        root.getChildren().addAll(bat.node, bat.imageNode);
        root.getChildren().addAll(leftEnemyBat.node, leftEnemyBat.imageNode);
        root.getChildren().addAll(rightEnemyBat.node, rightEnemyBat.imageNode);
        root.getChildren().add(triangle.node);
        root.getChildren().add(redGoal.node);
        root.getChildren().add(greenGoal.node);
        root.getChildren().add(blueGoal.node);
    }

    public void addLabels() {
        player1NameLabel = new Label("Player1:");
        player2NameLabel = new Label("Player2:");
        player3NameLabel = new Label("Player3:");
        player1ScoreLabel = new Label("20");
        player2ScoreLabel = new Label("20");
        player3ScoreLabel = new Label("20");

        player1NameLabel.setFont(Font.font("Arial Black", 24.0));
        player2NameLabel.setFont(Font.font("Arial Black", 24.0));
        player3NameLabel.setFont(Font.font("Arial Black", 24.0));
        player1ScoreLabel.setFont(Font.font("Arial Black", 24.0));
        player2ScoreLabel.setFont(Font.font("Arial Black", 24.0));
        player3ScoreLabel.setFont(Font.font("Arial Black", 24.0));

        player1NameLabel.relocate(870, 10);
        player2NameLabel.relocate(870, 40);
        player3NameLabel.relocate(870, 70);
        player1ScoreLabel.relocate(990, 10);
        player2ScoreLabel.relocate(990, 40);
        player3ScoreLabel.relocate(990, 70);

        root.getChildren().addAll(player1NameLabel, player2NameLabel, player3NameLabel, player1ScoreLabel, player2ScoreLabel, player3ScoreLabel);
    }

    private class MyHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            //Create time step. Set Iteration count 8 for velocity and 3 for positions
            Utils.world.step(1.0f / 60.f, 8, 3);

            //Move balls to the new position computed by JBox2D
            Body puckBody = (Body) puck.node.getUserData();
            batBody = (Body) bat.node.getUserData();

            if (canImpulsBall) {
                puckBody.applyLinearImpulse(new Vec2(-10.0f, -12.0f), puckBody.getWorldCenter());
                canImpulsBall = false;
            }

            float xpos = Utils.toPixelPosX(puckBody.getPosition().x);
            float ypos = Utils.toPixelPosY(puckBody.getPosition().y);
            puck.node.setLayoutX(xpos);
            puck.node.setLayoutY(ypos);

            if (batSideMovementLeft) {
                batBody.setLinearVelocity(new Vec2(-25.0f, 0.0f));
                batSideMovementLeft = false;
                batSideMovementRight = false;
            } else if (batSideMovementRight) {
                batBody.setLinearVelocity(new Vec2(25.0f, 0.0f));
                batSideMovementRight = false;
                batSideMovementLeft = false;
            }

            float xposB = Utils.toPixelPosX(batBody.getPosition().x);
            float yposB = Utils.toPixelPosY(batBody.getPosition().y);
            bat.setPosition(xposB, yposB);

            moveLeftEnemyBat(puckBody);
            moveRightEnemyBat(puckBody);
        }

    }

    public void startBatMovement(int direction) {
        if (direction == LEFT) {
            batSideMovementLeft = true;
            batSideMovementRight = false;
        } else if (direction == RIGHT) {
            batSideMovementRight = true;
            batSideMovementLeft = false;
        }
    }

    public void stopBatMovement() {
        batSideMovementLeft = false;
        batSideMovementRight = false;
        batBody.setLinearVelocity(new Vec2(0.0f, 0.0f));
    }

    private void moveLeftEnemyBat(Body puckBody) {
        float puckPositionY = Utils.toPixelPosY(puckBody.getPosition().y);

        Body leftEnemyBatBody = (Body) leftEnemyBat.node.getUserData();
        float leftEnemyBatPositionY = Utils.toPixelPosY(leftEnemyBatBody.getPosition().y);

        if (puckPositionY > leftEnemyBatPositionY) {
            if (leftEnemyBatPositionY < 400) {
                leftEnemyBat.moveDown(puckBody);
            } else {
                leftEnemyBat.stop();
            }
        } else if (puckPositionY < leftEnemyBatPositionY) {
            if (leftEnemyBatPositionY > 200) {
                leftEnemyBat.moveUp(puckBody);
            } else {
                leftEnemyBat.stop();
            }
        }
    }

    private void moveRightEnemyBat(Body puckBody) {
        float puckPositionY = Utils.toPixelPosY(puckBody.getPosition().y);

        Body rightEnemyBatBody = (Body) rightEnemyBat.node.getUserData();
        float rightEnemyBatPositionY = Utils.toPixelPosY(rightEnemyBatBody.getPosition().y);

        if (puckPositionY - 1 > rightEnemyBatPositionY + 1) {
            if (rightEnemyBatPositionY < 400) {
                rightEnemyBat.moveDown(puckBody);
            } else {
                rightEnemyBat.stop();
            }
        } else if (puckPositionY < rightEnemyBatPositionY) {
            if (rightEnemyBatPositionY > 200) {
                rightEnemyBat.moveUp(puckBody);
            } else {
                rightEnemyBat.stop();
            }
        }
    }

    public void setTextFields(String field, String value) {
        switch (field) {
            case "PLAYER1_NAME":
                player1NameLabel.setText(value);
                break;
            case "PLAYER2_NAME":
                player2NameLabel.setText(value);
                break;
            case "PLAYER3_NAME":
                player3NameLabel.setText(value);
                break;
            case "PLAYER1_SCORE":
                break;
            case "PLAYER2_SCORE":
                break;
            case "PLAYER3_SCORE":
                break;
        }
    }

    private void drawShapes(GraphicsContext gc) {
        double centerPointX = Utils.WIDTH / 2;
        double centerPointY = Utils.HEIGHT / 2;

//        gc.setStroke(Color.BLACK);
//        gc.setLineWidth(2);
//
//        gc.strokeLine(centerPointX, centerPointY, centerPointX, LEFT);
//        gc.strokeLine(centerPointX, centerPointY, 0, Utils.HEIGHT);
//        gc.strokeLine(centerPointX, centerPointY, Utils.WIDTH, Utils.HEIGHT);
        gc.setStroke(Color.RED);
        gc.setLineWidth(3);

        gc.strokeOval(centerPointX - 100, centerPointY - 100, 200, 200);
    }
}
