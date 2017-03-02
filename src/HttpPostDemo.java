import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by FYGANG on 2017/3/1
 */
public class HttpPostDemo{

    /**
     * 数据流post请求
     * @param urlStr
     */
    public static String doPost(String urlStr, String strInfo) {
        String reStr="";
        URLConnection con = null;
        BufferedReader br = null;
        try {
            URL url = new URL(urlStr);
            con = url.openConnection();
            //post必须设置下面两个
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Cache-Control", "no-cache");
            con.setRequestProperty("Content-Type", "text/xml");
            con.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            con.setRequestProperty("Accept","*/*");
            con.connect();
            OutputStreamWriter out = new OutputStreamWriter(con.getOutputStream());
            out.write(new String(strInfo.getBytes("utf-8")));
            out.flush();
            out.close();
            Map<String , List<String>> Hmap = con.getHeaderFields();
            for(String key:Hmap.keySet()){
                System.out.println(key+"--->"+Hmap.get(key));
            }
            br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
            String line = "";
            for (line = br.readLine(); line != null; line = br.readLine()) {
                reStr += line;
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(br != null){
                try{
                    br.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return reStr;
    }
    public static void main(String args[]){
        String response = doPost("http://www.ctrip.com/","123");
        System.out.print("返回的响应数据："+response);
    }
}
