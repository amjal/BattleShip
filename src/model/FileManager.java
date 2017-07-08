package model;

import java.io.*;

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
    public String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader (file));
        String         line = null;
        StringBuilder  stringBuilder = new StringBuilder();
        String         ls = System.getProperty("line.separator");

        try {
            while((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }
}
