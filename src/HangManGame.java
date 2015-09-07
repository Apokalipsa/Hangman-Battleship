
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class GameStructure {
    private String[] wordList = { "computer", "java", "activity", "alaska",           // create String Array of words for game for simplicity
            "appearance", "javatar", "automobile", "falafel", "birthday",
            "canada", "central", "character", "chicken", "chosen", "cutting",
            "daily", "darkness", "shawarma", "disappear", "driving", "effort",
            "establish", "exact", "establishment", "fifteen", "football",
            "foreign", "frequently", "frighten", "function", "gradually",
            "hurried", "identity", "importance", "impossible", "invented",
            "italian", "journey", "lincoln", "london", "massage", "minerals",
            "outer", "paint", "particles", "personal", "physical", "progress",
            "quarter", "recognise", "replace", "rhythm", "situation",
            "slightly", "steady", "stepped", "strike", "successful", "sudden",
            "terrible", "traffic", "unusual", "volume", "yesterday" };
    private JTextField tf;
    private JLabel jlLetsUsed;
    static String letter;
    static int[] wordLength = new int[64];
    static int level = (int) (Math.random() * 64);        //  chose random word
    static JFrame frame = new JFrame();
    static ImageIcon bg = new ImageIcon("hangman1.png");
    static ImageIcon logo = new ImageIcon("logo.png");
    static ImageIcon ic = new ImageIcon("hangmanIcon.png");
    JLabel img = new JLabel(bg, JLabel.CENTER);

    public void window() {
        bg = new ImageIcon("hangman1.png");
        img.setIcon(bg);
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        menu.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menu);
        JMenuItem menuItem = new JMenuItem("Developer", KeyEvent.VK_T);
        menu.add(menuItem);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {         // right click key
                JOptionPane.showMessageDialog(frame, "Developer: Dijana Markovic"
                        + "\n" + "\n" + "The Javatar", "Developer",
                        JOptionPane.INFORMATION_MESSAGE, logo);
            }              // end actionPerformed method
        });
        JMenuItem menuItem2 = new JMenuItem("Instructions", KeyEvent.VK_T);
        menu.add(menuItem2);
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {        // right click key
                JOptionPane
                        .showMessageDialog(
                                frame,
                                " Hangman is a guessing game where the word"
                                        + "\n"
                                        + " to guess is represented by dashes. The player"
                                        + "\n"
                                        + " is given the option to enter a letter. If the letter"
                                        + "\n"
                                        + " guessed is contained in the word, the letter will"
                                        + "\n"
                                        + " replace the dash in its approprate placement."
                                        + "\n"
                                        + " You can not exceed 6 wrong guesses or else you"
                                        + "\n"
                                        + " lose. Words are selected randomly.",
                                "Instructions",
                                JOptionPane.INFORMATION_MESSAGE, logo);
            }           // end actionPerformed method
        });
        JMenuItem menuItem3 = new JMenuItem("Restart", KeyEvent.VK_T);
        menu.add(menuItem3);
        menuItem3.addActionListener(new ActionListener() {
            @Override 
            public void actionPerformed(ActionEvent e) {      // right click key
                GameStructure restart = new GameStructure();
                level = (int) (Math.random() * 64);          // chose random word
                restart.window();
            }              // end actionPerformed method
        });
        JMenuItem menuItem4 = new JMenuItem("Exit", KeyEvent.VK_T);
        menu.add(menuItem4);
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {     // right click key
                System.exit(0);
            }               // end actionPerformed method
        });

        JFrame gameFrame = new JFrame();
        JPanel bottomRight = new JPanel();
        JPanel bottomLeft = new JPanel();
        JPanel top = new JPanel();
        JPanel bottom = new JPanel();
        JPanel imgPane = new JPanel();
        JPanel panel1 = new JPanel();
        bottom.setLayout(new BoxLayout(bottom, BoxLayout.X_AXIS));
        imgPane.setLayout(new BorderLayout());
        panel1.setLayout(new BorderLayout());
        panel1.setOpaque(false);
        top.setBorder(BorderFactory.createTitledBorder(""));
        bottom.setBorder(BorderFactory.createTitledBorder(""));
        tf = new JTextField(1);
        JLabel jl = new JLabel("Enter a letter", JLabel.CENTER);
        jlLetsUsed = new JLabel("Letters used:  ", JLabel.CENTER);
        final JLabel jlLines = new JLabel("__ ", JLabel.CENTER);
        jl.setFont(new Font("Rockwell", Font.PLAIN, 20));
        tf.setFont(new Font("Rockwell", Font.PLAIN, 20));
        jlLetsUsed.setFont(new Font("Rockwell", Font.PLAIN, 20));
        jlLines.setFont(new Font("Rockewell", Font.PLAIN, 20));
        imgPane.add(img);                                            // center
        top.add(jl);                                                // top center
        top.add(tf);                                               // top center
        bottomLeft.add(jlLetsUsed);                               // bottom left position
        bottomRight.add(jlLines);                                // bottom right position
        bottom.add(bottomLeft);                                 // bottom
        bottom.add(bottomRight);                               // bottom
        panel1.add(imgPane, BorderLayout.CENTER);             // background image (center)
        panel1.add(top, BorderLayout.NORTH);                 // text field and jlabel (top)
        panel1.add(bottom, BorderLayout.SOUTH);             // blank spaces and letters used
        gameFrame.setJMenuBar(menuBar);
        gameFrame.setTitle("Hangman");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setIconImage(new ImageIcon("logo.png").getImage());
        gameFrame.setResizable(false);
        gameFrame.add(panel1);
        gameFrame.setSize(800, 500);
        gameFrame.setLocationRelativeTo(null);
        gameFrame.setVisible(true);

        int j = 0;
        String line = "";
        for (j = 0; j < 64; j++) {
            wordLength[j] = wordList[j].length();                // gets length of words
        }  // end for loop
        int m = 0;
        // creates line first then put into .setText
        while (m < wordLength[level]) {
            line += "__ ";
            m++;
        } // end for loop
        jlLines.setText(line);

        tf.addActionListener(new ActionListener() {
            int wrong = 0;
            int right = 0;

            @Override
            public void actionPerformed(ActionEvent e) {       // enter key
                JTextField tf = (JTextField) e.getSource();
                letter = tf.getText();
                tf.setText("");
                // tf.requestFocus();
                jlLetsUsed.setText(jlLetsUsed.getText() + letter + " ");   // sets jlabel text to users entered letter
                char[] jlabelText = jlLines.getText().toCharArray();      // converts string to character array(array is length of string)
                char userEnteredChar = letter.charAt(0);
                // System.out.println(wordList[level]);
                if (!wordList[level].contains(letter)) {
                    wrong++;
                    if (wrong == 1) {
                        bg = new ImageIcon("hangman2.png");
                        img.setIcon(bg);
                    }
                    if (wrong == 2) {
                        bg = new ImageIcon("hangman3.png");
                        img.setIcon(bg);
                    }
                    if (wrong == 3) {
                        bg = new ImageIcon("hangman4.png");
                        img.setIcon(bg);
                    }
                    if (wrong == 4) {
                        bg = new ImageIcon("hangman5.png");
                        img.setIcon(bg);
                    }
                    if (wrong == 5) {
                        bg = new ImageIcon("hangman6.png");
                        img.setIcon(bg);
                    }
                    if (wrong == 6) {
                        bg = new ImageIcon("hangman7.png");
                        img.setIcon(bg);
                    }
                    if (wrong == 6) {
                        JOptionPane
                                .showMessageDialog(frame, "He's dead." + "\n"
                                        + "Press 'OK' to restart." + "\n"
                                        + "The word was " + wordList[level]
                                        + ".", "You Lost",
                                        JOptionPane.INFORMATION_MESSAGE, ic);
                        GameStructure restart = new GameStructure();
                        level = (int) (Math.random() * 64);                       // generate new random word
                                                       
                        restart.window();
                    }
                    return;
                }
                int i = 0;
                for (i = 0; i < wordList[level].length(); i++) {
                    if (wordList[level].charAt(i) == userEnteredChar) {
                        jlabelText[3 * i] = ' ';
                        jlabelText[3 * i + 1] = userEnteredChar;
                        right++;
                    }              // end if statment
                }        // end for loop
                jlLines.setText(String.valueOf(jlabelText));
                if (jlabelText.length / 3 == right) {
                    JOptionPane.showMessageDialog(frame, "You got the word!"
                            + "\n" + "Press 'OK' for new word", "Good Job",
                            JOptionPane.INFORMATION_MESSAGE, logo);
                    GameStructure restart = new GameStructure();
                    level = (int) (Math.random() * 64);                            // generate new random word
                                                        
                    restart.window();
                }
            }
        });
    }                                            
}

public class HangManGame {                                      // invoke methods for start game
    public static void main(String[] args) {
        GameStructure game = new GameStructure();
        game.window();
    }
}