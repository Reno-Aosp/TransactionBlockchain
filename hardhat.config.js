import "@nomicfoundation/hardhat-toolbox";

export default {
  solidity: "0.8.20",
  networks: {
    hardhat: {
      initialBaseFeePerGas: 0,
      chainId: 31337,
    },
    localhost: { url: "http://127.0.0.1:8545", chainId: 31337 }
  }
};
