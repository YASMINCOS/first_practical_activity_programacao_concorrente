package sistema_bancario;

import sistema_bancario.banco.Banco;
import sistema_bancario.banco.Conta;
import sistema_bancario.loja.Loja;
import sistema_bancario.pessoa.Cliente;
import sistema_bancario.pessoa.Funcionario;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Banco banco = new Banco("Nubank", 1000000000);

        Conta contaLoja1 = new Conta("1", 0);
        Conta contaLoja2 = new Conta("2", 0);

        List<Funcionario> funcionariosLoja1 = new ArrayList<>();
        List<Funcionario> funcionariosLoja2 = new ArrayList<>();

        Conta contaFuncionario1Loja1 = new Conta("Funcionario1Loja1", 0);
        Conta contaFuncionario2Loja1 = new Conta("Funcionario2Loja1", 0);
        Conta contaFuncionario1Loja2 = new Conta("Funcionario1Loja2", 0);
        Conta contaFuncionario2Loja2 = new Conta("Funcionario2Loja2", 0);

        Conta contaInvestimentoFuncionario1Loja1 = new Conta("InvestimentoFuncionario1Loja1", 0);
        Conta contaInvestimentoFuncionario2Loja1 = new Conta("InvestimentoFuncionario2Loja1", 0);
        Conta contaInvestimentoFuncionario1Loja2 = new Conta("InvestimentoFuncionario1Loja2", 0);
        Conta contaInvestimentoFuncionario2Loja2 = new Conta("InvestimentoFuncionario2Loja2", 0);

        Funcionario funcionario1Loja1 = new Funcionario("Funcionario1Loja1", "Endereco1", "CPF1", 1400,
                contaFuncionario1Loja1, contaInvestimentoFuncionario1Loja1, contaLoja1);
        Funcionario funcionario2Loja1 = new Funcionario("Funcionario2Loja1", "Endereco2", "CPF2", 1400,
                contaFuncionario2Loja1, contaInvestimentoFuncionario2Loja1, contaLoja2);
        Funcionario funcionario1Loja2 = new Funcionario("Funcionario1Loja2", "Endereco3", "CPF3", 1400,
                contaFuncionario1Loja2, contaInvestimentoFuncionario1Loja2, contaLoja1);
        Funcionario funcionario2Loja2 = new Funcionario("Funcionario2Loja2", "Endereco4", "CPF4", 1400,
                contaFuncionario2Loja2, contaInvestimentoFuncionario2Loja2, contaLoja2);

        funcionariosLoja1.add(funcionario1Loja1);
        funcionariosLoja1.add(funcionario2Loja1);
        funcionariosLoja2.add(funcionario1Loja2);
        funcionariosLoja2.add(funcionario2Loja2);

        Loja loja1 = new Loja("Loja 1", banco, contaLoja1, funcionariosLoja1);
        Loja loja2 = new Loja("Loja 2", banco, contaLoja2, funcionariosLoja2);

        Conta contaCliente1 = new Conta("C1", 1000);
        Conta contaCliente2 = new Conta("C2", 1000);
        Conta contaCliente3 = new Conta("C3", 1000);
        Conta contaCliente4 = new Conta("C4", 1000);
        Conta contaCliente5 = new Conta("C5", 1000);

        Cliente cliente1 = new Cliente("Cliente1", "Endereco1", "CPF1", contaCliente1, loja1, loja2);
        Cliente cliente2 = new Cliente("Cliente2", "Endereco2", "CPF2", contaCliente2, loja1, loja2);
        Cliente cliente3 = new Cliente("Cliente3", "Endereco3", "CPF3", contaCliente3, loja1, loja2);
        Cliente cliente4 = new Cliente("Cliente4", "Endereco4", "CPF4", contaCliente4, loja1, loja2);
        Cliente cliente5 = new Cliente("Cliente5", "Endereco5", "CPF5", contaCliente5, loja1, loja2);

        Thread threadCliente1 = new Thread(cliente1);
        Thread threadCliente2 = new Thread(cliente2);
        Thread threadCliente3 = new Thread(cliente3);
        Thread threadCliente4 = new Thread(cliente4);
        Thread threadCliente5 = new Thread(cliente5);

        threadCliente1.start();
        threadCliente2.start();
        threadCliente3.start();
        threadCliente4.start();
        threadCliente5.start();

        Thread threadFuncionario1Loja1 = new Thread(funcionario1Loja1);
        Thread threadFuncionario2Loja1 = new Thread(funcionario2Loja1);
        Thread threadFuncionario1Loja2 = new Thread(funcionario1Loja2);
        Thread threadFuncionario2Loja2 = new Thread(funcionario2Loja2);

        try {
            threadCliente1.join();
            threadCliente2.join();
            threadCliente3.join();
            threadCliente4.join();
            threadCliente5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        threadFuncionario1Loja1.start();
        threadFuncionario2Loja1.start();
        threadFuncionario1Loja2.start();
        threadFuncionario2Loja2.start();

        try {
            threadFuncionario1Loja1.join();
            threadFuncionario2Loja1.join();
            threadFuncionario1Loja2.join();
            threadFuncionario2Loja2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
