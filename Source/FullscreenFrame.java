import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class FullscreenFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	
	JLabel pic = new JLabel("", JLabel.CENTER);

	public FullscreenFrame() {
		
		CreateGUI();
		
		addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					Main.b.dispose();
					Main.a.setVisible(true);
					Main.a.cp.setVisible(true);
					Main.a.ap.setVisible(true);
					Main.a.sp.setVisible(true);
					if(!MainFrame.sg.isEmpty())
						Main.showImage(MainFrame.sg.elementAt(Gallery.currentAlbum).allFiles[MainFrame.sg.elementAt(Gallery.currentAlbum).currentImageNumber]);
				}
			}
		});
		
	}

	private void CreateGUI() {
		
		setUndecorated(true);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		device.setFullScreenWindow(this);
		setPreferredSize(new Dimension(width, height));
		getContentPane().setBackground(Color.BLACK);
		
		add(pic, BorderLayout.CENTER);
		
	}

}
