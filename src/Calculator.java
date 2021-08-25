import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    //Layout Declarations
    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[9];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton, negButton;
    JPanel panel;

    //Font Declaration
    Font myFont = new Font("Roboto", Font.BOLD, 30);

    //User Input Declarations
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    Calculator() {

        //Create the frame
        frame = new JFrame("Calculator");
        //Exit program on close
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Set frame's size
        frame.setSize(420, 550);
        //No layout manager / Handle positioning yourself
        frame.setLayout(null);

        //Create text field where input/output is shown
        textfield = new JTextField();
        //Resize and position the text field
        textfield.setBounds(50,25,300,50);
        //Set font to what was declared above
        textfield.setFont(myFont);
        //Disable user's ability to edit text field - input will be handled with buttons
        textfield.setEditable(false);

        //Create the function buttons
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("x");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        clrButton = new JButton("Clr");
        delButton = new JButton("Del");
        negButton = new JButton("(-)");

        //Add each function button to the functionButtons array
        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = clrButton;
        functionButtons[7] = delButton;
        functionButtons[8] = negButton;

        //For each button, add a listener for user input, set the font, and disable its ability to have focus
        for(int i = 0; i<9; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        //Use a loop to create the 0-9 number buttons, while also adding a listener, setting the font, and disabling focus
        for(int i = 0; i<10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        //Position and size the bottoms we want across the bottom
        negButton.setBounds(50,430,100,50);
        delButton.setBounds(150, 430, 100, 50);
        clrButton.setBounds(250,430,100,50);

        //Create a panel
        panel = new JPanel();
        //Position and size the panel
        panel.setBounds(50,100,300,300);
        //Configure the layout of things in the panel
        panel.setLayout(new GridLayout(4,4,10,10));

        //Add all buttons (except the bottom three addressed above) to the panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        //Add everything to the frame and make it visible
        frame.add(panel);
        frame.add(negButton);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textfield);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Calculator calc = new Calculator();
    }

    public void actionPerformed(ActionEvent e) {

        //For each number button, add functionality upon being clicked
        for(int i=0; i<10; i++) {
            //If (1,2,3, etc.) is clicked, add (1,2,3, etc.) to the text field
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }

        //If the decimal button is clicked add a decimal...
        if (e.getSource() == decButton) {
            //... if the text field doesn't already contain a decimal
            if (!textfield.getText().contains(".")) {
                textfield.setText(textfield.getText().concat("."));
            }
        }

        //If + is clicked, save the input, set the operator to +, and clear the text field.
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '+';
            textfield.setText("");
        }

        //If - is clicked, save the input, set the operator to -, and clear the text field.
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '-';
            textfield.setText("");
        }

        //If x is clicked, save the input, set the operator to x, and clear the text field.
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = 'x';
            textfield.setText("");
        }

        //If / is clicked, save the input, set the operator to /, and clear the text field.
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = '/';
            textfield.setText("");
        }

        //If = is clicked, save the second input and use the operator to determine what action to perform on the two numbers
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textfield.getText());
            switch (operator) {
                case '+' :
                    result = num1 + num2;
                    break;
                case '-' :
                    result = num1 - num2;
                    break;
                case 'x' :
                    result = num1 * num2;
                    break;
                case '/' :
                    result = num1 / num2;
            }

            //Set the text field to the result of our operation, and set the first input to the result
            // just in case we want to continue performing operations on our results.
            textfield.setText(String.valueOf(result));
            num1 = result;
        }

        //If Clr is clicked, clear the text field.
        if (e.getSource() == clrButton) {
            textfield.setText("");
        }

        //If Del is clicked, remove the last character from the text field
        if (e.getSource() == delButton) {
            //Save the current string
            String string = textfield.getText();
            //Clear the text field
            textfield.setText("");
            //For the length of the string minus 1, add each character back to the text field.
            // The -1 will ensure that the last character entered will not come back with the rest.
            for (int i = 0; i<string.length()-1; i++) {
                textfield.setText(textfield.getText()+string.charAt(i));
            }
        }

        //If (-) is clicked, make the current input negative by multiplying it by -1
        if (e.getSource() == negButton) {
            double temp = Double.parseDouble(textfield.getText());
            temp *= -1;
            textfield.setText(String.valueOf(temp));
        }
    }
}
