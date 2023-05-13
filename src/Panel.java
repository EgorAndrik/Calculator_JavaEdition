import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Panel extends JPanel {
    private JButton[][] numbers = new JButton[4][3];
    private JButton[] operators = new JButton[4];
    private JButton[] buttons = new JButton[20];
    private int count_b = 0;
    private Font font = new Font("SanSerif", Font.BOLD, 24);
    private Font font_d = new Font("Arial", Font.ITALIC, 30);
    private JButton clear = new JButton("C"), backspace = new JButton("<-");
    private JButton eq = new JButton("="), dot = new JButton(".");
    private JTextField display = new JTextField("0");
    private Double first_num;
    private String current_operation = "";
    public Panel() {
        setLayout(null);
        setBackground(new Color(0, 255, 196));
        int n = 7;
        for (int y = 0; y < 3; ++y) {
            for (int x = 0; x < 3; ++x) {
                numbers[x][y] = new JButton((n++) + "");
                numbers[x][y].setBounds(x * (50 + 10) + 10, y * (50 + 10) + 120, 50, 50);
                addToButtons(numbers[x][y]);
            }
            n -= 6;
        }
        numbers[3][1] = new JButton("0");
        numbers[3][1].setBounds((50 + 10) + 10, 3 * (50 + 10) + 120, 50, 50);
        addToButtons(numbers[3][1]);

        String op = "/*-+";
        for (int y = 0; y < 4; ++y) {
            operators[y] = new JButton(Character.toString(op.charAt(y)));
            operators[y].setBounds(3 * (50 + 10) + 10, y * (50 + 10) + 120, 50, 50);
            addToButtons(operators[y]);
        }

        clear.setBounds(3 * (50 + 10) + 70, 120,60,50);
        addToButtons(clear);

        backspace.setBounds(3 * (50 + 10) + 70, 180,60,50);
        addToButtons(backspace);

        eq.setBounds(3 * (50 + 10) + 70, 3 * (50 + 10) + 120,60,50);
        addToButtons(eq);

        dot.setBounds(2 * (50 + 10) + 10, 3 * (50 + 10) + 120,50,50);
        addToButtons(dot);

        display.setBounds(10, 10, 305, 40);
        display.setFont(font_d);
        display.setEditable(false);
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        add(display);

        ActionListener listner = (ActionEvent e) -> {
            JButton b = (JButton)e.getSource();
            String txt = b.getText();
            if (txt.equals(".")) {
                if (!display.getText().contains(".")) display.setText(display.getText() + txt);
            }
            else if (txt.equals("/")) doOper("/");
            else if (txt.equals("*")) doOper("*");
            else if (txt.equals("-")) doOper("-");
            else if (txt.equals("+")) doOper("+");
            else if (txt.equals("C")) doClear();
            else if (txt.equals("<-")) operationBacks();
            else if (txt.equals("=")) doOperation();
            else addNum(txt);
        };

        for (JButton b: buttons) if (b != null) b.addActionListener(listner);

    }
    private void addNum(String num) {
        if (display.getText().equals("0")) display.setText(num);
        else display.setText(display.getText() + num);
    }

    private void doOper(String oper) {
        first_num = Double.valueOf(display.getText());
        current_operation = oper;
        display.setText("0");
    }

    private void doOperation() {
        Double result = 0.0;
        Double second_num = Double.valueOf(display.getText());
        switch (current_operation) {
            case "/": result = first_num / second_num; break;
            case "*": result = first_num * second_num; break;
            case "+": result = first_num + second_num; break;
            case "-": result = first_num - second_num; break;
            default: result = 0.0;
        }
        display.setText(result.toString());
    }

    private void operationBacks(){
        display.setText(String_backspace.chop(display.getText()));
        if (display.getText().equals("")) display.setText("0");
    }

    private void doClear() {
        display.setText("0");
        first_num = 0.0;
        current_operation = "";
    }

    private void addToButtons(JButton b) {
        buttons[count_b++] = b;
        b.setFont(font);
        b.setForeground(new Color(255, 150, 30));
        b.setBackground(new Color(0, 161, 199));
        add(b);
    }
}
