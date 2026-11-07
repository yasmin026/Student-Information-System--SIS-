package model;

import java.io.Serializable;

public class Aluno implements Serializable {
    private static final long serialVersionUID = 1L;

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

    public double getMedia() {
        return (nota1 + nota2) / 2.0;
    }

    public String getStatus() {
        return getMedia() >= 6.0 ? "Aprovado" : "Reprovado";
    }

    // Getters
    public String getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public String getCurso() {
        return curso;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }
}