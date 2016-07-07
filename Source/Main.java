import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Main {
	
	static MainFrame a;
	static FullscreenFrame b;
	
	public static void main(String[] args) throws IOException {
		
		a = new MainFrame();
		
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				a.setTitle("Image Gallery Viewer");
//				Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//				int width = (int)screenSize.getWidth();
//				int height = (int)screenSize.getHeight();
//				a.setLocation(100, 100);
//				a.setPreferredSize(new Dimension(width-200, height-200));
//				a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//				a.getContentPane().setBackground(Color.BLACK);
//				try {
//					a.CreateGUI();
//				} catch (IOException e) {}
//				a.pack();
//				a.setExtendedState(a.getExtendedState()|JFrame.MAXIMIZED_BOTH );
//				a.setVisible(true);
//			}
//		});
		
//		a.addKeyListener(new KeyListener() {
//			public void keyTyped(KeyEvent arg0) {}
//			public void keyReleased(KeyEvent arg0) {}
//			public void keyPressed(KeyEvent e) {
//				if(e.getKeyCode() == KeyEvent.VK_RIGHT){
//					sg.elementAt(Gallery.currentAlbum).showNext(shuf);
//				}
//				else if(e.getKeyCode() == KeyEvent.VK_LEFT){
//					sg.elementAt(Gallery.currentAlbum).showPre(shuf);
//				}
//			}
//		});
		
	}
	
	public static void showImage(File f) {
		try {
			if(a.isVisible()){
				Image gkl = ImageIO.read(f);
				double multiply;
				int width = gkl.getWidth(null);
				int height = gkl.getHeight(null);
				if(ControlPanel.fit){
					if(width>height)
						multiply = (double)(a.pic.getSize().width)/(double)(width);
					else
						multiply = (double)(a.pic.getSize().height)/(double)(height);
				}
				else
					multiply = Integer.parseInt(a.sp.zoomTF.getText())/100.0;
				a.pic.setIcon(new ImageIcon(gkl.getScaledInstance((int)(width*(multiply)), (int)(height*(multiply)), Image.SCALE_DEFAULT)));
			}
			else
				b.pic.setIcon(new ImageIcon(ImageIO.read(f)));
		} catch (IOException e) {}
	}
	
}
