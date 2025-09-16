const hre = require("hardhat");

async function main() {
  const Factory = await hre.ethers.getContractFactory("PublicTxLog");
  const c = await Factory.deploy();
  await c.deployed();
  console.log("PublicTxLog:", c.target); // address to paste into Spring
}

main().catch((e) => { console.error(e); process.exit(1); });
