import javax.swing.*;

public class Calculator {
    public static void createUI () {
        JFrame window = new JFrame("Calculator 1.0");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(335, 400);
        window.setResizable(false);
        window.add(new Panel());
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createUI();
            }
        });
    }
}
