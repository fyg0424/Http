import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by FYGANG on 2017/3/2
 */
public class HttpClientPostDemo{

    /**
     * 随机生成6为数验证码
     *
     * @return String
     */
    public static String CreateMobileVCode(){
        //验证码
        String vcode = "";
        for(int i = 0; i < 6; i++){
            vcode = vcode + (int)(Math.random() * 9);
        }
        return vcode;
    }

    public static void doPost(){
        String url = "https://www.baidu.com/";
        //创建HTTPClient实例
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Cache-Control", "no-cache");
        httpPost.setHeader("Content-Type", "text/xml");
        httpPost.setHeader("User-Agent","Mozilla/5.0 ( compatible ) ");
        httpPost.setHeader("Accept","*/*");
        HttpEntity entity = new StringEntity("123","utf-8");
        httpPost.setEntity(entity);
        try{
            HttpResponse response = httpClient.execute(httpPost);
            String resp = EntityUtils.toString(response.getEntity());
            System.out.print(resp);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public static void main(String args[]){
        doPost();
    }

}
