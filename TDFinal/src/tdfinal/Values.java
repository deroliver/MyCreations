/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdfinal;

/**
 * A class to keep track of values 
 * @author Derik
 */
public class Values {
    public static int trackID = 0; // The ID of a part of the map that is not a track piece
    public static int groundTrack = 1; // The ID corresponding to a part of the map that is a track piece
       
    public static int groundField = 5; // The ID corresponding to a part of the map where a tower can be places
    
    public static int redLaserTower = 0; // The ID corresponding to the red laser tower
    public static int blueLaserTower = 1; // The ID corresponding to the blue laser tower
    public static int goldLaserTower = 2; // The ID corresponding to the gold laser tower
    public static int trash = 3; // The ID corresponding to the trash button
    
 
    public static int Ship = 8; // The ID corresponding to the ship on the map - The enemies' goal
    
    public static int[] deathReward = {10}; // The reward for killing an enemy
}
