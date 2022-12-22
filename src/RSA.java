import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import static Utilities.Utility.*;

public class RSA {

    private final static long MINIMUM_PRIME_RANGE = (long) 1e0;
    private final static long MAXIMUM_PRIME_RANGE = (long) 1e2;

    private final ArrayList<BigInteger> encrypted_blocks = new ArrayList<>();
    private final ArrayList<BigInteger> decrypted_blocks = new ArrayList<>();
    private final ArrayList<BigInteger> initBlocks = new ArrayList<>();
    private BigInteger e, n, d;
    private int block_length;

    private long generateRandomPrimeNumber(){
        Random r = new Random();
        long k = r.nextLong(MINIMUM_PRIME_RANGE, MAXIMUM_PRIME_RANGE);
        do {
            k++;
        } while (!isPrime(k));
        return k;
    }

    private boolean isPrime(long n){
        if (n % 2 == 0) return false;
        for (long i = 3; i < n; i+=2) {
            if ((n % i) == 0) return false;
        }
        return true;
    }

    public ArrayList<BigInteger[]> generateKeyPairs(){
        ArrayList<BigInteger[]> key_pairs = new ArrayList<>();

        long p = generateRandomPrimeNumber();
        long q = generateRandomPrimeNumber();

        BigInteger n = BigInteger.valueOf(p * q);
        BigInteger euler_f_n = BigInteger.valueOf((p - 1) * (q -1));
        BigInteger e = calculate_e(euler_f_n);
        BigInteger d = get_d(e, euler_f_n);


        key_pairs.add(new BigInteger[] {e, n});
        key_pairs.add(new BigInteger[] {d, n});

        try {
            BufferedWriter open_key_writer = new BufferedWriter(new FileWriter("src/public_rsa_key_pair.txt"));
            open_key_writer.write(Arrays.toString(key_pairs.get(0)));
            open_key_writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        try {
            BufferedWriter private_key_writer = new BufferedWriter(new FileWriter("src/private_rsa_key_pair.txt"));
            private_key_writer.write(Arrays.toString(key_pairs.get(1)));
            private_key_writer.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        return key_pairs;
    }

    private BigInteger get_d(BigInteger e, BigInteger f) {
        BigInteger d, k = new BigInteger("1");
        while (!f.multiply(k).add(BigInteger.ONE).mod(e).equals(BigInteger.ZERO)) {
            k = k.add(BigInteger.ONE);
        }
        d = f.multiply(k).add(BigInteger.ONE).divide(e);
        assert f.min(d).signum() == 1;
        return d;
    }

    private boolean isCoprime(BigInteger a, BigInteger b){
        BigInteger gcd = a.gcd(b);
        return gcd.equals(BigInteger.ONE);
    }

    private BigInteger calculate_e(BigInteger euler_f_n){
        BigInteger res;
        int n = 0;

        do {
            n++;
            res = BigInteger.valueOf( (long) Math.pow(2, n) + 1);
        }
        while (!isCoprime(res, euler_f_n));

        return res;
    }


    private int log2(BigInteger number){
      //  if (number.equals(BigInteger.TWO)) return 1;

        int i = 0;
        while (number.min(BigInteger.TWO).signum() == 1) {
            number = number.divide(BigInteger.TWO);
            i++;
        }
        return i - 1;
    }


    public void encrypt(String message){
        ArrayList<BigInteger[]> key_pairs = generateKeyPairs();

        e = key_pairs.get(0)[0];
        n = key_pairs.get(0)[1];
        d = key_pairs.get(1)[0];

        // Длина блока текста
        block_length = log2(n);

        // Получаем массив блоков
        String[] blocks = splitStringIntoBlocks(message, block_length);

        for (String block : blocks) initBlocks.add(binaryStringToBigInt(block));


        for (String block : blocks) {
            // Численное представление блока
            BigInteger p = binaryStringToBigInt(block);
            // Шифрование численного представления
            BigInteger c = bigIntPow(p, e).mod(n);
            encrypted_blocks.add(c);
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/key_encrypted_with_rsa.txt"));
            encrypted_blocks.forEach(block -> {
                try {
                    writer.write(block.toString() + " ");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
            writer.close();

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void decrypt(){

        for (BigInteger block : encrypted_blocks) {
            // Расшифрование численного предсставления
            BigInteger m = bigIntPow(block, d).mod(n);
            decrypted_blocks.add(m);
        }

        StringBuilder result = new StringBuilder();
        for (BigInteger block : decrypted_blocks) {
            String binary = bigIngegerToBinaryString(block);
            int delta = block_length - binary.length();
            result.append("0".repeat(Math.max(0, delta)));
            result.append(binary);
        }
        result.reverse().setLength(64);
        result.reverse();
        System.out.println(result);
    }

}
