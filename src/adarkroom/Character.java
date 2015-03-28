package adarkroom;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Benjamin
 */
public class Character {

    //<editor-fold defaultstate="collapsed" desc="paintCharacter">
    void paint(Graphics graphics) {
        Point topLeft = mapDrawData.getCellSystemCoordinate(getLocation());
        graphics.setColor(Color.WHITE);
//        graphics.fillRect(topLeft.x, topLeft.y, mapDrawData.getCellWidth(), mapDrawData.getCellHeight());
//        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Courier New", Font.PLAIN, 10));
        graphics.drawString("@", topLeft.x, topLeft.y + 3*mapDrawData.getCellHeight()/4);
        graphics.setColor(Color.BLACK);
        graphics.drawString("Difficulty " + getDifficulty(), 20, 20);
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Point STARTING_POINT = new Point(50, 50);
    private Point location = STARTING_POINT;
    private MapDrawDataIntf mapDrawData;
    private int difficulty;
    private ArrayList<Point> revealedLocations;
    private int STARTING_SCANNED_RADIUS = 3;
    private int scanRadius = STARTING_SCANNED_RADIUS;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters/Getters">
    /**
     * @return the location
     */
    public Point getLocation() {
        return location;
    }
    
    /**
     * @param location the location to set
     */
    public void setLocation(Point location) {
        this.location = location;
    }
    
    /**
     * @return the mapDrawData
     */
    public MapDrawDataIntf getMapDrawData() {
        return mapDrawData;
    }
    
    /**
     * @param mapDrawData the mapDrawData to set
     */
    public void setMapDrawData(MapDrawDataIntf mapDrawData) {
        this.mapDrawData = mapDrawData;
    }
    
    /**
     * @return the scannedArea
     */
    public int getScannedArea() {
        return scanRadius;
    }
    
    /**
     * @param scannedArea the scannedArea to set
     */
    public void setScannedArea(int scannedArea) {
        this.scanRadius = scannedArea;
    }
    
    /**
     * @return the Difficulty
     */
    public int getDifficulty() {
        // Checks for difficulty within a diamond with a radius of 10 in relation to STARTING_POINT
        return (int) (Math.floorDiv((Math.abs(getLocation().x - STARTING_POINT.x) + Math.abs(getLocation().y - STARTING_POINT.y)), 10) + 1);
    }

    /**
     * @param Difficulty the Difficulty to set
     */
    public void setDifficulty(int Difficulty) {
        this.difficulty = Difficulty;
    }
    
    /**
     * @param revealedLocations the revealedLocations to set
     */
    public void setRevealedLocations(ArrayList<Point> revealedLocations) {
        this.revealedLocations = revealedLocations;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Other Methods">
    void drawScanned(Graphics graphics) {
        //Draw the area the character can see
        for (Point revealedLocation : getSafeScannedLocation()) {
            Point topLeft = mapDrawData.getCellSystemCoordinate(revealedLocation);
            graphics.setColor(new Color(255, 0, 0, 50));
            graphics.fillRect(topLeft.x, topLeft.y, mapDrawData.getCellWidth(), mapDrawData.getCellHeight());
        }
    }
    
    public ArrayList<Point> getScannedLocations() {
        //Neat awesome code that draws a diamond with the radius of whatever scanRadius is
        revealedLocations = new ArrayList<>();
        for (int i = -scanRadius; i < scanRadius + 1; i++) {
            for (int j = -(scanRadius - Math.abs(i)); j < (scanRadius - Math.abs(i) + 1); j++) {
                revealedLocations.add(new Point(getLocation().x + j, getLocation().y + i));
            }
        }
        return revealedLocations;
    }
    
    /**
     * @return the safeRevealedLocations
     */
    public ArrayList<Point> getSafeScannedLocation() {
        ArrayList<Point> safeRevealedLocations = new ArrayList<>();
        for (Point score : getScannedLocations()) {
            safeRevealedLocations.add(score);
        }
        return safeRevealedLocations;
    }
//</editor-fold>

}
