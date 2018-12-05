import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * A single block on the blockchain.
 */

public class Block implements HashableObject {

    // Data stored in this block
    public String _data;

    // Hash pointer to predecessor
    private HashPointer _hp;

    private String _prevHash;

    private String numOfTransactions;

    private String timeStamp;

    private String difficulty;

    private Nonce nonce;

    private String concatRoot;

    private ArrayList<Transaction> transactionList;

    private String hashOfBlock;

    private String creator;

    public String getNumOfTransactions(){
        return numOfTransactions;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public String getDifficulty(){
        return difficulty;
    }

    public String getNonce(){
        return nonce.getNonce();
    }

    public String getConcatRoot(){
        return concatRoot;
    }

    public String getHashOfBlock(){
        return hashOfBlock;
    }

    public String getPrevHash(){
        return _prevHash;
    }

    public String getTransaction(){
        String temp=new String("");
        for(Transaction item:transactionList){
            temp+=item.getTransaction();
        }
        return temp;
    }

    public ArrayList<Transaction> getTransactionList(){
        return transactionList;
    }

    public String getNarrowString(){
        String narrow=new String("");
        narrow+=getPrevHash()+getNumOfTransactions()+getTimeStamp()+getDifficulty()+getNonce()+getConcatRoot();
        return narrow;
    }

    public String getCreator(){
        return creator;
    }

    public static String generateConcatRoot(ArrayList<Transaction> dealedTransactions) {
        String rawString = new String("");
        for (Transaction item : dealedTransactions) {
            rawString += item.getTransaction();
        }
        return Sha256Hash.calculateHash(rawString);
    }

    public void increaseNonce(){
        try {
            nonce.increase();
        }
        catch (Exception ex) {
            System.out.println(ex);
        }
    }

    public BigInteger caculateTarget(){
        return Sha256Hash.hashBigInteger(getNarrowString());
    }

    public String getDataAsString() {
        return _data;
    }


    public boolean validPointer() throws InvalidHashException {
        // If invalid, will throw an InvalidHashException
        return (_hp == null || (_hp.referenceValid()));
    }

    public Block previousBlock() throws InvalidHashException {
        if (validPointer()) {
            return (Block) _hp.getReference();
        } else {
            // This will actually never happen.
            // If invalid, an InvalidHashException will be thrown by validPointer call
            return null;
        }
    }
    public String setHashOfBlock() {
        this.hashOfBlock = Sha256Hash.calculateHash(getNarrowString());
        return hashOfBlock;
    }

    public static BigInteger getTarget(String difficulty){
        BigInteger MAX_TARGET = new BigInteger("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF", 16);
        BigInteger temp=new BigInteger(difficulty);
        return MAX_TARGET.divide(temp);

    }

    public void PrintBlock(){
        System.out.println("CANDIDATE BLOCK = Hash "+getHashOfBlock());
        for(Transaction item:transactionList){
            System.out.println(item.getTransaction());
        }
    }

    public Block(Block predecessor, ArrayList<Transaction> _transactionList,String _creator) {
        transactionList = _transactionList;
        creator=_creator;
        if (predecessor == null) {
            _prevHash = "0";
        } else {
            _prevHash = predecessor.getHashOfBlock();
        }
        _hp = new HashPointer(predecessor);
        _data=getTransaction();
        numOfTransactions=Integer.toString(
                transactionList.size());
        nonce=new Nonce();
        timeStamp=String.valueOf(System.currentTimeMillis());
        difficulty="100000";
        concatRoot=generateConcatRoot(transactionList);
    }


}
