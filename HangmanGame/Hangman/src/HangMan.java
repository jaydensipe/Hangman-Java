import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class HangMan
{

    // GUI Variables
    private JPanel mainPanel;
    private JButton hardButton;
    private JButton mediumButton;
    private JButton easyButton;
    public static JFrame frame = new JFrame("Hangman!");

    private Random rng = new Random();
    private Scanner fileScanner;

    // This detects whether the user picks "easy, medium, or hard" from the main menu, and loads
    // the associating file containing the texts for the game
    public HangMan()
    {
        ArrayList<String> words = new ArrayList<>();

        easyButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // loads the file and adds words to word array
                try
                {
                    fileScanner = new Scanner(new File("textlist/easy.txt"));

                } catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }

                while(fileScanner.hasNext())
                {
                    String word = fileScanner.nextLine();
                    words.add(word);
                }

                frame.setVisible(false);

                // calls play on the game object, to start the game
                MainGame game = new MainGame(words.get(rng.nextInt(words.size())));
                game.play(words.get(rng.nextInt(words.size())));
            }
        });
        mediumButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    fileScanner = new Scanner(new File("textlist/medium.txt"));

                } catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }

                while(fileScanner.hasNext())
                {
                    String word = fileScanner.nextLine();
                    words.add(word);
                }

                frame.setVisible(false);

                MainGame game = new MainGame(words.get(rng.nextInt(words.size())));
                game.play(words.get(rng.nextInt(words.size())));
            }
        });
        hardButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                try
                {
                    fileScanner = new Scanner(new File("textlist/hard.txt"));

                } catch (FileNotFoundException fileNotFoundException)
                {
                    fileNotFoundException.printStackTrace();
                }

                while(fileScanner.hasNext())
                {
                    String word = fileScanner.nextLine();
                    words.add(word);
                }

                frame.setVisible(false);

                MainGame game = new MainGame(words.get(rng.nextInt(words.size())));
                game.play(words.get(rng.nextInt(words.size())));
            }
        });
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException
    {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        frame.setContentPane(new HangMan().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setResizable(false);
        frame.setVisible(true);
        frame.requestFocus();
    }
}
