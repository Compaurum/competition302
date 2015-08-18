package com.company.SpaceShip;

import java.util.Date;

/**
 * Created by compaurum on 22.05.2015.
 */
public class math {
    public static void main (String [] args){

        int powerEngine = 0;
        int powerEngineAngle = 0;
        int HS = 10;
        int VS = -10;

        Date st = new Date();
        System.out.println(new CalculateDirection(powerEngine, powerEngineAngle, HS, VS).getVectorXY());
        Date end = new Date();

        System.out.println(end.getTime() - st.getTime());
    }
}

