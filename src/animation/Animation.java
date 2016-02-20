package animation;

/*
 * **************************************** CREDITS & RESOURCES *****************************************************
 * CREDITS:
 * Created & Coded By: Jordan Davidson
 * Other Group Members: None (Group Dissolved)
 * Group Project 01: Animation
 * Date: 10-15-2015
 * 
 * 
 * RESOURCES:
 * Cool Shapes Gif from: http://giphy.com
 * Slider Timer Control: http://stackoverflow.com/questions/13577707/java-timer-change-delay-with-button
 * How to use a Timer in Java with Images: https://www.youtube.com/watch?v=7NTXu0V97BE
 * ******************************************************************************************************************
 */

import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Animation extends JFrame {

    private final static int SLIDES = 73; //number of images in gif
    private static int SLIDE_TIMER = 40; //number of MS per image
    private int slideNumber = 1; //starts on frame_1 gif
    private final JLabel imageLabel = new JLabel(); //label for placing the images    
    private JLabel selectionLabel = new JLabel(); //this will hold the slider
    private JPanel imagePanel = new JPanel(new FlowLayout()); //this is the JPanel that contains the Labels
    private JSlider slider = new JSlider(10, 100); //the slider that controls slide timer

    public Animation() {
        Timer timer = new Timer(SLIDE_TIMER, new Listener()); //need timer because images need to change over time
        timer.start();

        add(imagePanel);
        imagePanel.add(imageLabel);
        imagePanel.add(selectionLabel);
        
        //now we construct the slider:
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setValue(40); //default to 40ms
        
        imagePanel.add(new JLabel("Fast"));
        imagePanel.add(slider);
        imagePanel.add(new JLabel("Slow"));

        slider.addChangeListener((ChangeEvent e) -> {
            timer.setDelay(slider.getValue()); //sets timer delay to what the slider's value is
        });

        imageLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/frame_1.gif"))); //start on frame1

    }

    class Listener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (slideNumber == 72) {
                slideNumber = 1; //if this doesn't exist we get a runtime error
            } else {
                slideNumber = (slideNumber + 1) % SLIDES; //allows infinite repetition 
            }
            imageLabel.setIcon(new ImageIcon(getClass().getClassLoader().getResource("resources/images/frame_" + slideNumber + ".gif")));
            //loops through frames
        }
    }

    public static void main(String[] args) {
        
        //constructing the JFrame:
        Animation frame = new Animation();
        frame.setTitle("A cool Animation");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
