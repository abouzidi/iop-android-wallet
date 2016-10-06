package de.schildbach.wallet.networks;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.params.RegTestParams;
import org.bitcoinj.params.TestNet3Params;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mati on 04/10/16.
 */

public class TestnetUtil {


    public static List<PeerAddress> getConnectedPeers(NetworkParameters params){
        if (!(params instanceof TestNet3Params)) throw new IllegalArgumentException("NetworkParameters input is not a RegTestParams");
        List<PeerAddress> list = new ArrayList<>();
        // peers
//        list.add(new PeerAddress(params,new InetSocketAddress("186.23.58.203",7475)));
//        list.add(new PeerAddress(params,new InetSocketAddress("52.43.229.240",7475)));
//        list.add(new PeerAddress(params,new InetSocketAddress("181.169.84.146",7475)));
        list.add(new PeerAddress(params,new InetSocketAddress("ham4.fermat.cloud",7475)));
        list.add(new PeerAddress(params,new InetSocketAddress("ham5.fermat.cloud",7475)));
        list.add(new PeerAddress(params,new InetSocketAddress("ham6.fermat.cloud",7475)));
//        list.add(new PeerAddress(params,new InetSocketAddress("ham7.fermat.cloud",7475)));
        list.add(new PeerAddress(params,new InetSocketAddress("ham8.fermat.cloud",7475)));
        return list;
    }

}
