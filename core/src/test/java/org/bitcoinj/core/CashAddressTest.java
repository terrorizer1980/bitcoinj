package org.bitcoinj.core;

import static org.junit.Assert.assertEquals;

import org.bitcoinj.params.BitcoinCashMainNetParams;
import org.junit.Test;
import org.spongycastle.util.encoders.Hex;

public class CashAddressTest {

    String str = "bitcoincash:qpm2qsznhks23z7629mms6s4cwef74vcwvy22gdx6a";
    String prefix = "bitcoincash";
    String type = "pubkeyhash";
    String hex = "0076a04053bda0a88bda5177b86a15c3b29f559873";
    byte[] words = {0, 1, 27, 10, 0, 16, 2, 19, 23, 22, 16, 10, 17, 2, 30, 26, 10, 5, 27, 27, 16, 26, 16, 21, 24, 14, 25, 9, 30, 21, 12, 24, 14, 12};

    @Test
    public void doitTest() throws Exception {

        Address address = Address
            .fromBase58(BitcoinCashMainNetParams.get(), "1BpEi6DfDAUFd7GtittLSdBeYJvcoaVggu");

        String encode = CashAddress.encode(prefix, "pubkeyhash", address.getHash160());
        System.out.println(encode);
        assertEquals("bitcoincash:qpm2qsznhks23z7629mms6s4cwef74vcwvy22gdx6a", encode);
    }

    @Test
    public void encodeTest() throws Exception {

        //Not working
        byte[] versionAndData = Hex.decode(hex);
        byte[] data = new byte[versionAndData.length - 1];
        System.arraycopy(versionAndData, 1, data, 0, data.length);
        String encode = CashAddress.encode(prefix, type, data);

        assertEquals(encode, str.toLowerCase());
    }

    @Test
    public void testDecode() throws Exception {

        //Working
        CashAddress result = CashAddress.decode(str);
        assertEquals(result.prefix, prefix);
        assertEquals(result.scriptType, type);
        assertEquals(result.hash, hex);
    }
}
