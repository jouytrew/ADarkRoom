/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package adarkroom;

import environment.Environment;
import grid.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 *
 * @author Benjamin
 */
class ADarkRoomEnvironment extends Environment implements MapDrawDataIntf {

    public ADarkRoomEnvironment() {
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getRows(); j++) {
                mapPoints.add(new Point(j, i));
            }
        }
        updateScannedArea();
    }

    //<editor-fold defaultstate="collapsed" desc="AbstractMethods">
    //<editor-fold defaultstate="collapsed" desc="initializeEnvironment">
    @Override
    public void initializeEnvironment() {
        grid = new Grid(101, 101, 15, 15, new Point(25, 25), Color.BLACK);
        human_bean = new Character();
        human_bean.setMapDrawData(this);
        mapPoints = new ArrayList<>();
        
        MapPoint[][] mapPointsBeta = new MapPoint[grid.getColumns()][grid.getRows()];
        
        setObjects(new ArrayList<>());
        visiblePoints = new ArrayList<>();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="timerTaskHandler">
    @Override
    public void timerTaskHandler() {
        
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="keyPressedHandler">
    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A) {
            human_bean.setLocation(new Point(human_bean.getLocation().x - 1, human_bean.getLocation().y));
            updateScannedArea();
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            human_bean.setLocation(new Point(human_bean.getLocation().x, human_bean.getLocation().y - 1));
            updateScannedArea();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            human_bean.setLocation(new Point(human_bean.getLocation().x + 1, human_bean.getLocation().y));
            updateScannedArea();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            human_bean.setLocation(new Point(human_bean.getLocation().x, human_bean.getLocation().y + 1));
            updateScannedArea();
        } else if (e.getKeyCode() == KeyEvent.VK_B) {
            objects.add(new Object(ObjectType.T_PLANET, new Point(2, 2), this));
            objects.add(new Object(ObjectType.G_GIANT, new Point(5, 4), this));
        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="keyReleasedHandler">
    @Override
    public void keyReleasedHandler(KeyEvent e) {
//        if (e.getKeyCode() == KeyEvent.VK_A) {
//            human_bean.setLocation(new Point(human_bean.getLocation().x - 1, human_bean.getLocation().y));
//            updateScannedArea();
//        } else if (e.getKeyCode() == KeyEvent.VK_W) {
//            human_bean.setLocation(new Point(human_bean.getLocation().x, human_bean.getLocation().y - 1));
//            updateScannedArea();
//        } else if (e.getKeyCode() == KeyEvent.VK_D) {
//            human_bean.setLocation(new Point(human_bean.getLocation().x + 1, human_bean.getLocation().y));
//            updateScannedArea();
//        } else if (e.getKeyCode() == KeyEvent.VK_S) {
//            human_bean.setLocation(new Point(human_bean.getLocation().x, human_bean.getLocation().y + 1));
//            updateScannedArea();
//        } else if (e.getKeyCode() == KeyEvent.VK_B) {
//            objects.add(new Object(ObjectType.T_PLANET, new Point(2, 2), this));
//            objects.add(new Object(ObjectType.G_GIANT, new Point(5, 4), this));
//        }
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="environmentMouseClicked">
    @Override
    public void environmentMouseClicked(MouseEvent e) {
        
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="paintEnvironment">
    @Override
    public void paintEnvironment(Graphics graphics) {
        //<editor-fold defaultstate="collapsed" desc="Antialias">
        Graphics2D g2d = (Graphics2D) graphics;
        g2d.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//</editor-fold>
        for (Point mapPoint : mapPoints) {
            Point topLeft = grid.getCellSystemCoordinate(mapPoint);
            graphics.setColor(Color.GRAY);
            graphics.fillRect(topLeft.x, topLeft.y, grid.getCellWidth(), grid.getCellHeight());
        }
        for (Point visiblePoint : getVisiblePoints()) {
            Point topLeft = grid.getCellSystemCoordinate(visiblePoint);
            graphics.setColor(Color.BLACK);
            graphics.fillRect(topLeft.x, topLeft.y, grid.getCellWidth(), grid.getCellHeight());
            
        }
        for (Object object : getObjects()) {
            if (visiblePoints.contains(object.getLocation())) {
                object.paintObject(graphics);
            }
        }
        if (human_bean != null/** && human_bean.getScannedLocations() != null*/) {
            human_bean.paint(graphics);
//            human_bean.drawScanned(graphics);
        }
        
    }
//</editor-fold>
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Fields">
    private Grid grid;
    private ArrayList<Point> mapPoints = new ArrayList<>();
    private ArrayList<Point> visiblePoints = new ArrayList<>();
    private ArrayList<Object> objects = new ArrayList<>();
    private Character human_bean;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="MapDrawDataIntf">
    /**
     * @return the gridLocations
     */
    @Override
    public ArrayList<Point> getGridLocations() {
        return mapPoints;
    }

    @Override
    public int getCellHeight() {
        return grid.getCellHeight();
    }

    @Override
    public int getCellWidth() {
        return grid.getCellWidth();
    }

    @Override
    public Point getCellSystemCoordinate(Point cellLocation) {
        return grid.getCellSystemCoordinate(cellLocation);
    }

    @Override
    public int getColumns() {
        return grid.getColumns();
    }

    @Override
    public int getRows() {
        return grid.getRows();
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Setters/Getters">
    /**
     * @return the gridPoints
     */
    public ArrayList<Object> getGridPoints() {
        return getObjects();
    }
    
    /**
     * @param gridLocations the gridLocations to set
     */
    public void setGridLocations(ArrayList<Point> gridLocations) {
        this.mapPoints = gridLocations;
    }
    
    /**
     * @param gridPoints the gridPoints to set
     */
    public void setGridPoints(ArrayList<Object> gridPoints) {
        this.setObjects(gridPoints);
    }
    
    /**
     * @return the visiblePoints
     */
    public ArrayList<Point> getVisiblePoints() {
        return visiblePoints;
    }
    
    /**
     * @param visiblePoints the visiblePoints to set
     */
    public void setVisiblePoints(ArrayList<Point> visiblePoints) {
        this.visiblePoints = visiblePoints;
    }
    
    /**
     * @return the objects
     */
    public ArrayList<Object> getObjects() {
        return objects;
    }
    
    /**
     * @param objects the objects to set
     */
    public void setObjects(ArrayList<Object> objects) {
        this.objects = objects;
    }
//</editor-fold>

    public void updateScannedArea() {
        for (Point revealedLocation : human_bean.getScannedLocations()) {
            if (!getVisiblePoints().contains(revealedLocation) && mapPoints.contains(revealedLocation)){
                getVisiblePoints().add(revealedLocation);
            }
        }
    }

}
