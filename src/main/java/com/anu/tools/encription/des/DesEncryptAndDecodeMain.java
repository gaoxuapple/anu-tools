package com.anu.tools.encription.des;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Describe: DES算法加解密小工具
 * Author: anu
 * Mail: anhui_gaoxu@126.com
 * Phone: 18355150480
 * Date: 2019年05月29日 15:02
 * Copyright: © 2019.Anu., Ltd. All rights reserved.
 */
public class DesEncryptAndDecodeMain  extends JFrame {

    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JButton jButton4;
    private JButton jButton5;
    private JButton jButton6;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JTextArea jTextArea1;
    private JTextArea jTextArea2;

    /**
     * 构造方法
     */
    public DesEncryptAndDecodeMain() {
        initComponents();
        setLocationRelativeTo(getOwner());
    }


    private void initComponents()
    {
        this.jPanel1 = new JPanel();
        this.jPanel2 = new JPanel();
        this.jButton1 = new JButton();
        this.jButton2 = new JButton();
        this.jButton3 = new JButton();
        this.jButton4 = new JButton();
        this.jScrollPane1 = new JScrollPane();
        this.jTextArea1 = new JTextArea();
        this.jScrollPane2 = new JScrollPane();
        this.jTextArea2 = new JTextArea();
        this.jLabel1 = new JLabel();
        this.jLabel2 = new JLabel();
        this.jLabel3 = new JLabel();
        this.jLabel4 = new JLabel();
        this.jButton5 = new JButton();
        this.jButton6 = new JButton();

        GroupLayout jPanel1Layout = new GroupLayout(this.jPanel1);
        this.jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, 32767));

        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 100, 32767));

        setDefaultCloseOperation(3);
        setTitle("<DES算法> 加密、解密工具");
        setResizable(false);

        this.jButton1.setText("加密");
        this.jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DesEncryptAndDecodeMain.this.jButton1ActionPerformed(evt);
            }
        });
        this.jButton2.setText("解密");
        this.jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DesEncryptAndDecodeMain.this.jButton2ActionPerformed(evt);
            }
        });
        this.jButton3.setText("复制");
        this.jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DesEncryptAndDecodeMain.this.jButton3ActionPerformed(evt);
            }
        });
        this.jButton4.setText("关闭");
        this.jButton4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DesEncryptAndDecodeMain.this.jButton4ActionPerformed(evt);
            }
        });
        this.jTextArea1.setColumns(20);
        this.jTextArea1.setRows(5);
        this.jScrollPane1.setViewportView(this.jTextArea1);
        this.jTextArea1.setLineWrap(true);

        this.jTextArea2.setColumns(20);
        this.jTextArea2.setRows(5);
        this.jScrollPane2.setViewportView(this.jTextArea2);
        this.jTextArea2.setLineWrap(true);

        this.jLabel1.setText("请输入文本：");

        this.jLabel2.setText("输出文本为：");

        this.jLabel3.setText("阿奴开发小工具");

        this.jLabel4.setFont(new Font("隶书", 1, 24));
        this.jLabel4.setText("  <DES算法> 加密、解密工具");

        this.jButton5.setText("清除");
        this.jButton5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DesEncryptAndDecodeMain.this.jButton5ActionPerformed(evt);
            }
        });
        this.jButton6.setText("清除");
        this.jButton6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                DesEncryptAndDecodeMain.this.jButton6ActionPerformed(evt);
            }
        });
        GroupLayout jPanel2Layout = new GroupLayout(this.jPanel2);
        this.jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(28, 28, 28)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(this.jLabel2, -1, -1, 32767)
                                                        .addComponent(this.jLabel1, -1, -1, 32767))
                                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addComponent(this.jScrollPane1, -1, 447, 32767)
                                                        .addComponent(this.jScrollPane2)))
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(228, 228, 228)
                                                                .addComponent(this.jButton3)
                                                                .addGap(52, 52, 52)
                                                                .addComponent(this.jButton4)
                                                                .addGap(37, 37, 37)
                                                                .addComponent(this.jButton6))
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGap(224, 224, 224)
                                                                .addComponent(this.jButton1)
                                                                .addGap(53, 53, 53)
                                                                .addComponent(this.jButton2)
                                                                .addGap(43, 43, 43)
                                                                .addComponent(this.jButton5)))
                                                .addGap(0, 0, 32767)))
                                .addGap(49, 49, 49))
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, 32767)
                                .addComponent(this.jLabel3, -2, 294, -2)
                                .addGap(68, 68, 68))
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(121, 121, 121)
                                .addComponent(this.jLabel4, -2, 358, -2)
                                .addContainerGap(-1, 32767)));

        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addComponent(this.jLabel4, -2, 38, -2)
                                .addGap(39, 39, 39)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.jLabel1, -2, 24, -2)
                                        .addComponent(this.jScrollPane1, -2, 77, -2))
                                .addGap(26, 26, 26)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(this.jButton2)
                                        .addComponent(this.jButton1)
                                        .addComponent(this.jButton5))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.jScrollPane2, -2, 80, -2)
                                        .addComponent(this.jLabel2, -2, 37, -2))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(this.jButton3)
                                        .addGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                                .addComponent(this.jButton4)
                                                .addComponent(this.jButton6)))
                                .addGap(28, 28, 28)
                                .addComponent(this.jLabel3, -1, 35, 32767)));

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(this.jPanel2, -1, -1, 32767)
                                .addContainerGap()));

        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(this.jPanel2, -1, -1, 32767)
                                .addContainerGap()));

        pack();
    }

    private void jButton1ActionPerformed(ActionEvent evt)
    {
        try {
            String data1 = this.jTextArea1.getText();
            String data2 = DesEncryptAndDecodeUtil.encode(data1);
            this.jTextArea2.setText(data2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jButton4ActionPerformed(ActionEvent evt) {
        System.exit(0);
    }

    private void jButton5ActionPerformed(ActionEvent evt) {
        this.jTextArea1.setText("");
    }

    private void jButton6ActionPerformed(ActionEvent evt) {
        this.jTextArea2.setText("");
    }

    private void jButton3ActionPerformed(ActionEvent evt) {
        this.jTextArea2.selectAll();
        this.jTextArea2.copy();
    }

    private void jButton2ActionPerformed(ActionEvent evt)
    {
        try {
            String data1 = this.jTextArea1.getText();
            String data2 = DesEncryptAndDecodeUtil.decode(data1);
            this.jTextArea2.setText(data2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
        }
        catch (ClassNotFoundException ex) {
            Logger.getLogger(DesEncryptAndDecodeMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DesEncryptAndDecodeMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DesEncryptAndDecodeMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(DesEncryptAndDecodeMain.class.getName()).log(Level.SEVERE, null, ex);
        }

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DesEncryptAndDecodeMain().setVisible(true);
            }
        });
    }


}
