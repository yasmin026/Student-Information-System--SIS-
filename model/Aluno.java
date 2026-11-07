package SIS.model;

import java.io.Serializable;

public class Aluno implements Serializable {
    private String matricula;
    private String nome;
    private String curso;
    private double nota1;
    private double nota2;

    public Aluno(String matricula, String nome, String curso, double nota1, double nota2) {
        this.matricula = matricula;
        this.nome = nome;
        this.curso = curso;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

}
