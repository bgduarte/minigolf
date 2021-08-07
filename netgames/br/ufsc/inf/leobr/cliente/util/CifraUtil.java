package br.ufsc.inf.leobr.cliente.util;

import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.security.cert.X509Certificate;



public class CifraUtil {

	/**
	 * Extrai a chave pública do arquivo.
	 */
	public static PublicKey getPublicKeyFromFile(InputStream is, String alias,
			String password) throws Exception {
		KeyStore ks = KeyStore.getInstance("JKS");
		char[] pwd = password.toCharArray();
		X509Certificate certificado = X509Certificate.getInstance(is);  
		return certificado.getPublicKey();
	}


	 
	public static byte[] decifrarDados(PublicKey key, byte[] txt) {
		try {

			Cipher c = Cipher.getInstance("RSA/ECB/NoPadding");
			c.init(Cipher.DECRYPT_MODE, key);

			return c.doFinal(txt);

		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
