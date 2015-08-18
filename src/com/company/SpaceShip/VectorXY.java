package com.company.SpaceShip;

/**
 * Created by compaurum on 25.05.2015.
 */
class VectorXY {
    double x;
    double y;

    public VectorXY(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public VectorXY plusVector(VectorXY vector){
        return new VectorXY(this.x + vector.x, this.y + vector.y);
    }

    public VectorXY minusVector(VectorXY vector){
        return new VectorXY(this.x - vector.x, this.y - vector.y);
    }

    @Override
    public String toString() {
        return "X = " + this.x + "  Y = " + this.y;
    }
}
