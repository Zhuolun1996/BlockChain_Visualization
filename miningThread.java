import java.util.*;

class miningThread implements Runnable {
    private Block mineBlock;
    private boolean miningResult;
    private Miner miner;
    private ArrayList<Transaction> transactionPool;
    private Blockchain blockchain;
    private HashMap<String, String> coinPubkeyTable;
    private String coinBasePrivateKey = "3081c60201003081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca404160214556d46e1888b30bccf9c4a5ea71b41c107b5d219";
    private String coinBasePublicKey = "3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405b0656317dd257ec71982519d38b42c02621290656eba54c955704e9b5d606062ec663bdeef8b79daa2631287d854da77c05d3e178c101b2f0a1dbbe5c7d5e10";

    public miningThread(Miner _miner, ArrayList<Transaction> _transactionPool, Blockchain _blockchain, HashMap<String, String> _coinPubkeyTable) {
        miner = _miner;
        miningResult = false;
        transactionPool = _transactionPool;
        blockchain = _blockchain;
        coinPubkeyTable = _coinPubkeyTable;
    }

    private String getValidCoinID() {
        if (coinPubkeyTable.size() == 0) {
            return "0000";
        }
        List<HashMap.Entry<String, String>> infoIds = new ArrayList<HashMap.Entry<String, String>>(
                coinPubkeyTable.entrySet());
        Collections.sort(infoIds, new Comparator<HashMap.Entry<String, String>>() {
            public int compare(HashMap.Entry<String, String> o1, HashMap.Entry<String, String> o2) {
                return (o1.getKey()).toString().compareTo(o2.getKey());
            }
        });
        String biggestCoinID = infoIds.get(infoIds.size() - 1).getKey();
        Integer newCoinID = Integer.parseInt(biggestCoinID);
        newCoinID++;
        String CoinID = newCoinID.toString();
        while (CoinID.length() < 4) {
            CoinID = "0" + CoinID;
        }
        return CoinID;

    }

    private String signCreate(String strToSign) {
        String sign = new String();
        try {
            sign = PublicKeyDemo.signMessage(strToSign, coinBasePrivateKey);
        } catch (Exception e) {
            System.out.println(e);
        }
        return sign;
    }

    private String signTransfer(String strToSign, String privateKey) {
        String sign = new String();
        try {
            sign = PublicKeyDemo.signMessage(strToSign, privateKey);
        } catch (Exception e) {
            System.out.println(e);
        }
        return sign;
    }

    @Override
    public void run() {
        miningResult = false;
        ArrayList<Transaction> tempTransactionPool = new ArrayList<Transaction>();
        if (transactionPool.size() != 0) {
            tempTransactionPool.addAll(transactionPool);
        }
        String validCoinID = getValidCoinID();
        String prevHash = null;
        if (blockchain.getTopBlock() == null) {
            prevHash = "0";
        } else {
            prevHash = blockchain.getTopBlock().getHashOfBlock();

        }
        String transactionString = prevHash + "," + "CREATE" + "," + validCoinID + "," + signCreate(validCoinID);
        transactionString += "," + signCreate(transactionString);
        tempTransactionPool.add(new Transaction(transactionString));
        transactionString = prevHash + "," + "TRANSFER" + "," + validCoinID + "," + miner.getPublicKey();
        transactionString += "," + signTransfer(transactionString, coinBasePrivateKey);
        tempTransactionPool.add(new Transaction(transactionString));
        mineBlock = new Block(blockchain.getTopBlock(), tempTransactionPool, miner.getName());
        while (mineBlock.caculateTarget().compareTo(mineBlock.getTarget(mineBlock.getDifficulty())) == 1) {
            mineBlock.increaseNonce();
            miner.setNonce(mineBlock.getNonce());
            mineBlock.setHashOfBlock();
        }
        miningResult = true;
    }

    public boolean getMiningResult() {
        return miningResult;
    }

    public Block getMineBlock() {
        return mineBlock;
    }
}
