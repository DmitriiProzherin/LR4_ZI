import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import static Utilities.Utility.*;

public class DES_PLUS_RSA_ENCRYPTOR {
    RSA rsa = new RSA();
    DES des = new DES();
    private String encryptedText;
    public DES_PLUS_RSA_ENCRYPTOR plainText(String fileName) throws IOException {

        rsa.encrypt(random64bool("src/key.txt"));

        String key = readStringFromFile("src/key.txt");
        String message = readStringFromFile(fileName);

        encryptedText = des.encrypt(message, key);
        System.out.println(formatedBoolStringtoString(encryptedText));

        return this;
    }

    public void toFile(String fileName) throws IOException {
        BufferedWriter writerEncrypted = new BufferedWriter(new FileWriter(fileName));
        writerEncrypted.write(encryptedText);
        writerEncrypted.close();
    }
}
