package br.com.virtual.market.DAO;

import br.com.virtual.market.Contracts.IDAO;
import br.com.virtual.market.Enum.ETipoUsuario;
import br.com.virtual.market.Model.Adm;
import br.com.virtual.market.Model.Cliente;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DAO implements IDAO {

    private ArrayList<Cliente> bancoDeDadosCliente = new ArrayList<>();
    private ArrayList<Adm> bancoDeDadosAdm = new ArrayList<>();


    @Override
    public Cliente cadastrarCliente(Map<String, Object> user) {
        Cliente cliente = new Cliente(
                user.get("nome").toString(),
                user.get("email").toString(),
                user.get("senha").toString(),
                (ETipoUsuario) user.get("tipo")
        );
        System.out.println("\nCadastro Realizado com sucesso!\n");
        this.bancoDeDadosCliente.add(cliente);
        return cliente;
    }

    @Override
    public Adm cadastrarAdm(Map<String, Object> user) {
        Adm adm = new Adm(
                user.get("nome").toString(),
                user.get("email").toString(),
                user.get("senha").toString(),
                (ETipoUsuario) user.get("tipo")
        );
        this.bancoDeDadosAdm.add(adm);
        return adm;
    }

    @Override
    public void removerCliente(Cliente cliente) {
        try {
            if (!this.bancoDeDadosCliente.isEmpty()) {
                for (Cliente cliente1 : bancoDeDadosCliente) {
                    if (cliente.getEmail().equalsIgnoreCase(cliente1.getEmail())) {
                        bancoDeDadosCliente.remove(cliente1);
                        System.out.println("\nCliente removido com sucesso!\n");
                        break;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Cliente> listarClientes() {
        return bancoDeDadosCliente;
    }

    @Override
    public List<Adm> listarAdm() {
        return bancoDeDadosAdm;
    }
}
