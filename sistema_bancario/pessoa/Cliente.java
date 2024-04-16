package sistema_bancario.pessoa;

import sistema_bancario.banco.Conta;

public class Cliente  extends Pessoa{
    
    private Conta conta; 

    public Cliente(String nome, String endereco, String cpf, Conta conta) {
        super(nome, endereco, cpf);
        this.conta = conta;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }
}
