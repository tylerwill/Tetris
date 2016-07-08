package com.tyler.tetris.ui.swing.widget;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import com.tyler.tetris.model.Block.ColoredSquare;
import com.tyler.tetris.ui.swing.GridPainter;

/**
 * Displays a progress bar using JPanels
 * @author Tyler
 */
public abstract class ProgressBar extends GridPainter {
	
	private Color barColor;
	private int percentagePerPanel;
	
	protected ProgressBar(int cols, Color barColor) {
		super(1, cols);
		this.barColor = barColor;
		this.percentagePerPanel = (int)(100.0 / cols);
	}
	
	public abstract double getCurrentPercentage();
	
	@Override
	public List<ColoredSquare> getCurrentColors() {
		
		// Keep everything in terms of whole numbers - easier
		int percentageComplete = (int)(getCurrentPercentage() * 100);
		
		// Fill in first panel if it's above zero but below percentage per panel value
		if (percentageComplete > 0 && percentageComplete < percentagePerPanel) {
			percentageComplete = percentagePerPanel;
		}
		
		List<ColoredSquare> squares = new ArrayList<>();
		for (int panel = 1; panel <= panelGrid[0].length ; panel++) {
			Color squareColor = (panel * percentagePerPanel) <= percentageComplete ? barColor : null;
			ColoredSquare square = new ColoredSquare(squareColor, 0, panel - 1);
			squares.add(square);
		}
		
		return squares;
	}
	
}
