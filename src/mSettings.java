import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 29.05.13
 * Time: 23:53
 * To change this template use File | Settings | File Templates.
 */
public class mSettings extends JFrame implements ActionListener{
    private JPanel jp=new JPanel(new BorderLayout());
    private JPanel jp2=new JPanel();
    private JPanel jp3=new JPanel(new BorderLayout());
    private JPanel jp4=new JPanel(new BorderLayout());
    private JPanel jp5=new JPanel(new BorderLayout());
    private JPanel jp6 =new JPanel(new FlowLayout());
    private JTextField x=new JTextField(5);
    private JTextField y=new JTextField(5);
    private JTextField mines=new JTextField(5);
    private GUI gui;
    public mSettings(int x,int y,int mines,GUI gui)
    {
        super("Settings");
        this.gui=gui;
        this.x.setText(x + "");
        this.y.setText(y + "");
        this.mines.setText(mines+"");
        this.setSize(150, 140);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jp.add(jp2, BorderLayout.CENTER);
        jp2.setBorder(BorderFactory.createEmptyBorder(4, 10, 4, 10));
        jp2.setLayout(new BoxLayout(jp2, BoxLayout.PAGE_AXIS));
        jp2.add(jp3);
        jp3.add(new JLabel("X: "), BorderLayout.WEST);
        jp3.add(this.x, BorderLayout.EAST);
        jp2.add(jp4);
        jp4.add(new JLabel("Y: "),BorderLayout.WEST);
        jp4.add(this.y,BorderLayout.EAST);
        jp2.add(jp5);
        jp5.add(new JLabel("Mines: "),BorderLayout.WEST);
        jp5.add(this.mines,BorderLayout.EAST);
        jp.add(jp6,BorderLayout.SOUTH);
        JButton ok=new JButton("OK");
        ok.addActionListener(this);
        jp6.add(ok);
        this.add(jp);

        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gui.init(Integer.parseInt(x.getText()),Integer.parseInt(y.getText()),Integer.parseInt(mines.getText()));
        gui.revalidate();
        this.dispose();
    }
}
