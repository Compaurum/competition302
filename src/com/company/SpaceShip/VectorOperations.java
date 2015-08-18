package com.company.SpaceShip;

/**
 * Created by compaurum on 25.05.2015.
 */
class VectorOperations{
    static VectorXY sumVectorsXY(VectorXY v1, VectorXY v2){
        return v1.plusVector(v2);
    }

    static VectorXY sumVectorsXY(VectorXY v1, VectorXY v2, VectorXY v3){
        return v1.plusVector(v2).plusVector(v3);
    }

    static VectorXY sumVectorsXY(VectorXY v1, VectorXY v2, VectorXY v3, VectorXY v4){
        return v1.plusVector(v2).plusVector(v3).plusVector(v4);
    }

    public static double degreesInRadians(int angle){
        return (double)angle/180*Math.PI;
    }

    public static double radiansInDegrees(double angle){
        return (double)angle*180/Math.PI;
    }
    public static double standartFormRadians(double angle){
        //return (int)(((angle < 0) ? angle+2*Math.PI : angle)*1000000)/1000000d;
        return angle;
    }
}
