package com.example.chain.config;

import com.example.chain.PublicTxLog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

@Configuration
public class Web3Config {
    
    @Bean
    public Web3j web3j() {
        return Web3j.build(new HttpService("http://localhost:8545")); // or your RPC URL
    }
    
    @Bean
    public Credentials credentials() {
        // Use one of Hardhat's test accounts
        return Credentials.create("0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80");
    }
    
    @Bean
    public PublicTxLog publicTxLog(Web3j web3j, Credentials credentials) {
        return PublicTxLog.load(
            "0xf39Fd6e51aad88F6F4ce6aB8827279cffFb92266", 
            web3j, 
            credentials, 
            new DefaultGasProvider()
        );
    }
}
