import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")

public class Main extends JFrame {
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;
    private JTextField textFieldA;
    private JTextField textFieldB;
    private JTextField textFieldC;
    private JTextField textFieldM1;
    private JTextField textFieldM2;
    private JTextField textFieldM3;
    private JTextField textFieldResult;
    private ButtonGroup radioButtonsfor = new ButtonGroup();
    private ButtonGroup radioButtonsvar = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private Box hboxVariableSelect = Box.createHorizontalBox();
    private int formulaId = 1;
    private int varId = 1;
    Double mem1 =0.0; Double mem2 = 0.0; Double mem3 = 0.0;
    Double result;
    JLabel labelFormula1 = new JLabel();

    public Double formula1(Double x, Double y, Double z)  {
        result = Math.sin(Math.log(y)+Math.sin(3.14159265*y*y))*Math.pow((x*x+Math.sin(z)+Math.exp(Math.cos(z))), 0.25);
        return result;
    }

    public Double formula2(Double x, Double y, Double z) {
        result = Math.pow(Math.cos(Math.exp(x))+Math.log(Math.pow((1+y), 2))+Math.sqrt(Math.exp(Math.cos(x))+Math.pow(Math.sin(3.14159265*z), 2))+Math.sqrt(1/x)+Math.cos(y*y), Math.sin(z));
        return result;
    }

    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);

        ImageIcon imagef1 = new ImageIcon("src\\Formula1.png");
        Image image = imagef1.getImage();
        Image newimg = image.getScaledInstance(500, 60,  java.awt.Image.SCALE_SMOOTH);
        imagef1 = new ImageIcon(newimg);
        labelFormula1.setIcon(imagef1);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Main.this.formulaId = formulaId;

                if (formulaId==1){
                    ImageIcon imagef1 = new ImageIcon("src\\Formula1.png");
                    Image image = imagef1.getImage();
                    Image newimg = image.getScaledInstance(500, 60,  java.awt.Image.SCALE_SMOOTH);
                    imagef1 = new ImageIcon(newimg);
                    labelFormula1.setIcon(imagef1);}
                else {
                    ImageIcon imagef2 = new ImageIcon("src\\Formula2.png");
                    Image image = imagef2.getImage();
                    Image newimg = image.getScaledInstance(500, 60,  java.awt.Image.SCALE_SMOOTH);
                    imagef2 = new ImageIcon(newimg);
                    labelFormula1.setIcon(imagef2);}
            }
        });
        radioButtonsfor.add(button);
        hboxFormulaType.add(button);
    }

    private void addRadioButton_var(String buttonName, final int varId) {
        JRadioButton button = new JRadioButton(buttonName);

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Main.this.varId = varId;
            }
        });
        radioButtonsvar.add(button);
        hboxVariableSelect.add(button);
    }

    public Main() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);

        hboxVariableSelect.add(Box.createHorizontalGlue());
        addRadioButton_var("Переменная 1", 1);
        addRadioButton_var("Переменная 2", 2);
        addRadioButton_var("Переменная 3", 3);


        Box hboxRMButtons = Box.createHorizontalBox();
        hboxRMButtons.add(Box.createHorizontalGlue());
        textFieldM1 = new JTextField(15);
        textFieldM1.setMaximumSize(textFieldM1.getPreferredSize());
        hboxRMButtons.add(textFieldM1);
        hboxRMButtons.add(Box.createHorizontalGlue());
        textFieldM2 = new JTextField(15);
        textFieldM2.setMaximumSize(textFieldM2.getPreferredSize());
        hboxRMButtons.add(textFieldM2);
        hboxRMButtons.add(Box.createHorizontalGlue());
        textFieldM3 = new JTextField(15);
        textFieldM3.setMaximumSize(textFieldM3.getPreferredSize());
        hboxRMButtons.add(textFieldM3);
        hboxRMButtons.add(Box.createHorizontalGlue());


        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);

        radioButtonsvar.setSelected(
                radioButtonsvar.getElements().nextElement().getModel(), true);
        hboxVariableSelect.add(Box.createHorizontalGlue());

        radioButtonsfor.setSelected(
                radioButtonsfor.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());

        JLabel labelForA = new JLabel("x:");
        textFieldA = new JTextField(10);
        textFieldA.setMaximumSize(textFieldA.getPreferredSize());
        JLabel labelForB = new JLabel("y:");
        textFieldB = new JTextField(10);
        textFieldB.setMaximumSize(textFieldB.getPreferredSize());
        JLabel labelForC= new JLabel("z:");
        textFieldC = new JTextField( 10);
        textFieldC.setMaximumSize(textFieldC.getPreferredSize());
        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForA);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldA);
        hboxVariables.add(Box.createHorizontalStrut(45));
        hboxVariables.add(labelForB);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldB);
        hboxVariables.add(Box.createHorizontalStrut(45));
        hboxVariables.add(labelForC);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldC);
        hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат:");

        textFieldResult = new JTextField("0", 15);
        textFieldResult.setMaximumSize(
                textFieldResult.getPreferredSize());

        JButton buttonMp = new JButton("M+");
        buttonMp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try { if (varId==1) {
                    mem1+=result;
                    String resS = mem1.toString();
                    textFieldM1.setText(resS);
                    }
                    if (varId==2) {
                        mem2+=result;
                        String resS = mem2.toString();
                        textFieldM2.setText(resS);
                    }
                    if (varId==3) {
                        mem3+=result;
                        String resS = mem3.toString();
                        textFieldM3.setText(resS);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    if (varId==1) {
                        mem1 =0.0;
                        result = 0.0;
                        textFieldM1.setText("0");
                    }
                    if (varId==2) {
                        mem2=0.0;
                        result = 0.0;
                        textFieldM2.setText("0");
                    }
                    if (varId==3) {
                        mem3=0.0;
                        result = 0.0;
                        textFieldM3.setText("0");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка", "Error",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        Box hboxMButtons = Box.createHorizontalBox();
        buttonMp.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonMC.setAlignmentX(Component.RIGHT_ALIGNMENT);
        hboxMButtons.add(buttonMp);
        hboxMButtons.add(Box.createHorizontalGlue());
        hboxMButtons.add(buttonMC);




        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        
        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double A = Double.parseDouble(textFieldA.getText());
                    Double B = Double.parseDouble(textFieldB.getText());
                    Double C = Double.parseDouble(textFieldC.getText());

                    if (formulaId==1)  result  = formula1(A, B, C);
                    else result = formula2(A, B, C);
                    String resS = result.toString();
                    textFieldResult.setText(resS);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(Main.this,
                            "Ошибка в формате записи числа с плавающей точкой", "Ошибочный формат числа",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        JButton buttonReset = new JButton("Очистить поля");
        buttonReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                textFieldA.setText("0");
                textFieldB.setText("0");
                textFieldC.setText("0");
                textFieldResult.setText("0");
            }
        });
        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonReset);
        hboxButtons.add(Box.createHorizontalGlue());

        Box hboxFormulaImage1  = Box.createHorizontalBox();
        hboxFormulaImage1.add(Box.createHorizontalGlue());
        hboxFormulaImage1.add(labelFormula1);
        hboxFormulaImage1.add(Box.createHorizontalGlue());

        Box contentBox = Box.createVerticalBox();
        contentBox.add(Box.createVerticalGlue());
        contentBox.add(hboxVariableSelect);
        contentBox.add(hboxRMButtons);
        contentBox.add(hboxFormulaType);
        contentBox.add(hboxVariables);
        contentBox.add(hboxMButtons);
        contentBox.add(hboxFormulaImage1);
        contentBox.add(hboxResult);
        contentBox.add(hboxButtons);
        contentBox.add(Box.createVerticalGlue());
        getContentPane().add(contentBox, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        Main frame = new Main();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}