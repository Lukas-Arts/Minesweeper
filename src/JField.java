import javax.swing.*;
import java.awt.event.*;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 29.05.13
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public class JField extends JButton implements MouseListener{
    private boolean mine;
    private int minesAround=0;
    private GUI gui;
    private String text="";
    public JField(boolean mine,GUI gui)
    {
        this.setMine(mine);
        this.gui=gui;
        this.addMouseListener(this);
    }
    public void setMine(boolean b)
    {
        this.mine=b;
    }
    public boolean getMine()
    {
        return mine;
    }
    public void setMinesAround(int m)
    {
        this.minesAround=m;
    }
    public int getMinesAround()
    {
        return this.minesAround;
    }
    public void setText(String text)
    {
        super.setText(text);
        if(!text.equalsIgnoreCase(""))this.setEnabled(false);
            else this.setEnabled(true);
    }
    @Override
    public void mouseClicked(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    @Override
    public void mousePressed(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton()==1)
        {
            if(this.getMine())
            {
                gui.setLost(true);
            }
            gui.proceedClick(this);
        }else if(e.getButton()==3)
            {
                if(this.getText().equalsIgnoreCase("F"))
                {
                    this.setText("");
                    gui.decFound();
                }else
                {
                    this.setText("F");
                    gui.incFound();
                }
            }
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
    @Override
    public void mouseExited(MouseEvent e) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
