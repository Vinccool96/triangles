package com.relax.triangles.models;

import com.relax.triangles.utils.Angles;

public class Triangle {
    private double x;
    private double y;
    private double orientation;

    public Triangle() {
        this.x = 0;
        this.y = 0;
        this.orientation = 0;
    }

    public Triangle(double x, double y) {
        this.x = x;
        this.y = y;
        this.orientation = 0;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getOrientation() {
        return orientation;
    }

    public void setOrientation(double angle) {
        this.orientation = Angles.angle(angle);
    }
}
