package bitz;

import lombok.SneakyThrows;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.properties.EncryptableProperties;

import java.security.Security;
import java.util.Properties;

public class Encryptor {

    public static void main(String[] args ) {
        //checkAlgorithms();
        checkEncryptedProperty();
    }


    @SneakyThrows
    private static void checkEncryptedProperty() {
        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword("jasypt");
        encryptor.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
         //PBEWITHMACSHA512ANDAES_256");
        encryptor.setIvGenerator(new RandomIvGenerator());

        String output = encryptor.encrypt("this is my message");
        System.out.println("Update property to: ENC(" + output + ")");
        System.out.println("Decrypted value: '"+ encryptor.decrypt(output)+"'");

        Properties props = new EncryptableProperties(encryptor);
        ClassLoader loader = Encryptor.class.getClassLoader();
        props.load(loader.getResourceAsStream("application.properties"));
        System.out.println("From Property file: '" + props.getProperty("bar") + "'");

    }

    private static void checkAlgorithms() {
        for (String alg : Security.getAlgorithms("Cipher")) {
            System.out.println(alg);
        }
    }

}
