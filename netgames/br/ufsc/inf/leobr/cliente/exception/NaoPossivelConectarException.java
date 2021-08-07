package br.ufsc.inf.leobr.cliente.exception;


/**
 * Lançada quando não é possível por algum motivo se conectar ao cleinte.
 * Dentre a gama de possíveis motivos estão:
 * Firewall bloquando
 * Número máximo de clientes conectados
 * Servidor fora do ar.
 * @author LSB
 *
 */
public class NaoPossivelConectarException extends Exception {
	
	public NaoPossivelConectarException(){
		super("Por algum motivo não é possível conectar, possíveis motivos:" + "\n" +
				"Firewall bloquando" + "\n" +
				"Número máximo de clientes conectados" + "\n" +
				"Servidor Fora do ar" + "\n" +
				"Por favor tente mais tarde");
	}
}
