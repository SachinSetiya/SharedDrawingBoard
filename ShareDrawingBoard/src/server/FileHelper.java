package server;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class FileHelper {
	String folder_path;
	static String CURRENT_DIR= System.getProperty("user.dir");
	static String SESSION_FOLDER=System.getProperty("user.dir")+"/src/server/Data";

	FileHelper(String path) {
		this.folder_path = path;
	}

	static ArrayList<String> fileNames(String path) {
		File directoryPath = new File(path);
		File[] filelist = directoryPath.listFiles();
		ArrayList<String> files = new ArrayList<String>();
		for (File file : filelist) {
			files.add(file.getName());
		}
		return files;
	}
	
	static void deleteFile(String fileName)
	{
		boolean success= new File(fileName).delete();
		if (success)
			JOptionPane.showMessageDialog(null, "Session Deleted");
		else
			JOptionPane.showMessageDialog(null, "Session Does not exist");
	}
}
