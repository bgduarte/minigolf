package br.ufsc.inf.leobr.cliente.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

	public static byte[] readContentsToBytes(InputStream is)
			throws FileNotFoundException, IOException {

		BufferedInputStream bis = new BufferedInputStream(is);

		byte[] buffer = new byte[bis.available()];
		bis.read(buffer);

		bis.close();
		is.close();

		return buffer;
	}

	 
	public static void writeContentsFromBytes(File f, byte[] contents,
			boolean overwrite) throws IOException {
		if (!f.exists() || (f.exists() && overwrite)) {
			FileOutputStream fos = new FileOutputStream(f);
			BufferedOutputStream bos = new BufferedOutputStream(fos);

			bos.write(contents);

			bos.close();
			fos.close();
		}
	}

	 
	public static byte[] retirarZeros(byte[] textoDecifrado) {
		// retirar 0's
		List<Byte> lista = new ArrayList<Byte>();
		for (byte b : textoDecifrado) {
			if (b != 0) {
				lista.add(b);
			}
		}

		byte[] retorno = new byte[lista.size()];

		for (int i = 0; i < retorno.length; i++) {
			retorno[i] = lista.get(i);
		}
		return retorno;
	}

}
