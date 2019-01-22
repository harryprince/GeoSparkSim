package com.zishanfu.vistrips.sim.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Stroke;
import java.util.List;


public class Segment {
	private final static int DEFAULT_LANE = 1;
	private int laneWidth = 5; //pixel
	
	private final Point pointA, pointB;
	private final Color color;
	private Stroke stroke;
	private final int lanes;
//	private final List<Lane> landList;
	
	/**
	 * Creates a new segment.
	 * 
	 * @param pointA the first point
	 * @param pointB the second point
	 * @param color the color of the segment
	 */
	public Segment(Point pointA, Point pointB, Color color) {
		this(pointA, pointB, color, DEFAULT_LANE);
	}
	
	public Segment(Point pointA, Point pointB, Color color, int lanes) {
		this.pointA = pointA;
		this.pointB = pointB;
		this.color = color;
		this.lanes = lanes;
	}

	/**
	 * Returns the first point of the segment ("A")
	 * 
	 * @return the first point of the segment
	 */
	public Point getPointA() {
		return pointA;
	}

	/**
	 * Returns the second point of the segment ("B")
	 * 
	 * @return the second point of the segment
	 */
	public Point getPointB() {
		return pointB;
	}

	/**
	 * Returns the color of the segment
	 * 
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	
	public void setStroke(int width) {
		int w = lanes * width;
		stroke = new BasicStroke(w);
	}

	public Stroke getStroke() {
		return stroke;
	}

	
	
		
}
