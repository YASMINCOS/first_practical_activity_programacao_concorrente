package sistema_bancario.pessoa;

import sistema_bancario.banco.Conta;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Funcionario extends Pessoa implements Runnable {
    private String cargo;
    private double salario;
    private Conta contaSalario;
    private Conta contaInvestimentos;
    private Lock lock;

    public Funcionario(String nome, String endereco, String cpf, String cargo, double salario, Conta contaSalario, Conta contaInvestimentos) {
        super(nome, endereco, cpf);
        this.cargo = cargo;
        this.salario = salario;
        this.contaSalario = contaSalario;
        this.contaInvestimentos = contaInvestimentos;
        this.lock = new ReentrantLock();
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    @Override
    public void run() {
        receberSalario(salario);
        investirSalario();
    }

    public void receberSalario(double valor) {
        lock.lock(); 
        try {
            contaSalario.depositar(valor);
            System.out.println(getNome() + " recebeu o salário de R$" + valor);
        } finally {
            lock.unlock(); 
        }
    }

    private void investirSalario() {
        double valorInvestido = salario * 0.2;
        lock.lock(); 
        try {
            if (contaSalario.getSaldo() >= valorInvestido) {
                contaSalario.sacar(valorInvestido);
                contaInvestimentos.depositar(valorInvestido);
                System.out.println(getNome() + " investiu R$" + valorInvestido + " em conta de investimentos");
            } else {
                System.out.println(getNome() + " não possui saldo suficiente para investir R$" + valorInvestido);
            }
        } finally {
            lock.unlock(); 
        }
    }
}
