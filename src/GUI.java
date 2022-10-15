import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: lukas
 * Date: 29.05.13
 * Time: 20:26
 * To change this template use File | Settings | File Templates.
 */
public class GUI extends JFrame
{
    private JPanel top=new JPanel(new FlowLayout());
    private JPanel board=new JPanel();
    private JLabel minesLeft=new JLabel("10");
    private JLabel minesFound=new JLabel("000");
    private JButton reset=new JButton(":)");
    private JMenuBar jmb=new JMenuBar();
    private JMenu game=new JMenu("Game");
    private JMenuItem settings=new JMenuItem("Settings");
    private int size_x=10,size_y=7,mines=10,found=0;
    private GUI gui;
    private ArrayList<ArrayList<JField>> field;
    private boolean lost=false;
    public GUI()
    {
        super("Minesweeper");
        gui=this;
        this.setSize(300, 300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(top, BorderLayout.NORTH);
        this.top.add(minesLeft, FlowLayout.LEFT);
        this.top.add(reset, FlowLayout.CENTER);
        this.top.add(minesFound,FlowLayout.RIGHT);
        this.board.setLayout(new GridLayout(size_x,size_y));
        this.add(board,BorderLayout.CENTER);

        game.add(settings);
        this.jmb.add(game);
        this.setJMenuBar(jmb);

        settings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new mSettings(size_x,size_y,mines,gui);
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                init(size_x,size_y,mines);
                reset.setText(":)");
                revalidate();
            }
        });
        init(size_x,size_y,mines);
        revalidate();
        this.setVisible(true);
    }
    public void init(int size_x,int size_y,int mines)
    {
        this.size_x=size_x;
        this.size_y=size_y;
        this.mines=mines;
        //fill board
        board.removeAll();
        board.setLayout(new GridLayout(size_x,size_y));
        field=new ArrayList<>();
        for(int i=0;i<size_x;i++)
        {
            ArrayList<JField> templist=new ArrayList<>();
            this.field.add(templist);
            for(int j=0;j<size_y;j++)
            {
                JField jf=new JField(false,this);
                templist.add(jf);
                this.board.add(jf);
            }
        }
        //spread mines
        Random r=new Random();
        for(int i=0;i<mines;i++)
        {
            int x=r.nextInt(size_x),y=r.nextInt(size_y);
            if(!field.get(x).get(y).getMine())
            {
                field.get(x).get(y).setMine(true);
            }else i--;
        }
        //calculates mines around this mine
        for(int i=0;i<size_x;i++)
        {
            for(int j=0;j<size_y;j++)
            {
                if(!field.get(i).get(j).getMine())
                    field.get(i).get(j).setMinesAround(getMinesAround(field.get(i).get(j)));
            }
        }
    }
    private void proceedClick(int i,int j)
    {
        JField jf2=field.get(i).get(j);
        if(jf2.isEnabled()&&!jf2.getMine())
        {
            proceedClick(jf2);
        }
    }
    public void proceedClick(JField jf)
    {
        if(!jf.getMine())
        {
            for(int i=0;i<size_x;i++)
            {
                for(int j=0;j<size_y;j++)
                {
                    if(field.get(i).get(j)==jf)
                    {
                        jf.setText(jf.getMinesAround()+"");
                        if(jf.getMinesAround()==0)
                        {
                            if(i>0)proceedClick(i-1,j);

                            if(j>0)proceedClick(i,j-1);
                            if(j<size_y-1)proceedClick(i,j+1);

                            if(i<size_x-1)proceedClick(i+1,j);
                        }
                    }
                }
            }
        }
    }
    public int getMinesAround(JField jf)
    {
        int minesAround=0;
        if(!jf.getMine())
        {
            for(int i=0;i<size_x;i++)
            {
                for(int j=0;j<size_y;j++)
                {
                    if(field.get(i).get(j)==jf)
                    {
                        if(i>0&&j>0)if(field.get(i-1).get(j-1).getMine())minesAround++;
                        if(i>0)if(field.get(i-1).get(j).getMine())minesAround++;
                        if(i>0&&j<size_y-1)if(field.get(i-1).get(j+1).getMine())minesAround++;

                        if(j>0)if(field.get(i).get(j-1).getMine())minesAround++;
                        if(j<size_y-1)if(field.get(i).get(j+1).getMine())minesAround++;

                        if(i<size_x-1&&j>0)if(field.get(i+1).get(j-1).getMine())minesAround++;
                        if(i<size_x-1)if(field.get(i+1).get(j).getMine())minesAround++;
                        if(i<size_x-1&&j<size_y-1)if(field.get(i+1).get(j+1).getMine())minesAround++;

                        return minesAround;
                    }
                }
            }
        }
        return -1;
    }
    public void setLost(boolean b)
    {
        this.lost=b;
        if(this.lost)
        {
            for(ArrayList<JField> al:field)
                for(JField jf:al)
                {
                    if(jf.getMine())jf.setText("x");
                        else jf.setText(jf.getMinesAround()+"");
                    jf.setEnabled(false);
                }
            this.reset.setText(":(");
        }
    }
    public void incFound()
    {
        found++;
        minesLeft.setText((mines-found)+"");
        minesFound.setText(found+"");
    }
    public void decFound()
    {
        found--;
        minesLeft.setText((mines-found)+"");
        minesFound.setText(found+"");
    }
    public static void main(String args[])
    {
        JFrame jf=new GUI();
    }
}
