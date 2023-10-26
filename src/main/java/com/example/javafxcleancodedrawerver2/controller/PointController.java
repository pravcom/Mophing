package com.example.javafxcleancodedrawerver2.controller;

import com.example.javafxcleancodedrawerver2.model.PointModel;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;

public class PointController {

    PointModel pointModel;

    public PointController(PointModel pointModel) {
        this.pointModel = pointModel;
    }

    public void mouseClicked(MouseEvent e ){
        pointModel.addToPointList(new Point2D(e.getX(),e.getY()));
    }
}
