import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class ControlPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	static JButton List = new JButton();
	static JButton Previous = new JButton();
	static JButton Play = new JButton();
	static JButton Pause = new JButton();
	static JButton Next = new JButton();
	static JButton Repeat = new JButton();
	static JButton Shuffle = new JButton();
	static JButton Fit = new JButton();
	static JButton Actual = new JButton();
	
	static String s = null;
	
	static boolean play = false;
	static boolean shuf = false;
	static boolean fit = false;
	
	public ControlPanel() throws IOException {
		
		SearchForIcon();
		
		List.setIcon(new ImageIcon(ImageIO.read(new File(s+"/list1.png"))));
		List.setDisabledIcon(new ImageIcon(ImageIO.read(new File(s+"/list2.png"))));
		List.setPressedIcon(new ImageIcon(ImageIO.read(new File(s+"/list3.png"))));
		List.setBackground(new Color(255,255,255));
		List.setToolTipText("<html><font size=\"6\">List Of Images</font></html>");
		List.setEnabled(false);
		
		Play.setIcon(new ImageIcon(ImageIO.read(new File(s+"/play1.png"))));
		Play.setDisabledIcon(new ImageIcon(ImageIO.read(new File(s+"/play2.png"))));
		Play.setPressedIcon(new ImageIcon(ImageIO.read(new File(s+"/play3.png"))));
		Play.setBackground(new Color(255,255,255));
		Play.setToolTipText("<html><font size=\"6\">Play</font></html>");
		Play.setEnabled(false);
		
		Pause.setIcon(new ImageIcon(ImageIO.read(new File(s+"/pause1.png"))));
		Pause.setDisabledIcon(new ImageIcon(ImageIO.read(new File(s+"/pause2.png"))));
		Pause.setPressedIcon(new ImageIcon(ImageIO.read(new File(s+"/pause3.png"))));
		Pause.setBackground(new Color(255,255,255));
		Pause.setToolTipText("<html><font size=\"6\">Pause</font></html>");
		
		Previous.setIcon(new ImageIcon(ImageIO.read(new File(s+"/pre1.png"))));
		Previous.setDisabledIcon(new ImageIcon(ImageIO.read(new File(s+"/pre2.png"))));
		Previous.setPressedIcon(new ImageIcon(ImageIO.read(new File(s+"/pre3.png"))));
		Previous.setBackground(new Color(255,255,255));
		Previous.setToolTipText("<html><font size=\"6\">Previous</font></html>");
		Previous.setEnabled(false);
		
		Next.setIcon(new ImageIcon(ImageIO.read(new File(s+"/next1.png"))));
		Next.setDisabledIcon(new ImageIcon(ImageIO.read(new File(s+"/next2.png"))));
		Next.setPressedIcon(new ImageIcon(ImageIO.read(new File(s+"/next3.png"))));
		Next.setBackground(new Color(255,255,255));
		Next.setToolTipText("<html><font size=\"6\">Next</font></html>");
		Next.setEnabled(false);
		
		Repeat.setIcon(new ImageIcon(ImageIO.read(new File(s+"/repeat1.png"))));
		Repeat.setDisabledIcon(new ImageIcon(ImageIO.read(new File(s+"/repeat2.png"))));
		Repeat.setPressedIcon(new ImageIcon(ImageIO.read(new File(s+"/repeat3.png"))));
		Repeat.setBackground(new Color(255,255,255));
		Repeat.setToolTipText("<html><font size=\"6\">Repeat</font></html>");
		Repeat.setEnabled(false);
		
		Shuffle.setIcon(new ImageIcon(ImageIO.read(new File(s+"/shuffle1.png"))));
		Shuffle.setDisabledIcon(new ImageIcon(ImageIO.read(new File(s+"/shuffle2.png"))));
		Shuffle.setPressedIcon(new ImageIcon(ImageIO.read(new File(s+"/shuffle3.png"))));
		Shuffle.setBackground(new Color(255,255,255));
		Shuffle.setToolTipText("<html><font size=\"6\">Shuffle</font></html>");
		Shuffle.setEnabled(false);
		
		Fit.setIcon(new ImageIcon(ImageIO.read(new File(s+"/fit1.png"))));
		Fit.setDisabledIcon(new ImageIcon(ImageIO.read(new File(s+"/fit2.png"))));
		Fit.setPressedIcon(new ImageIcon(ImageIO.read(new File(s+"/fit3.png"))));
		Fit.setBackground(new Color(255,255,255));
		Fit.setToolTipText("<html><font size=\"6\">Fit To Screen</font></html>");
		Fit.setEnabled(false);
		
		Actual.setIcon(new ImageIcon(ImageIO.read(new File(s+"/actual1.png"))));
		Actual.setDisabledIcon(new ImageIcon(ImageIO.read(new File(s+"/actual2.png"))));
		Actual.setPressedIcon(new ImageIcon(ImageIO.read(new File(s+"/actual3.png"))));
		Actual.setBackground(new Color(255,255,255));
		Actual.setToolTipText("<html><font size=\"6\">Actual Size</font></html>");
		
		add(List);
		add(Repeat);
		add(Previous);
		add(Play);
		add(Next);
		add(Shuffle);
		add(Fit);
		
		Play.addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {
				if(ControlPanel.Play.isEnabled()){
					ControlPanel.Play.setEnabled(!ControlPanel.Play.isEnabled());
					Main.a.P2P();
					for(int i=0; i<Main.a.ap.albums.size(); i++)
						Main.a.ap.albums.elementAt(i)[0].setEnabled(false);
					if(shuf)
						MainFrame.sg.elementAt(Gallery.currentAlbum).playShuffle();
					else
						MainFrame.sg.elementAt(Gallery.currentAlbum).play();
				}
			}
			
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent arg0) {}
		});
		
		Pause.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				ControlPanel.Play.setEnabled(!ControlPanel.Play.isEnabled());
				Main.a.P2P();
				Gallery.timer.cancel();
				Gallery.timer.purge();
				for(int i=0; i<Main.a.ap.albums.size(); i++)
					Main.a.ap.albums.elementAt(i)[0].setEnabled(true);
			}
		});
		
		Next.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(ControlPanel.Next.isEnabled()){
					MainFrame.sg.elementAt(Gallery.currentAlbum).showNext(shuf);
				}
			}
		});
		
		Previous.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				MainFrame.sg.elementAt(Gallery.currentAlbum).showPre(shuf);
			}
		});
		
		Shuffle.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(ControlPanel.Shuffle.isEnabled()){
					shuf = !shuf;
					if(shuf){
						MainFrame.sg.elementAt(Gallery.currentAlbum).buildShuffleArray();
						if(!Main.a.sp.speedTF.isEditable()){
							Gallery.timer.cancel();
							Gallery.timer.purge();
							MainFrame.sg.elementAt(Gallery.currentAlbum).playShuffle();
						}
						try {
							ControlPanel.Shuffle.setIcon(new ImageIcon(ImageIO.read(new File(ControlPanel.s+"/shuffle4.png"))));
						} catch (IOException e) {}
					}
					else{
						try {
							ControlPanel.Shuffle.setIcon(new ImageIcon(ImageIO.read(new File(ControlPanel.s+"/shuffle1.png"))));
						} catch (IOException e) {}
					}
				}
			}
		});
		
		Repeat.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(ControlPanel.Repeat.isEnabled()){
					Gallery.rep = !Gallery.rep;
					if(Gallery.rep){
						try {
							ControlPanel.Repeat.setIcon(new ImageIcon(ImageIO.read(new File(ControlPanel.s+"/repeat4.png"))));
						} catch (IOException e) {}
					}
					else{
						try {
							ControlPanel.Repeat.setIcon(new ImageIcon(ImageIO.read(new File(ControlPanel.s+"/repeat1.png"))));
						} catch (IOException e) {}
					}
				}
			}
		});
		
		Actual.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(ControlPanel.Actual.isEnabled()){
					fit = !fit;
					Main.a.P2P();
					Main.showImage(MainFrame.sg.elementAt(Gallery.currentAlbum).allFiles[MainFrame.sg.elementAt(Gallery.currentAlbum).currentImageNumber]);
				}
			}
		});
		
		Fit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(ControlPanel.Fit.isEnabled()){
					fit = !fit;
					Main.a.P2P();
					Main.showImage(MainFrame.sg.elementAt(Gallery.currentAlbum).allFiles[MainFrame.sg.elementAt(Gallery.currentAlbum).currentImageNumber]);
				}
			}
		});
		
		List.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				if(ControlPanel.List.isEnabled()){
					try {
						new ListOfFiles();
					} catch (IOException e) {}
				}
			}
		});
		
	}

	public void SearchForIcon() {
		String fileToFind = File.separator + "1111aaaaa.search-helper";
		try {
		  Files.walkFileTree(Paths.get("").toAbsolutePath().getParent(), new SimpleFileVisitor<Path>() {
		    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
		      String fileString = file.toAbsolutePath().toString();
//		      System.out.println("pathString = " + fileString);
		      if(fileString.endsWith(fileToFind)){
		        //System.out.println("file found at path: " + file.toAbsolutePath());
		        s = file.toAbsolutePath().getParent().toString();
		        return FileVisitResult.TERMINATE;
		      }
		      return FileVisitResult.CONTINUE;
		    }
		  });
		} catch(IOException e){}
	}

}
