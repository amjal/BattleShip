package model;

import jdk.nashorn.api.scripting.JSObject;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import view.ChatHistoryContainer;
import view.ChatHistoryPreview;

import java.io.FileReader;
import java.text.ParseException;
import java.util.Iterator;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;


/**
 * Created by parsa on 7/8/17.
 */
public class FileManager {
    public FileManager() {
    }

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
    public void addToJsonObject(String chats){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String[] lines = chats.split(System.getProperty("line.separator"));
        JSONArray chatArray =new JSONArray();
        JSONObject obj = new JSONObject();
        obj.put("chat",chats);
        obj.put("last chat",lines[lines.length-1]);
        obj.put("date and time",dateFormat.format(date));
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile("chatHistory.json", "rw");
            long pos = 0;
            try {
                pos = randomAccessFile.length();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                while (randomAccessFile.length() > 0) {
                    pos--;
                    randomAccessFile.seek(pos);
                    if (randomAccessFile.readByte() == ']') {
                        randomAccessFile.seek(pos);
                        break;
                    }
                }

                randomAccessFile.writeBytes("," + obj.toString() + "]}");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
    public JSONArray readFromJsonfile(){

String str;
JSONArray jsonArray=null;
        try {
            str=new String(readFile(("chatHistory.JSON")));
            JSONObject jsonObject =new JSONObject(str);
            jsonArray=jsonObject.getJSONArray("chat history");
        } catch (IOException e) {
            e.printStackTrace();
        }


        return jsonArray;
    }
    public void loadHistory(){
      JSONArray jsonArray  =readFromJsonfile();
        ChatHistoryContainer chatHistoryContainer =new ChatHistoryContainer();
        for(int i=jsonArray.length()-1;i>=0;i--){
            ChatHistoryPreview chatHistoryPreview=new ChatHistoryPreview(jsonArray.getJSONObject(i));
            chatHistoryContainer.addPanel(chatHistoryPreview);

        }
    }


}
