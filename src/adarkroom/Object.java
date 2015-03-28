/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adarkroom;

import images.ResourceTools;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;

/**
 *
 * @author Benjamin
 */
public class Object {

    public Object(ObjectType type, Point location, MapDrawDataIntf mapDrawData) {
        this.type = type; this.location = location; this.gridDrawData = mapDrawData;
    }

    public void paintObject(Graphics graphics) {
        Point topLeft = gridDrawData.getCellSystemCoordinate(getLocation());
        switch (type) {
            case T_PLANET:
                graphics.drawImage(Earth, topLeft.x, topLeft.y, null);
                break;
            case G_GIANT:
                graphics.drawImage(Saturn, topLeft.x, topLeft.y, null);
                break;
            default:
                break;
        }
    }

    private ObjectType type;
    private Point location;
    private MapDrawDataIntf gridDrawData;
    private Image Earth = ResourceTools.loadImageFromResource("resources/EarthHalfDark.gif");
    private Image Saturn = ResourceTools.loadImageFromResource("resources/RingedGas.gif");

    /**
     * @return the type
     */
    public ObjectType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ObjectType type) {
        this.type = type;
    }

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
        return gridDrawData;
    }

    /**
     * @param mapDrawData the mapDrawData to set
     */
    public void setMapDrawData(MapDrawDataIntf mapDrawData) {
        this.gridDrawData = mapDrawData;
    }

}
