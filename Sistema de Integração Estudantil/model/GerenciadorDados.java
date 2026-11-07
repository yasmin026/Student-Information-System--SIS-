package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorDados {
    private static final String ARQUIVO = "dados_alunos.dat";

    @SuppressWarnings("unchecked")
    public static List<Aluno> carregarAlunos() {
        File file = new File(ARQUIVO);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Aluno>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void salvarAlunos(List<Aluno> alunos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO))) {
            oos.writeObject(alunos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}