package sistema_bancario.pessoa;

import java.util.Random;

import sistema_bancario.banco.Conta;
import sistema_bancario.loja.Loja;

public class Cliente extends Pessoa implements Runnable {
    private Conta conta;

    public Cliente(String nome, String endereco, String cpf, Conta conta) {
        super(nome, endereco, cpf);
        this.conta = conta;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (conta.getSaldo() > 0) {
            int valorCompra = random.nextInt(2) == 0 ? 100 : 200; 
            Loja loja = random.nextInt(2) == 0 ? new Loja("Loja 1") : new Loja("Loja 2");
            realizarCompra(loja, valorCompra);
        }
    }

    private void realizarCompra(Loja loja, double valor) {
        synchronized (conta) {
            if (conta.getSaldo() >= valor) {
                conta.sacar(valor);
                System.out.println(getNome() + " realizou uma compra de R$" + valor + " na " + loja.getName());
            } else {
                System.out.println(getNome() + " não possui saldo suficiente para realizar a compra de R$" + valor);
            }
        }
    }
}

