package sistema_bancario.loja;

import sistema_bancario.banco.Banco;
import sistema_bancario.banco.Conta;
import sistema_bancario.pessoa.Funcionario;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Loja {
    private String nome;
    private Banco banco;
    private Conta contaRecebimento;
    private List<Funcionario> funcionarios; 
    private Lock lock;

    public Loja(String nome, Banco banco, Conta contaRecebimento, List<Funcionario> funcionarios) {
        this.nome = nome;
        this.banco = banco;
        this.contaRecebimento = contaRecebimento;
        this.funcionarios = funcionarios; 
        this.lock = new ReentrantLock();
    }

    public String getNome() {
        return nome;
    }

    public void receberPagamento(double valor) {
        lock.lock();
        try {
            contaRecebimento.depositar(valor);
            System.out.println("A loja " + nome + " recebeu um pagamento de R$" + valor);
        } finally {
            lock.unlock();
        }
    }

    public void pagarSalarios() {
        lock.lock();
        try {
            double totalSalarios = calcularTotalSalarios();
            if (contaRecebimento.getSaldo() >= totalSalarios) {
                for (Funcionario funcionario : funcionarios) {
                    double salario = funcionario.getSalario();
                    if (contaRecebimento.getSaldo() >= salario) {
                        contaRecebimento.sacar(salario);
                        Thread threadFuncionario = new Thread(funcionario);
                        threadFuncionario.start();
                        try {
                            threadFuncionario.join(); 
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("A loja " + nome + " pagou o salário de R$" + salario + " ao funcionário " + funcionario.getNome());
                    } else {
                        System.out.println("A loja " + nome + " não possui saldo suficiente para pagar o salário do funcionário " + funcionario.getNome());
                    }
                }
            } else {
                System.out.println("A loja " + nome + " não possui saldo suficiente para pagar os salários dos funcionários.");
            }
        } finally {
            lock.unlock();
        }
    }
    

    private double calcularTotalSalarios() {
        double total = 0;
        for (Funcionario funcionario : funcionarios) {
            total += funcionario.getSalario();
        }
        return total;
    }
}
