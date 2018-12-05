import javax.swing.*;
import java.awt.*;

public class minerPanel extends JPanel {
    private Miner miner;
    JLabel minerName;
    JLabel minerNonce;
    public minerPanel(Miner _miner){
        super();
        miner=_miner;
        try {
            setPreferredSize(new Dimension(60,60));
            setBackground(Color.ORANGE);
            setBorder(BorderFactory.createRaisedBevelBorder());
            minerName=new JLabel(miner.getName(),JLabel.CENTER);
            minerName.setBackground(Color.ORANGE);
            minerNonce=new JLabel("",JLabel.CENTER);
            minerNonce.setBackground(Color.ORANGE);
            add(minerName);
            add(minerNonce);
        } catch (Exception e){
            System.out.println(e);
            System.exit(-1);
        }
    }

    public void setMinerNonce(){
        minerNonce.setText(miner.getNonce());
    }

}
