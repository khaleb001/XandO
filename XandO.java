import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class XandO {

    JFrame tictac = new JFrame("X and O");

    JButton[] buttons = new JButton[9];

    boolean isXTurn = true;

    public XandO() {
        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Arial", Font.PLAIN, 60));
            buttons[i].setFocusPainted(false);
            buttons[i].addActionListener(new ButtonClickListener());
            tictac.add(buttons[i]);
        }
    }

    void drawUI(){
        tictac.setLayout(new GridLayout(3, 3));
        tictac.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tictac.setSize(400, 400);
        tictac.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();

            if (!clickedButton.getText().isEmpty()) {
                return;
            }

            clickedButton.setText(isXTurn ? "X" : "O");
            isXTurn = !isXTurn;

            checkForWinner();
        }
    }

    private void checkForWinner() {
        int[][] winCombinations = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8},
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
                {0, 4, 8}, {2, 4, 6}
        };

        for (int[] combination : winCombinations) {
            String b1 = buttons[combination[0]].getText();
            String b2 = buttons[combination[1]].getText();
            String b3 = buttons[combination[2]].getText();

            if (!b1.isEmpty() && b1.equals(b2) && b2.equals(b3)) {
                JOptionPane.showMessageDialog(tictac, "Player " + b1 + " wins!");
                resetBoard();
                return;
            }
        }

        boolean allButtonsClicked = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                allButtonsClicked = false;
                break;
            }
        }

        if (allButtonsClicked) {
            JOptionPane.showMessageDialog(tictac, "It's a tie!");
            resetBoard();
        }
    }

    private void resetBoard() {
        for (JButton button : buttons) {
            button.setText("");
        }
        isXTurn = true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            XandO game = new XandO();
            game.drawUI();
        });
    }
}