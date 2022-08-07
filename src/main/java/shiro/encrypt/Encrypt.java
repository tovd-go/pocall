package shiro.encrypt;


import sun.misc.BASE64Encoder;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class Encrypt {
    public static String encrypt(byte[] serialized, byte[] key) throws Exception {



        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");//"算法/模式/补码方式"

        int sizeInBytes = 16;
        byte[] iv = new byte[sizeInBytes];
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        SecretKeySpec skeySpec = new SecretKeySpec(key, "AES");
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
}
