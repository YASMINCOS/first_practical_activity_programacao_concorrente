package sistema_bancario.pessoa;

import sistema_bancario.banco.Conta;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Funcionario extends Thread {
    private double salario;
    private Conta contaSalario;
    private Conta contaInvestimentos;
    private Lock lock;

    public Funcionario(String nome, String endereco, String cpf, double salario, Conta contaSalario, Conta contaInvestimentos) {
        super(nome);
        this.salario = salario;
        this.contaSalario = contaSalario;
        this.contaInvestimentos = contaInvestimentos;
        this.lock = new ReentrantLock();
    }

    @Override
    public void run() {
        realizarOperacoes();
    }

    private void realizarOperacoes() {
        lock.lock();
        try {
            receberSalario();
            investirSalario();
        } finally {
            lock.unlock();
        }
    }

    public void receberSalario() {
        contaSalario.depositar(salario);
        System.out.println(getName() + " recebeu o salário de R$" + salario);
    }

    private void investirSalario() {
        double valorInvestimento = salario * 0.2;
        if (contaSalario.getSaldo() >= valorInvestimento) {
            contaSalario.sacar(valorInvestimento);
            contaInvestimentos.depositar(valorInvestimento);
            System.out.println(getName() + " investiu R$" + valorInvestimento + " em conta de investimentos");
        } else {
            System.out.println(getName() + " não possui saldo suficiente para investir R$" + valorInvestimento);
        }
    }

    public double getSalario() {
        return salario;
    }
}
