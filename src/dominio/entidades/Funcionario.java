package dominio.entidades;

import java.math.BigDecimal;

public class Funcionario {
    private String nome;
    private String email;
    private BigDecimal salario;

    public Funcionario(String nome, String email, BigDecimal salario) {
        this.nome = nome;
        this.email = email;
        this.salario = salario;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public BigDecimal getSalario() {
        return salario;
    }
}
