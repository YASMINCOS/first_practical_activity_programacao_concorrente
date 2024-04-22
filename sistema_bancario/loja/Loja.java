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
            exibirSaldo();
            if (contaRecebimento.getSaldo() >= 1400.0) {
                realizarPagamento();
            }
        } finally {
            lock.unlock();
        }
    }

    private void realizarPagamento() {
        lock.lock();
        try {
            double saldoAtual = contaRecebimento.getSaldo();
            if (saldoAtual >= 1400.0) {
                for (Funcionario funcionario : funcionarios) {
                    if (!funcionario.recebeuSalario()) {
                        double salario = funcionario.getSalario();
                        if (saldoAtual >= salario) {
                            contaRecebimento.sacar(salario);
                            funcionario.receberSalario(salario);
                            System.out.println("### Pagamento de Salários ###");
                            System.out.println("A loja " + nome + " pagou o salário de R$" + salario + " ao funcionário(loja) " + funcionario.getNome());
                            funcionario.investirSalario();
                            saldoAtual -= salario; 
                            atualizarSaldo(saldoAtual); 

                        } else {
                            break; 
                        }
                    }
                }
                exibirSaldo();
            }
        } finally {
            lock.unlock();
        }
    }

    

    private void atualizarSaldo(double novoSaldo) {
        contaRecebimento.setSaldo(novoSaldo);
    }

    public void exibirSaldo() {
        System.out.println("--- Saldo atual da loja " + nome + ": R$" + contaRecebimento.getSaldo() + " ---");
    }
}
