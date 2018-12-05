package entity.account;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
public class Account {
    @JSONField(name = "LedgerEntryType")
    private String ledgerEntryType;
    @JSONField(name = "Account")
    private String account;
    @JSONField(name = "Balance")
    private String balance;
    @JSONField(name = "Flags")
    private Long flags;
    @JSONField(name = "OwnerCount")
    private Long ownerCount;
    @JSONField(name = "PreviousTxnID")
    private String previousTxnID;
    @JSONField(name = "PreviousTxnLgrSeq")
    private Long previousTxnLgrSeq;
    @JSONField(name = "Sequence")
    private Long sequence;
    @JSONField(name = "AccountTxnID")
    private String accountTxnID;
    @JSONField(name = "Domain")
    private String domain;
    @JSONField(name = "EmailHash")
    private String emailHash;
    @JSONField(name = "MessageKey")
    private String messageKey;
    @JSONField(name = "RegularKey")
    private String regularKey;
    @JSONField(name = "TickSize")
    private Long tickSize;
    @JSONField(name = "TransferRate")
    private Long transferRate;
    @JSONField(name = "WalletLocator")
    private String walletLocator;
    @JSONField(name = "WalletSize")
    private Long walletSize;
}
