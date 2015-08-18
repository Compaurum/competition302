package com.company.SpaceShip;

import static com.company.SpaceShip.VectorOperations.*;

/**
 * Created by compaurum on 25.05.2015.
 */
public class CalculateDirection {


    private double powerEngine = 10;
    private double powerEngineAngle = degreesInRadians(45);
    private double HS = 10;
    private double HSAngle = degreesInRadians(270);
    private double VS = -10;
    private double VSAngle = degreesInRadians(0);

    final double powerGravitation = 3.711;
    final double powerGravitationAngle = degreesInRadians(180);

    private Vector vector ;
    private VectorXY vectorXY;

    CalculateDirection(int powerEngine, int powerEngineAngle, int HS, int VS ) {
        this.powerEngine = powerEngine;
        this.powerEngineAngle = degreesInRadians(powerEngineAngle);
        this.HS = HS;
        this.VS = VS;

        VectorXY engine = new Vector(this.powerEngine, this.powerEngineAngle).convertToVectorXY();
        VectorXY horizontalSpeed = new Vector(this.HS, this.HSAngle).convertToVectorXY();
        VectorXY verticalSpeed = new Vector(this.VS, this.VSAngle).convertToVectorXY();
        VectorXY gravitation = new Vector(this.powerGravitation, this.powerGravitationAngle).convertToVectorXY();

        this.vectorXY = (sumVectorsXY(engine, horizontalSpeed, verticalSpeed, gravitation));
    }


    public VectorXY getVectorXY() {
        return vectorXY;
    }
}
