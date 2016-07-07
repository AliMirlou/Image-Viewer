import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SettingPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	JLabel s0 = new JLabel(" Settings:");
	
	JLabel s1 = new JLabel(" Speed:");
	JTextField speedTF = new JTextField();
	JLabel s2 = new JLabel(" seconds ");
	
	JLabel z1 = new JLabel(" Zoom:");
	JTextField zoomTF = new JTextField();
	JLabel z2 = new JLabel(" % ");
	
	JButton apply = new JButton("Apply");

	public SettingPanel() {
		
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 15;
		
		gbc.gridx = 0;
	    gbc.gridy = 0;
		gbc.weightx = 0.5;
	    s0.setFont(MainFrame.font);
	    add(s0, gbc);
		
		gbc.gridx = 0;
	    gbc.gridy = 1;
		s1.setFont(MainFrame.font);
		add(s1, gbc);
		
		gbc.gridx = 1;
		speedTF.setFont(MainFrame.font);
		speedTF.setPreferredSize(new Dimension(100, 40));
		speedTF.setEditable(false);
		add(speedTF, gbc);
		
		gbc.gridx = 2;
		s2.setFont(MainFrame.font);
		add(s2, gbc);
		
		gbc.gridx = 0;
	    gbc.gridy = 2;
	    z1.setFont(MainFrame.font);
	    add(z1, gbc);
	    
	    gbc.gridx = 1;
	    zoomTF.setFont(MainFrame.font);
	    zoomTF.setPreferredSize(new Dimension(100, 40));
		zoomTF.setEditable(false);
		add(zoomTF, gbc);
		
		gbc.gridx = 2;
		z2.setFont(MainFrame.font);
	    add(z2, gbc);
		
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.PAGE_END;
		gbc.gridx = 0;
		gbc.gridwidth = 3;
		gbc.gridy = 3;
		apply.setFont(MainFrame.font);
		apply.setEnabled(false);
		add(apply, gbc);
		
		speedTF.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					MainFrame.sg.elementAt(Gallery.currentAlbum).speed = Integer.parseInt(speedTF.getText());
					Main.showImage(MainFrame.sg.elementAt(Gallery.currentAlbum).allFiles[MainFrame.sg.elementAt(Gallery.currentAlbum).currentImageNumber]);
				}
			}
		});
		
		zoomTF.addKeyListener(new KeyListener() {
			public void keyTyped(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {}
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					MainFrame.sg.elementAt(Gallery.currentAlbum).speed = Integer.parseInt(speedTF.getText());
					Main.showImage(MainFrame.sg.elementAt(Gallery.currentAlbum).allFiles[MainFrame.sg.elementAt(Gallery.currentAlbum).currentImageNumber]);
				}
			}
		});
		
		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.sg.elementAt(Gallery.currentAlbum).speed = Integer.parseInt(speedTF.getText());
				Main.showImage(MainFrame.sg.elementAt(Gallery.currentAlbum).allFiles[MainFrame.sg.elementAt(Gallery.currentAlbum).currentImageNumber]);
			}
		});
		
	}

}
