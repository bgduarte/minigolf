// Search for the string "change me" to learn what code must be
// altered when using your own gui class.

/* This file is part of the DualRpcServer libraries
 Copyright (c) 2004-2005, Vick Perry

 This library is free software; you can redistribute it and/or
 modify it under the terms of the BSD license. See the contents
 of the file LICENSE.txt for more details.
 */
package br.ufsc.inf.leobr.cliente;

import java.rmi.MarshalledObject;

import com.retrogui.dualrpc.client.AbstractClientRpcHandler;

/**
 * Client handler processa as chamadas RPC do servidor.
 * 
 * @author Leonardo de S. Brasil
 */
public class ClienteTratador extends AbstractClientRpcHandler {

	private ClienteReceptor cliente = null;

	/**
	 * Construtor que recebe o cliente
	 * 
	 * @param client
	 */
	public ClienteTratador(ClienteReceptor cliente) {
		this.cliente = cliente;
	}

	public void recebeJogada(MarshalledObject jogadaMarshalled) {
		cliente.recebaJogada(jogadaMarshalled);
	}

	public void iniciarNovaPartida(Integer posicao) {
		cliente.iniciarNovaPartida(posicao);
	}

	public void finalizarPartidaComErro(String message) {
		cliente.finalizarPartidaComErro(message);
	}

	public void tratarPartidaNaoInciada(String message) {
		cliente.tratarPartidaNaoInciada(message);
	}

}
