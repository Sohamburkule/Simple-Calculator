package Basics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {
    private final JTextField displayField;
    private String operator;
    private double firstNumber;

    public Calculator() {
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(400, 500);
        setLocationRelativeTo(null);

        // Create the display field
        displayField = new JTextField(25);
        displayField.setEditable(false);
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Arial", Font.PLAIN, 20));
        displayField.setPreferredSize(new Dimension(400, 150));

        // Create the number buttons
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(new Font("Arial", Font.PLAIN, 20));
        }

        // Create the operator buttons
        JButton addButton = createButton("+");
        JButton subtractButton = createButton("-");
        JButton multiplyButton = createButton(" * ");
        JButton divideButton = createButton("/");
        JButton equalsButton = createButton("=");
        addButton.setFont(new Font("Arial", Font.PLAIN, 20));
        subtractButton.setFont(new Font("Arial", Font.PLAIN, 20));
        multiplyButton.setFont(new Font("Arial", Font.PLAIN, 20));
        divideButton.setFont(new Font("Arial", Font.PLAIN, 20));
        equalsButton.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create the panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4));
        buttonPanel.add(numberButtons[7]);
        buttonPanel.add(numberButtons[8]);
        buttonPanel.add(numberButtons[9]);
        buttonPanel.add(addButton);
        buttonPanel.add(numberButtons[4]);
        buttonPanel.add(numberButtons[5]);
        buttonPanel.add(numberButtons[6]);
        buttonPanel.add(subtractButton);
        buttonPanel.add(numberButtons[1]);
        buttonPanel.add(numberButtons[2]);
        buttonPanel.add(numberButtons[3]);
        buttonPanel.add(multiplyButton);
        buttonPanel.add(numberButtons[0]);
        buttonPanel.add(equalsButton);
        buttonPanel.add(new JButton("."));
        buttonPanel.add(divideButton);

        // Create the content pane and add components to it
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(displayField, BorderLayout.NORTH);
        contentPane.add(buttonPanel, BorderLayout.CENTER);
    }

    private JButton createButton(String label) {
        JButton button = new JButton(label);
        button.addActionListener(this);
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();

        // Handle number buttons
        if (actionCommand.matches("\\d")) {
            displayField.setText(displayField.getText() + actionCommand);
        }
        // Handle operator buttons
        else if (actionCommand.matches("[+\\- */]")) {
            firstNumber = Double.parseDouble(displayField.getText());
            operator = actionCommand;
            displayField.setText("");

        }
        // Handle equals button
        else if (actionCommand.equals("=")) {
            double secondNumber = Double.parseDouble(displayField.getText());
            double result = switch (operator) {
                case "+" -> firstNumber + secondNumber;
                case "-" -> firstNumber - secondNumber;
                case "*" -> firstNumber * secondNumber;
                case "/" -> firstNumber / secondNumber;
                default -> 0.0;
            };

            displayField.setText(String.valueOf(result));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }
}

