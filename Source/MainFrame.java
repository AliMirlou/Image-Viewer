import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainFrame extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	JFrame load;
	JLabel j;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	int width = (int)screenSize.getWidth();
	int height = (int)screenSize.getHeight();
		
	JMenuBar MenuBar = new JMenuBar();
	
	JMenu FileMenu = new JMenu("File");
	JMenuItem NewMenuItem = new JMenuItem("New Album");
	JMenuItem OpenMenuItem = new JMenuItem("Open Album");
	JMenuItem SaveMenuItem = new JMenuItem("Save Album");
	JMenuItem ExitMenuItem = new JMenuItem("Exit");
	
	JMenu HelpMenu = new JMenu("Help");
	JMenuItem HowToUseMenuItem = new JMenuItem("How To Use");
	JMenuItem AboutMenuItem = new JMenuItem("About");
	
	JLabel pic = new JLabel("", JLabel.CENTER);
	
	ControlPanel cp = new ControlPanel();
	AlbumPanel ap = new AlbumPanel();
	SettingPanel sp = new SettingPanel();
	
	GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0];
	static boolean fullScreen = false;
	
	static Vector<Gallery> sg = new Vector<>();
	
	static Font font = new Font("", Font.PLAIN, 20);
	static int count=0;
	Timer timer;
	boolean check = false;
	
	static String[] save = new String[2];
	static File da = null;
	static Scanner s;
	static PrintWriter writer = null;
	
	public MainFrame() throws IOException {
		
		CreateGUI();
		
		NewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("."));
			    fc.setDialogTitle("Select A Directory");
			    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			    fc.setAcceptAllFileFilterUsed(false);
				if(fc.showDialog(null, "Select") == JFileChooser.APPROVE_OPTION){
					if((new File(fc.getSelectedFile().getAbsolutePath()).listFiles(new FilenameFilter() {
						public boolean accept(File dir, String name) {
							return name.endsWith("jpg") || name.endsWith("jpeg") || name.endsWith("bmp") || name.endsWith("png") || name.endsWith("gif") || name.endsWith("wbmp") || name.endsWith("JPG") || name.endsWith("JPEG") || name.endsWith("PNG") || name.endsWith("BMP") || name.endsWith("GIF") || name.endsWith("WBMP");
						}
					})).length != 0){
						sg.add(new Gallery(fc.getSelectedFile().getParent(), fc.getSelectedFile().getName()));
						save[0] = fc.getSelectedFile().getName();
						save[1] = fc.getSelectedFile().getParent();
						ControlPanel.List.setEnabled(true);
						ControlPanel.Play.setEnabled(true);
						ControlPanel.Previous.setEnabled(true);
						ControlPanel.Next.setEnabled(true);
						ControlPanel.Repeat.setEnabled(true);
						ControlPanel.Shuffle.setEnabled(true);
						ControlPanel.Fit.setEnabled(true);
						sp.speedTF.setEditable(true);
						sp.speedTF.setText("5");
						sp.zoomTF.setEditable(true);
						sp.zoomTF.setText("100");
						sp.apply.setEnabled(true);
						SaveMenuItem.setEnabled(true);
						add(pic,BorderLayout.CENTER);
						Main.showImage(sg.lastElement().allFiles[0]);
						Gallery.currentAlbum = sg.size()-1;
						ap.albums.addElement(new JButton[2]);
						ap.albums.lastElement()[0] = new JButton(fc.getSelectedFile().getName());
						sg.lastElement().ListenAlbumButton();
						ap.addAlbum();
					}
					else{
						JOptionPane pane = new JOptionPane("No image was found in the selected directory.", JOptionPane.ERROR_MESSAGE);
						JDialog d = pane.createDialog(null, "Error");
						d.setVisible(true);
					}
				}
			}
		});
		
		OpenMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fc = new JFileChooser();
				fc.setCurrentDirectory(new File("."));
			    fc.setDialogTitle("Select The igv File");
			    fc.setFileFilter(new FileNameExtensionFilter("Image Gallery Viewer Files (*.igv)", "igv"));
			    if(fc.showDialog(null, "Open")==JFileChooser.APPROVE_OPTION){
					try {
						s = new Scanner(fc.getSelectedFile());
						if(s.nextLine().equals("[Image Gallery Viewer]")){
							if(!ControlPanel.Play.isEnabled()){
								sp.speedTF.setEditable(true);
								sp.zoomTF.setEditable(true);
								sp.apply.setEnabled(true);
								ControlPanel.List.setEnabled(true);
								ControlPanel.Play.setEnabled(true);
								ControlPanel.Previous.setEnabled(true);
								ControlPanel.Next.setEnabled(true);
								ControlPanel.Repeat.setEnabled(true);
								ControlPanel.Shuffle.setEnabled(true);
								ControlPanel.Fit.setEnabled(true);
								SaveMenuItem.setEnabled(true);
								add(pic,BorderLayout.CENTER);
							}
							while(s.hasNext()){
								save[0] = s.nextLine();
								save[1] = s.nextLine();
								sg.add(new Gallery(save[0], save[1]));
								Gallery.currentAlbum++;
								String tp = s.nextLine();
								sp.speedTF.setText(tp);
								ap.albums.addElement(new JButton[2]);
								ap.albums.elementAt(Gallery.currentAlbum)[0] = new JButton(save[1]);
								sg.lastElement().ListenAlbumButton();
								ap.addAlbum();
								sg.elementAt(Gallery.currentAlbum).speed = Integer.parseInt(tp);
								sp.zoomTF.setText(s.nextLine());
							}
							Main.showImage(sg.lastElement().allFiles[0]);
							s.close();
						}
						else{
							JOptionPane pane = new JOptionPane("The selected file is not a valid Image Gallery Viewer file.", JOptionPane.ERROR_MESSAGE);
							JDialog d = pane.createDialog(null, "Error");
							d.setFont(MainFrame.font);
							d.setVisible(true);
						}
					} catch (FileNotFoundException e) {
						JOptionPane pane = new JOptionPane("File not Found.", JOptionPane.ERROR_MESSAGE);
						JDialog d = pane.createDialog(null, "Error");
						d.setFont(MainFrame.font);
						d.setVisible(true);
					}
			    }
			}
		});
		
		SaveMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(da == null){
					JFileChooser fc = new JFileChooser();
					fc.setCurrentDirectory(new File("."));
				    fc.setDialogTitle("Select A Directory To Save The File");
				    fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				    fc.setFileFilter(new FileNameExtensionFilter("Image Gallery Viewer Files (*.igv)", "igv"));
				    if(fc.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION){
				    	try {
				    		da = new File(fc.getSelectedFile() + "\\" + save[0] + ".igv");
							if(da.createNewFile()){
								write();
								JOptionPane pane = new JOptionPane("The file has been successfully saved with the name of the parent folder.", JOptionPane.INFORMATION_MESSAGE);
								JDialog d = pane.createDialog(null, "Save Successful");
								d.setFont(MainFrame.font);
								d.setVisible(true);
							}
							else{
								Object[] o = {"Yes","No"};
								if(JOptionPane.showOptionDialog(null, "The file already exists.\nDo you want to overwrite the existing file?", "Save Unsuccessful", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,o,o[0])==0){
									write();
									JOptionPane pane = new JOptionPane("The file has been successfully overwrited.", JOptionPane.INFORMATION_MESSAGE);
									JDialog d = pane.createDialog(null, "Save Successful");
									d.setFont(MainFrame.font);
									d.setVisible(true);
								}
							}
						} catch (IOException e) {}
				    }
				}
				else{
					try {
						write();
					} catch (FileNotFoundException e) {
						Object[] o = {"Yes","No"};
						if(JOptionPane.showOptionDialog(null, "The file was not found.\nDo you want to create the file?", "Save Unsuccessful", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null,o,o[0])==0){
							try {
								da.createNewFile();
							} catch (IOException e1) {}
							try {
								write();
							} catch (FileNotFoundException e1) {}
							JOptionPane pane = new JOptionPane("The file has been successfully created.", JOptionPane.INFORMATION_MESSAGE);
							JDialog d = pane.createDialog(null, "Save Successful");
							d.setFont(MainFrame.font);
							d.setVisible(true);
						}
					}
				}
			}
		});
		
		ExitMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		HowToUseMenuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane pane = new JOptionPane();
				String s[] = {"To start a new slideshow, press File, press New Album then select a folder.","To open a saved slideshow, press File, press Open Album then choose the ivg file made by the image gallery viewer program.","To save the current albums, press File, press Save Album then select a folder to save."};
				pane.setMessage(s);
				JDialog d = pane.createDialog(null, "How To Use This Program");
				d.setFont(font);
				d.setVisible(true);
			}
		});
		
		AboutMenuItem.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				JOptionPane pane = new JOptionPane("Image Gallery Viewer Version 1.0\nDeveloped By Ali Mirlou!");
				JDialog d = pane.createDialog(null, "About This Program");
				d.setSize(new Dimension(370, 150));
				d.setVisible(true);
			}
		});
		
		addMouseListener(new MouseListener() {
			
			public void mouseReleased(MouseEvent arg0) {}
			public void mousePressed(MouseEvent arg0) {}
			public void mouseExited(MouseEvent arg0) {}
			public void mouseEntered(MouseEvent arg0) {}
			public void mouseClicked(MouseEvent e) {
				
				if(cp.isVisible()){
					cp.setVisible(false);
					ap.setVisible(false);
					sp.setVisible(false);
				}
				else{
					cp.setVisible(true);
					ap.setVisible(true);
					sp.setVisible(true);
				}
				if(e.getClickCount() == 2){
					Main.a.setVisible(false);
					Main.b = new FullscreenFrame();
					if(!sg.isEmpty())
						Main.showImage(sg.elementAt(Gallery.currentAlbum).allFiles[sg.elementAt(Gallery.currentAlbum).currentImageNumber]);
				}
				
			}
		});
		
	}
	
	private void CreateGUI() throws IOException {

		load = new JFrame();
		load.setUndecorated(true);
		load.setPreferredSize(new Dimension(width/5, height/5));
		load.setLocation((4*width/10), (4*height/10));
		j = new JLabel("Loading Frame...",JLabel.CENTER);
		load.add(j);
		load.pack();
		load.setVisible(true);
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {}
		
		setTitle("Image Gallery Viewer");
		setIconImage(ImageIO.read(new File(ControlPanel.s+"/MainIcon.png")));
		setExtendedState(getExtendedState()|JFrame.MAXIMIZED_BOTH);
		setPreferredSize(new Dimension(width-200, height-200));
		setLocation(100, 100);
		getContentPane().setBackground(Color.BLACK);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		j.setText("Loading Menu...");
		load.add(j);
		load.pack();
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {}
		
		FileMenu.setFont(font);
		NewMenuItem.setFont(font);
		OpenMenuItem.setFont(font);
		SaveMenuItem.setFont(font);
		SaveMenuItem.setEnabled(false);
		ExitMenuItem.setFont(font);
		
		FileMenu.add(NewMenuItem);
		FileMenu.add(OpenMenuItem);
		FileMenu.add(SaveMenuItem);
		FileMenu.addSeparator();
		FileMenu.add(ExitMenuItem);
		
		HelpMenu.setFont(font);
		HowToUseMenuItem.setFont(font);
		AboutMenuItem.setFont(font);
		HelpMenu.add(HowToUseMenuItem);
		HelpMenu.add(AboutMenuItem);
		
		MenuBar.add(FileMenu);
		MenuBar.add(HelpMenu);
		setJMenuBar(MenuBar);
		
		j.setText("Loading Panels...");
		load.add(j);
		load.pack();
		
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {}
		
		add(cp, BorderLayout.SOUTH);
		add(ap, BorderLayout.WEST);
		add(sp, BorderLayout.EAST);
		
		add(pic,BorderLayout.CENTER);
		
		pack();
		setVisible(true);
		load.dispose();
		
	}
	
	public void P2P() {
		cp.removeAll();
		cp.add(ControlPanel.List);
		cp.add(ControlPanel.Repeat);
		cp.add(ControlPanel.Previous);
		if(!ControlPanel.Play.isEnabled())
			cp.add(ControlPanel.Pause);
		else
			cp.add(ControlPanel.Play);
		cp.add(ControlPanel.Next);
		cp.add(ControlPanel.Shuffle);
		if(ControlPanel.fit)
			cp.add(ControlPanel.Actual);
		else
			cp.add(ControlPanel.Fit);
		sp.apply.setEnabled(!sp.apply.isEnabled());
		sp.speedTF.setEditable(!sp.speedTF.isEditable());
		sp.zoomTF.setEditable(!sp.zoomTF.isEditable());
		cp.revalidate();
		cp.repaint();
	}
	
	void write() throws FileNotFoundException {
		writer = new PrintWriter(da);
		writer.println("[Image Gallery Viewer]");
		for(int i=0; i<sg.size(); i++){
			writer.println(MainFrame.save[1]);
			writer.println(MainFrame.save[0]);
			writer.println(sg.elementAt(i).speed);
			writer.println(sp.zoomTF.getText());
		}
		writer.close();
	}
	
}
