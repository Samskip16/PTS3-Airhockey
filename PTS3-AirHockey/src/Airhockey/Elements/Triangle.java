package Airhockey.Elements;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Sam
 */
public class Triangle extends Polygon{

    private final int width = 798;
    private final int height = 646;
    private final int leftBottomX = 0;
    private final int bottomLineY = 664;
    private final int topY;

    public Triangle(int screenHeight) {
        //width = (int) Math.floor(screenHeight * 1.1);  
        //height = (int) Math.floor(Math.tan(45) * (width / 2));
        //bottomLineY = (int) Math.floor(screenHeight - (screenHeight * 0.1)); 
        
        topY = bottomLineY - height;      
        
        addPoint(leftBottomX + (width / 2), (bottomLineY - height));
        addPoint(leftBottomX, bottomLineY);
        addPoint(leftBottomX + width, bottomLineY);
    }

    public int getBottomLineY() {
        return bottomLineY;
    }

    public int getTopY() {
        return topY;
    }

    public int getLeftBottomX() {
        return leftBottomX;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Graphics graphics) {
        Graphics2D g2d = (Graphics2D) graphics;
        int[] xPoints = new int[]{leftBottomX + (width / 2), leftBottomX, leftBottomX + width};
        int[] yPoints = new int[]{(bottomLineY - height), bottomLineY, bottomLineY};
        
        int[] xPoints1 = new int[]{leftBottomX + (width / 2), leftBottomX + 3, leftBottomX + width - 3};
        int[] yPoints1 = new int[]{(bottomLineY - height) + 3, bottomLineY - 1, bottomLineY - 1};
        
        g2d.drawPolygon(xPoints, yPoints, 3);
        g2d.setColor(Color.WHITE);
        g2d.fillPolygon(xPoints1, yPoints1, 3);
        //g2d.drawLine(leftBottomX + (width / 2), bottomLineY, leftBottomX + (width / 2), topY);
    }
}
