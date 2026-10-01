import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class a1123353_hw1 extends JFrame {
    private int count = 0;
    private int sum = 0;

    private JLabel statsLabel;
    private JLabel diceLabel;
    private JButton rollButton;
    private Random random = new Random();

    public a1123353_hw1() {
        setTitle("骰子模擬器");
        setSize(400, 320);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        statsLabel = new JLabel("已擲 0 次，總和 0，平均 0.00", SwingConstants.CENTER);
        statsLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
        add(statsLabel, BorderLayout.NORTH);

        diceLabel = new JLabel("-", SwingConstants.CENTER);
        diceLabel.setFont(new Font("SansSerif", Font.BOLD, 60));
        add(diceLabel, BorderLayout.CENTER);

        rollButton = new JButton("擲骰子");
        rollButton.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 18));
        add(rollButton, BorderLayout.SOUTH);

        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
    }

    private void rollDice() {
        int point = random.nextInt(6) + 1;

        count++;
        sum += point;
        double avg = (double) sum / count;

        statsLabel.setText(String.format("已擲 %d 次，總和 %d，平均 %.2f", count, sum, avg));

        diceLabel.setText(String.valueOf(point));

        if (point == 6) {
            diceLabel.setForeground(Color.GREEN);
        } else if (point == 1) {
            diceLabel.setForeground(Color.RED);
        } else {
            diceLabel.setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new a1123353_hw1().setVisible(true);
        });
    }
}