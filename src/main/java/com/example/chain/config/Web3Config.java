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
        // Replace with your private key or use environment variable
        return Credentials.create("0xYOUR_PRIVATE_KEY_HERE");
    }
    
    @Bean
    public PublicTxLog publicTxLog(Web3j web3j, Credentials credentials) {
        return PublicTxLog.load(
            "0xYOUR_CONTRACT_ADDRESS_HERE", 
            web3j, 
            credentials, 
            new DefaultGasProvider()
        );
    }
}
