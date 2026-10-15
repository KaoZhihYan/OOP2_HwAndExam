import javax.swing.*;
import java.awt.*;

public class a1123353_loginwindow extends JFrame {
    public a1123353_loginwindow() {
        setTitle("登入");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2, 10, 10));

        JLabel l1 = new JLabel("帳號");
        JTextField t1 = new JTextField();
        JLabel l2 = new JLabel("密碼");
        JPasswordField t2 = new JPasswordField();
        JButton btn = new JButton("登入");

        add(l1); add(t1); add(l2); add(t2); add(btn);

        btn.addActionListener(e -> {
            if (t1.getText().equals("admin") && new String(t2.getPassword()).equals("123456")) {
               System.out.println("登入成功"); 
            } else {
                JOptionPane.showMessageDialog(this, "帳號或密碼錯誤", "登入失敗", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
    public static void main(String[] a) {new a1123353_loginwindow(); }
}
