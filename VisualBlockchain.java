import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;
import java.util.Random;

public class VisualBlockchain {
    private JFrame mainFrame;
    private JPanel transactionPoolInformationPanel;
    private JPanel blockchainInformationPanel;
    private JPanel blockchainPanel;
    private JPanel controlPanel;
    private JPanel blockInformationPanel;
    private JPanel informationPanel;
    private JPanel visualPanel;
    private JPanel minersPanel;
    private Blockchain blockchain;
    private HashMap<String, String> coinPubkeyTable;
    private ArrayList<Transaction> transactionPool;
    private JTextArea blockInformation;
    private JTextArea blockchainInformation;
    private JTextArea transactionPoolInformation;
    private ArrayList<Miner> miners;
    private ArrayList<minerPanel> minerPanels;
    String coinBasePrivateKey="3081c60201003081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca404160214556d46e1888b30bccf9c4a5ea71b41c107b5d219";
    String coinBasePublicKey="3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405b0656317dd257ec71982519d38b42c02621290656eba54c955704e9b5d606062ec663bdeef8b79daa2631287d854da77c05d3e178c101b2f0a1dbbe5c7d5e10";


    public VisualBlockchain() {
        initGUI();
    }

    public static void main(String[] args) {
        VisualBlockchain visualBlockchain = new VisualBlockchain();
    }

    private void initGUI() {
        mainFrame = new JFrame("Java Swing Examples");
        mainFrame.setSize(1600, 800);
        mainFrame.setLayout(new GridLayout(1, 3));
        mainFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent) {
                System.exit(0);
            }
        });
        controlPanel = new JPanel();
        informationPanel = new JPanel();
        visualPanel = new JPanel();
        mainFrame.add(visualPanel);
        mainFrame.add(informationPanel);
        mainFrame.add(controlPanel);
        mainFrame.setVisible(true);
        initVisualPanel();
        initInformationPanel();
        initBlockchain();
        initControlPanel();
        mainFrame.setVisible(true);
    }

    private void initVisualPanel() {
        blockchainPanel = new JPanel();
        minersPanel = new JPanel();

        visualPanel.setLayout(new GridLayout(2, 1));
        visualPanel.add(minersPanel);
        visualPanel.add(blockchainPanel);
        blockchainPanel.setLayout(new FlowLayout());
        minersPanel.setLayout(new FlowLayout());
    }

    private void initInformationPanel() {
        blockInformationPanel = new JPanel();
        transactionPoolInformationPanel = new JPanel();
        blockchainInformationPanel=new JPanel();

        blockInformationPanel.setLayout(new GridLayout(1, 1));
        blockInformation = new JTextArea();
        JScrollPane scrollBlockInformation = new JScrollPane (blockInformation,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        blockInformation.setEditable(false);
        blockInformation.setLineWrap(true);
        blockInformation.setWrapStyleWord(true);
        blockInformation.setAutoscrolls(false);
        blockInformationPanel.add(scrollBlockInformation);

        transactionPoolInformationPanel.setLayout(new GridLayout(1, 1));
        transactionPoolInformation = new JTextArea();
        JScrollPane scrollTransactionInformation = new JScrollPane (transactionPoolInformation,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        transactionPoolInformation.setEditable(false);
        transactionPoolInformation.setLineWrap(true);
        transactionPoolInformation.setWrapStyleWord(true);
        transactionPoolInformation.setAutoscrolls(true);
        transactionPoolInformationPanel.add(scrollTransactionInformation);

        blockchainInformationPanel.setLayout(new GridLayout(1, 1));
        blockchainInformation = new JTextArea();
        JScrollPane scrollBlockchainInformation = new JScrollPane (blockchainInformation,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        blockchainInformation.setEditable(false);
        blockchainInformation.setLineWrap(true);
        blockchainInformation.setWrapStyleWord(true);
        blockchainInformation.setAutoscrolls(false);
        blockchainInformationPanel.add(scrollBlockchainInformation);

        informationPanel.setLayout(new GridLayout(0,1));
        informationPanel.add(transactionPoolInformationPanel);
        informationPanel.add(blockInformationPanel);
        informationPanel.add(blockchainInformationPanel);
        informationPanel.setVisible(true);

    }

    private void initControlPanel() {
        JButton createTransaction = new JButton("New Transaction");
        JButton createMiner = new JButton("New Miner");
        JButton startMining = new JButton("Start Mining");
        JButton initButton=new JButton("Start with Initiation");
        JButton randomTransaction=new JButton("Generate Legal Random Transaction");

        createTransaction.setPreferredSize(new Dimension(10,10));
        createMiner.setPreferredSize(new Dimension(10,10));
        startMining.setPreferredSize(new Dimension(10,10));


        controlPanel.setLayout(new GridLayout(0, 1));
        controlPanel.add(createTransaction);
        controlPanel.add(createMiner);
        controlPanel.add(startMining);
        controlPanel.add(initButton);
        controlPanel.add(randomTransaction);

        createTransaction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addTransaction();
            }
        });

        startMining.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mining();
            }
        });

        createMiner.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addMiner();
            }
        });

        initButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initMiners();
            }
        });

        randomTransaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generateRandomTransaction();
            }
        });
    }


    private void initBlockchain() {
        blockchain = new Blockchain();
        transactionPool=new ArrayList<Transaction>();
        coinPubkeyTable = new HashMap<String, String>();
        miners=new ArrayList<Miner>();
        minerPanels=new ArrayList<minerPanel>();
//        Block prevBlock = new Block(null, "0,CREATE,0000,302c021438f0d799617ce8df63fa9ce19933d6f0c0aa571402142a5ec931ed5869db154780d25a5282217a673f93,302d0215008d9a0e258486cf11f5e55ca0396bb61ff8d569c402147a09c9a7e63f225278d5b8262ce29a3e278a7c72");
//        coinPubkeyTable = new HashMap<String, String>();
//        try {
//            StringCoin.verifyCREATE(prevBlock, prevBlock, coinPubkeyTable);
//            coinPubkeyTable.put(prevBlock.getCoinID(),
//                    prevBlock.getOwnersPubKey(coinPubkeyTable, prevBlock.getCoinID()));
//        } catch (Exception ex) {
//            System.out.println("Exception: " + ex);
//            System.exit(-1);
//        }
//        coinPubkeyTable.put(prevBlock.getCoinID(), prevBlock.getOwnersPubKey(coinPubkeyTable, prevBlock.getCoinID()));
//        Block newBlock = blockchain.addBlock("0,CREATE,0000,302c021438f0d799617ce8df63fa9ce19933d6f0c0aa571402142a5ec931ed5869db154780d25a5282217a673f93,302d0215008d9a0e258486cf11f5e55ca0396bb61ff8d569c402147a09c9a7e63f225278d5b8262ce29a3e278a7c72");
//        BlockPanel initBlock = new BlockPanel(newBlock);
//        blockchainPanel.setLayout(new FlowLayout());
//        blockchainPanel.add(initBlock);
    }

    private boolean verifyTransaction(String coinID, String input, String privateKey) {
        try {
            boolean validSig = PublicKeyDemo.verifyMessage(input, PublicKeyDemo.signMessage(input, privateKey),
                    coinPubkeyTable.get(coinID));
            if (!validSig) {
                throw new Exception("Invalid Key Pair");
            }
            return validSig;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    private String signTransfer(String strToSign, String privateKey){
        String sign=new String();
        try {
            sign = PublicKeyDemo.signMessage(strToSign, privateKey);
        } catch (Exception e){
            System.out.println(e);
        }
        return sign;
    }

    private void addTransaction() {
        JFrame newFrame = new JFrame("Add New Transaction");
        newFrame.setSize(200, 200);
        newFrame.setLayout(new GridLayout(1, 1));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1));
        JTextField coinID = new JTextField("COINID");
        JTextField pubKey = new JTextField("TARGET ADDRESS - PUBLICKEY");
        JTextField privateKey = new JTextField("PRIVATEKEY");
        JButton submit = new JButton("Submit");

        inputPanel.add(coinID);
        inputPanel.add(pubKey);
        inputPanel.add(privateKey);
        inputPanel.add(submit);
        newFrame.add(inputPanel);
        newFrame.setVisible(true);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
//                for(HashMap.Entry<String, String> item: coinPubkeyTable.entrySet()){
//                    System.out.println("Coin " + item.getKey() + " / Owner = " + item.getValue());
//                }
                String input = new String(blockchain.getHashPointer() + "," + "TRANSFER" + "," + coinID.getText().trim() + "," + pubKey.getText().trim());
                input+=","+signTransfer(input,privateKey.getText().trim());
                boolean validTransaction = verifyTransaction(coinID.getText().trim(), input, privateKey.getText().trim());

                if (validTransaction) {
                    transactionPool.add(new Transaction(input));
                    transactionPoolInformation.append(input+"\n");
                }
                newFrame.dispose();
            }
        });

    }

    private boolean verifyCREATE(Transaction newTransaction, HashMap<String, String> coinPubkeyTable)
            throws Exception {
        boolean valid = PublicKeyDemo.verifyMessage(newTransaction.getCoinID(), newTransaction.getCoinSigORPubKey(),
                coinBasePublicKey);
        if (!valid) {
            return false;
        }
        boolean validSig = PublicKeyDemo.verifyMessage(newTransaction.getSigVerifyData(), newTransaction.getSig(),
                coinBasePublicKey);
        if (!validSig) {
            return false;
        }
        return true;
    }

    private boolean verifyTRANSFER(Transaction newTransaction, HashMap<String, String> coinPubkeyTable)
            throws Exception {
        boolean validSig = PublicKeyDemo.verifyMessage(newTransaction.getSigVerifyData(), newTransaction.getSig(),
                newTransaction.getOwnersPubKey(coinPubkeyTable, newTransaction.getCoinID()));
        if (!validSig) {
            return false;
        }
        return true;
    }

    private boolean verifyBlock(Block newBlock, HashMap<String, String> coinPubkeyTable)
            throws Exception {
        if(blockchain.getTopBlock()!=null) {
            if (!newBlock.getPrevHash().equals(blockchain.getTopBlock().getHashOfBlock())) {
                return false;
            }
        }
        for(Transaction item:newBlock.getTransactionList()) {
            if (item.getMethod().equals("CREATE")) {
                if (!verifyCREATE(item, coinPubkeyTable)) {
                    System.out.println("1");
                    return false;
                }
                coinPubkeyTable.put(item.getCoinID(), coinBasePublicKey);
            } else {
                if (!verifyTRANSFER(item, coinPubkeyTable)) {
                    System.out.println("2");
                    return false;
                }
                coinPubkeyTable.put(item.getCoinID(), item.getCoinSigORPubKey());
            }
        }
        return true;
    }



    private void mining(){
        ArrayList<miningThread> miningThreadArrayList=new ArrayList<>();
        for(Miner item:miners){
            miningThread tempThread=new miningThread(item,transactionPool,blockchain,coinPubkeyTable);
            miningThreadArrayList.add(tempThread);
        }
        for(miningThread item:miningThreadArrayList){
            Thread tempThread=new Thread(item);
            tempThread.start();
        }
        boolean flag=true;
        Block mineBlock=null;
        while(flag){
            for(miningThread item:miningThreadArrayList){
                if(item.getMiningResult()){
                    mineBlock=item.getMineBlock();
                    flag=false;
                    break;
                }
            }
        }
        for(minerPanel item:minerPanels){
            item.setMinerNonce();
        }
        mineBlock.PrintBlock();
        boolean valid=false;
        try {
            valid=verifyBlock(mineBlock, coinPubkeyTable);
        } catch (Exception e){
            System.out.println(e);
        }
        if(valid) {
            blockchain.addBlock(mineBlock);
            transactionPool.clear();
            transactionPoolInformation.setText("");
            BlockPanel newBlock = new BlockPanel(mineBlock);
            blockchainPanel.add(newBlock);
            mainFrame.setVisible(true);
            newBlock.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    blockInformation.setText("");
                    blockInformation.append("PREV_HASH: "+newBlock.getBlock().getPrevHash()+"\n");
                    blockInformation.append("BLOCK_HASH: "+newBlock.getBlock().getHashOfBlock()+"\n");
                    blockInformation.append("TIMESTAMP: "+newBlock.getBlock().getTimeStamp()+"\n");
                    blockInformation.append("CREATOR: "+newBlock.getBlock().getCreator()+"\n");
                    blockInformation.append("NONCE: "+newBlock.getBlock().getNonce()+"\n");
                    blockInformation.append("TRANSACTIONS: "+newBlock.getBlock().getTransaction()+"\n");
                }
            });
            for(Transaction item:mineBlock.getTransactionList()){
                if (item.getMethod().equals("CREATE")) {
                    coinPubkeyTable.put(item.getCoinID(), item.getOwnersPubKey(coinPubkeyTable, item.getCoinID()));
                } else {
                    coinPubkeyTable.put(item.getCoinID(), item.getCoinSigORPubKey());
                }
            }
            List<HashMap.Entry<String, String>> infoIds = new ArrayList<HashMap.Entry<String, String>>(
                    coinPubkeyTable.entrySet());
            Collections.sort(infoIds, new Comparator<HashMap.Entry<String, String>>() {
                public int compare(HashMap.Entry<String, String> o1, HashMap.Entry<String, String> o2) {
                    return (o1.getKey()).toString().compareTo(o2.getKey());
                }
            });
            blockchainInformation.setText("");
            for (HashMap.Entry<String, String> item : infoIds) {
                blockchainInformation.append("Coin " + item.getKey() + " / Owner = " + item.getValue()+"\n");
            }
        }
        else{
            JOptionPane.showMessageDialog(null, "Invalid Transactions");
            blockInformation.setText("");
            blockInformation.append("Invalid Transactions");
        }
    }

    private void addMiner(){
        JFrame newFrame = new JFrame("Add New Miner");
        newFrame.setSize(200, 200);
        newFrame.setLayout(new GridLayout(1, 1));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 1));
        JTextField name = new JTextField("Name");
        JTextField pubKey = new JTextField("PUBLICKEY");
        JTextField privateKey = new JTextField("PRIVATEKEY");
        JButton submit = new JButton("Submit");

        inputPanel.add(name);
        inputPanel.add(pubKey);
        inputPanel.add(privateKey);
        inputPanel.add(submit);
        newFrame.add(inputPanel);
        newFrame.setVisible(true);
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             Miner newMiner=new Miner(name.getText().trim(),pubKey.getText().trim(),privateKey.getText().trim());
             miners.add(newMiner);
             minerPanel newMinerPanel=new minerPanel(newMiner);
             minersPanel.add(newMinerPanel);
             minerPanels.add(newMinerPanel);
             mainFrame.setVisible(true);
             newMinerPanel.addMouseListener(new MouseAdapter() {
                 @Override
                 public void mouseEntered(MouseEvent e) {
                     blockInformation.setText("");
                     blockInformation.append("MINER_NAME: "+newMiner.getName()+"\n");
                     blockInformation.append("PUBLICKEY: "+newMiner.getPublicKey()+"\n");
                     blockInformation.append("PRIVATEKEY: "+newMiner.getPrivateKey()+"\n");
                 }
             });
             newFrame.dispose();
            }
        });
    }

    private void initMiners(){
        Miner newMiner1=new Miner("Mario","3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca40343000240437cefda4a79ded357c6a976803e73ba9f08cebc257e401dd42d8e71a04e7d8fb97f3d70c7fdd1b7579af65b407b2f382f316d3afb9b687d1c289c1bf6a1ee04","3081c60201003081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca4041602141da8d6f0b3653346a5377cab2fc7140022206a31");
        Miner newMiner2=new Miner("Peach","3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca40343000240502d40adb1c58beaede4cee3ce8450626a5922b60ef66f4d96d7496cda8661dd2c06c3a09b4fadcd3a6c36b7bdec474fde18cf7bff68258f0edfa281857d72aa","3081c60201003081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca4041602140767f5828e24761782413054778d969a06b0ce26");
        miners.add(newMiner1);
        miners.add(newMiner2);
        minerPanel newMinerPanel1=new minerPanel(newMiner1);
        minerPanel newMinerPanel2=new minerPanel(newMiner2);
        minersPanel.add(newMinerPanel1);
        minerPanels.add(newMinerPanel1);
        minersPanel.add(newMinerPanel2);
        minerPanels.add(newMinerPanel2);
        mainFrame.setVisible(true);
        newMinerPanel1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                blockInformation.setText("");
                blockInformation.append("MINER_NAME: "+newMiner1.getName()+"\n");
                blockInformation.append("PUBLICKEY: "+newMiner1.getPublicKey()+"\n");
                blockInformation.append("PRIVATEKEY: "+newMiner1.getPrivateKey()+"\n");
            }
        });
        newMinerPanel2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                blockInformation.setText("");
                blockInformation.append("MINER_NAME: "+newMiner2.getName()+"\n");
                blockInformation.append("PUBLICKEY: "+newMiner2.getPublicKey()+"\n");
                blockInformation.append("PRIVATEKEY: "+newMiner2.getPrivateKey()+"\n");
            }
        });
    }

    private void generateRandomTransaction(){
        if(coinPubkeyTable.size()==0){
            JOptionPane.showMessageDialog(null, "Do not have any coins!\nMine some Coin first.");
            return;
        }
        Random rand = new Random();
        String randomCoin = (String) coinPubkeyTable.keySet().toArray()[rand.nextInt(coinPubkeyTable.keySet().size())];
        String randomPubkey= (String) coinPubkeyTable.values().toArray()[rand.nextInt(coinPubkeyTable.keySet().size())];
        for(Miner item:miners){
            if(item.getPublicKey().equals(randomPubkey)){
                String input = new String(blockchain.getHashPointer() + "," + "TRANSFER" + "," + randomCoin + "," + randomPubkey);
                input+=","+signTransfer(input,item.getPrivateKey());
                boolean validTransaction = verifyTransaction(randomCoin, input, item.getPrivateKey());
                if (validTransaction) {
                    transactionPool.add(new Transaction(input));
                    transactionPoolInformation.append(input+"\n");
                }
            }
        }
    }
}
