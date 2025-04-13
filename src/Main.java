

import Tank_Res.MyPanel;

import javax.swing.*;

//This is the main fuction to start this game
public class Main extends JFrame {
    MyPanel panel = null;
    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        panel = new MyPanel();
        this.add(panel);
        this.setSize(1000,750);
        this.setVisible(true);
        this.addKeyListener(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}