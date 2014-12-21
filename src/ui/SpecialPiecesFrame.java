package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import model.PieceFactory;

public class SpecialPiecesFrame extends JFrame {
	
	Map<Integer, PieceSelectorButton> pieceIDToButtonMap = new HashMap<>();
	static Map<Integer, Boolean> pieceIDToSelectedMap = initSelectionMap(); // Cached settings for which piece IDs are selected
	
	private NextPiecePanel cornerBlockDisplay = new NextPiecePanel("Corner Block", PieceFactory.order(PieceFactory.CORNER_BLOCK_ID));
	private NextPiecePanel twinPillarsBlockDisplay = new NextPiecePanel("Twin-pillars Block", PieceFactory.order(PieceFactory.TWIN_PILLARS_BLOCK_ID));
	
	private JButton jbtSave = new JButton("Save and Return");
	
	SpecialPiecesFrame() {
		
		// All all selector buttons according to their cached selected settings in the
		// pieceIDToSelectedMap
		pieceIDToButtonMap.put(
				PieceFactory.CORNER_BLOCK_ID,
				new PieceSelectorButton(pieceIDToSelectedMap.get(PieceFactory.CORNER_BLOCK_ID)));
		
		pieceIDToButtonMap.put(
				PieceFactory.TWIN_PILLARS_BLOCK_ID,
				new PieceSelectorButton(pieceIDToSelectedMap.get(PieceFactory.TWIN_PILLARS_BLOCK_ID)));
		
		JPanel cornerBlockPanel = new JPanel(new BorderLayout());
		cornerBlockPanel.add(cornerBlockDisplay, BorderLayout.CENTER);
		cornerBlockPanel.add(pieceIDToButtonMap.get(PieceFactory.CORNER_BLOCK_ID), BorderLayout.SOUTH);
		
		JPanel twinPillarsPanel = new JPanel(new BorderLayout());
		twinPillarsPanel.add(twinPillarsBlockDisplay, BorderLayout.CENTER);
		twinPillarsPanel.add(pieceIDToButtonMap.get(PieceFactory.TWIN_PILLARS_BLOCK_ID), BorderLayout.SOUTH);
		
		JPanel piecePanels = new JPanel(new GridLayout(1,2));
		piecePanels.add(cornerBlockPanel);
		piecePanels.add(twinPillarsPanel);
		
		JPanel saveContainer = new JPanel();
		saveContainer.add(jbtSave);
		jbtSave.setFocusable(false);
		jbtSave.addActionListener(SaveListener);
		
		add(piecePanels, BorderLayout.CENTER);
		add(saveContainer, BorderLayout.SOUTH);
		
		setTitle("Special Pieces");
		setResizable(false);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	private ActionListener SaveListener = new ActionListener() {

		public void actionPerformed(ActionEvent e) {
			
			for (Integer id : PieceFactory.SPECIAL_BLOCK_IDS) {
				
				// Obtain the button representing this piece ID
				PieceSelectorButton b = pieceIDToButtonMap.get(id);
				
				// Update cached settings for this piece ID
				pieceIDToSelectedMap.put(id, b.isSelected());
				
				if (b.isSelected())
					PieceFactory.addPieceID(id);
				else
					PieceFactory.removePieceID(id);
				
			}
			
			dispose();
			
		}
		
	};
	
	private static Map<Integer, Boolean> initSelectionMap() {
		
		Map<Integer, Boolean> map = new HashMap<>();
		map.put(PieceFactory.CORNER_BLOCK_ID, false);
		map.put(PieceFactory.TWIN_PILLARS_BLOCK_ID, false);
		
		return map;
		
	}
	
	private static class PieceSelectorButton extends JButton {
		
		private PieceSelectorButton(boolean selected) {
			
			setSelected(selected);
			setFocusable(false);
			
			addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) { toggle(); }
			});
			
			setPreferredSize(new Dimension(getWidth(), 30));
			
		}
		
		public void toggle() {
			setSelected(!isSelected());
		}
		
		public void setSelected(boolean selected) {
			setBackground(selected ? Color.YELLOW : Color.LIGHT_GRAY);
		}
		
		public boolean isSelected() {
			return getBackground() == Color.YELLOW;
		}
		
	}
	
	
}
