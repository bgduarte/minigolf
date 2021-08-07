/* This file is part of the DualRpcServer libraries
   Copyright (c) 2004-2005, Vick Perry

   This library is free software; you can redistribute it and/or
   modify it under the terms of the BSD license. See the contents
   of the file LICENSE.txt for more details.
 */
package br.ufsc.inf.leobr.cliente;

import org.apache.log4j.Logger;


import com.retrogui.dualrpc.client.IClientCallbackHandler;

/**
 * Lost connection handler will terminate client if connection is broken. You
 * could also display a dialog to the user, etc.
 */
public class ConexaoPerdidaTratadorCliente implements IClientCallbackHandler {

	public ClienteReceptor cliente = null;

	public static Logger logger = Logger.getLogger(ConexaoPerdidaTratadorCliente.class
			.getName());
	
	public ConexaoPerdidaTratadorCliente(ClienteReceptor cliente) {
		this.cliente = cliente;
	}

	/**
	 * Método de callback chamado quando a conexão é perdida.
	 */
	public void brokenConnection() {

		logger.info("Conexão de rede caiu. Cliente foi finalizado");
		cliente.tratarPerdaConexao();

	}

}
