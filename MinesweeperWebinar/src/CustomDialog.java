import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JDialog {
    public CustomDialog(JFrame JavaSweeper) {
        super(JavaSweeper, "Custom Settings", true);

        JButton ok = new JButton("OK");
        JButton cancel = new JButton("Cancel");
        TextField colsInputField = new TextField("Columns");
        TextField rowsInputField = new TextField("Rows");
        TextField bombsInputField = new TextField("Bombs");
        colsInputField.setColumns(8);
        rowsInputField.setColumns(8);
        bombsInputField.setColumns(8);

        cancel.addActionListener(actionEvent -> setVisible(false));

        ok.addActionListener(event -> {
            System.out.println(colsInputField.getText());
            setVisible(false);
        });

        JPanel panel = new JPanel();
        panel.add(colsInputField);
        panel.add(rowsInputField);
        panel.add(bombsInputField);
        panel.add(ok);
        panel.add(cancel);
        add(panel, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setPreferredSize(new Dimension(200, 200));
    }
}