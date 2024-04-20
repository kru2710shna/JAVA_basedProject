import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

class Notepad extends JFrame implements ActionListener {
    private JTextPane textPane;
    private JLabel wordCountLabel;
    private JLabel letterCountLabel;

    public Notepad() {
        super("Notepad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        textPane = new JTextPane();
        textPane.setFont(new Font("Arial", Font.PLAIN, 12));
        textPane.setMargin(new Insets(20, 20, 20, 20)); // Add margin for header and footer

        JScrollPane scrollPane = new JScrollPane(textPane);

        // Create word count label
        wordCountLabel = new JLabel("Word Count: 0");
        letterCountLabel = new JLabel("Letter Count: 0");

        // Create panel to hold both labels
        JPanel countPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        countPanel.add(wordCountLabel);
        countPanel.add(letterCountLabel);

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");

        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem printMenuItem = new JMenuItem("Print");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem closeMenuItem = new JMenuItem("Close");

        JMenuItem boldMenuItem = new JMenuItem("Bold");
        JMenuItem underlineMenuItem = new JMenuItem("Underline");
        JMenuItem italicMenuItem = new JMenuItem("Italic");
        JMenuItem findReplaceMenuItem = new JMenuItem("Find and Replace");

        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        printMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        closeMenuItem.addActionListener(this);

        boldMenuItem.addActionListener(this);
        underlineMenuItem.addActionListener(this);
        italicMenuItem.addActionListener(this);
        findReplaceMenuItem.addActionListener(this);

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(printMenuItem);
        fileMenu.add(exitMenuItem);
        fileMenu.add(closeMenuItem);

        editMenu.add(boldMenuItem);
        editMenu.add(underlineMenuItem);
        editMenu.add(italicMenuItem);
        editMenu.add(findReplaceMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);

        setJMenuBar(menuBar);

        // Add components to content pane
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(countPanel, BorderLayout.SOUTH); // Add countPanel to the bottom

        // Set initial counts
        updateCounts();

        // Add text change listener
        textPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateCounts();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateCounts();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateCounts();
            }
        });

        setSize(500, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        switch (command) {
            case "Bold":
                boldSelectedText();
                break;
            case "Underline":
                underlineSelectedText();
                break;
            case "Italic":
                italicizeSelectedText();
                break;
            case "Find and Replace":
                showFindReplaceDialog();
                break;
            case "Close":
                closeNotepad();
                break;
            case "Exit":
                exitProgram();
                break;
        }
    }

    private void boldSelectedText() {
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Bold", null);
        StyleConstants.setBold(style, true);
        doc.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), doc.getStyle("Bold"), true);
    }

    private void underlineSelectedText() {
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Underline", null);
        StyleConstants.setUnderline(style, true);
        doc.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), doc.getStyle("Underline"), true);
    }

    private void italicizeSelectedText() {
        StyledDocument doc = textPane.getStyledDocument();
        Style style = textPane.addStyle("Italic", null);
        StyleConstants.setItalic(style, true);
        doc.setCharacterAttributes(textPane.getSelectionStart(), textPane.getSelectionEnd() - textPane.getSelectionStart(), doc.getStyle("Italic"), true);
    }

    private void closeNotepad() {
        int choice = JOptionPane.showConfirmDialog(this, "Do you want to create a new notepad?", "Close", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            dispose();
            new Notepad();
        } else {
            int exitChoice = JOptionPane.showConfirmDialog(this, "Do you want to exit the program?", "Exit", JOptionPane.YES_NO_OPTION);
            if (exitChoice == JOptionPane.YES_OPTION) {
                dispose();
                System.exit(0);
            }
        }
    }

    private void exitProgram() {
        int exitChoice = JOptionPane.showConfirmDialog(this, "Do you want to exit the program?", "Exit", JOptionPane.YES_NO_OPTION);
        if (exitChoice == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        }
    }

    private void showFindReplaceDialog() {
        String findText = JOptionPane.showInputDialog(this, "Enter text to find:");
        if (findText != null && !findText.isEmpty()) {
            String currentText = textPane.getText();
            int index = currentText.indexOf(findText);
            if (index != -1) {
                String replaceText = JOptionPane.showInputDialog(this, "Replace '" + findText + "' with:");
                if (replaceText != null) {
                    currentText = currentText.replace(findText, replaceText);
                    textPane.setText(currentText);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Word not found.");
            }
        }
    }

    private void updateCounts() {
        String text = textPane.getText();
        int wordCount = text.isEmpty() ? 0 : text.split("\\s+").length;
        int letterCount = text.replaceAll("\\s+", "").length();
        wordCountLabel.setText("Word Count: " + wordCount);
        letterCountLabel.setText("Letter Count: " + letterCount);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Notepad());
    }
}
