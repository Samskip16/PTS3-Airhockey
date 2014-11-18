package Airhockey.Elements;

import Airhockey.Main.Utils;
import javafx.animation.RotateTransition;
import javafx.animation.RotateTransitionBuilder;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.shape.Line;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import org.jbox2d.collision.shapes.CircleShape;
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
public class Triangle {

    public Node node;

    private final int width = 1024;
    private final int height = 768;
    private final int leftBottomX;
    private final int bottomLineY = 664;
    private final int topY;

    public Triangle(int screenHeight) {
//        width = (int) Math.floor(screenHeight * 1.1);  
//        height = (int) Math.floor(Math.tan(45) * (width / 2));
//        bottomLineY = (int) Math.floor(screenHeight - (screenHeight * 0.1)); 

        topY = bottomLineY - height;
        leftBottomX = 5;

        node = Create();
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

//    public void draw(Graphics graphics) {
//        Graphics2D g2d = (Graphics2D) graphics;
//        int[] xPoints = new int[]{leftBottomX + (width / 2), leftBottomX, leftBottomX + width};
//        int[] yPoints = new int[]{(bottomLineY - height), bottomLineY, bottomLineY};
//        
//        int[] xPoints1 = new int[]{leftBottomX + (width / 2), leftBottomX + 3, leftBottomX + width - 3};
//        int[] yPoints1 = new int[]{(bottomLineY - height) + 3, bottomLineY - 1, bottomLineY - 1};
//        
//        g2d.drawPolygon(xPoints, yPoints, 3);
//        g2d.setColor(Color.WHITE);
//        g2d.fillPolygon(xPoints1, yPoints1, 3);
//        g2d.drawLine(leftBottomX + (width / 2), bottomLineY, leftBottomX + (width / 2), topY);
//    }
    private Group Create() {
//        PolygonShape ps = new PolygonShape();
//
//        BodyDef bd = new BodyDef();
//
//        bd.type = BodyType.STATIC;
//        bd.position.set(10, 0);
//        bd.angle = 0;
//
//        Vec2 A = new Vec2(leftBottomX, bottomLineY);
//        Vec2 B = new Vec2(leftBottomX + width, bottomLineY);
//        Vec2 C = new Vec2(leftBottomX + (width / 2), (bottomLineY - height));
//        Vec2[] ABC = new Vec2[]{A, B, C};
//
//        ps.set(ABC, 3);
//
//        double[] guiABC = new double[6];
//        guiABC[0] = A.x;
//        guiABC[1] = A.y;
//        guiABC[2] = B.x;
//        guiABC[3] = B.y;
//        guiABC[4] = C.x;
//        guiABC[5] = C.y;
//
//        javafx.scene.shape.Polygon polygon = new javafx.scene.shape.Polygon(guiABC);
//
////        polygon.setLayoutX(Utils.toPixelPosX(leftBottomX));
////        polygon.setLayoutY(Utils.toPixelPosX(bottomLineY));
//        Body body = Utils.world.createBody(bd);
//
//        FixtureDef fd = new FixtureDef();
//        fd.shape = ps;
//
//        body.createFixture(fd);
//        polygon.setUserData(body);
//
//        return polygon;

        PolygonShape ps = new PolygonShape();

        BodyDef bd = new BodyDef();

        Vec2 A = new Vec2(leftBottomX, bottomLineY);
        Vec2 Ab = new Vec2((float) (leftBottomX + (width * 0.3)), bottomLineY);
        Vec2 Ba = new Vec2((float) (leftBottomX + (width * 0.7)), bottomLineY);
        Vec2 B = new Vec2(leftBottomX + width, bottomLineY);
//        Vec2 C = new Vec2(leftBottomX + (width / 2), (bottomLineY - height));  

        // creates lineAB
        Group LineAB = new Group();
        Line AAb = new Line(A.x, A.y, Ab.x, Ab.y);
        Line ABA = new Line(Ba.x, Ba.y, B.x, B.y);
        LineAB.getChildren().add(AAb);
        LineAB.getChildren().add(ABA);

        // create ancherpoint for AB
        Point3D ancherpoint = new Point3D(B.x, B.y, 0);
        Transform transform = new Translate(topY, topY);
        Rotate rotation = new Rotate(-60, A.x, A.y);

        // rotates line AB
        LineAB.getTransforms().add(rotation);
        //LineAB.setRotationAxis(ancherpoint);
        //LineAB.setRotate(60);

        Rotate rotationMatrixLeft = new Rotate(60, A.x, A.y);

        Group linePieceAB = createLinePieceAB();
        Group linePieceBC = createLinePieceBC();
        Group linePieceAC = createLinePieceAC();

        Group triangle = new Group(linePieceAB, linePieceBC, linePieceAC);
        return triangle;

    }

    private Group createLinePieceAB() {
        // create the vectors for the linePiece
        Vec2 A = new Vec2(leftBottomX, bottomLineY);
        Vec2 Ab = new Vec2((float) (leftBottomX + (width * 0.3)), bottomLineY);
        Vec2 Ba = new Vec2((float) (leftBottomX + (width * 0.7)), bottomLineY);
        Vec2 B = new Vec2(leftBottomX + width, bottomLineY);
        Vec2[] vertices = new Vec2[]{A, Ab, Ba, B};

        // creates lineAB
        Group LineAB = new Group();
        Line AAb = new Line(A.x, A.y, Ab.x, Ab.y);
        Line ABA = new Line(Ba.x, Ba.y, B.x, B.y);
        LineAB.getChildren().add(AAb);
        LineAB.getChildren().add(ABA);

        //Create an JBox2D body defination for bat.
        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position.set(A.x, A.y);

        PolygonShape line = new PolygonShape();
        line.set(vertices, 4);

        // Create a fixture for bat
        FixtureDef fd = new FixtureDef();
        fd.shape = line;
        fd.density = 0.0f;
        fd.friction = 0.0f;
        fd.restitution = 0.0f;

        Body body = Utils.world.createBody(bd);
        body.createFixture(fd);

        return LineAB;
    }

    private Group createLinePieceBC() {
        // create the vectors for the linePiece
        Vec2 A = new Vec2(leftBottomX, bottomLineY);
        Vec2 Ab = new Vec2((float) (leftBottomX + (width * 0.3)), bottomLineY);
        Vec2 Ba = new Vec2((float) (leftBottomX + (width * 0.7)), bottomLineY);
        Vec2 B = new Vec2(leftBottomX + width, bottomLineY);
        Vec2[] vertices = new Vec2[]{A, Ab, Ba, B};

        // creates lineAB
        Group LineAB = new Group();
        Line AAb = new Line(A.x, A.y, Ab.x, Ab.y);
        Line ABA = new Line(Ba.x, Ba.y, B.x, B.y);
        LineAB.getChildren().add(AAb);
        LineAB.getChildren().add(ABA);

        // create ancherpoint and sets the rotation for AB
        Rotate rotation = new Rotate(60, B.x, B.y);

        // rotates line AB
        LineAB.getTransforms().add(rotation);

        //Create an JBox2D body defination for bat.
        BodyDef bd = new BodyDef();
        bd.type = BodyType.STATIC;
        bd.position.set(A.x, A.y);

        PolygonShape line = new PolygonShape();
        line.set(vertices, 4);

        // Create a fixture for bat
        FixtureDef fd = new FixtureDef();
        fd.shape = line;
        fd.density = 0.0f;
        fd.friction = 0.0f;
        fd.restitution = 0.0f;

        Body body = Utils.world.createBody(bd);
        body.createFixture(fd);

        return LineAB;
    }

    private Group createLinePieceAC() {
        // create the vectors for the linePiece
        Vec2 A = new Vec2(leftBottomX, bottomLineY);
        Vec2 Ab = new Vec2((float) (leftBottomX + (width * 0.3)), bottomLineY);
        Vec2 Ba = new Vec2((float) (leftBottomX + (width * 0.7)), bottomLineY);
        Vec2 B = new Vec2(leftBottomX + width, bottomLineY);
        Vec2[] vecAAb = new Vec2[]{A, Ab};
        Vec2[] vecBBa = new Vec2[]{Ab, B};

        // creates lineAB
        Group LineAB = new Group();
        Line AAb = new Line(A.x, A.y, Ab.x, Ab.y);
        Line ABA = new Line(Ba.x, Ba.y, B.x, B.y);
        LineAB.getChildren().add(AAb);
        LineAB.getChildren().add(ABA);

        // create ancherpoint and sets the rotation for AB
        Rotate rotation = new Rotate(-60, A.x, A.y);

        // rotates line AB
        LineAB.getTransforms().add(rotation);

        //Create an JBox2D body defination for bat.
        BodyDef bdAAb = new BodyDef();
        BodyDef bdBBa = new BodyDef();
        bdAAb.type = BodyType.STATIC;
        bdBBa.position.set(Ba.x, Ba.y);

        PolygonShape lineAAB = new PolygonShape();
        PolygonShape lineBBA = new PolygonShape();
        lineAAB.set(vecAAb, 2);
        lineBBA.set(vecBBa, 2);

        // Create a fixture for bat
        FixtureDef fdAAB = new FixtureDef();
        fdAAB.shape = lineAAB;
        FixtureDef fdBBA = new FixtureDef();
        fdBBA.shape = lineBBA;

        Body bodyAAB = Utils.world.createBody(bdAAb);
        Body bodyBBa = Utils.world.createBody(bdBBa);
        bodyAAB.createFixture(fdAAB);
        bodyBBa.createFixture(fdBBA);

        return LineAB;
    }
}
