// SPDX-License-Identifier: MIT
pragma solidity ^0.8.20;

contract PublicTxLog {
    struct Entry { address actor; string ref; string payload; uint256 timestamp; }
    Entry[] public entries;
    event Logged(uint256 indexed id, address indexed actor, string ref, string payload);

    function log(string calldata ref, string calldata payload) external returns (uint256 id) {
        id = entries.length;
        entries.push(Entry(msg.sender, ref, payload, block.timestamp));
        emit Logged(id, msg.sender, ref, payload);
    }

    function get(uint256 id) external view returns (Entry memory) { return entries[id]; }
    function count() external view returns (uint256) { return entries.length; }
}
