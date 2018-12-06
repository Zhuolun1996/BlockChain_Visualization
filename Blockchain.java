// Define blockchain
// Include some functions to add a block to current blockchain and 
// analyse the entire block chain to find the owners of each coin.

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.ArrayList;

public class Blockchain {

    private HashPointer _head = null;
    private Block topBlock = null;

    /**
     * Generates a block and adds it to the end of the blockchain.
     * NOTE: Ordinarily we would need to have a direct reference
     * to the generated block.  We should only reference blocks
     * via HashPointer.  I am including it so that I can
     * directly modify it later and show what happens when a HashPointer
     * is invalid..
     *
     * @param transactions - The data to be added to the block
     * @return A direct reference to the generated block
     */

//    public Block addBlock(ArrayList<Transaction> transactions) {
//        Block b = null;
//        if (_head == null) {
//            b = new Block(null, transactions);
//        } else {
//            b = new Block((Block) _head.getReference(), transactions);
//        }
//
//        _head = new HashPointer(b);
//        topBlock = b;
//        return b;
//    }

    public Block addBlock(Block newBlock) {
        _head = new HashPointer(newBlock);
        topBlock = newBlock;
        return newBlock;
    }


    public String getHashPointer() {
        return _head.getHash();
    }

    public Block getTopBlock() {
        return topBlock;
    }

    public boolean iterateAndVerify() {
        // Trivial case - no blocks in blockchain, it's valid
        if (_head == null) {
            return true;
        }
        // Otherwise, iterate through all of the blocks until you get to null
        // If hashes don't match up, blockchain is invalid
        Block _current = (Block) _head.getReference();
        try {
            while (_current != null) {
                System.out.println("Block data: " + _current.getDataAsString() + "||Previous Hash: " + _current.getPrevHash());
                _current = _current.previousBlock();

            }
        } catch (InvalidHashException ihex) {
            System.out.println(ihex);
            return false;
        }
        // We have iterated through the entire blockchain without an error, thus
        // it is valid.
        return true;
    }

    public HashMap<String, String> traceBlockchainGetCoinPubkey() {
        HashMap<String, String> coinPubkeyTable = new HashMap<String, String>();
        if (_head == null) {
            return null;
        }
        // Otherwise, iterate through all of the blocks until you get to null
        // If hashes don't match up, blockchain is invalid
        Block _current = (Block) _head.getReference();
        try {
            while (_current != null) {
                for (Transaction item : _current.getTransactionList()) {
                    if (item.getMethod().equals("TRANSFER")) {
                        if (!coinPubkeyTable.containsKey(item.getCoinID())) {
                            coinPubkeyTable.put(item.getCoinID(), item.getCoinSigORPubKey());
                        }
                    }
                    if (item.getMethod().equals("CREATE")) {
                        if (!coinPubkeyTable.containsKey(item.getCoinID())) {
                            coinPubkeyTable.put(item.getCoinID(), item.getOwnersPubKey(null, item.getCoinID()));
                        }
                    }
                }
                _current = _current.previousBlock();

            }
        } catch (InvalidHashException ihex) {
            System.out.println(ihex);
            System.exit(-1);
        }
        // We have iterated through the entire blockchain without an error, thus
        // it is valid.
        return coinPubkeyTable;
    }


}
