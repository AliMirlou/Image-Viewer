import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Timer;
import java.util.TimerTask;

public class Gallery {
	
	static int currentAlbum = -1;
	static boolean rep = false;
	static Timer timer;
	static TimerTask timerTask;
	
	int currentImageNumber = 0;
	int speed = 5;
	int[] shuffleNumbers;
	
	File[] allFiles;
	
	public Gallery(String dir, String name){
		
		allFiles = new File(dir+"\\"+name).listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith("jpg") || name.endsWith("jpeg") || name.endsWith("bmp") || name.endsWith("png") || name.endsWith("gif") || name.endsWith("wbmp") || name.endsWith("JPG") || name.endsWith("JPEG") || name.endsWith("PNG") || name.endsWith("BMP") || name.endsWith("GIF") || name.endsWith("WBMP");
			}
		});
		
	}
	
	public void play(){
		timer = new Timer();
		timerTask = new TimerTask() {
			public void run() {
				Main.showImage(allFiles[currentImageNumber]);
				currentImageNumber++;
				if(currentImageNumber == allFiles.length){
					currentImageNumber = 0;
					if(!rep){
						Main.a.P2P();
						timer.cancel();
						timer.purge();
					}
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask, 1, speed*1000);
	}
	
	public void playShuffle(){
		timer = new Timer();
		timerTask = new TimerTask() {
			public void run() {
				Main.showImage(allFiles[shuffleNumbers[currentImageNumber]]);
				currentImageNumber++;
				if(currentImageNumber == allFiles.length){
					currentImageNumber = 0;
					if(!rep){
						Main.a.P2P();
						timer.cancel();
						timer.purge();
					}
				}
			}
		};
		timer.scheduleAtFixedRate(timerTask, 1, speed*1000);
	}
	
	public void showNext(boolean shuf){
		currentImageNumber++;
		if(currentImageNumber == allFiles.length){
			currentImageNumber = 0;
		}
		if(shuf)
			Main.showImage(allFiles[shuffleNumbers[currentImageNumber]-1]);
		else
			Main.showImage(allFiles[currentImageNumber]);
	}
	
	public void showPre(boolean shuf){
		currentImageNumber--;
		if(currentImageNumber == -1)
			currentImageNumber = allFiles.length-1;
		if(shuf)
			Main.showImage(allFiles[shuffleNumbers[currentImageNumber]-1]);
		else
			Main.showImage(allFiles[currentImageNumber]);
	}

	public void buildShuffleArray() {
		int count = 0;
		shuffleNumbers = new int[allFiles.length];
		shuffleNumbers[0] = currentImageNumber;
		while(count != allFiles.length){
			int rand = (((int)(Math.random()*100))%(allFiles.length-1))+1;
			if(count == currentImageNumber)
				count++;
			if(shuffleNumbers[rand] == 0){
				shuffleNumbers[rand] = count;
				count++;
			}
		}
	}

	public void ListenAlbumButton() {
		Main.a.ap.albums.lastElement()[0].addActionListener(new ActionListener() {
			int t = currentAlbum;
			public void actionPerformed(ActionEvent arg0) {
				currentAlbum = t;
				Main.showImage(MainFrame.sg.elementAt(currentAlbum).allFiles[MainFrame.sg.elementAt(currentAlbum).currentImageNumber]);
			}
		});
	}
	
}
