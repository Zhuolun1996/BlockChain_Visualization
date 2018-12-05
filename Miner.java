public class Miner {
    private String name;
    private String publicKey;
    private String privateKey;
    private String nonce;

    public Miner(String _name,String _publicKey,String _privateKey){
        name=_name;
        publicKey=_publicKey;
        privateKey=_privateKey;
    }

    public String getName(){
        return name;
    }

    public String getPublicKey(){
        return publicKey;
    }

    public String getPrivateKey(){
        return privateKey;
    }

    public void setNonce(String _nonce){
        nonce=_nonce;
    }

    public String getNonce(){
        return nonce;
    }
}
