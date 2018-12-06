import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;


public class BlockPanel extends JPanel {
    private Block block;
    public BlockPanel(Block _block){
        super();
        block=_block;
        try {
            setPreferredSize(new Dimension(60,60));
            setBackground(Color.PINK);
            setBorder(BorderFactory.createRaisedBevelBorder());
            setLayout(new GridLayout(2, 1));
            JLabel blockName=new JLabel(block.getHashOfBlock().substring(block.getHashOfBlock().length()-4,block.getHashOfBlock().length()),JLabel.CENTER);
            JLabel blockID=new JLabel("B"+block.getIdentification(),JLabel.CENTER);
            blockName.setBackground(Color.PINK);
            blockID.setBackground(Color.PINK);
            add(blockName);
            add(blockID);
        } catch (Exception e){
            System.out.println(e);
            System.exit(-1);
        }
    }

    public Block getBlock(){
        return block;
    }

    private static ImageIcon createImageIcon(String path,
                                             String description) {
        java.net.URL imgURL = VisualBlockchain.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(new ImageIcon(imgURL, description).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
