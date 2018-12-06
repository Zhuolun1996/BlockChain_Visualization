// Define transaction
// Include functions that analyse the transaction string.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Transaction {
    private String transaction;



    Transaction(String transaction){
        this.transaction=new String(transaction);

    }

    public String getPrevHashFromData() {
        String prevHash = transaction.split(",")[0];
        return prevHash;
    }

    public String getMethod() {
        String method = transaction.split(",")[1];
        return method;
    }

    public String getCoinID() {
        String coinID = transaction.split(",")[2];
        return coinID;
    }

    public String getCoinSigORPubKey() {
        String CoinSigORPubKey = transaction.split(",")[3];
        return CoinSigORPubKey;
    }

    public String getSig() {
        String sig = transaction.split(",")[4];
        return sig;
    }

    public String getSigVerifyData() {
        return getPrevHashFromData() + "," + getMethod() + "," + getCoinID() + "," + getCoinSigORPubKey();
    }

    public String getOwnersPubKey(HashMap<String,String> coinPubkeyTable, String coinID) {
        if (getMethod().equals("CREATE")) {
            return "3081f03081a806072a8648ce38040130819c024100fca682ce8e12caba26efccf7110e526db078b05edecbcd1eb4a208f3ae1617ae01f35b91a47e6df63413c5e12ed0899bcd132acd50d99151bdc43ee737592e17021500962eddcc369cba8ebb260ee6b6a126d9346e38c50240678471b27a9cf44ee91a49c5147db1a9aaf244f05a434d6486931d2d14271b9e35030b71fd73da179069b32e2935630e1c2062354d0da20a6c416e50be794ca403430002405b0656317dd257ec71982519d38b42c02621290656eba54c955704e9b5d606062ec663bdeef8b79daa2631287d854da77c05d3e178c101b2f0a1dbbe5c7d5e10";
        } else {
            return coinPubkeyTable.get(coinID);
        }
    }

    public boolean validPrevHash(HashPointer _hp) throws InvalidHashException{
        if (! _hp.getHash().equals(getPrevHashFromData())){
            throw new InvalidHashException(_hp.getHash(), getPrevHashFromData());
        }
        return true;
    }


    public String getTransaction(){
        return transaction;
    }
}
