import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class a1123353_unitConversion {
    static JFrame frm = new JFrame("Unit Conversion");

    static final String[] lengthUnits = {"公尺", "公分", "英吋", "英尺"};
    static final String[] weightUnits = {"公斤", "公克", "磅", "盎司"};
    static final String[] temperatureUnits = {"攝氏", "華氏", "克氏"};

    static JComboBox<String> typeComboBox = new JComboBox<>(new String[]{"長度", "重量", "溫度"}); //north

    static JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10)); //center
    static JTextField inputField = new JTextField();
    static JComboBox<String> sourceUnitComboBox = new JComboBox<>(lengthUnits);
    static JTextField outputField = new JTextField();
    static JComboBox<String> targetUnitComboBox = new JComboBox<>(lengthUnits);

    static JButton convertButton = new JButton("轉換");//south

    public static void main(String[] args) {
        frm.setSize(480, 280);
        frm.setLayout(new BorderLayout(5, 5));
        
        outputField.setEditable(false);
        centerPanel.add(inputField);
        centerPanel.add(sourceUnitComboBox);
        centerPanel.add(outputField);
        centerPanel.add(targetUnitComboBox);

        frm.add(typeComboBox, BorderLayout.NORTH);
        frm.add(centerPanel, BorderLayout.CENTER);
        frm.add(convertButton, BorderLayout.SOUTH);

        typeComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedType = (String) typeComboBox.getSelectedItem();
                String[] units = lengthUnits; // 預設為長度單位
                switch (selectedType) {
                    case "長度":
                        units = lengthUnits;
                        break;
                    case "重量":
                        units = weightUnits;
                        break;
                    case "溫度":
                        units = temperatureUnits;
                        break;
                }
                sourceUnitComboBox.setModel(new DefaultComboBoxModel<>(units));
                targetUnitComboBox.setModel(new DefaultComboBoxModel<>(units));
            }
        });

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inputText = inputField.getText().trim();
                try {
                    double value = Double.parseDouble(inputText);
                    
                    String type = (String) typeComboBox.getSelectedItem();
                    String sourceUnit = (String) sourceUnitComboBox.getSelectedItem();
                    String targetUnit = (String) targetUnitComboBox.getSelectedItem();

                    double result = 0;
                    
                    if ("長度".equals(type)) {
                        result = convertLength(value, sourceUnit, targetUnit);
                    } else if ("重量".equals(type)) {
                        result = convertWeight(value, sourceUnit, targetUnit);
                    } else if ("溫度".equals(type)) {
                        result = convertTemperature(value, sourceUnit, targetUnit);
                    }

                    outputField.setText(String.format("%.4f", result));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frm, "請輸入有效的數字！", "錯誤", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frm.setLocationRelativeTo(null);
        frm.setVisible(true);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }

    static double convertLength(double val, String src, String tgt) {
        double meters = switch (src) {
            case "公尺" -> val;
            case "公分" -> val / 100.0;
            case "英吋" -> val * 0.0254;
            case "英尺" -> val * 0.3048;
            default -> val;
        };

        return switch (tgt) {
            case "公尺" -> meters;
            case "公分" -> meters * 100.0;
            case "英吋" -> meters / 0.0254;
            case "英尺" -> meters / 0.3048;
            default -> meters;
        };
    }

    static double convertWeight(double val, String src, String tgt) {
        double kg = switch (src) {
            case "公斤" -> val;
            case "公克" -> val / 1000.0;
            case "磅" -> val * 0.45359237;
            case "盎司" -> val * 0.028349523125;
            default -> val;
        };

        return switch (tgt) {
            case "公斤" -> kg;
            case "公克" -> kg * 1000.0;
            case "磅" -> kg / 0.45359237;
            case "盎司" -> kg / 0.028349523125;
            default -> kg;
        };
    }

    static double convertTemperature(double val, String src, String tgt) {
        double celsius = switch (src) {
            case "攝氏" -> val;
            case "華氏" -> (val - 32) * 5 / 9;
            case "克氏" -> val - 273.15;
            default -> val;
        };

        return switch (tgt) {
            case "攝氏" -> celsius;
            case "華氏" -> celsius * 9 / 5 + 32;
            case "克氏" -> celsius + 273.15;
            default -> celsius;
        };
    }
}