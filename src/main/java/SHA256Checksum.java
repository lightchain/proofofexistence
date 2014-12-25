/**
 * Created by uwecerron on 12/25/14.
 */
import java.io.*;
import java.security.MessageDigest;

public class SHA256Checksum {

    private static String hashtype = "SHA-256";
    private static final String HEXES = "0123456789ABCDEF";

    public static byte[] createChecksum(String filename) throws Exception
    {
        InputStream fis =  new FileInputStream(filename);

        byte[] buffer = new byte[1024];
        MessageDigest complete = MessageDigest.getInstance(hashtype);
        int numRead;
        do {
            numRead = fis.read(buffer);
            if (numRead > 0) {
                complete.update(buffer, 0, numRead);
            }
        } while (numRead != -1);
        fis.close();
        return complete.digest();
    }

    public static String getSHA256Checksum(String filename) throws Exception {
        byte[] b = createChecksum(filename);
        return getHex(b);
    }

    public static String getHex( byte [] raw ) {
        if ( raw == null ) {
            return null;
        }
        final StringBuilder hex = new StringBuilder( 2 * raw.length );
        for ( final byte b : raw ) {
            hex.append(HEXES.charAt((b & 0xF0) >> 4))
                    .append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
    }

    /*
    public static void main(String args[]) {
        try {
            System.out.println(getSHA256Checksum("pom.xml"));
            // output :
            //  2eda517d623aeea46e842d794f3829a082d8ee803c21700c6990a82163ed301c
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }*/
}