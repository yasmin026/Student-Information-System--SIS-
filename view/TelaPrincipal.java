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

    private JPanel criarPainelFormulario() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(BG_CARD);
        panel.setPreferredSize(new Dimension(280, 0));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        JLabel title = new JLabel("Cadastrar Aluno");
        title.setFont(new Font("SansSerif", Font.BOLD, 16));
        title.setForeground(COLOR_TEXT);
        panel.add(title, gbc);

        JTextField txtMatricula = criarTextField();
        JTextField txtNome = criarTextField();
        JTextField txtCurso = criarTextField();
        JTextField txtNota1 = criarTextField();
        JTextField txtNota2 = criarTextField();

        adicionarCampo(panel, "Matrícula:", txtMatricula, gbc, 1);
        adicionarCampo(panel, "Nome:", txtNome, gbc, 3);
        adicionarCampo(panel, "Curso:", txtCurso, gbc, 5);
        adicionarCampo(panel, "Nota 1:", txtNota1, gbc, 7);
        adicionarCampo(panel, "Nota 2:", txtNota2, gbc, 9);

        JButton btnSalvar = new JButton("Adicionar Aluno");
        btnSalvar.setBackground(COLOR_ACCENT);
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("SansSerif", Font.BOLD, 14));
        btnSalvar.setFocusPainted(false);
        btnSalvar.setBorderPainted(false);

        gbc.gridy = 11;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(15, 5, 5, 5);
        panel.add(btnSalvar, gbc);

        btnSalvar.addActionListener(e -> {
            try {
                Aluno a = new Aluno(
                        txtMatricula.getText(),
                        txtNome.getText(),
                        txtCurso.getText(),
                        Double.parseDouble(txtNota1.getText()),
                        Double.parseDouble(txtNota2.getText()));
                adicionarAluno(a);
                txtMatricula.setText("");
                txtNome.setText("");
                txtCurso.setText("");
                txtNota1.setText("");
                txtNota2.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Preencha as notas corretamente!", "Erro de Validação",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        return panel;
    }

    private void adicionarCampo(JPanel p, String label, JTextField txt, GridBagConstraints gbc, int y) {
        gbc.gridy = y;
        gbc.gridwidth = 2;
        JLabel lbl = new JLabel(label);
        lbl.setForeground(new Color(180, 180, 180));
        p.add(lbl, gbc);

        gbc.gridy = y + 1;
        p.add(txt, gbc);
    }

    private JTextField criarTextField() {
        JTextField txt = new JTextField(15);
        txt.setBackground(BG_DARK);
        txt.setForeground(COLOR_TEXT);
        txt.setCaretColor(COLOR_TEXT);
        txt.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(70, 70, 70)),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return txt;
    }

    private void adicionarAluno(Aluno a) {
        listaAlunos.add(a);
        tableModel.addRow(new Object[] {
                a.getMatricula(), a.getNome(), a.getCurso(),
                a.getNota1(), a.getNota2(), String.format("%.2f", a.getMedia()), a.getStatus()
        });
        atualizarEstatisticas();
    }

    private void atualizarEstatisticas() {
        int total = listaAlunos.size();
        labelTotal.setText(String.valueOf(total));

        if (total > 0) {
            double somaMedias = 0;
            int aprovados = 0;
            for (Aluno a : listaAlunos) {
                somaMedias += a.getMedia();
                if (a.getMedia() >= 6.0)
                    aprovados++;
            }
            labelMediaGeral.setText(String.format("%.1f", somaMedias / total));
            labelAprovados.setText(String.format("%.0f%%", ((double) aprovados / total) * 100));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaPrincipal().setVisible(true));
    }

}