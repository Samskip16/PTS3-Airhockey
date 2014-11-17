package Airhockey.Elements;

import com.sun.javafx.geom.Arc2D;
import com.sun.javafx.geom.Line2D;
import com.sun.javafx.geom.Shape;
import com.sun.javafx.geom.ShapePair;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import javafx.scene.shape.Line;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sam
 */
public class Goal extends Rectangle {

    private int topLeftX;
    private int topLeftY;
    private int width;
    private int height;
    private double rotation = 0.0;
    private Line collisionLine;

    AffineTransform rotationMatrix;

    private Color color;

//    private double angle;
//    private Shape rotatedRect;
    public Goal(Color color, int topLeftX, int topLeftY, int triangleWidth) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.color = color;

        width = (int) Math.floor((double) triangleWidth * 0.4);
        height = (int) Math.floor((double) triangleWidth * 0.04);

        Line line = new Line();

        double inCollisionLine[] = new double[4];
        inCollisionLine[0] = topLeftX;
        inCollisionLine[1] = topLeftY;
        inCollisionLine[2] = topLeftX + width;
        inCollisionLine[3] = topLeftY;

        double outCollisionLine[] = new double[4];
        double something[] = new double[20];

        AffineTransform collisionLineMatrix = new AffineTransform(inCollisionLine);

//        collisionLine = new Line(inCollisionLine[0], inCollisionLine[1], inCollisionLine[2], inCollisionLine[3]);
//        
//        collisionLine = (Line) collisionLineMatrix.createTransformedShape((java.awt.Shape) collisionLine);
        if (color == Color.RED) {
            rotation = 0.0;
            rotationMatrix = AffineTransform.getRotateInstance(rotation, topLeftX + 1, topLeftY + 1);
            collisionLineMatrix = rotationMatrix;

        } else if (color == Color.GREEN) {
            rotation = 2.13;
            rotationMatrix = AffineTransform.getRotateInstance(rotation, topLeftX + width, topLeftY + 1);
            collisionLineMatrix = rotationMatrix;
        } else if (color == Color.BLUE) {
            rotation = 4.16;
            rotationMatrix = AffineTransform.getRotateInstance(rotation, topLeftX + 1, topLeftY + 1);
            collisionLineMatrix = rotationMatrix;
        }

        System.out.print(collisionLineMatrix.getType());

        collisionLineMatrix.transform(inCollisionLine, 0, outCollisionLine, 0, 2);

        collisionLine = new Line(outCollisionLine[0], outCollisionLine[1], outCollisionLine[2], outCollisionLine[3]);
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopRightX() {
        return topLeftX + width;
    }

    public void draw(Graphics graphics) {
        //Graphics2D g2d = (Graphics2D) graphics;
        Graphics2D gGoal = (Graphics2D) graphics.create();
        gGoal.setColor(color);        

        gGoal.drawLine((int) collisionLine.getStartX(), (int) collisionLine.getStartY(), (int) collisionLine.getEndX(), (int) collisionLine.getEndY());
//        rotation -= 0.001;
//        rotationMatrix = AffineTransform.getRotateInstance(rotation, topLeftX, topLeftY); 
        gGoal.transform(rotationMatrix);
        gGoal.drawLine(topLeftX, topLeftY, topLeftX + width, topLeftY);

        // gGoal.drawLine((int) collisionLine.getStartX(), (int) collisionLine.getStartY(), (int) collisionLine.getEndX(), (int) collisionLine.getEndX());
        //gGoal.draw((Shape)collisionLine);
        gGoal.fillRect(topLeftX, topLeftY, width, height);
        //g2d.draw(rotatedRect);
    }

    @Override
    public boolean intersectsLine(double d, double d1, double d2, double d3) {
        return collisionLine.contains(d, d1) || collisionLine.contains(d2, d3);
        //return collisionLine.getStartX() < d && d1 <= collisionLine.getStartX();
    }

    public boolean customIntersect(double d, double d1, double d2, double d3) {
        return collisionLine.contains(d, d1) || collisionLine.contains(d2, d3);
    }
}
