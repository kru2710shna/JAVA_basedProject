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

// Plugin class for syntax highlighting
class SyntaxHighlightingPlugin implements Plugin {
    private JTextPane textPane;

    // Constructs a SyntaxHighlightingPlugin object.
    public SyntaxHighlightingPlugin(JTextPane textPane) {
        this.textPane = textPane;
    }

   @Override
    public void initialize() {
        // Create a new StyleContext
        StyleContext styleContext = new StyleContext();

        // Add a new style named "Keyword" to the StyleContext
        Style keywordStyle = styleContext.addStyle("Keyword", null);

        // Check if the keywordStyle is successfully created
        if (keywordStyle == null) {
            // Print an error message if the style creation fails
            System.err.println("Failed to create keyword style.");
            return; // Exit the method if style creation fails
        }

        // Set the foreground color of the keywordStyle to blue
        StyleConstants.setForeground(keywordStyle, Color.BLUE);

        // Print a message to confirm the color set for the keywordStyle
        System.out.println("Keyword color set to: " + StyleConstants.getForeground(keywordStyle));
    }


 @Override
    public void execute() {
        // Get the StyledDocument from the textPane
        StyledDocument doc = textPane.getStyledDocument();
        // Get the text content from the textPane
        String text = textPane.getText();

        // Execute the content updates in the event dispatch thread
        SwingUtilities.invokeLater(() -> {
            try {
                int start = 0;
                // Iterate through the text to find curly brackets and apply styling
                while ((start = text.indexOf('{', start)) != -1) {
                    // Find the corresponding closing curly bracket
                    int end = text.indexOf('}', start);
                    // Break the loop if there's no closing curly bracket
                    if (end == -1) break;

                    // Extract the text between the curly brackets
                    String content = text.substring(start + 1, end).trim();

                    // Check if the content is a Java keyword and apply the style
                    if (isJavaKeyword(content)) {
                        int contentStart = start + 1;
                        int contentLength = content.length();
                        // Get the keyword style from the textPane
                        Style keywordStyle = textPane.getStyle("Keyword");
                        // Create and initialize the style if not present
                        if (keywordStyle == null) {
                            keywordStyle = textPane.addStyle("Keyword", null);
                            StyleConstants.setForeground(keywordStyle, Color.BLUE);
                        }
                        // Apply the keyword style to the content range
                        if (contentStart + contentLength <= doc.getLength()) {
                            doc.setCharacterAttributes(contentStart, contentLength, keywordStyle, false);
                        }
                    }
                    // Move to the next character after the closing curly bracket
                    start = end + 1;
                }
            } catch (Exception e) {
                // Handle any errors that occur during style updates
                System.err.println("Error while updating styles: " + e.getMessage());
            }
        });
    }

    @Override
    public void cleanup() {
        // Clean up resources used by the syntax highlighting plugin (if any)
        textPane = null;
    }

    // Checks if the given word is a Java keyword.
   private boolean isJavaKeyword(String word) {
        String[] javaKeywords = {"abstract", "assert", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else", "enum", "extends", "final", "finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "try", "void", "volatile", "while"};
        return Arrays.asList(javaKeywords).contains(word);
    }

}

// Define the User class
class User {
    private String name;
    private String id;
    private String filePriorityType; // New member variable

    public User(String name, String id) {
        this.name = name;
        this.id = id;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setFilePriorityType(String filePriorityType) {
        this.filePriorityType = filePriorityType;
    }

    public String getFilePriorityType() {
        return filePriorityType;
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
    private void addTag() {
        String[] options = {"Low Priority", "Medium Priority", "High Priority"};
        int choice = JOptionPane.showOptionDialog(this, "Choose a tag type:", "Add Tag", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice != JOptionPane.CLOSED_OPTION) {
            currentTag = options[choice];
            currentUser.setFilePriorityType(currentTag); // Set the file priority type for the current user
            updateUserInfoDisplay(); // Update the user info display
            JOptionPane.showMessageDialog(this, "Tag added successfully: " + currentTag, "Tag Added", JOptionPane.INFORMATION_MESSAGE);
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
            String userInfo = "User: " + currentUser.getName() + " (ID: " + currentUser.getId() + ")";
            if (currentUser.getFilePriorityType() != null) {
                userInfo += " - Priority Type: " + currentUser.getFilePriorityType(); // Append file priority type if available
            }
            userInfoLabel.setText(userInfo);
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

    // Makes the selected text bold in the JTextPane.
    private void boldSelectedText() {
        // Get the styled document of the JTextPane
        StyledDocument doc = textPane.getStyledDocument();
        
        // Add a new style for bold (if not already present)
        Style style = textPane.addStyle("Bold", null);
        
        // Set the bold attribute to true for the style
        StyleConstants.setBold(style, true);
        
        // Apply the bold style to the selected text in the document
        doc.setCharacterAttributes(
            textPane.getSelectionStart(), // Start position of the selection
            textPane.getSelectionEnd() - textPane.getSelectionStart(), // Length of the selection
            doc.getStyle("Bold"), // Style to apply (bold)
            true // Replace existing attributes
        );
    }

    // Underlines the selected text in the JTextPane.
    private void underlineSelectedText() {
        // Get the styled document of the JTextPane
        StyledDocument doc = textPane.getStyledDocument();
        
        // Add a new style for underline (if not already present)
        Style style = textPane.addStyle("Underline", null);
        
        // Set the underline attribute to true for the style
        StyleConstants.setUnderline(style, true);
        
        // Apply the underline style to the selected text in the document
        doc.setCharacterAttributes(
            textPane.getSelectionStart(), // Start position of the selection
            textPane.getSelectionEnd() - textPane.getSelectionStart(), // Length of the selection
            doc.getStyle("Underline"), // Style to apply (underline)
            true // Replace existing attributes
        );
    }

    // Italicizes the selected text in the JTextPane.
    private void italicizeSelectedText() {
        // Get the styled document of the JTextPane
        StyledDocument doc = textPane.getStyledDocument();
        
        // Add a new style for italic (if not already present)
        Style style = textPane.addStyle("Italic", null);
        
        // Set the italic attribute to true for the style
        StyleConstants.setItalic(style, true);
        
        // Apply the italic style to the selected text in the document
        doc.setCharacterAttributes(
            textPane.getSelectionStart(), // Start position of the selection
            textPane.getSelectionEnd() - textPane.getSelectionStart(), // Length of the selection
            doc.getStyle("Italic"), // Style to apply (italic)
            true // Replace existing attributes
        );
    }

    // Closes the Notepad window.
    private void closeNotepad() {
        // Ask the user if they want to create a new Notepad or exit the program
        int choice = JOptionPane.showConfirmDialog(
            this,
            "Do you want to create a new notepad?",
            "Close",
            JOptionPane.YES_NO_OPTION
        );
        
        // If the user chooses to create a new Notepad
        if (choice == JOptionPane.YES_OPTION) {
            // Dispose the current window
            dispose();
            // Open a new instance of Notepad
            new Notepad();
        } else {
            // If the user chooses not to create a new Notepad, ask if they want to exit the program
            int exitChoice = JOptionPane.showConfirmDialog(
                this,
                "Do you want to exit the program?",
                "Exit",
                JOptionPane.YES_NO_OPTION
            );
            
            // If the user chooses to exit the program
            if (exitChoice == JOptionPane.YES_OPTION) {
                // Dispose the current window
                dispose();
                // Exit the program
                System.exit(0);
            }
        }
    }
    // Exits the program.
    private void exitProgram() {
        // Ask the user if they want to exit the program
        int exitChoice = JOptionPane.showConfirmDialog(
            this,"Do you want to exit the program?", "Exit",
            JOptionPane.YES_NO_OPTION
        );
        
        // If the user chooses to exit the program
        if (exitChoice == JOptionPane.YES_OPTION) {
            // Dispose the current window
            dispose();
            // Exit the program
            System.exit(0);
        }
    }

    // Shows a dialog for finding and replacing text in the text area.
    private void showFindReplaceDialog() {
        // Prompt the user to enter text to find
        String findText = JOptionPane.showInputDialog(this, "Enter text to find:");
        // Check if user input is not null and not empty
        if (findText != null && !findText.isEmpty()) {
            // Get the current text from the text area
            String currentText = textPane.getText();
            // Find the index of the text to find in the current text
            int index = currentText.indexOf(findText);
            // If the text to find is found
            if (index != -1) {
                // Prompt the user to enter text to replace with
                String replaceText = JOptionPane.showInputDialog(this, "Replace '" + findText + "' with:");
                // If user input for replacement is not null
                if (replaceText != null) {
                    // Replace the text in the current text with the replacement text
                    currentText = currentText.replace(findText, replaceText);
                    // Set the updated text in the text area
                    textPane.setText(currentText);
                }
            } else {
                // If the text to find is not found, show a message dialog
                JOptionPane.showMessageDialog(this, "Word not found.");
            }
        }
    }
    
    // Updates the word count and letter count labels based on the text in the text area.
    private void updateCounts() {
        // Get the text from the text area
        String text = textPane.getText();
        // Count the number of words (split by whitespace) and letters (excluding whitespace)
        int wordCount = text.isEmpty() ? 0 : text.split("\\s+").length;
        int letterCount = text.replaceAll("\\s+", "").length();
        // Set the word count and letter count in their respective labels
        wordCountLabel.setText("Word Count: " + wordCount);
        letterCountLabel.setText("Letter Count: " + letterCount);
    }

    // Saves the content of the text area to a file. 
   private void saveFile() {
        // Create a file chooser dialog
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            // Get the selected file from the file chooser
            File selectedFile = fileChooser.getSelectedFile();
            // Get the selected priority tag (or an empty string if null)
            String selectedPriority = currentTag != null ? currentTag.toLowerCase() : "";
            // Create the directory based on the selected priority tag if it doesn't exist
            File folder = new File("/Users/krushna/Java_Project/Advanced_project/GUI/Priority/" + selectedPriority);
            if (!folder.exists()) {
                if (!folder.mkdirs()) { // Attempt to create the directory
                    JOptionPane.showMessageDialog(this, "Error creating folder.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            // Construct the file path with the priority tag and file name
            String filePath = folder.getAbsolutePath() + "/" + selectedFile.getName();
            File file = new File(filePath);
            try (FileWriter writer = new FileWriter(file)) {
                // Write the text area content to the file
                writer.write(textPane.getText());
                // Display a success message with the selected priority tag
                JOptionPane.showMessageDialog(this, "File saved successfully with " + selectedPriority + " priority.");
            } catch (IOException e) {
                // Display an error message if file writing fails
                JOptionPane.showMessageDialog(this, "Error saving file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Creates a new Notepad instance.
    private void createNewNotepad() {
        // Prompt the user to save changes before creating a new Notepad
        int saveChoice = JOptionPane.showConfirmDialog(this, "Do you want to save the changes?", "Save Changes", JOptionPane.YES_NO_CANCEL_OPTION);
        // Display a confirmation dialog for creating a new Notepad
        int newChoice = JOptionPane.showConfirmDialog(this, "Do you want to create a new Notepad?", "New Notepad", JOptionPane.YES_NO_OPTION);
        // Display a confirmation dialog for exiting the program (if needed)
        int exitChoice = JOptionPane.showConfirmDialog(this, "Do you want to create a new Notepad?", "New Notepad", JOptionPane.YES_NO_OPTION);
        
        if (newChoice == JOptionPane.YES_OPTION) {
            if (saveChoice == JOptionPane.YES_OPTION) {
                saveFile(); // Save changes if requested
                if (exitChoice == JOptionPane.YES_OPTION) {
                    exitProgram(); // Exit the program if requested
                } else {
                    return; // Do nothing if exit is not requested
                }
            } else if (saveChoice == JOptionPane.CANCEL_OPTION) {
                return; // Do nothing if cancel is chosen
            }
            new Notepad(); // Create a new Notepad instance
        } else {
            dispose(); // Dispose of the current Notepad instance if new Notepad is not created
        }
    }

    // Opens a file selected by the user.
    private void openFile() {
        // Display a file chooser dialog for selecting a file to open
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                StringBuilder sb = new StringBuilder();
                String line;
                // Read the content of the selected file
                while ((line = reader.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                // Set the text pane content to the content of the file
                textPane.setText(sb.toString());
            } catch (IOException e) {
                // Display an error message if an IOException occurs during file reading
                JOptionPane.showMessageDialog(this, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
/**
     * Deletes the current tag.
     * Resets the currentTag variable to an empty string.
     * Displays a success message indicating that the tag has been deleted.
 */
    private void deleteTag() {
        // Reset the currentTag variable to an empty string
        currentTag = "";
        // Display a success message indicating that the tag has been deleted
        JOptionPane.showMessageDialog(this, "Tag deleted successfully.", "Tag Deleted", JOptionPane.INFORMATION_MESSAGE);
    }

/**
     * Replaces the current tag with a new one.
     * Checks if the currentTag is not null and not empty.
     * If so, calls the addTag method to replace the current tag.
     * If the currentTag is null or empty, displays a warning message indicating that a tag is required.
 */
    private void replaceTag() {
        if (currentTag != null && !currentTag.isEmpty()) {
            // If the currentTag is not null and not empty, call the addTag method to replace the current tag
            addTag();
        } else {
            // If the currentTag is null or empty, display a warning message indicating that a tag is required
            JOptionPane.showMessageDialog(this, "Please add a tag before replacing.", "Tag Required", JOptionPane.WARNING_MESSAGE);
        }
    }

    // Filters files by the selected tag.
    private void filterByTag() {
        // Array of options for tag selection
        String[] options = {"Low Priority", "Medium Priority", "High Priority"};
        // Prompt the user to choose a tag from the options
        String selectedTag = (String) JOptionPane.showInputDialog(
            this, 
            "Choose a tag to filter by:", 
            "Filter by Tag", 
            JOptionPane.QUESTION_MESSAGE, 
            null, 
            options, 
            options[0]
        );
        
        if (selectedTag != null) {
            // Filter files based on the selected tag
            List<String> filteredFiles = new ArrayList<>();
            for (Map.Entry<String, String> entry : filesWithTags.entrySet()) {
                if (entry.getValue().equals(selectedTag)) {
                    filteredFiles.add(entry.getKey());
                }
            }
            
            // Display filtered files
            if (!filteredFiles.isEmpty()) {
                // Build a message with the list of filtered files
                StringBuilder message = new StringBuilder("Files with Tag '" + selectedTag + "':\n");
                for (String file : filteredFiles) {
                    message.append(file).append("\n");
                }
                // Display the message with the list of filtered files
                JOptionPane.showMessageDialog(this, message.toString(), "Filtered Files", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Display a message indicating no files found with the selected tag
                JOptionPane.showMessageDialog(this, "No files found with Tag '" + selectedTag + "'", "Filtered Files", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    
    // Main Method 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Notepad());
    }
}
