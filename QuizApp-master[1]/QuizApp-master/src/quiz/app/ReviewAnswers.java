package quiz.app;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReviewAnswers extends JFrame {
    private static final Color BACKGROUND_COLOR = new Color(248, 249, 250);
    private static final Color PRIMARY_COLOR = new Color(52, 58, 64);
    private static final Color ACCENT_COLOR = new Color(23, 162, 184);
    private static final Color CORRECT_COLOR = new Color(40, 167, 69);
    private static final Color INCORRECT_COLOR = new Color(220, 53, 69);

    public ReviewAnswers(String name, int score, String[][] questions, String[][] answers, String[][] userAnswers) {
        // Set up frame with modern look
        setTitle("Quiz Review - " + name);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(BACKGROUND_COLOR);

        // Header Panel
        JPanel headerPanel = createHeaderPanel(name, score);
        mainPanel.add(headerPanel, BorderLayout.NORTH);

        // Scrollable Questions Panel
        JPanel questionsPanel = createQuestionsPanel(questions, answers, userAnswers);
        JScrollPane scrollPane = new JScrollPane(questionsPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16); // Smooth scrolling
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Close Button Panel
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        add(mainPanel);

        // Final touches
        setContentPane(mainPanel);
        pack();
        setVisible(true);
    }

    private JPanel createHeaderPanel(String name, int score) {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(BACKGROUND_COLOR);
        headerPanel.setBorder(new EmptyBorder(0, 0, 20, 0));

        // Main Heading
        JLabel heading = new JLabel("Quiz Review", SwingConstants.CENTER);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 32));
        heading.setForeground(PRIMARY_COLOR);
        headerPanel.add(heading, BorderLayout.NORTH);

        // Subheading with name and score
        JLabel subheading = new JLabel(name + " | Total Score: " + score + "/100", SwingConstants.CENTER);
        subheading.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        subheading.setForeground(new Color(108, 117, 125));
        headerPanel.add(subheading, BorderLayout.CENTER);

        return headerPanel;
    }

    private JPanel createQuestionsPanel(String[][] questions, String[][] answers, String[][] userAnswers) {
        JPanel questionsPanel = new JPanel();
        questionsPanel.setLayout(new BoxLayout(questionsPanel, BoxLayout.Y_AXIS));
        questionsPanel.setBackground(BACKGROUND_COLOR);

        for (int i = 0; i < questions.length; i++) {
            JPanel questionPanel = createDetailedQuestionPanel(i, questions, answers, userAnswers);
            questionsPanel.add(questionPanel);
            questionsPanel.add(Box.createRigidArea(new Dimension(0, 15))); // Enhanced spacing
        }

        return questionsPanel;
    }

    private JPanel createDetailedQuestionPanel(int index, String[][] questions, String[][] answers, String[][] userAnswers) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new LineBorder(new Color(220, 220, 220), 1, true)); // Soft border
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200)); // Prevent excessive height

        // Question Label
        JLabel questionLabel = new JLabel((index + 1) + ". " + questions[index][0]);
        questionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        questionLabel.setForeground(PRIMARY_COLOR);
        questionLabel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Details Panel
        JPanel detailsPanel = new JPanel(new GridLayout(3, 1));
        detailsPanel.setBackground(Color.WHITE);

        // User's Answer
        String userAnswer = userAnswers[index][0].isEmpty() ? "No Answer" : userAnswers[index][0];
        JLabel userAnswerLabel = new JLabel("Your Answer: " + userAnswer);
        userAnswerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        userAnswerLabel.setForeground(ACCENT_COLOR);

        // Correct Answer
        JLabel correctAnswerLabel = new JLabel("Correct Answer: " + answers[index][1]);
        correctAnswerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        correctAnswerLabel.setForeground(new Color(108, 117, 125));

        // Result
        JLabel resultLabel;
        if (userAnswer.equals(answers[index][1])) {
            resultLabel = new JLabel("✓ Correct (+10 points)", SwingConstants.LEFT);
            resultLabel.setForeground(CORRECT_COLOR);
        } else {
            resultLabel = new JLabel("✗ Incorrect (0 points)", SwingConstants.LEFT);
            resultLabel.setForeground(INCORRECT_COLOR);
        }
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));

        // Add to details panel
        detailsPanel.add(userAnswerLabel);
        detailsPanel.add(correctAnswerLabel);
        detailsPanel.add(resultLabel);

        // Combine question and details
        panel.add(questionLabel, BorderLayout.NORTH);
        panel.add(detailsPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(BACKGROUND_COLOR);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        JButton closeButton = new JButton("Close");
        closeButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        closeButton.setBackground(ACCENT_COLOR);
        closeButton.setForeground(Color.WHITE);
        closeButton.setFocusPainted(false);
        closeButton.addActionListener(e -> dispose());

        // Add hover effect
        closeButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(new Color(19, 134, 150));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                closeButton.setBackground(ACCENT_COLOR);
            }
        });

        buttonPanel.add(closeButton);
        return buttonPanel;
    }
}