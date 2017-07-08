package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by parsa on 7/8/17.
 */
public class FileManager {
    public FileManager(String str) {
       // File file = new File();
        PrintWriter out = null;
        try {
            out = new PrintWriter("file.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        out.println(str);
        out.close();
    }
}
