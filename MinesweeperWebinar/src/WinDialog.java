import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WinDialog extends JDialog {
    public WinDialog(JFrame JavaSweeper) {
        super(JavaSweeper, "Congratulations!", true);
        add(new JLabel("<html><h1><i>You Win!</i></h1><hr>"), BorderLayout.CENTER);

        JButton ok = new JButton("OK");

        ok.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setVisible(false);
            }
        });

        JPanel panel = new JPanel();
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setSize(260, 160);
    }
}