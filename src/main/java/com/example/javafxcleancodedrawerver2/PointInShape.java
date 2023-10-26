package com.example.javafxcleancodedrawerver2;

import javafx.geometry.Point2D;

public class PointInShape {
    // Класс для представления точки
    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    // Проверяет, находится ли точка внутри многоугольника
    public static boolean isPointInPolygon(Point2D point, Point2D[] polygon) {
        int intersectCount = 0;

        for (int i = 0; i < polygon.length; i++) {
            int j = (i + 1) % polygon.length;

            // Проверяем пересечение луча с каждой стороной многоугольника
            if (isIntersect(point, polygon[i], polygon[j])) {
                intersectCount++;
            }
        }

        // Если количество пересечений нечётное, то точка внутри
        return intersectCount % 2 == 1;
    }

    // Проверяет пересечение луча с отрезком прямой
    public static boolean isIntersect(Point2D point, Point2D a, Point2D b) {
        // Проверяем, лежит ли точка на отрезке
        if (Math.min(a.getX(), b.getX()) <= point.getX() &&
                point.getX() <= Math.max(a.getX(), b.getX()) &&
                Math.min(a.getY(), b.getY()) <= point.getY() &&
                point.getY() <= Math.max(a.getY(), b.getY())) {
            return true;
        }
        double val1 = (b.getX() - a.getX()) * (point.getY() - a.getY());
        double val2 = (point.getX() - a.getX()) * (b.getY() - a.getY());
        if (val2 != 0 || val1 !=0)
            return false;
        else
            return val1 > val2;
        // Проверяем, пересекается ли отрезок с лучом
//        return (b.getX() - a.getX()) * (point.getY() - a.getY()) > (point.getX() - a.getX()) * (b.getY() - a.getY());
    }

}
