import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {

    DES_PLUS_RSA combined = new DES_PLUS_RSA();

    combined.encryptor.plainText("src/input.txt").toFile("src/encrypted.txt");

    String desKey = "src/key_encrypted_with_rsa.txt";
    String rsaSecretKey = "src/key_encrypted_with_rsa.txt";

    combined.decryptor.cipherText("src/encrypted.txt").withKeys(desKey, rsaSecretKey).toFile("src/output.txt");
    }
}