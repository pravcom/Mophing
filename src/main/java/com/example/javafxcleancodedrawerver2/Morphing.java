package com.example.javafxcleancodedrawerver2;

import com.example.javafxcleancodedrawerver2.model.Shape;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.shape.Line;

import java.util.ArrayList;
import java.util.List;

public class Morphing {
    Shape shapeOne;
    Shape shapeTwo;
    List<Point2D> pointListOldShape = new ArrayList<>();
    List<Point2D> pointListNewShape = new ArrayList<>();
    Slider slider = new Slider(0, 100, 10);
    Label sliderLabel = new Label("Slider value: ");
    List<Node> nodeList = new ArrayList<>();

    private List<Line> oldShape;
    int countLines;
    public Morphing() {

        fillBaseNodes();
    }
    public void setShapes(List<Shape> shapeList){
        shapeOne = null;
        shapeTwo = null;
        clear();

        shapeList.forEach(s -> {
            if (s.isShapeIsSelected()) {
                if (shapeOne == null) shapeOne = s;
                else shapeTwo = s;
            }
        });
    }
    public List<Node> getNodeList() {
        return nodeList;
    }

    public Slider getSlider() {
        return slider;
    }

    public Label getSliderLabel() {
        return sliderLabel;
    }

    private void fillBaseNodes(){
        nodeList.add(slider);
        nodeList.add(sliderLabel);
    }
    public static boolean shapeSelectedIsTwo(List<Shape> shapeList) {
        boolean result = true;
        int count = 0;
        for (Shape shape : shapeList) {
            if (shape.isShapeIsSelected()){
                count++;}
            if (count > 2){
                result = false;
                break;}
        }
        if (count != 2) result = false;
        return result;
    }
    private void setPointsOfMorphShape(){
        for(int i =0;i<countLines;){
            if(i<shapeOne.getLineList().size()){
                double startX = shapeOne.getLineList().get(i).getStartX();
                double startY = shapeOne.getLineList().get(i).getStartY();

                pointListNewShape.add(new Point2D(startX,startY));
                i++;
            }else{
                //Находим новые точки на каждой линии пока не достигнем макс кол-во точек
                while (i<countLines){
                    int d =2;
                    for(int y=0;y<shapeOne.getLineList().size();y++){
                        double startX = shapeOne.getLineList().get(y).getStartX();
                        double startY = shapeOne.getLineList().get(y).getStartY();
                        double endX = shapeOne.getLineList().get(y).getEndX();
                        double endY = shapeOne.getLineList().get(y).getEndY();

                        double midX = (startX+endX)/d;
                        double midY = (startY+endY)/d;

                        pointListNewShape.add(new Point2D(midX,midY));

                        i++;
                        if(i>=countLines) break;
                    }
                    //Прибавляем делитель для получения новой точки на отрезке
                    d++;
                }
            }
        }
        System.out.println(pointListNewShape);
    }
    private int getMaxCountOfLines(){
     if(shapeOne.getLineList().size() > shapeTwo.getLineList().size()) return shapeOne.getLineList().size();
     else return shapeTwo.getLineList().size();
    }
    private void setPointsOldShape(){

        for(int i=0; i<countLines;){
            for(Line line: shapeTwo.getLineList()){
                pointListOldShape.add(new Point2D(line.getStartX(),line.getStartY()));
                i++;
                if(i==countLines) break;
            }
        }
    }
    public List<Point2D> morph(int val){

        System.out.println("Кол-во линий--> "+countLines);
        clear();
        countLines = getMaxCountOfLines();
        setPointsOfMorphShape();
        setPointsOldShape();

        List<Point2D> resultList = new ArrayList<>();

        for (int i =0;i<countLines;i++){
            double newShapeX = pointListNewShape.get(i).getX();
            double newShapeY = pointListNewShape.get(i).getY();

            double oldShapeX = pointListOldShape.get(i).getX();
            double oldShapeY = pointListOldShape.get(i).getY();

            double resultX;
            double resultY;
            //Расстояние между точками
            double d = Math.sqrt(Math.pow((newShapeX - oldShapeX),2) + Math.pow((oldShapeY - newShapeY),2) );

            if (oldShapeX - newShapeX > 0){
                resultX = newShapeX + (d * (double)val/100 * (Math.abs(oldShapeX - newShapeX))  / d);
            }else {
                resultX = newShapeX - (d * (double)val/100 * (Math.abs(oldShapeX - newShapeX) ) / d);
            }

            if (oldShapeY - newShapeY > 0){
                resultY = newShapeY + (d * (double)val/100 * (Math.abs(oldShapeY - newShapeY) ) / d);
            }else {
                resultY = newShapeY - (d * (double)val/100 * (Math.abs(oldShapeY - newShapeY) ) / d);
            }
            resultList.add(new Point2D(resultX,resultY));
        }

        return resultList;
    }

    public void setOldShape(List<Line> oldShape) {
        this.oldShape = oldShape;
    }

    public List<Line> getOldShape() {
        return oldShape;
    }

    public void clear(){
        pointListOldShape.clear();
        pointListNewShape.clear();
        countLines = 0;
    }
}
