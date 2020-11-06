package code;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaveFileTry {
	static char []a = {1,2,2,3,4,65};
	static String path= "/home/sachin/AIT/Sofware Design/"
			+ "workspace/ShareDrawingBoard/src/code/data.hex";
    public static void main(String[] args) {
		try {
			FileWriter fw= new FileWriter(path);
			for (int i = 0; i < a.length; i++) {	
				fw.append(a[i]);
			}
			fw.close();
			FileReader fr= new FileReader(path);
			int ch;
			do {
				ch= fr.read();
				System.out.println(ch);
			} while (ch != -1);
			fr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
    
}
