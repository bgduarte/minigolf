package interface_grafica;

import dominio_do_problema.*;
import rede.*;

public class AtorJogador {
	
	protected AtorNetgames ngServer;
	protected ElementoDominioProblema domProblema;

	public AtorJogador() {
		ngServer = new AtorNetgames();
		domProblema = new ElementoDominioProblema();
	}

	public String conectar(String string, String string2) {
		String mensagem = "ja conectado";
		boolean permitido = domProblema.permitidoConectar();
		if (permitido) {
			mensagem = ngServer.conectar(string, string2);
			if (mensagem.equals("Sucesso: conectado a Netgames Server")) {
				domProblema.definirConectado(true);
			}
		}
		return mensagem;
	}
	
	public String desconectar() {
		String mensagem = "Nao conectado para poder desconectar";
		boolean permitido = domProblema.permitidoDesconectar();
		if (permitido) {
			mensagem = ngServer.desconectar();
			if (mensagem.equals("Sucesso: desconectado de Netgames Server")) {
				domProblema.definirConectado(false);
			}
		}
		return mensagem;
	}
	
	public String iniciarPartida() {
		String mensagem = "Faltam jogadores suficientes conectados";
		boolean permitido = domProblema.permitidoIniciarPartida();
		if (permitido) {
			mensagem = ngServer.iniciarPartida();
		}
		return mensagem;
	}

}
