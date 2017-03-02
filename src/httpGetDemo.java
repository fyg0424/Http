import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by FYGANG on 2017/3/2
 */
public class httpGetDemo{
    /**
     * HTTP GET请求封装
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url,String params){
        String resp = "";
        BufferedReader in = null;
        try{
            String urlNameStirng = url+"?"+params;
            //创建一个URL
            URL urlC = new URL(urlNameStirng);
            //打开URL连接
            URLConnection connection = urlC.openConnection();

            //设置通用请求属性
            connection.setRequestProperty("Cache-Control", "no-cache");
            connection.setRequestProperty("Content-Type", "text/xml");
            connection.setRequestProperty("User-Agent","Mozilla/5.0 ( compatible ) ");
            connection.setRequestProperty("Accept","*/*");

            connection.connect();

            Map<String,List<String>> Hmap = connection.getHeaderFields();

            // 遍历所有的响应头字段
            for(String key:Hmap.keySet()){
                System.out.println(key+"--->"+Hmap.get(key));
            }
            //输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while((line = in.readLine())!= null){
               resp += line;
            }


        }catch(MalformedURLException e){
            System.out.println("发送GET求情URL异常！" + e);
            e.printStackTrace();
        }catch(IOException ioe){
            System.out.println("发送GET请求异常！" + ioe );
            ioe.printStackTrace();
        }finally{
            if(in != null){
                try{
                    in.close();
                }catch(IOException e){
                    System.out.println("关闭流异常");
                    e.printStackTrace();
                }
            }

        }
        return resp;
    }

    public static void main(String args[]){
        String resp = doGet("https://www.baidu.com/","mpa");
        System.out.println("返回响应数据："+ resp);
    }
}
