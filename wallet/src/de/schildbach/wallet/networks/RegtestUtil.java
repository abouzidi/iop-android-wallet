package de.schildbach.wallet.networks;

import org.bitcoinj.core.NetworkParameters;
import org.bitcoinj.core.PeerAddress;
import org.bitcoinj.params.RegTestParams;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mati on 30/09/16.
 */

public class RegtestUtil {


    private static final String LOCAL_PC = "186.23.58.203";//"192.168.0.111";

    public static List<PeerAddress> getConnectedPeers(NetworkParameters params){
        if (!(params instanceof RegTestParams)) throw new IllegalArgumentException("NetworkParameters input is not a RegTestParams");
        List<PeerAddress> list = new ArrayList<>();
        // peers
        list.add(new PeerAddress(params,new InetSocketAddress(LOCAL_PC,7685)));
        list.add(new PeerAddress(params,new InetSocketAddress(LOCAL_PC,7686)));
        list.add(new PeerAddress(params,new InetSocketAddress(LOCAL_PC,7684)));
        return list;
    }


}
