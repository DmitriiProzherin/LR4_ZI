import java.io.*;
import java.nio.file.Path;

public class DES_PLUS_RSA_DECRYPTOR {
    private String decryptedText;
    private String cipherText;
    RSA rsa = new RSA();
    DES des = new DES();
    String encryptedKey;
    public DES_PLUS_RSA_DECRYPTOR cipherText(String fileName) throws IOException {
        BufferedReader readerEncrypted = new BufferedReader(new FileReader(fileName));
        cipherText = readerEncrypted.readLine();
        readerEncrypted.close();
        return this;
    }

    public DES_PLUS_RSA_DECRYPTOR withKeys(String desKey, String rsaKey) throws IOException {
        BufferedReader readerEncrypted = new BufferedReader(new FileReader(desKey));
        encryptedKey = readerEncrypted.readLine();
        readerEncrypted.close();




    //    decryptedText = des.decrypt(encryptedString, key);
        return this;
    }

    public void toFile(String fileName) throws IOException {
        BufferedWriter writerEncrypted = new BufferedWriter(new FileWriter(fileName));
        writerEncrypted.write(decryptedText);
        writerEncrypted.close();
    }
}
