import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AlbumPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	GridBagConstraints gbc;
	Vector<JButton[]> albums = new Vector<>();
	
	public AlbumPanel(){
		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipady = 20;
		gbc.ipadx = 80;
	    JLabel title = new JLabel(" Albums:");
	    title.setFont(MainFrame.font);
	    add(title, gbc);
	    
	    gbc.ipady = 0;
        gbc.weighty = 5.0;
        gbc.anchor = GridBagConstraints.PAGE_END;
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.gridy = 50;
	    add(new JLabel(), gbc);
	    gbc.gridy = 0;
	    gbc.gridwidth = 3;
	    gbc.ipady = 20;
	    gbc.weighty = 0.0;
	    
	}

	public void addAlbum() {
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.gridx = 0;
		gbc.gridy++;
		albums.lastElement()[0].setFont(MainFrame.font);
		add(albums.lastElement()[0], gbc);
	}

}
