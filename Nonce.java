// Define Nonce

public class Nonce {
    private String nonce;
    private char c1;
    private char c2;
    private char c3;
    private char c4;

    Nonce(){
        c1=(char) 32;
        c2=(char) 32;
        c3=(char) 32;
        c4=(char) 32;
        generateNonce();
    }

    public static String asciiToString(char value)
    {
        StringBuffer sbu = new StringBuffer();
        sbu.append(value);
        return sbu.toString();
    }

    public void generateNonce(){
        nonce=new String("");
        nonce+=asciiToString(c4)+asciiToString(c3)+asciiToString(c2)+asciiToString(c1);
    }

    public void increase() throws Exception{
        c1++;
        if(c1>126){
            c1=(char)32;
            c2++;
            if(c2>126){
                c2=(char)32;
                c3++;
                if(c3>126){
                    c3=(char)32;
                    c4++;
                    if(c4>126){
                        throw new Exception("Nonce out of bound");
                    }
                }
            }
        }
        generateNonce();

    }

    public String getNonce(){
        return nonce;
    }

}
