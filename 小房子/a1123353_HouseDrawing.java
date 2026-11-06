import javax.swing.*;
import java.awt.*;

public class a1123353_HouseDrawing extends JPanel {

    public a1123353_HouseDrawing() {
        // 設定畫布尺寸為 500x400
        this.setPreferredSize(new Dimension(500, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // 1. 天空與草地
        // 天空 (上半部，天藍色)
        g.setColor(new Color(135, 206, 235));
        g.fillRect(0, 0, 500, 320);

        // 草地 (下半部，綠色)
        g.setColor(new Color(50, 160, 65));
        g.fillRect(0, 320, 500, 80);

        // 2. 太陽與光芒
        int sunCenterX = 415;
        int sunCenterY = 85;
        int sunRadius = 38;

        // 用 drawLine 畫 8 條光芒 (搭配 for 迴圈與 Math.cos、Math.sin)
        g.setColor(Color.YELLOW);
        int rayCount = 8;
        int rInner = 45;
        int rOuter = 65;
        for (int i = 0; i < rayCount; i++) {
            double angle = i * (2 * Math.PI / rayCount);
            int x1 = sunCenterX + (int) (rInner * Math.cos(angle));
            int y1 = sunCenterY + (int) (rInner * Math.sin(angle));
            int x2 = sunCenterX + (int) (rOuter * Math.cos(angle));
            int y2 = sunCenterY + (int) (rOuter * Math.sin(angle));
            g.drawLine(x1, y1, x2, y2);
        }

        // 用 fillOval 畫黃色正圓太陽
        g.fillOval(sunCenterX - sunRadius, sunCenterY - sunRadius, sunRadius * 2, sunRadius * 2);

        // 3. 雲朵 (至少 3 個互相重疊的白色 fillOval)
        g.setColor(Color.WHITE);
        g.fillOval(70, 65, 80, 50);
        g.fillOval(110, 50, 90, 55);
        g.fillOval(160, 65, 65, 45);
        g.fillOval(85, 75, 120, 40);

        // 4. 房屋主體 (fillRect + drawRect)[cite: 2]
        int houseX = 156;
        int houseY = 200;
        int houseW = 188;
        int houseH = 150;

        g.setColor(new Color(252, 228, 186)); // 米黃色
        g.fillRect(houseX, houseY, houseW, houseH);
        g.setColor(Color.BLACK);
        g.drawRect(houseX, houseY, houseW, houseH);

        // 5. 屋頂 (只能用 drawLine 畫出三角形的三條線段，紅色邊框)
        g.setColor(Color.RED);
        int roofTopX = 250, roofTopY = 108;
        int roofLeftX = 136, roofLeftY = 200;
        int roofRightX = 364, roofRightY = 200;

        g.drawLine(roofTopX, roofTopY, roofLeftX, roofLeftY);   // 左斜邊
        g.drawLine(roofLeftX, roofLeftY, roofRightX, roofRightY); // 底邊
        g.drawLine(roofRightX, roofRightY, roofTopX, roofTopY); // 右斜邊

        // 6. 門 (fillRoundRect) 與 門把 (小的 fillOval)[cite: 2]
        int doorW = 46;
        int doorH = 80;
        int doorX = 227;
        int doorY = houseY + houseH - doorH;

        g.setColor(new Color(121, 62, 23)); // 啡色門
        g.fillRoundRect(doorX, doorY, doorW, doorH, 20, 20);

        g.setColor(Color.YELLOW); // 黃色門把
        g.fillOval(doorX + doorW - 10, doorY + doorH / 2, 7, 7);

        // 7. 窗戶 (2 扇，fillRect + drawRect，並用 drawLine 畫十字窗框)[cite: 2]
        int winW = 48;
        int winH = 50;
        int winY = 230;

        // 左窗戶
        drawWindow(g, 175, winY, winW, winH);
        // 右窗戶
        drawWindow(g, 277, winY, winW, winH);

        // 8. 畫面角落簽名[cite: 2]
        g.setColor(Color.BLACK);
        g.drawString("A1123353 高芷妍", 15, 385);
    }

    // 畫窗戶與十字窗框的輔助方法[cite: 2]
    private void drawWindow(Graphics g, int x, int y, int w, int h) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, w, h);
        // 十字窗框
        g.drawLine(x, y + h / 2, x + w, y + h / 2); // 橫線
        g.drawLine(x + w / 2, y, x + w / 2, y + h); // 直線
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("小房子風景畫");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new a1123353_HouseDrawing());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}