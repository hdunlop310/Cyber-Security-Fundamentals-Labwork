import java.security.Key;
import javax.crypto.KeyGenerator;
import javax.crypto.Cipher;

// Example Lab Code

public class EncryptDecrypt {
    public static void main(String[] args) {
        Key aesKey = null;
        try {
            KeyGenerator kg = KeyGenerator.getInstance("AES");
            kg.init(128);
            aesKey = kg.generateKey();
        } catch (Exception e) {
            System.err.println(e.toString());
            System.exit(1);
        }
        System.err.print("AES key = ");
        printBytes(aesKey.getEncoded());
    }

    public static void printBytes(byte[] b){
        System.err.print(" [" + b.length + "] ");
        for (int i = b.length - 1; i >= 0; i--)
        {
            int     k = (int) b[i] & 0xff;  // make unsigned
            int     k1 = k & 0xf;
            int     k2 = (k >> 4) & 0xf;
            System.err.print(String.format("%x%x", k1, k2));
        }
        System.err.print("\n");
    }

    public  static  byte[]  crypt(Key k, int mode, byte[] in) {
        Cipher c = null;
        try{
            c = Cipher.getInstance("AES");
            c.init(mode, k);
            return c.doFinal(in);
        }
        catch(Exception e){
            System.err.println(e.toString());
            System.exit(0);
        }
        return null;
    }
}
