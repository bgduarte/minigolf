/* This file is part of the DualRpcServer libraries
   Copyright (c) 2004-2005, Vick Perry

   This library is free software; you can redistribute it and/or
   modify it under the terms of the BSD license. See the contents
   of the file LICENSE.txt for more details.
*/
package br.ufsc.inf.leobr.cliente;

import java.io.*;

// This class is used by the handshake() method to exchange information with
// the client. Note that this class is Serializable so that it can be used as
// an RPC method parameter or return value.
public final class DadosAplicacao implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 2481927151974791444L;
  
  public Long serverSessionId = null;

  
  // Add other data fields that you need.
  // Note that all fields must be Serializable Objects...

}
