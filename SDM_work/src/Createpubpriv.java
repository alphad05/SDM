import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import cpabe.AbeDecryptionException;
import cpabe.AbeEncryptionException;
import cpabe.Cpabe;
import cpabe.policyparser.ParseException;

public class Createpubpriv {

	public static void main(String[] args) throws IOException, AbeEncryptionException, ParseException, AbeDecryptionException {
		File inputFile = new File("input.txt");
		FileWriter fw = new FileWriter(inputFile.getAbsoluteFile());
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write("this is a test");
		bw.close();
        File encryptedFile = new File("encrypted-input.enc");
        File decryptedFile = new File("encrypted-output.dec");
        File publicKey = new File("pk.pkey");
        File secretKey = new File("sk.mkey");
        File privateKey = new File("prk.private");
        Cpabe.setup(publicKey, secretKey);
        Cpabe.encrypt(publicKey, "patient_write or hospital_write or healthclub_write", inputFile, encryptedFile);
        Cpabe.keygen(privateKey, secretKey, "patient_write hospital_write healthclub_write");
        Cpabe.decrypt(privateKey, encryptedFile, decryptedFile);
	}

}
