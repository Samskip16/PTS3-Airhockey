package Airhockey.Elements;

import Airhockey.Main.Utils;
import com.sun.javafx.geom.Vec2d;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sam
 */
public class Goal {

    private int topLeftX;
    private int topLeftY;
    private int width = 200;
    private int height = 40;
    private double rotation;

    private Color color;

    public Node node;
    public Node collisionNode;

//    private double angle;
//    private Shape rotatedRect;
    public Goal(String color, int topLeftX, int topLeftY) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;

//        width = (int) Math.floor((double) triangleWidth * 0.4);
//        height = (int) Math.floor((double) triangleWidth * 0.04);
        if (color == "RED") {
            rotation = 0;
            this.color = Color.web("#dd4540");
        } else if (color == "BLUE") {
            rotation = -55;
            this.color = Color.web("#4d7fdd");
        } else {
            rotation = 55;
            this.color = Color.web("#009587");
        }

        this.node = createRect();
        this.collisionNode = createCollisionNode();
    }

    private Node createRect() {
        Rectangle r = new Rectangle();

        //r.getTransforms().add(new Rotate(90, topLeftX + (width / 2), topLeftY + (height / 2)));
        //r.getTransforms().add(new Rotate(rotation, topLeftX, topLeftY));
        r.setWidth(width);
        r.setHeight(height);
        r.setFill(color);
        r.setLayoutX(topLeftX);
        r.setLayoutY(topLeftY);

        RotateTransition t = new RotateTransition(Duration.millis(1), r);
        t.setByAngle(rotation);
        t.setAutoReverse(false);
        t.play();

        System.out.println("Value: " + Utils.toPixelPosX(topLeftY));
        System.out.println("Value2: " + (int) Math.floor(Utils.toPixelPosX(topLeftY)));
        System.out.println("LAYOUT: " + Utils.pixelEngineToFrame((int) Math.floor(Utils.toPixelPosX(topLeftY))));

//        Shape s = (Shape) r;
//        AffineTransform t = s.getr
//        t.rotate(Math.toRadians(thetaDegrees), shape.getCenter().x, shape.getCenter().y);
//        shape.setAffineTransform(t);
        // change colour
        return r;
    }

    public Node createCollisionNode() {
        Vec2 TopLeft = new Vec2(topLeftX, topLeftY + 20);
        Vec2 TopRight = new Vec2((float) (topLeftX + width), topLeftY + 20);

        Line line = new Line(TopLeft.x, TopLeft.y, TopRight.x, TopRight.y);
        line.setStroke(Color.WHITE);

        RotateTransition t = new RotateTransition(Duration.millis(1), line);
        t.setByAngle(rotation);
        t.setAutoReverse(false);
        t.play();

        return line;
    }
}
