package com.example.javafxcleancodedrawerver2.model;

import com.example.javafxcleancodedrawerver2.PointInShape;
import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Shape {
    List<Line> lineList = new ArrayList<>();
    boolean shapeIsSelected;

    Color defaultColor = Color.BLACK;
    double defaultWidth = 1;

    public List<Line> getLineList() {
        return lineList;
    }

    public boolean setShapeIsSelected(Point2D point) {

        boolean success = false;

        List<Line> lineList = this.getLineList();

        List<Point2D> point2DList1 = new ArrayList<>();

        for (int i = 0; i < lineList.size(); i++) {
            if (i % 2 == 0) {
                Line line = lineList.get(i);
                point2DList1.add(new Point2D(line.getStartX(), line.getStartY()));
                point2DList1.add(new Point2D(line.getEndX(), line.getEndY()));
            }
        }
        // удаляем последнюю точку потму что она содержит координаты первой точки
        if (point2DList1.size() > 0) point2DList1.remove(point2DList1.size() - 1);

        Point2D[] polygon = point2DList1.toArray(new Point2D[0]);


        if (shapeIsSelected == true) {
            if (PointInShape.isPointInPolygon(point, polygon)) {
                removeSelected();
                success = true;
            }else success = false;

        } else {
            shapeIsSelected = PointInShape.isPointInPolygon(point, polygon);
            if (shapeIsSelected == true) {
                for (Line line : lineList) {
                    line.setFill(Color.BLUE);
                    line.setStrokeWidth(2);
                }
                success = true;
            }else success = false;
        }
return success;
    }

    public boolean isShapeIsSelected() {
        return shapeIsSelected;
    }

    public void removeSelected() {
        shapeIsSelected = false;
        lineList.forEach(a -> {
            a.setStrokeWidth(defaultWidth);
            a.setFill(defaultColor);
        });
    }

    public void addLine(List<Point2D> point2DList) {
        if (point2DList.size() > 1) {

            for (int i = 0; i < point2DList.size() - 1; i++) {
                Point2D startPoint = point2DList.get(i);
                Point2D endPoint = point2DList.get(i + 1);
                Line line = new Line(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());

                lineList.add(line);

            }
            //Последняя линия которая соединяет крайнюю и первую точку
            Point2D startPoint = point2DList.get(point2DList.size() - 1);
            Point2D endPoint = point2DList.get(0);
            Line line = new Line(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());

            line.setFill(defaultColor);
            line.setStrokeWidth(defaultWidth);

            lineList.add(line);
        }


    }
}
