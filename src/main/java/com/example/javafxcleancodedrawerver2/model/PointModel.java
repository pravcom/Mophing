package com.example.javafxcleancodedrawerver2.model;

import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class PointModel {
    List<Point2D> pointList = new ArrayList<>();

    public void setPointList(List<Point2D> pointList) {
        this.pointList = pointList;
    }

    public List<Point2D> getPointList() {
        return pointList;
    }
    public void clearPointList(){
        pointList.clear();
    }
    public void addToPointList(Point2D point2D){
        pointList.add(point2D);
    }
    public double getPointX(){
        double pointX;
        pointX = pointList.get(pointList.size() - 1).getX();
        return pointX;
    }

    public double getPointY(){
        double pointY;
        pointY = pointList.get(pointList.size() - 1).getY();
        return pointY;
    }

}
