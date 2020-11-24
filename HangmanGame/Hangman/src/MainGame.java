import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainGame
{
    private JTextField answerInput;
    private JPanel mainGamePanel;
    private JTextField correct;
    private JButton retryButton;
    private JTextField incorrect;
    private JLabel hangmanPicture;
    public static JFrame frame = new JFrame("Hangman!");

    private int lives = 7;
    private boolean gotOneRight = false;

    public MainGame(String word)
    {
        char[] actualWord = word.toCharArray();
        ArrayList<Character> guessWord = new ArrayList<>(word.length());
        ArrayList<Character> incorrectGuesses = new ArrayList<>();

        // adds _ for each word, i.e. if word was "car" it would create "_ _ _"
        for (int i = 0; i < word.length(); i++)
        {
            guessWord.add(i, '_');
        }

        // initializes correct and incorrect guess text box, and sets the picture to be used
        correct.setText("Correct Guesses: " +  guessWord.toString());
        incorrect.setText("Incorrect Guesses: " + incorrectGuesses.toString());
        hangmanPicture.setIcon(new ImageIcon("images/hangman1.png"));
        hangmanPicture.repaint();

        answerInput.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                char guess = 0;
                try
                {
                    guess = answerInput.getText().charAt(0);
                    gotOneRight = false;

                    // if the char guessed is correct, sets the _ to the correct letter
                    for (int i = 0; i < word.length(); i++)
                    {
                        if (actualWord[i] == guess)
                        {
                            guessWord.set(i, guess);
                            gotOneRight = true;
                        }
                    }

                    // subtract a life from the player, and add inputted char into the incorrect
                    // guesses
                    if (gotOneRight == false)
                    {
                        incorrectGuesses.add(guess);
                        lives--;
                    }

                    // changes the hangman picture depending on how many lives are left
                    switch (lives)
                    {
                        case 7:
                            hangmanPicture.setIcon(new ImageIcon("images/hangman1.png"));
                            hangmanPicture.repaint();
                            break;
                        case 6:
                            hangmanPicture.setIcon(new ImageIcon("images/hangman2.png"));
                            hangmanPicture.repaint();
                            break;
                        case 5:
                            hangmanPicture.setIcon(new ImageIcon("images/hangman3.png"));
                            hangmanPicture.repaint();
                            break;
                        case 4:
                            hangmanPicture.setIcon(new ImageIcon("images/hangman4.png"));
                            hangmanPicture.repaint();
                            break;
                        case 3:
                            hangmanPicture.setIcon(new ImageIcon("images/hangman5.png"));
                            hangmanPicture.repaint();
                            break;
                        case 2:
                            hangmanPicture.setIcon(new ImageIcon("images/hangman6.png"));
                            hangmanPicture.repaint();
                            break;
                        case 1:
                            hangmanPicture.setIcon(new ImageIcon("images/hangman7.png"));
                            hangmanPicture.repaint();
                            break;
                        default:
                            hangmanPicture.setIcon(new ImageIcon("images/hangman8.png"));
                            hangmanPicture.repaint();
                            retryButton.setText("You lose! The word was " + new String(actualWord).toUpperCase() + "! " +
                                    "Retry?");
                            retryButton.setEnabled(true);
                            answerInput.setEnabled(false);
                            break;
                    }

                    // if the player inputs all the correct chars to form the word
                    if (guessCheck(guessWord))
                    {
                        hangmanPicture.setIcon(new ImageIcon("images/hangman9.png"));
                        hangmanPicture.repaint();
                        retryButton.setText("You Win! The word was " + new String(actualWord) +
                                "! " +
                                "Retry?");
                        answerInput.setEnabled(false);
                        retryButton.setEnabled(true);
                    }

                    // re-updates correct guesses/incorrect guesses
                    answerInput.setText("");
                    correct.setText("Correct Guesses: " +  guessWord.toString());
                    incorrect.setText("Incorrect Guesses: " + incorrectGuesses.toString());
                    correct.repaint();
                }
                catch (StringIndexOutOfBoundsException err)
                {
                    // if the player enters a blank instead of a char
                    JOptionPane.showMessageDialog(frame, "Please enter your guess in the box " +
                            "provided!", "Error", 2);
                }

            }
        });

        retryButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frame.setVisible(false);
                HangMan.frame.setVisible(true);
                mainGamePanel.repaint();
            }
        });
    }

    public void play(String word)
    {
        mainGamePanel = new JPanel();
        answerInput = new JFormattedTextField();
        incorrect = new JFormattedTextField();
        correct = new JFormattedTextField();
        hangmanPicture = new JLabel();
        retryButton = new JButton();

        frame.setContentPane(new MainGame(word).mainGamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setResizable(false);
        frame.setVisible(true);

        answerInput.requestFocus();
        frame.validate();
    }

    // Checks if the player guessed the word correctly
    public static boolean guessCheck(ArrayList<Character> array)
    {
        boolean win = true;

        for(int i = 0; i < array.size(); i++)
        {
            if(array.get(i) == '_')
            {
                win = false;
            }
        }

        return win;
    }

}
