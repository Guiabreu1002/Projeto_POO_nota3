package br.com.virtual.market.Contracts;

import br.com.virtual.market.Enum.ETipoUsuario;
import br.com.virtual.market.Model.Adm;
import br.com.virtual.market.Model.Cliente;
import br.com.virtual.market.Model.Produto;

import java.util.Map;

public interface IController {

    boolean validarEmail (String email);

    boolean validarPass (String pass);

    boolean validarTipo (ETipoUsuario tipo);

    boolean autenticarCliente (String email, String pass, ETipoUsuario tipo);

    boolean autenticarAdm (String email, String pass, ETipoUsuario tipo);

    Cliente inserirCliente(Map<String, Object> user);

    Cliente buscarPorEmail(String email);

    Adm inserirAdm(Map<String, Object> adm);

    void atualizarDados(String email, String pass ,Map<String, Object> dados);

    Cliente retornarCliente(String email, String pass);

    boolean validarProduto(Produto produto);
}
