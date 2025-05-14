import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FaithCalculator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Faith Calculator - Piety & Punishment");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1;

        // Piety Section
        gbc.gridx = 0; gbc.gridy = 0;
        frame.add(new JLabel("Faith level:"), gbc);

        JTextField faithField = new JTextField();
        gbc.gridx = 1; gbc.gridy = 0;
        frame.add(faithField, gbc);

        JButton pietyButton = new JButton("Calculate Piety");
        gbc.gridx = 2; gbc.gridy = 0;
        frame.add(pietyButton, gbc);

        JTextArea pietyResult = new JTextArea();
        pietyResult.setLineWrap(true);
        pietyResult.setWrapStyleWord(true);
        pietyResult.setEditable(false);
        JScrollPane pietyScroll = new JScrollPane(pietyResult);
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        frame.add(pietyScroll, gbc);

        // Punishment Section
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weighty = 0;
        frame.add(new JLabel("Days in faith:"), gbc);

        JTextField daysField = new JTextField();
        gbc.gridx = 1; gbc.gridy = 2;
        frame.add(daysField, gbc);

        JButton punishmentButton = new JButton("Calculate Punishment");
        gbc.gridx = 2; gbc.gridy = 2;
        frame.add(punishmentButton, gbc);

        JTextArea punishmentResult = new JTextArea();
        punishmentResult.setLineWrap(true);
        punishmentResult.setWrapStyleWord(true);
        punishmentResult.setEditable(false);
        JScrollPane punishmentScroll = new JScrollPane(punishmentResult);
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 3;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 0.5;
        frame.add(punishmentScroll, gbc);

        // Dynamic resize
        frame.setMinimumSize(new Dimension(500, 400));
        frame.setVisible(true);

        // Piety Calculation Action
        pietyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double faith = Double.parseDouble(faithField.getText());
                    if (faith < 15) {
                        pietyResult.setText("You don't have the Faith level to achieve the pet reward (15 Piety) and the weapon reward (35 Piety).\n");
                        return;
                    }

                    StringBuilder sb = new StringBuilder();

                    if (faith >= 15) {
                        double piety15 = 15 / faith * 100;
                        sb.append(String.format("To reach 15 Piety:\n- %.2f%% of Faith\n- %s\n\n", piety15, getFeedbackText(piety15)));
                    }

                    if (faith >= 35) {
                        double piety35 = 35 / faith * 100;
                        sb.append(String.format("To reach 35 Piety:\n- %.2f%% of Faith\n- %s", piety35, getFeedbackText(piety35)));
                    } else {
                        sb.append("You don't have the Faith level to achieve the weapon reward (35 Piety).\n");
                    }

                    pietyResult.setText(sb.toString());
                } catch (NumberFormatException ex) {
                    pietyResult.setText("Please enter a valid Faith level.");
                }
            }
        });

        // Punishment Calculation Action
        punishmentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    double days = Double.parseDouble(daysField.getText());
                    double punishmentTurns = 2000 + (days * 20);
                    double approxPunishmentDays = punishmentTurns / (27000.0 / 32.0);

                    StringBuilder sb = new StringBuilder();
                    sb.append(String.format("Punishment for %.0f days in faith:\n", days));
                    sb.append(String.format("- Approx. %.0f turns\n", punishmentTurns));
                    sb.append(String.format("- Approx. %.1f in game days\n", approxPunishmentDays));

                    punishmentResult.setText(sb.toString());
                } catch (NumberFormatException ex) {
                    punishmentResult.setText("Please enter a valid number of days.");
                }
            }
        });
    }

    public static String getFeedbackText(double percentage) {
        if (percentage < 25) {
            return "You feel your God(dess) is distant.";
        } else if (percentage < 50) {
            return "You feel the distance to your God(dess) lessening.";
        } else if (percentage < 75) {
            return "You feel a sense of peace.";
        } else if (percentage < 100) {
            return "You feel as if your God(dess) is right beside you.";
        } else {
            return "You feel a sense of oneness with your God(dess).";
        }
    }
}
