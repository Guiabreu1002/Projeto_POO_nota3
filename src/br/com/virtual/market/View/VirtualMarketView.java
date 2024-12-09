package br.com.virtual.market.View;

import br.com.virtual.market.Contracts.IVirtualMarketView;
import br.com.virtual.market.Enum.EFormaPagamento;
import br.com.virtual.market.Enum.ETipoProdutos;

import java.util.Scanner;

public class VirtualMarketView  implements IVirtualMarketView {

    @Override
    public int inputOp () {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Escolha uma das opções acima: ");
            int op = scanner.nextInt();

            return op;
        } catch (Exception e) {
            System.out.println("\nOpção invalida!\n");
        }
        return 0;
    }
    @Override
    public String inputNome () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu Nome: ");
        String nome = scanner.nextLine();

        return nome;
    }
    @Override
    public String inputEmail () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite seu Email: ");
        String email = scanner.nextLine();

        return email;
    }
    @Override
    public String inputPass () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite sua Senha: ");
        String senha = scanner.nextLine();

        return senha;
    }
    @Override
    public int inputQuantidade () {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite a quantidade do Produto: ");
            int quantidade = scanner.nextInt();

            return quantidade;
        } catch (Exception e) {
            System.out.println("\nQuantidade Invalida!\n");
        }
        return 0;
    }
    @Override
    public String inputNomeProduto () {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o Nome do Produto: ");
        String nomeProduto = scanner.nextLine();

        return nomeProduto;
    }
    @Override
    public double inputPreco () {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Digite o valor do produto: ");
            double preco = scanner.nextDouble();

            return preco;
        } catch (Exception e) {
            System.out.println("\nValor Invalido\n");
        }
        return 0.0;
    }
    @Override
    public String inputDescricao() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a descrição do produto: ");
        String describe = scanner.nextLine();

        return describe;
    }
    @Override
    public ETipoProdutos inputTipoProduto() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Escolha o tipo do produto..\n" +
                    "1-Perecivel\n" +
                    "2-Não Perecivel\n" +
                    "\nEscolha uma das formas: ");
            int tipo = scanner.nextInt();

            if (tipo == 1) {
                return ETipoProdutos.PERECIVEL;
            } else if (tipo == 2) {
                return ETipoProdutos.NAO_PERECIVEL;
            }else{
                System.err.println("O tipo não foi passado");
            }
        } catch (Exception e) {
            System.out.println("\nTipo do produto invalido!\n");
        }
        return null;
    }
    @Override
    public EFormaPagamento inputFormaPagamento () {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Escolha a forma de pagamento.\n" +
                    "1-Cartão\n" +
                    "2-Pix\n" +
                    "3-Boleto\n" +
                    "\nEscolha uma das formas: ");
            int forma = scanner.nextInt();

            if (forma == 1) {
                return EFormaPagamento.CARTAO;
            } else if (forma == 2) {
                return EFormaPagamento.PIX;
            }else if (forma == 3){
                return EFormaPagamento.BOLETO;
            } else {
                System.err.println("\nO forma de pagamento não foi passado!\n");
            }
        } catch (Exception e) {
            System.out.println("\nOpção invalida!\n");
        }
        return null;
    }
}
