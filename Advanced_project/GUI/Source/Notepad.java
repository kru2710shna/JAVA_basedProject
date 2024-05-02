import javax.swing.*;
import javax.swing.text.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.ArrayList;

// Define the Plugin interface
interface Plugin {
    void initialize(); // Method to initialize the plugin
    void execute();    // Method to execute the plugin functionality
    void cleanup();    // Method to clean up resources used by the plugin
}

// Example plugin class for syntax highlighting
class SyntaxHighlightingPlugin implements Plugin {
    private JTextPane textPane;

    public SyntaxHighlightingPlugin(JTextPane textPane) {
        this.textPane = textPane;
    }

   @Override
    public void initialize() {
        StyleContext styleContext = new StyleContext();
        Style keywordStyle = styleContext.addStyle("Keyword", null);
        if (keywordStyle == null) {
            System.err.println("Failed to create keyword style.");
            return;
        }
        StyleConstants.setForeground(keywordStyle, Color.BLUE);
        // Check if color is set
        System.out.println("Keyword color set to: " + StyleConstants.getForeground(keywordStyle));
    }


    @Override
    public void execute() {
        StyledDocument doc = textPane.getStyledDocument();
        String text = textPane.getText();

        SwingUtilities.invokeLater(() -> {
            try {
                int start = 0;
                while ((start = text.indexOf('{', start)) != -1) {
                    int end = text.indexOf('}', start);
                    if (end == -1) break;

                    // Extract the text between the curly brackets
                    String content = text.substring(start + 1, end).trim();

                    // Check if the content is a Java keyword and apply the style
                    if (isJavaKeyword(content)) {
                        int contentStart = start + 1;
                        int contentLength = content.length();
                        Style keywordStyle = textPane.getStyle("Keyword");
                        if (keywordStyle == null) {
                            // Create and initialize the style if not present
                            keywordStyle = textPane.addStyle("Keyword", null);
                            StyleConstants.setForeground(keywordStyle, Color.BLUE);
                        }
                        if (contentStart + contentLength <= doc.getLength()) {
                            doc.setCharacterAttributes(contentStart, contentLength, keywordStyle, false);
                        }
                    }
                    start = end + 1;
                }
            } catch (Exception e) {
                System.err.println("Error while updating styles: " + e.getMessage());
            }
        });
    }

    @Override
    public void cleanup() {
        // Clean up resources used by the syntax highlighting plugin (if any)
    }

   private boolean isJavaKeyword(String word) {
        String[] javaKeywords = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"};
        return Arrays.asList(javaKeywords).contains(word);
    }

}

// Define the User class
class User {
    private String name;
    private String id;

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }
}

interface Serializer<T> {
    String serialize(T object);
}

interface Deserializer<T> {
    T deserialize(String serializedData);
}

class JsonSerializer<T> implements Serializer<T>, Deserializer<T> {
    private Gson gson; // Gson library for JSON serialization/deserialization

    public JsonSerializer() {
        this.gson = new Gson();
    }

    @Override
    public String serialize(T object) {
        return gson.toJson(object);
    }

    @Override
    public T deserialize(String serializedData) {
        return gson.fromJson(serializedData, new TypeToken<T>(){}.getType());
    }
}

class XmlSerializer<T> implements Serializer<T>, Deserializer<T> {
    private XStream xStream; // XStream library for XML serialization/deserialization

    public XmlSerializer() {
        this.xStream = new XStream();
    }

    @Override
    public String serialize(T object) {
        return xStream.toXML(object);
    }

    @Override
    public T deserialize(String serializedData) {
        return (T) xStream.fromXML(serializedData);
    }
}


class Notepad extends JFrame implements ActionListener {
    private JTextPane textPane;
    private JLabel wordCountLabel;
    private JLabel letterCountLabel;
    private String currentTag;
    private Map<String, String> filesWithTags; // Map to store files with their tags
    private SyntaxHighlightingPlugin syntaxHighlightingPlugin; // Instance of SyntaxHighlightingPlugin
    private JLabel userInfoLabel; // Label to display user information
    private User currentUser;
    private Serializer<Map<String, String>> serializer;
    private Deserializer<Map<String, String>> deserializer;

    public Notepad() {
         super("Notepad");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setupUI();
        authenticateUser(); // Authenticate and set the user
        updateUserInfoDisplay(); // Update GUI with user info

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
        JMenu tagMenu = new JMenu("Tag");

        JMenuItem newMenuItem = new JMenuItem("New");
        JMenuItem openMenuItem = new JMenuItem("Open");
        JMenuItem saveMenuItem = new JMenuItem("Save");
        JMenuItem exitMenuItem = new JMenuItem("Exit");
        JMenuItem closeMenuItem = new JMenuItem("Close");

        JMenuItem boldMenuItem = new JMenuItem("Bold");
        JMenuItem underlineMenuItem = new JMenuItem("Underline");
        JMenuItem italicMenuItem = new JMenuItem("Italic");
        JMenuItem findReplaceMenuItem = new JMenuItem("Find and Replace");

        JMenuItem addTagMenuItem = new JMenuItem("Add Tag");
        JMenuItem deleteTagMenuItem = new JMenuItem("Delete Tag");
        JMenuItem replaceTagMenuItem = new JMenuItem("Replace Tag");
        JMenuItem filterByTagMenuItem = new JMenuItem("Filter by Tag");

        newMenuItem.addActionListener(this);
        openMenuItem.addActionListener(this);
        saveMenuItem.addActionListener(this);
        exitMenuItem.addActionListener(this);
        closeMenuItem.addActionListener(this);

        boldMenuItem.addActionListener(this);
        underlineMenuItem.addActionListener(this);
        italicMenuItem.addActionListener(this);
        findReplaceMenuItem.addActionListener(this);

        addTagMenuItem.addActionListener(this);
        deleteTagMenuItem.addActionListener(this);
        replaceTagMenuItem.addActionListener(this);
        filterByTagMenuItem.addActionListener(this); 

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        fileMenu.add(exitMenuItem);
        fileMenu.add(closeMenuItem);

        editMenu.add(boldMenuItem);
        editMenu.add(underlineMenuItem);
        editMenu.add(italicMenuItem);
        editMenu.add(findReplaceMenuItem);

        tagMenu.add(addTagMenuItem);
        tagMenu.add(deleteTagMenuItem);
        tagMenu.add(replaceTagMenuItem);
        tagMenu.add(filterByTagMenuItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(tagMenu);

        setJMenuBar(menuBar);

        // Add components to content pane
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(countPanel, BorderLayout.SOUTH); // Add countPanel to the bottom

        filesWithTags = new HashMap<>();

        // Instantiate SyntaxHighlightingPlugin and initialize it
        syntaxHighlightingPlugin = new SyntaxHighlightingPlugin(textPane);
        syntaxHighlightingPlugin.initialize();

        // Add text change listener
        textPane.getDocument().addDocumentListener(new DocumentListener() {
        @Override
        public void insertUpdate(DocumentEvent e) {
            updateCounts();
            syntaxHighlightingPlugin.execute();
        }

        @Override
        public void removeUpdate(DocumentEvent e) {
            updateCounts();
            syntaxHighlightingPlugin.execute();
        }

        @Override
        public void changedUpdate(DocumentEvent e) {
            updateCounts();
            syntaxHighlightingPlugin.execute();
        }
    });

        setSize(500, 500);
        setVisible(true);
    }

     private void authenticateUser() {
        // Create input fields for name and ID
        JTextField nameField = new JTextField(10);
        JTextField idField = new JTextField(10);

        // Panel to hold input fields
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(2, 2));
        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("ID:"));
        panel.add(idField);

        // Prompt the user to enter name and ID
        int result = JOptionPane.showConfirmDialog(null, panel, "User Authentication", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Retrieve name and ID from input fields
            String name = nameField.getText();
            String id = idField.getText();
            currentUser = new User(name, id);
        } else {
            // Default user if canceled
            currentUser = new User("Guest", "0000");
        }
    }
    private void setupUI() {
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

        // Add user info label
        userInfoLabel = new JLabel();
        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userInfoPanel.add(userInfoLabel);

        // Add components to content pane
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(countPanel, BorderLayout.SOUTH); // Add countPanel to the bottom
        getContentPane().add(userInfoPanel, BorderLayout.NORTH); // Add userInfoPanel to the top
    }

    private void updateUserInfoDisplay() {
        if (currentUser != null) {
            userInfoLabel.setText("User: " + currentUser.getName() + " (ID: " + currentUser.getId() + ")");
        }
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
            case "Save":
                saveFile();
                break;
            case "New":
                createNewNotepad();
                break;
            case "Open":
                openFile();
                break;
            case "Add Tag":
                addTag();
                break;
            case "Delete Tag":
                deleteTag();
                break;
            case "Replace Tag":
                replaceTag();
                break;
            case "Filter by Tag":
                filterByTag(); // Call filterByTag method
                break;
                
        }
    }
    // Method to serialize filesWithTags to JSON
    private String serializeFilesWithTags() {
        return serializer.serialize(filesWithTags);
    }
    
    // Method to deserialize JSON data to filesWithTags
    private void deserializeFilesWithTags(String serializedData) {
        filesWithTags = deserializer.deserialize(serializedData);
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

    private void saveFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            String selectedPriority = currentTag != null ? currentTag.toLowerCase() : ""; // Get selected priority tag
            File folder = new File("/Users/krushna/Java_Project/Advanced_project/GUI/Priority/" + selectedPriority);
    
            // Create the folder if it doesn't exist
            if (!folder.exists()) {
                if (!folder.mkdirs()) {
                    JOptionPane.showMessageDialog(this, "Error creating folder.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
    
            // Construct the file path with priority tag and file name
            String filePath = folder.getAbsolutePath() + "/" + selectedFile.getName();
            File file = new File(filePath);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(textPane.getText());
                JOptionPane.showMessageDialog(this, "File saved successfully with " + selectedPriority + " priority.");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void createNewNotepad() {
        
        int saveChoice = JOptionPane.showConfirmDialog(this, "Do you want to save the changes?", "Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);        
        int newChoice = JOptionPane.showConfirmDialog(this, "Do you want to create a new Notepad?", "New Notepad", JOptionPane.YES_NO_OPTION);
        int exitChoice = JOptionPane.showConfirmDialog(this, "Do you want to create a new Notepad?", "New Notepad", JOptionPane.YES_NO_OPTION);
        
        if (newChoice == JOptionPane.YES_OPTION) {
            if (saveChoice == JOptionPane.YES_OPTION) {
                saveFile();
                if (exitChoice == JOptionPane.YES_OPTION) {
                    exitProgram(); 
                }
                else {
                    return;
                }
            } else if (saveChoice == JOptionPane.CANCEL_OPTION) {
                return; // Do nothing if cancel is chosen
            }
            new Notepad();
        }
        else {
            dispose();
        }
    }

    private void openFile() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                textPane.setText(sb.toString());
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void addTag() {
        String[] options = {"Low Priority", "Medium Priority", "High Priority"};
        int choice = JOptionPane.showOptionDialog(this, "Choose a tag type:", "Add Tag", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice != JOptionPane.CLOSED_OPTION) {
            currentTag = options[choice];
            JOptionPane.showMessageDialog(this, "Tag added successfully: " + currentTag, "Tag Added", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void deleteTag() {
        currentTag = "";
        JOptionPane.showMessageDialog(this, "Tag deleted successfully.", "Tag Deleted", JOptionPane.INFORMATION_MESSAGE);
    }

    private void replaceTag() {
        if (currentTag != null && !currentTag.isEmpty()) {
            addTag(); // Call addTag method to replace the current tag
        } else {
            JOptionPane.showMessageDialog(this, "Please add a tag before replacing.", "Tag Required", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void filterByTag() {
        String[] options = {"Low Priority", "Medium Priority", "High Priority"};
        String selectedTag = (String) JOptionPane.showInputDialog(this, "Choose a tag to filter by:", "Filter by Tag", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (selectedTag != null) {
            // Filter files based on selected tag
            List<String> filteredFiles = new ArrayList<>();
            for (Map.Entry<String, String> entry : filesWithTags.entrySet()) {
                if (entry.getValue().equals(selectedTag)) {
                    filteredFiles.add(entry.getKey());
                }
            }
            // Display filtered files
            if (!filteredFiles.isEmpty()) {
                StringBuilder message = new StringBuilder("Files with Tag '" + selectedTag + "':\n");
                for (String file : filteredFiles) {
                    message.append(file).append("\n");
                }
                JOptionPane.showMessageDialog(this, message.toString(), "Filtered Files", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No files found with Tag '" + selectedTag + "'", "Filtered Files", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Notepad());
    }
}
