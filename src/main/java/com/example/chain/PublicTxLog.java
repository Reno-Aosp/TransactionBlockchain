package com.example.chain;


import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.util.Arrays;
import java.util.Collections;

public class PublicTxLog extends Contract {

    // Empty binary is fine for load-only wrappers
    private static final String BINARY = "";

    protected PublicTxLog(
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider contractGasProvider
    ) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    protected PublicTxLog(
            String contractAddress,
            Web3j web3j,
            TransactionManager transactionManager,
            ContractGasProvider contractGasProvider
    ) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> log(String ref, String payload) {
        final Function function = new Function(
                "log",
                Arrays.asList(new Utf8String(ref), new Utf8String(payload)),
                Collections.emptyList()
        );
        return executeRemoteCallTransaction(function);
    }

    public static PublicTxLog load(
            String contractAddress,
            Web3j web3j,
            Credentials credentials,
            ContractGasProvider contractGasProvider
    ) {
        return new PublicTxLog(contractAddress, web3j, credentials, contractGasProvider);
    }
}
