package br.com.virtual.market.Controller;

import br.com.virtual.market.Contracts.IController;
import br.com.virtual.market.DAO.DAO;
import br.com.virtual.market.Enum.ETipoUsuario;
import br.com.virtual.market.Model.Adm;
import br.com.virtual.market.Model.Cliente;
import br.com.virtual.market.Model.Produto;

import java.util.Map;

public class UsuarioController implements IController {

    public DAO dao;

    public UsuarioController () {
        this.dao = new DAO();
    }

    @Override
    public boolean autenticarCliente (String email, String pass, ETipoUsuario tipo) {
        for (Cliente cliente : this.dao.listarClientes()) {
            if (email.equalsIgnoreCase(cliente.getEmail()) && pass.equalsIgnoreCase(cliente.getPass()) && tipo == ETipoUsuario.CLIENTE) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean autenticarAdm(String email, String pass, ETipoUsuario tipo) {
        for (Adm adm : this.dao.listarAdm()) {
            if (email.equalsIgnoreCase(adm.getEmail()) && pass.equalsIgnoreCase(adm.getPass()) && tipo == ETipoUsuario.ADM) {
                return true;
            }
        }
        return false;
    }


    @Override
    public boolean validarEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if (email.matches(emailRegex)) {
            for (Cliente cliente : this.dao.listarClientes()) {
                if (cliente.getEmail().equals(email)) {
                    System.out.println("\nEsse email ja existe:" + email + "\n");
                    return false;
                }
            }
            return true;
        }else{
            System.out.println("\nEmail com formato invalido\n");
        }
        return false;
    }

    @Override
    public boolean validarPass(String pass) {
        String passRegex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

        if (pass.matches(passRegex)) {
            return true;
        }else if (pass.length() < 8){
            System.err.println("\nSenha muito curta\n");
            return false;
        }else{
            System.err.println("\nFormato de senha invalido\n");
        }
        return false;
    }

    @Override
    public boolean validarTipo(ETipoUsuario tipo) {
        if (tipo == null){
            System.err.println("\nO tipo é nulo\n");
            return false;
        }
        return true;
    }

    @Override
    public Cliente inserirCliente(Map<String, Object> user) {
        if (validarEmail(user.get("email").toString()) && validarPass(user.get("senha").toString()) && validarTipo((ETipoUsuario) user.get("tipo"))) {
            return this.dao.cadastrarCliente(user);
        }
        return null;
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        if (!dao.listarClientes().isEmpty()) {
            for (Cliente cliente : dao.listarClientes()) {
                if (email.equalsIgnoreCase(cliente.getEmail())) {
                    System.out.println("\nUsuario encontrado!\n");
                    return cliente;
                }
            }
        } else {
            System.out.println("\nBanco de dados vazio!\n");
        }
        return null;
    }

    @Override
    public Adm inserirAdm(Map<String, Object> adm) {
        if (validarEmail(adm.get("email").toString()) && validarPass(adm.get("senha").toString()) && validarTipo((ETipoUsuario) adm.get("tipo"))) {
            return this.dao.cadastrarAdm(adm);
        }
        return null;
    }

    @Override
    public void atualizarDados(String email, String pass, Map<String, Object> dados) {
        for (Cliente usuario : dao.listarClientes()) {
            if (email.equalsIgnoreCase(usuario.getEmail()) && pass.equalsIgnoreCase(usuario.getPass())) {

                usuario.setNome((String) dados.get("nome"));
                usuario.setEmail((String) dados.get("email"));
                usuario.setPass((String) dados.get("senha"));
                usuario.setTipo((ETipoUsuario) dados.get("tipo"));

                System.out.println("\nSucesso");
            }
        }
    }
    
    @Override
    public Cliente retornarCliente(String email, String pass) {
        for (Cliente user : this.dao.listarClientes()) {
            if (email.equalsIgnoreCase(user.getEmail()) && pass.equalsIgnoreCase(user.getPass())) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean  validarProduto(Produto produto) {
        if (produto.getNome().length() < 3) {
            System.out.println("\nNome invalido\n");
            return false;
        } else if (produto.getPreco() <= 0 || produto.getPreco() >= 10000 || produto.getPreco() == 0) {
            System.out.println("\nPreço invalido\n");
            return false;
        } else if (produto.getDescricao().length() < 5) {
            System.out.println("\nDescrição invalida\n");
            return false;
        } else if (produto.getQuantidade() <= 0 || produto.getQuantidade() >= 10000 || produto.getQuantidade() == 0) {
            System.out.println("\nQuantidade invalida\n");
            return false;
        }else{
            return true;
        }
    }
}