package br.ufsc.inf.leobr.cliente;

import br.ufsc.inf.leobr.cliente.exception.JahConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoConectadoException;
import br.ufsc.inf.leobr.cliente.exception.NaoJogandoException;

import com.retrogui.dualrpc.common.CallException;

public class ProxyConectadoEstado extends EstadoProxy {

	private Proxy proxy;
	
	public ProxyConectadoEstado(Proxy proxy){
		this.proxy = proxy;
	}

	public void conectar(String ipServidor, String nome)
			throws JahConectadoException {
		throw new JahConectadoException();
		
	}
	 
	public void desconectar() throws NaoConectadoException {
		super.desconectar();
		proxy.setEstadoProxy(proxy.getEstadoDesconectado());
		
	}

	public void enviaJogada(Jogada jogada) throws NaoJogandoException {
		throw new NaoJogandoException();
		
	}

	 
	public void iniciarPartida(Integer qtdeJogadoresNaPartida) throws NaoConectadoException {
		try {
			cliente.iniciarPartida(qtdeJogadoresNaPartida);
		} catch (CallException e) {
			e.printStackTrace();
		}
		
	}

	public void reiniciarPartida() throws NaoConectadoException ,NaoJogandoException {
		throw new NaoJogandoException();
	}

	public void finalizarPartida() throws NaoConectadoException,
			NaoJogandoException {
		throw new NaoJogandoException();
		
	}
	
	

	
}
