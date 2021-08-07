package br.ufsc.inf.leobr.cliente.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.PublicKey;
import java.util.Properties;

import org.apache.log4j.Logger;

import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;

/**
 * Classe utilitária para leitura de arquivos.
 * 
 * @author Leonardo de Souza Brasil
 * 
 */
public final class LerArquivoUtil {

	private String alias = "jogos";

	private String pwd = "jogomp";

	private Logger log;

	public LerArquivoUtil() {
		log = Logger.getLogger(LerArquivoUtil.class.getName());

	}

	/**
	 * Captura o id do jogo no arquivo jogoMultiplayer.properties
	 * 
	 * @return
	 * @throws IOException
	 * @throws ArquivoMultiplayerException
	 */
	public Long getIdJogo() throws IOException, ArquivoMultiplayerException {

		URL url = ClassLoader.getSystemResource("cert.x509");
		InputStream is;

		if (url == null) {
			is = this.getClass().getClassLoader().getResourceAsStream(
					"cert.x509");

		} else {
			is = url.openStream();
		}

		if (is == null) {
			throw new IOException("O arquivo cert.x509 não foi encontrado");
		}

		PublicKey publicKey;
		try {
			publicKey = CifraUtil.getPublicKeyFromFile(is, alias, pwd);
		} catch (Exception e) {
			throw new IOException("Não foi possível extrair chave pública.");
		}
		
		
		URL urlJmp = ClassLoader.getSystemResource("jogoMultiPlayer.properties");
		
		InputStream isj = null;

		if (urlJmp == null) {
			isj = this.getClass().getClassLoader().getResourceAsStream(
			"jogoMultiPlayer.properties");
		} else {
			isj = urlJmp.openStream();
		}
		
		File arquivoMultiplayer = new File("jogoMultiPlayer.properties");

		if (isj == null) {
			log.info("Arquivo não encontrado internamente");
			isj = new BufferedInputStream(new FileInputStream(
					arquivoMultiplayer));

			if (isj == null) {
				throw new ArquivoMultiplayerException(
						"Arquivo jogoMultiPlayer.properties não encontrado.");
			}

		}

		byte[] textoCifrado = FileUtil.readContentsToBytes(isj);

		// decifrar o texto (passando o array de bytes) e como retorno obter
		// outro array de bytes
		byte[] textoDecifrado = CifraUtil
				.decifrarDados(publicKey, textoCifrado);

		if (textoDecifrado.length == 0 || textoDecifrado == null) {
			throw new ArquivoMultiplayerException(
					"O arquivo jogoMultiplayer está corrompido.");
		}

		textoDecifrado = FileUtil.retirarZeros(textoDecifrado);

		// jogar o array de bytes em um byteArrayInputStream
		ByteArrayInputStream bis = new ByteArrayInputStream(textoDecifrado);

		// criar um properties com o texto decifrado (a partir do
		// byteArrayInputStream
		Properties p = new Properties();
		p.load(bis);
		bis.close();
		// capturar o idJogo, lança exceção se não conseguir
		String idJogo = p.getProperty("idJogo");

		if (idJogo == null || idJogo.equals("")) {
			throw new ArquivoMultiplayerException(
					"O arquivo jogoMultiplayer está corrompido.");
		}

		// tentar transformar o idJogo para float
		Long lidJogo;
		try {
			lidJogo = Long.valueOf(idJogo);
		} catch (Exception e) {
			throw new ArquivoMultiplayerException(
					"O arquivo jogoMultiplayer está corrompido.");
		}

		return lidJogo;

	}
}
