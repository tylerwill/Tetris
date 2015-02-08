package ui.components;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import ui.GameFrame;

/**
 *  Standard button style for all frames
 * @author Tyler
 */
public class TetrisButton extends JButton {
	
	private final static Dimension BUTTON_DIMENSIONS = new Dimension(100,30);
	
	// Causes the mouse to change to a hand icon when mousing over the button
	private MouseListener handCursorListener = new MouseAdapter() {
		
		public void mouseEntered(MouseEvent e) {
			if (isEnabled()) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
				setBackground(Color.LIGHT_GRAY);
			}
		}
		
		public void mouseExited(MouseEvent e) {
			setBackground(null);
		}
		
	};
	
	public TetrisButton(String buttonText) {
		setText(buttonText);
		setPreferredSize(BUTTON_DIMENSIONS);
		setBorder(GameFrame.ETCHED_BORDER);
		setFocusable(false);
		addMouseListener(handCursorListener);
	}
	
}
