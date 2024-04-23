
# Sistema Bancário Multithreaded 

Este é um projeto de simulação de um sistema bancário multithreaded. O sistema utiliza threads para representar entidades como bancos, lojas, funcionários e clientes, permitindo a execução concorrente de diferentes operações bancárias.

## Funcionalidades do Sistema

O sistema possui as seguintes entidades:

- **Banco:** Representa a entidade principal responsável por intermediar as transações e garantir a consistência dos saldos das contas.
  
- **Loja:** Representa um estabelecimento onde os clientes realizam compras. Cada loja possui uma conta para receber os pagamentos dos clientes e paga os funcionários.

- **Funcionário:** Representa um funcionário de uma loja. Cada funcionário possui duas contas: uma para receber o salário da loja e outra de investimentos.

- **Cliente:** Representa um cliente do banco. Cada cliente possui uma conta com um saldo inicial e realiza compras alternadas entre as lojas até que o saldo da conta esteja vazio.

## Utilização do Runnable

O uso da interface `Runnable` foi adotado para separar a lógica da tarefa que cada thread executa da própria thread. Isso promove um design mais flexível e modular, facilitando a execução concorrente de múltiplas tarefas no sistema.

## Funcionamento do Sistema

1. O `Main` inicializa o sistema, criando instâncias de banco, lojas, funcionários e clientes.

2. Cada cliente é executado em uma thread separada, realizando compras alternadas entre as lojas até que o saldo da conta esteja vazio.

3. As lojas recebem os pagamentos dos clientes e pagam os funcionários assim que possuem o valor dos salários.

4. Cada funcionário recebe seu salário, investe uma parte dele em uma conta de investimentos e realiza operações bancárias.

5. O banco coordena as transações, garantindo a consistência dos saldos das contas.

## Observações

- O uso de locks (`Lock`) foi adotado para garantir a sincronização entre as threads e evitar condições de corrida.
  
- Todas as operações bancárias são protegidas por locks para garantir a atomicidade e a consistência das transações.

- Clientes e Funcionarios usam obrigatoriamente Threads

