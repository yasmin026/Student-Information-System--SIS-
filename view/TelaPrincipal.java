package view;

import model.Aluno;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TelaPrincipal extends JFrame {
    private List<Aluno> listaAlunos = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable tabela;
    private JLabel labelTotal, labelMediaGeral, labelAprovados;

    // palette Dark
    private final Color BG_DARK = new Color(43, 42, 42);
    private final Color BG_CARD = new Color(55, 54, 54);
    private final Color COLOR_ACCENT = new Color(255, 106, 40);
    private final Color COLOR_TEXT = new Color(230, 230, 230);

    public TelaPrincipal() {
        setTitle("Sistema de Gerenciamento de Alunos");
        setSize(950, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout(15, 15));
        mainPanel.setBackground(BG_DARK);
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Topo: Cards de Estatísticas
        mainPanel.add(criarPainelEstatisticas(), BorderLayout.NORTH);

        // Centro: Tabela de Alunos
        mainPanel.add(criarPainelTabela(), BorderLayout.CENTER);

        // Direita: Formulário de Cadastro
        mainPanel.add(criarPainelFormulario(), BorderLayout.EAST);
        add(mainPanel);
    }
}