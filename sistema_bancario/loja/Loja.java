package sistema_bancario.loja;

import sistema_bancario.banco.Banco;
import sistema_bancario.banco.Conta;

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

   
}
