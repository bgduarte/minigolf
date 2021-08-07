package br.ufsc.inf.leobr.cliente;

import java.io.IOException;
import java.security.GeneralSecurityException;

import br.ufsc.inf.leobr.cliente.exception.ArquivoMultiplayerException;
import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;
import br.ufsc.inf.leobr.cliente.exception.NaoPossivelConectarException;
import br.ufsc.inf.leobr.cliente.util.LerArquivoUtil;

import com.retrogui.dualrpc.client.NotConnectedException;
import com.retrogui.dualrpc.common.CallException;

public class ProxyDesconectadoEstado extends EstadoProxy {

	private Proxy proxy;
	
	public ProxyDesconectadoEstado(Proxy proxy){
		this.proxy = proxy;
	}
	
	public void conectar(String ipServidor, String nome)
			throws JahConectadoException, NaoPossivelConectarException, ArquivoMultiplayerException {
		
		try {
			
			LerArquivoUtil lau = new LerArquivoUtil();
			Long idJogo = lau.getIdJogo();

			cliente = new Cliente();
			cliente.connect(proxy, idJogo, ipServidor, nome);
			
			proxy.setEstadoProxy(proxy.getEstadoConectado());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NaoPossivelConectarException();
		} catch (CallException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NaoPossivelConectarException();
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NaoPossivelConectarException();
		} catch (NotConnectedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new NaoPossivelConectarException();
		}
	}

	public void desconectar() throws NaoConectadoException {
		throw new NaoConectadoException();
	}

	public void enviaJogada(Jogada jogada) throws NaoJogandoException {
		throw new NaoJogandoException();
		
	}

	public void iniciarPartida(Integer qtdeJogadoresNaPartida) throws NaoConectadoException {
		throw new NaoConectadoException();
		
	}

	public void reiniciarPartida() throws NaoConectadoException, NaoJogandoException {
		throw new NaoConectadoException();
		
	}

	public void finalizarPartida() throws NaoConectadoException,
			NaoJogandoException {
		throw new NaoConectadoException();
		
	}
	
	

}
