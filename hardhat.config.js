require("@nomicfoundation/hardhat-toolbox");

module.exports = {
  solidity: "0.8.20",
  networks: {
    hardhat: {
      initialBaseFeePerGas: 0, // EIP-1559 base fee = 0
      chainId: 31337,
      // Accounts with plenty of ETH; Hardhat prints their private keys on `npx hardhat node`
    },
    localhost: { url: "http://127.0.0.1:8545", chainId: 31337 }
  }
};
