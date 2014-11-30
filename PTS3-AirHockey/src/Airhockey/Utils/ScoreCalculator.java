package Airhockey.Utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Roel
 */
public class ScoreCalculator {
    
    /**
     *  r1 is the most recent result and r5 is the lat
     * if there is no score available enter -1;
     * @param r1 most recent
     * @param r2
     * @param r3
     * @param r4
     * @param r5 earliest of the five
     * @return the rating
     */
    public static int calculateRating(int r1, int r2, int r3, int r4, int r5) {
        return r5 != -1 ? (int) Math.floor( (5*r1 + 4*r4 + 3*r3 + 2*r2 + r1)/15) : 15;
    }
}
