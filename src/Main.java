import java.io.*;

import Utilities.Utility;
import static Utilities.Utility.*;
import java.math.BigInteger;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {

    RSA rsa1 = new RSA();


    rsa1.encrypt("0001111010011110000001011011011001001110011001000111001110011011");

//        DES des = new DES();
//        String inputString, encryptedString, decryptedString, key;
//        generateRandomKey("src/key.txt");
//        long time;
//
//
//        BufferedReader readerInput = new BufferedReader(new FileReader("src/input.txt"));
//        inputString = readerInput.readLine();
//        readerInput.close();
//
//        BufferedReader readerKey = new BufferedReader(new FileReader("src/key.txt"));
//        key = readerKey.readLine();
//        readerKey.close();
//
//        BufferedWriter writerEncrypted = new BufferedWriter(new FileWriter("src/encrypted.txt"));
//        time = System.nanoTime();
//        encryptedString = des.encrypt(inputString, key);
//        long encTime = System.nanoTime() - time;
//        writerEncrypted.write(encryptedString);
//        writerEncrypted.close();
//
//        BufferedReader readerEncrypted = new BufferedReader(new FileReader("src/encrypted.txt"));
//        encryptedString = readerEncrypted.readLine();
//        readerEncrypted.close();
//
//        BufferedWriter writerDecrypted = new BufferedWriter(new FileWriter("src/decrypted.txt"));
//        time = System.nanoTime();
//        decryptedString = des.decrypt(encryptedString, key);
//        long decTime = System.nanoTime() - time;
//        writerDecrypted.write(decryptedString);
//        writerDecrypted.close();
//
//
//        out.println("Input text: \n" + inputString + "\n");
//        out.println("Encrypting time: " + (double) encTime / 1_000_000_000.0 + ". Result:\n" + formatedBoolStringtoString(encryptedString) + "\n");
//        out.println("Decrypting time: " + (double) decTime / 1_000_000_000.0 + ". Result:\n" + decryptedString + "\n");

    }
}