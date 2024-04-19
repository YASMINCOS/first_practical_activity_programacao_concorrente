package sistema_bancario.loja;

import sistema_bancario.banco.Banco;
import sistema_bancario.banco.Conta;
import sistema_bancario.pessoa.Funcionario;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Loja {
    private String nome;
    private Banco banco;
    private Conta conta;
    private Lock lock;

    public Loja(String nome, Banco banco, Conta conta) {
        this.nome = nome;
        this.banco = banco;
        this.conta = conta;
        this.lock = new ReentrantLock();
    }

    public String getName() {
        return nome;
    }

    public void receberPagamento(double valor) {
        lock.lock(); 
        try {
            conta.depositar(valor);
            System.out.println("A loja " + nome + " recebeu um pagamento de R$" + valor);
        } finally {
            lock.unlock();
        }
    }

    public void pagarSalarios(Funcionario funcionario) {
        lock.lock();
        try {
            if (conta.getSaldo() >= funcionario.getSalario()) {
                conta.sacar(funcionario.getSalario());
                funcionario.receberSalario(funcionario.getSalario());
                System.out.println("A loja " + nome + " pagou o salário de R$" + funcionario.getSalario() + " ao funcionário " + funcionario.getNome());
            } else {
                System.out.println("A loja " + nome + " não possui saldo suficiente para pagar o salário do funcionário " + funcionario.getNome());
            }
        } finally {
            lock.unlock(); 
        }
    }
   
}
