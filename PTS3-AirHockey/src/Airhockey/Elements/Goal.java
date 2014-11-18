package Airhockey.Elements;

import com.sun.javafx.geom.Vec2d;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import org.jbox2d.common.Vec2;

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
    private int width;
    private int height;
    private double rotation;

    private Color color;
    
    public Node node;

//    private double angle;
//    private Shape rotatedRect;
    public Goal(Color color, int topLeftX, int topLeftY, int triangleWidth) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.color = color;

        width = (int) Math.floor((double) triangleWidth * 0.4);
        height = (int) Math.floor((double) triangleWidth * 0.04);

        if (color == Color.RED) {
            rotation = 0.0;

        } else if (color == Color.GREEN) {
            rotation = -60;
        } else if (color == Color.BLUE) {
            rotation = 60;
        }
        
        node = create();
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopRightX() {
        return topLeftX + width;
    }

    private Node create(){
        // create the vectors for the goal used in physics engine
        Vec2 A = new Vec2(topLeftX, topLeftY);
        Vec2 B = new Vec2((float)(topLeftX + width), topLeftY);
        Vec2 C = new Vec2((float)(topLeftX + width),(float) topLeftY - height);
        Vec2 D = new Vec2(topLeftX,(float) topLeftY - height);
        
        // create the goal used to draw the goal
        Group rect = new Group();
        Line AB = new Line(A.x, A.y, B.x, B.y);
        Line BC = new Line(C.x, C.y, B.x, B.y);
        Line CD = new Line(C.x, C.y, D.x, D.y);
        Line AD = new Line(A.x, A.y, D.x, D.y);
        
        // changes the colour of the lines
        AB.setFill(color);
        BC.setFill(color);
        CD.setFill(color);
        AD.setFill(color);
        
        // adds the lines to the group
        rect.getChildren().add(AB);
        rect.getChildren().add(BC);
        rect.getChildren().add(CD);
        rect.getChildren().add(AD);
        
        // create ancherpoint and sets the rotation for the goal
        Rotate rotationMatrix = new Rotate(rotation, A.x, A.y);
        
        // rotates line AB
        rect.getTransforms().add(rotationMatrix);
        
        // change colour
        
        
        return rect;
    }
}
