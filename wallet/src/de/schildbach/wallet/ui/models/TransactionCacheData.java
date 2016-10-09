package de.schildbach.wallet.ui.models;

import org.bitcoinj.core.Address;
import org.bitcoinj.core.Coin;
import org.bitcoinj.core.Sha256Hash;
import org.bitcoinj.core.Transaction;
import org.bitcoinj.core.TransactionConfidence;
import org.bitcoinj.utils.ExchangeRate;
import org.bitcoinj.wallet.Wallet;

import java.util.Date;

import javax.annotation.Nullable;

/**
 * Created by mati on 07/10/16.
 */

public class TransactionCacheData {

    private final Coin value;
    private final boolean sent;
    private final boolean self;
    private final boolean showFee;
    @Nullable
    private final Address address;
    @Nullable
    private final String addressLabel;
    private TransactionConfidence confidence;
    private boolean coinBase;
    private Transaction.Purpose purpose;
    private Coin fee;
    private String memo;
    private Sha256Hash hash;
    private Date updateTime;
    private ExchangeRate exchangeRate;
    private boolean optInFullRBF;
    private int outputsSize;
    private boolean isSelectable;


    public TransactionCacheData(final Coin value, final boolean sent, final boolean self, final boolean showFee,
                                final @Nullable Address address, final @Nullable String addressLabel)
    {
        this.value = value;
        this.sent = sent;
        this.self = self;
        this.showFee = showFee;
        this.address = address;
        this.addressLabel = addressLabel;
    }


    public TransactionConfidence getConfidence() {
        return confidence;
    }

    public boolean isCoinBase() {
        return coinBase;
    }

    public Transaction.Purpose getPurpose() {
        return purpose;
    }

    public Coin getFee() {
        return fee;
    }

    public String getMemo() {
        return memo;
    }

    public Sha256Hash getHash() {
        return hash;
    }

    public Coin getValue() {
        return value;
    }

    public boolean isSent() {
        return sent;
    }

    public boolean isSelf() {
        return self;
    }

    public boolean isShowFee() {
        return showFee;
    }

    @Nullable
    public Address getAddress() {
        return address;
    }

    @Nullable
    public String getAddressLabel() {
        return addressLabel;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public ExchangeRate getExchangeRate() {
        return exchangeRate;
    }

    public boolean isOptInFullRBF() {
        return optInFullRBF;
    }

    public int getOutputsSize() {
        return outputsSize;
    }

    public boolean isSelectable() {
        return isSelectable;
    }

    public void setConfidence(TransactionConfidence confidence) {
        this.confidence = confidence;
    }

    public void setCoinBase(boolean coinBase) {
        this.coinBase = coinBase;
    }

    public void setPurpose(Transaction.Purpose purpose) {
        this.purpose = purpose;
    }

    public void setFee(Coin fee) {
        this.fee = fee;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public void setHash(Sha256Hash hash) {
        this.hash = hash;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public void setExchangeRate(ExchangeRate exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public void setOptInFullRBF(boolean optInFullRBF) {
        this.optInFullRBF = optInFullRBF;
    }

    public void setOutputsSize(int outputsSize) {
        this.outputsSize = outputsSize;
    }

    public void setSelectable(boolean selectable) {
        isSelectable = selectable;
    }
}
