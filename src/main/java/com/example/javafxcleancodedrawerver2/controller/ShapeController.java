package com.example.javafxcleancodedrawerver2.controller;

import com.example.javafxcleancodedrawerver2.model.ShapeModel;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyEvent;

import java.util.List;

public class ShapeController {
    ShapeModel shapeModel;

    public ShapeController(ShapeModel shapeModel) {
        this.shapeModel = shapeModel;
    }
    public void setOnEnterPressed(List<Point2D> point2DList){
//        shapeModel.addPolygon(point2DList);
        shapeModel.addShape(point2DList);
    }
}
