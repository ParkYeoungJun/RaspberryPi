package refrigerator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import org.json.simple.JSONObject;

public  class test extends SwingWorker {

	@Override
	protected Object doInBackground() throws Exception {
	
            try {
//                ArrayList<NameValue>

                String uri = "http://52.78.88.182/insertFood.php";
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("group", "육류");
                jsonObj.put("purchase_date", "2016-09-06");
                jsonObj.put("name", "고기");
                jsonObj.put("image_num", 1);
                jsonObj.put("shelf_life", "2019-10-16");
                jsonObj.put("num", 1);
                jsonObj.put("position", 1);

                BufferedWriter bufferedWriter = null;
                URL url = new URL(uri);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                StringBuilder sb = new StringBuilder();
                con.setDoOutput(true);
                con.setDoInput(true);
                
                String data ="&" + URLEncoder.encode("data", "UTF-8") + "="+ jsonObj.toString();

                OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

//                wr.write(data);
//                wr.flush();
                
                BufferedReader reader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                String line=null;
                while((line=reader.readLine())!=null){
                    //서버응답값을 String 형태로 추가함
//                	System.out.println(line);
                }
           
                return sb.toString().trim();
                
           
            }catch(Exception e){
            	System.out.println(e);
            	return null;
            }



        }


        protected void onPostExecute(String result){
        	System.out.println(result);
        	//            myJSON=result;
//            showList();
        }

        public static void main(String[] args)
        {
        	JFrame t = new JFrame();
        	
          test g = new test();
          g.execute();        	
          
          t.setVisible(true);
        }
}


