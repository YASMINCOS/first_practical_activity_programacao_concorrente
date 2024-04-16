package sistema_bancario.banco;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Conta {

    private String numero;
    private double saldo;
    private Lock lock; 

    public Conta(String numero) {
        this.numero = numero;
        this.saldo = 0;
        this.lock = new ReentrantLock();
    }

    public double getSaldo() {
        return saldo;
    }

    public void depositar(double valor) {
        lock.lock(); // Adquire o lock para garantir exclusão mútua
        try {
            saldo += valor;
            System.out.println("Depósito de " + valor + " realizado. Novo saldo: " + saldo);
        } finally {
            lock.unlock(); // Libera o lock
        }
    }

    public void sacar(double valor) {
        lock.lock(); // Adquire o lock para garantir exclusão mútua
        try {
            if (saldo >= valor) {
                saldo -= valor;
                System.out.println("Saque de " + valor + " realizado. Novo saldo: " + saldo);
            } else {
                System.out.println("Saldo insuficiente para sacar " + valor);
            }
        } finally {
            lock.unlock(); // Libera o lock
        }
    }
    
}