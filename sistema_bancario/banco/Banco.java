package sistema_bancario.banco;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banco {
    private String nome;
    private double saldoInicial;
    private List<Conta> contas;
    private Lock lock;

    public Banco(String nome, double saldoInicial) {
        this.nome = nome;
        this.saldoInicial = saldoInicial;
        this.contas = new ArrayList<>();
        this.lock = new ReentrantLock();
    }

    public String getNome() {
        return nome;
    }

    public double getSaldoInicial() {
        return saldoInicial;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void removerConta(Conta conta) {
        contas.remove(conta);
    }

    public void transferir(Conta origem, Conta destino, double valor) {
        lock.lock();
        try {
            if (origem.getSaldo() >= valor) {
                origem.sacar(valor);
                destino.depositar(valor);
                System.out.println("Transferência de R$" + valor + " da conta " + origem.getNumero() + " para a conta " + destino.getNumero() + " realizada com sucesso.");
            } else {
                System.out.println("Saldo insuficiente na conta " + origem.getNumero() + " valor: " + origem.getSaldo() +" para realizar a transferência de R$" + valor);
            }
        } finally {
            lock.unlock();
        }
    }
}
