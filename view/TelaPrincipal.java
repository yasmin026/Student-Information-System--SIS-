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

    private JPanel criarPainelEstatisticas() {
        JPanel panel = new JPanel(new GridLayout(1, 3, 15, 0));
        panel.setOpaque(false);

        labelTotal = new JLabel("0", SwingConstants.CENTER);
        labelMediaGeral = new JLabel("0.0", SwingConstants.CENTER);
        labelAprovados = new JLabel("0%", SwingConstants.CENTER);

        panel.add(criarCardEstatistica("Total de Alunos", labelTotal, COLOR_ACCENT));
        panel.add(criarCardEstatistica("Média Geral", labelMediaGeral, new Color(100, 180, 246)));
        panel.add(criarCardEstatistica("Taxa de Aprovação", labelAprovados, new Color(76, 175, 80)));

        return panel;
    }

    private JPanel criarCardEstatistica(String titulo, JLabel labelValor, Color corDestaque) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBackground(BG_CARD);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 4, 0, 0, corDestaque),
                BorderFactory.createEmptyBorder(10, 15, 10, 15)));

        JLabel lblTitulo = new JLabel(titulo);
        lblTitulo.setForeground(new Color(170, 170, 170));
        lblTitulo.setFont(new Font("SansSerif", Font.PLAIN, 12));

        labelValor.setForeground(COLOR_TEXT);
        labelValor.setFont(new Font("SansSerif", Font.BOLD, 24));

        card.add(lblTitulo, BorderLayout.NORTH);
        card.add(labelValor, BorderLayout.CENTER);
        return card;
    }

    private JPanel criarPainelTabela() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(BG_CARD);

        String[] colunas = { "Matrícula", "Nome", "Curso", "N1", "N2", "Média", "Status" };
        tableModel = new DefaultTableModel(colunas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tabela = new JTable(tableModel);
        tabela.setBackground(BG_CARD);
        tabela.setForeground(COLOR_TEXT);
        tabela.setGridColor(new Color(70, 70, 70));
        tabela.setRowHeight(28);
        tabela.getTableHeader().setBackground(BG_DARK);
        tabela.getTableHeader().setForeground(COLOR_TEXT);
        tabela.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 13));

        // Renderizador de Status Colorido (Aprovado/Reprovado)
        tabela.getColumnModel().getColumn(6).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if ("Aprovado".equals(value)) {
                    c.setForeground(new Color(76, 175, 80));
                    setFont(getFont().deriveFont(Font.BOLD));
                } else {
                    c.setForeground(new Color(244, 67, 54));
                    setFont(getFont().deriveFont(Font.BOLD));
                }
                return c;
            }
        });
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.getViewport().setBackground(BG_CARD);
        scroll.setBorder(BorderFactory.createEmptyBorder());

        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

}