import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class EncryptDecryptFiles {


    private static void write_file(String fname, byte[] data)
    {
        try
        {
            File f = new File(fname);
            FileOutputStream fos = new FileOutputStream(f);
            fos.write(data);
            fos.close();
        }
        catch (Exception x)
        {
            System.err.println(x.toString());
            System.exit(1);
        }
    }

    private static byte[]  read_file(String fname)
    {
        try
        {
            File    f = new File(fname);
            byte[]  data = new byte[(int)f.length()];
            FileInputStream fis = new FileInputStream(f);
            fis.read(data);
            fis.close();
            return data;
        }
        catch (Exception x)
        {
            System.err.println(x.toString());
            System.exit(1);
        }
        return null;
    }

    private void erase_file(String fname)
    {
        File  f = new File(fname);
        Random rand = new Random();
        byte[]  b = new byte[(int)f.length()];
        rand.nextBytes(b);
        write_file(fname, b);
        f.delete();
    }

    public static void generateKey() throws NoSuchAlgorithmException {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128);   // key length 128 bits (16 bytes)
        Key aes_key = kg.generateKey();
        byte[] out = aes_key.getEncoded();
        write_file("file", out);
    }

    public static void main (String [] args) throws NoSuchAlgorithmException {
        generateKey();
        
    }

    private void enc_dec(int mode, byte[] kb, String infile, String outfile){
        try
        {
            Key k = new SecretKeySpec(kb, "AES");
            byte[]  in = read_file(infile);
            Cipher c = Cipher.getInstance("AES");
            c.init(mode, k);
            byte[]  out = c.doFinal(in);
            write_file(outfile, out);
        }
        catch(Exception x)
        {
            System.err.println(x.toString());
            System.exit(1);
        }
    }
}
