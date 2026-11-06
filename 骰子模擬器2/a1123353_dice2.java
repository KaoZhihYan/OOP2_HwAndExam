import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class a1123353_dice2 extends JFrame {
    private int count = 0;
    private int sum = 0;

    private JLabel statsLabel;
    private JLabel diceLabel;
    private JButton rollButton;
    private Random random = new Random();

    // 1 到 6 點對應的 Unicode 骰子圖案
    private final String[] DICE_ICONS = {"⚀", "⚁", "⚂", "⚃", "⚄", "⚅"};

    public a1123353_dice2() {
        setTitle("骰子模擬器");
        setSize(400, 340);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        statsLabel = new JLabel("已擲 0 次，總和 0，平均 0.00", SwingConstants.CENTER);
        statsLabel.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 16));
        add(statsLabel, BorderLayout.NORTH);

        diceLabel = new JLabel("⚀", SwingConstants.CENTER);
        // 使用支援符號的字型放大顯示骰子圖案
        diceLabel.setFont(new Font("Segoe UI Symbol", Font.BOLD, 100));
        add(diceLabel, BorderLayout.CENTER);

        rollButton = new JButton("擲骰子");
        rollButton.setFont(new Font("Microsoft JhengHei", Font.PLAIN, 18));
        add(rollButton, BorderLayout.SOUTH);

        rollButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startRollAnimation();
            }
        });
    }

    private void startRollAnimation() {
        // 動畫開始時暫時停用按鈕，避免連續快速點擊
        rollButton.setEnabled(false);

        final int durationMs = 600; // 動畫總持續時間 (毫秒)
        final int intervalMs = 50;  // 畫面更新間隔 (毫秒)
        final long startTime = System.currentTimeMillis();

        // 建立 Swing Timer 實現轉動動畫效果
        Timer timer = new Timer(intervalMs, null);
        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long elapsed = System.currentTimeMillis() - startTime;

                if (elapsed < durationMs) {
                    // 動畫播放中：隨機快速變換圖案與灰色顯示
                    int tempPoint = random.nextInt(6);
                    diceLabel.setText(DICE_ICONS[tempPoint]);
                    diceLabel.setForeground(Color.GRAY);
                } else {
                    // 動畫結束：停止計時器、計算最終點數並恢復按鈕
                    timer.stop();
                    finalizeRoll();
                    rollButton.setEnabled(true);
                }
            }
        });

        timer.start();
    }

    private void finalizeRoll() {
        int point = random.nextInt(6) + 1;

        count++;
        sum += point;
        double avg = (double) sum / count;

        statsLabel.setText(String.format("已擲 %d 次，總和 %d，平均 %.2f", count, sum, avg));

        // 設定最終骰子圖案
        diceLabel.setText(DICE_ICONS[point - 1]);

        // 依據點數變更顏色
        if (point == 6) {
            diceLabel.setForeground(new Color(46, 139, 87)); // 綠色
        } else if (point == 1) {
            diceLabel.setForeground(Color.RED);
        } else {
            diceLabel.setForeground(Color.BLACK);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new a1123353_dice2().setVisible(true);
        });
    }
}