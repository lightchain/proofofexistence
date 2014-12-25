import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.kits.WalletAppKit;
import org.bitcoinj.params.MainNetParams;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;
import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.script.ScriptBuilder;
import org.bitcoinj.script.ScriptOpCodes;


/**
 * Created by uwecerron on 12/25/14.
 */
public class Proof {

    public NetworkParameters params = RegTestParams.get();
    private WalletAppKit walletAppKit;

    public void storeToBlockchain(String filepath) throws Exception {
        Transaction tx = new Transaction(params);
        String checksum = SHA256Checksum.getSHA256Checksum(filepath);
        tx.addOutput(Coin.ZERO, new ScriptBuilder().op(ScriptOpCodes.OP_RETURN).data(checksum.getBytes()).build());
        walletAppKit.peerGroup().broadcastTransaction(tx);
    }

    public Transaction addToTransaction(Transaction tx, String filepath) throws Exception {
        String checksum = SHA256Checksum.getSHA256Checksum(filepath);
        tx.addOutput(Coin.ZERO, new ScriptBuilder().op(ScriptOpCodes.OP_RETURN).data(checksum.getBytes()).build());
        return tx;
    }

    


}
