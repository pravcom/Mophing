package com.example.javafxcleancodedrawerver2.model;

import javafx.geometry.Point2D;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

public class ShapeModel {
    List<Polygon> polygonList = new ArrayList<>();

    List<Shape> shapeList = new ArrayList<>();

    Shape shape;

    public List<Shape> getShapeList() {
        return shapeList;
    }

    public void addShape(List<Point2D> point2DList){
        shape = new Shape();
        shape.addLine(point2DList);
        shapeList.add(shape);
    }

    public void addPolygon(List<Point2D> point2DList) {

        List<Double> listDoubleX = new ArrayList<>();
        List<Double> listDoubleY = new ArrayList<>();

        point2DList.stream().forEach(x -> {
            listDoubleX.add(x.getX());
            listDoubleY.add(x.getY());
        });

        double[] array = new double[listDoubleX.size() + listDoubleY.size()];
        Iterator<Double> iteratorX = listDoubleX.iterator();
        Iterator<Double> iteratorY = listDoubleY.iterator();

        for (int i = 0; i < array.length; i++) {
            Stream<Double> streamX = listDoubleX.stream();
            if (i % 2 == 0) {
                array[i] = iteratorX.next();
            } else {
                array[i] = iteratorY.next();
            }
        }
        Polygon polygon = new Polygon(array);
        polygon.setFill(Color.WHITE);
//        polygon.set
        polygonList.add(polygon);

    }

    public List<Polygon> getPolygonList() {
        return polygonList;
    }
    public void clearShapeList(){
        shapeList.clear();
    }
}
