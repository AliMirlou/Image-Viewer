import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class ListOfFiles extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	JPanel p;

	public ListOfFiles() throws IOException {
		
		setTitle("List Of Images");
		setIconImage(ImageIO.read(new File(ControlPanel.s+"/MainIcon.png")));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int width = (int)screenSize.getWidth();
		int height = (int)screenSize.getHeight();
		setPreferredSize(new Dimension(width/4, height/2));
		setLocation((3*width/8), height/4);
		getContentPane().setBackground(Color.BLACK);
				
		p = new JPanel();
		p.setLayout(new GridLayout(100, 0));
		for(int i=0; i<MainFrame.sg.elementAt(Gallery.currentAlbum).allFiles.length; i++){
			JLabel jl = new JLabel(MainFrame.sg.elementAt(Gallery.currentAlbum).allFiles[i].getPath());
			jl.setFont(MainFrame.font);
			p.add(jl);
        }
		
		JScrollPane sp = new JScrollPane(p);
		sp.setPreferredSize(new Dimension(width/4, height/2));
		add(sp, BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		
	}

}
