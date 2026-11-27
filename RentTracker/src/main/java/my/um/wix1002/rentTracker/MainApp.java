package my.um.wix1002.rentTracker;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.awt.Component;
import java.awt.Font;

/**
 * Entry point and initial FIRST screen launcher for Rent Tracker.
 */
public class MainApp {
    private final RentalManager manager = new RentalManager(50, 600);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainApp app = new MainApp();
            app.createAndShowGui();
        });
    }

    private void createAndShowGui() {
        JFrame frame = new JFrame("Rent Tracker");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(60, 160, 60, 160));

        JLabel titleLabel = new JLabel("RENT TRACKER", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton landlordBtn = new JButton("Landlord");
        JButton tenantBtn = new JButton("Tenant");
        JButton askAiBtn = new JButton("ASK AI");

        landlordBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        tenantBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        askAiBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        landlordBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "Units loaded: " + manager.getPropertyCount(), "Landlord", JOptionPane.INFORMATION_MESSAGE)
        );
        tenantBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "Tenant flow pending integration.", "Tenant", JOptionPane.INFORMATION_MESSAGE)
        );
        askAiBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(frame, "ASK AI flow pending integration.", "ASK AI", JOptionPane.INFORMATION_MESSAGE)
        );

        panel.add(titleLabel);
        panel.add(Box.createVerticalStrut(30));
        panel.add(landlordBtn);
        panel.add(Box.createVerticalStrut(15));
        panel.add(tenantBtn);
        panel.add(Box.createVerticalStrut(15));
        panel.add(askAiBtn);

        frame.setContentPane(panel);
        frame.setVisible(true);
    }
}