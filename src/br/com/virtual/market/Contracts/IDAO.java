package br.com.virtual.market.Contracts;

import br.com.virtual.market.Model.Adm;
import br.com.virtual.market.Model.Cliente;

import java.util.List;
import java.util.Map;

public interface IDAO {

    Cliente cadastrarCliente (Map<String, Object> user);


    Adm cadastrarAdm (Map<String, Object> adm);

    void removerCliente (Cliente cliente);

    List<Cliente> listarClientes ();

    List<Adm> listarAdm ();

}
