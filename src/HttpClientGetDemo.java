import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by FYGANG on 2017/3/2
 * HttpClient GET请求Demo
 */
public class HttpClientGetDemo{
    public static void doGet(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://www.baidu.com/");
        try{
            HttpResponse resp = httpClient.execute(httpGet);
            System.out.println("返回的状态码："+resp.getStatusLine().getStatusCode());
            if(resp.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String msg = EntityUtils.toString(resp.getEntity(),"utf-8");
                System.out.println("返回的数据："+msg);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void main(String args[]){
        doGet();
    }
}
