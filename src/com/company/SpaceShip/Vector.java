package com.company.SpaceShip;

/**
 * Created by compaurum on 25.05.2015.
 */
class Vector{
    double size;
    double angleRadian;
    VectorXY vectorXY;
    public double getAngleRadian() {
        return angleRadian;
    }

    public double getAngleDegree() {
        return angleRadian/Math.PI * 180;
    }

    public Vector(double size, double angleRadian) {
        this.size = size;
        this.angleRadian = angleRadian;
    }

    public VectorXY convertToVectorXY(){
        vectorXY = new VectorXY(size*(Math.sin(Math.PI + angleRadian)), size*(Math.cos(angleRadian)));
        return vectorXY;
    }

    @Override
    public String toString() {
        return this.size + " " + this.getAngleDegree();
    }
}
