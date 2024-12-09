# Sistema de Gerenciamento de SuperMecardo Virtual

Este é um sistema de gerenciamento de SuperMercado em Java, o sistema permite realizar cadastro de clientes e adms, permite realizar a compra de produtos pereciveis e não pereciveis, possui sistema de carrinho, historico de compras e notas fiscais basicas.

## Arquitetura

### O sistema é estruturado em MVC com uso do DAO:

## Model

- **Usuario**: Classe abstrata que define os atributos comuns a `Cliente` e `Adm`.
- **Cliente**: Extende `Usuario` e implementa a interface `ICliente`.
- **Adm**: Extende `Usuario` e implementa a interface `IAdm`.
- **Carrinho**: Classe que gera um array de `produtos`.
- **Produto**: Classe abstrata que define os atributos comuns a `ProdutoPerecivel` e `ProdutoNaoPerecivel`.
- **ProdutoPerecivel**: Extende `Produto`.
- **ProdutoNaoPerecivel**: Extende `Produto`.
- **Pagamento**: Classe abstrata que armazena o valor, a forma e status de uma transação.
- **Pedido**: Extende `Pagamento` e adiciona ao historico.
- **VirtualMarket**: Classe que contem o estoque e o `controller`.

## Controller

- **UsuarioController**: Classe que faz verificações e validações de dados e informações enviados do `Main`.

## View

- **VirtualMarketView**: Classe que retorna dados e valores solicitados pelo usuario no `Main`.

## Contracts

- **IAdm**: Interface usada para criar, remover e editar `Produtos`.
- **ICliente**: Interface usada para exibir informações sobre o `Cliente`.
- **ICarrinho**: Interface usada para funções do carrinho, como adicionar, remover, calcular total.
- **IController**: Interface usada para gerenciar dados enviados  do `Main` fazendo uma verificação e validação para o `VirtualMarket`, onde possui o estoque e para o `DAO` onde possue o banco de dados.
- **IDao**: Interface usuada para realizar o cadastro de clientes e adms.
- **IVirtualMarket**: Interface usada para realizar a venda de produtos e tambem pesquisa de produtos.
- **IVirtualMarketView**: Interface usada para retornar a informação solicitada pelo usuario.

## Enum

- **EFormaPagamento**: Enum que contem as formas de Pagamento, `PIX`, `BOLETO`, `CARTAO`.
- **EStatus**: Enum que contem Status, `SUCESSO`, `FALHA`, `PENDENTE`.
- **ETipoProdutos**: Enum que contem o tipo do produto, `PERECIVEL` e `NAO_PERECIVEL`.
- **ETipoUsuario**: Enum que contem o tipo de usuario, `CLIENTE`, `ADM`.

## Main

- **Main**: Onde é instaciado todo o programa.

## Funcionalidades

- **Cadastro de Clientes**: Permite o usuario se cadastrar no Programa se tornando cliente.
- **Cadastro de Adm**: Permite que outro adm cadastre um adm.
- **Cadastro de Produtos**: Permite o adm realizar o cadastro de Produtos.
- **Remover Produtos**: Permite o adm remover Produtos do estoque.
- **Pesquisar Produtos**: Permite a pesquisa de um Produto.
- **Consulta Historico de compras**: Permite o Cliente consultar o historico de compras.
- **Consultar Notas Fiscais**: Permite o Cliente consultar o historico de notas fiscais.
- **Remover Cliente**: Permite o adm remover clientes.
- **Vender Produto**: Permite o Cliente comprar um produto, gerando uma nota e adicionando no historico, tambem decrementando a quantidade do produto no estoque.

## Diagrama do Projeto
![projetoVirtualMarket2 0 drawio](https://github.com/user-attachments/assets/b85193c8-9801-4762-917d-cac9cb991468)
