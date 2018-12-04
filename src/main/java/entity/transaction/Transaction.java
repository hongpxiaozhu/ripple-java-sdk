package entity.transaction;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.annotation.JSONField;
import entity.RippleResponse;
import entity.ledger.LedgerRequest;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class Transaction extends RippleResponse<TransactionRequest> {
    @JSONField(name="Account")
    private String account;
    @JSONField(name="TransactionType")
    private String transactionType;
    @JSONField(name="Fee")
    private String fee;
    @JSONField(name="Sequence")
    private Long sequence;
    @JSONField(name="AccountTxnID")
    private String accountTxnID;
    @JSONField(name="Flags")
    private Long flags;
    @JSONField(name="LastLedgerSequence")
    private Long lastLedgerSequence;
    @JSONField(name="Memos")
    private JSONArray memos;
    @JSONField(name="Signers")
    private JSONArray signers;
    @JSONField(name="SourceTag")
    private Long sourceTag;
    @JSONField(name="SigningPubKey")
    private String signingPubKey;
    @JSONField(name="TxnSignature")
    private String txnSignature;
    @JSONField(name="ledger_index")
    private Long ledgerIndex;
    @JSONField(name="Destination")
    private String destination;
    @JSONField(name="Amount")
    private String amount;
    @JSONField(name="validated")
    private Boolean validated;
    @JSONField(name="inLedger")
    private Long inLedger;
    @JSONField(name="DestinationTag")
    private Long destinationTag;
    @JSONField(name="hash")
    private String hash;
}
