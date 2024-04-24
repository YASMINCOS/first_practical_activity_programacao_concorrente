package sistema_bancario.pessoa;

import sistema_bancario.banco.Conta;
import sistema_bancario.loja.Loja;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cliente extends Pessoa implements Runnable {
    private Conta conta;
    private Loja loja1;
    private Loja loja2;
    private Lock lock;

    public Cliente(String nome, String endereco, String cpf, Conta conta, Loja loja1, Loja loja2) {
        super(nome, endereco, cpf);
        this.conta = conta;
        this.loja1 = loja1;
        this.loja2 = loja2;
        this.lock = new ReentrantLock();
    }

    @Override
    public void run() {
        realizarCompras();
    }

    public void realizarCompras() {
        while (true) {
            int valorCompra = Math.random() < 0.5 ? 100 : 200;
            lock.lock();
            try {
                if (conta.getSaldo() >= valorCompra) {
                    conta.sacar(valorCompra);
                    String nomeLoja = Math.random() < 0.5 ? loja1.getNome() : loja2.getNome();
                    System.out.println("#INICIANDO COMPRA#");
                    System.out.println(getNome() + " realizou uma compra de R$" + valorCompra + " na loja " + nomeLoja);
                    if (Math.random() < 0.5) {
                        loja1.receberPagamento(valorCompra);
                    } else {
                        loja2.receberPagamento(valorCompra);
                    }
                    try {
                        Thread.sleep(1000); 
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        System.err.println("Thread interrupted while sleeping: " + e.getMessage());
                    }
                } else {
                    System.out.println(getNome() + " não possui saldo suficiente para realizar a compra de R$" + valorCompra);
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
        System.out.println(getNome() + " terminou suas compras. Saldo final: R$" + conta.getSaldo());
    }
}
