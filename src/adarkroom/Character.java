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

    void paint(Graphics graphics) {
        Point topLeft = mapDrawData.getCellSystemCoordinate(getLocation());
        graphics.setColor(Color.WHITE);
//        graphics.fillRect(topLeft.x, topLeft.y, mapDrawData.getCellWidth(), mapDrawData.getCellHeight());
//        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font("Courier New", Font.PLAIN, 10));
        graphics.drawString("@", topLeft.x, topLeft.y + 3*mapDrawData.getCellHeight()/4);
        graphics.drawString("Difficulty " + getDifficulty(), 20, 20);
    }

    private Point STARTING_POINT = new Point(50, 50);
    private Point location = STARTING_POINT;
    private MapDrawDataIntf mapDrawData;
    private int difficulty;
    private ArrayList<Point> revealedLocations;
    private int STARTING_SCANNED_AREA = 2;
    private int scannedArea = STARTING_SCANNED_AREA;

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

    void drawScanned(Graphics graphics) {
        for (Point revealedLocation : getSafeRevealedLocations()) {
            Point topLeft = mapDrawData.getCellSystemCoordinate(revealedLocation);
            graphics.setColor(new Color(255, 0, 0, 50));
            graphics.fillRect(topLeft.x, topLeft.y, mapDrawData.getCellWidth(), mapDrawData.getCellHeight());
        }
    }

    public ArrayList<Point> getScannedLocations() {
        revealedLocations = new ArrayList<>();
        for (int i = -scannedArea; i < scannedArea + 1; i++) {
            for (int j = -(scannedArea - Math.abs(i)); j < (scannedArea - Math.abs(i) + 1); j++) {
		revealedLocations.add(new Point(getLocation().x + j, getLocation().y + i));
            }
        }
        return revealedLocations;
    }

    /**
     * @param revealedLocations the revealedLocations to set
     */
    public void setRevealedLocations(ArrayList<Point> revealedLocations) {
        this.revealedLocations = revealedLocations;
    }

    /**
     * @return the safeRevealedLocations
     */
    public ArrayList<Point> getSafeRevealedLocations() {
        ArrayList<Point> safeRevealedLocations = new ArrayList<>();
        for (Point score : getScannedLocations()) {
            safeRevealedLocations.add(score);
        }
        return safeRevealedLocations;
    }

    /**
     * @return the scannedArea
     */
    public int getScannedArea() {
        return scannedArea;
    }

    /**
     * @param scannedArea the scannedArea to set
     */
    public void setScannedArea(int scannedArea) {
        this.scannedArea = scannedArea;
    }

}
