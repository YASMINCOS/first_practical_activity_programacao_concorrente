package sistema_bancario.pessoa;

import sistema_bancario.banco.Conta;
import sistema_bancario.loja.Loja;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Cliente extends Pessoa implements Runnable {
    private Conta conta;
    private Loja loja1;
    private Loja loja2;

    public Cliente(String nome, String endereco, String cpf, Conta conta, Loja loja1, Loja loja2) {
        super(nome, endereco, cpf);
        this.conta = conta;
        this.loja1 = loja1;
        this.loja2 = loja2;
    }

    @Override
    public void run() {
        realizarCompras();
    }

    public void realizarCompras() {
        while (true) {
            int valorCompra = Math.random() < 0.5 ? 100 : 200;
            Lock lock = new ReentrantLock();
            lock.lock();
            try {
                if (conta.getSaldo() >= valorCompra) {
                    conta.sacar(valorCompra);
                    System.out.println(getNome() + " realizou uma compra de R$" + valorCompra);
                    if (Math.random() < 0.5) {
                        loja1.receberPagamento(valorCompra);
                    } else {
                        loja2.receberPagamento(valorCompra);
                    }
                } else {
                    break;
                }
            } finally {
                lock.unlock();
            }
        }
        System.out.println(getNome() + " terminou suas compras. Saldo final: R$" + conta.getSaldo());
    }
}
