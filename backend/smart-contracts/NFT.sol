// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "@openzeppelin/contracts-upgradeable/token/ERC721/ERC721Upgradeable.sol";
import "@openzeppelin/contracts-upgradeable/access/OwnableUpgradeable.sol";
import "@openzeppelin/contracts-upgradeable/proxy/utils/Initializable.sol";
import "@openzeppelin/contracts-upgradeable/proxy/utils/UUPSUpgradeable.sol";

contract MyToken is Initializable, ERC721Upgradeable, OwnableUpgradeable, UUPSUpgradeable {
    /// @custom:oz-upgrades-unsafe-allow constructor
    constructor() {
        _disableInitializers();
    }

    function initialize() initializer public {
        __ERC721_init("FANFTASY", "NFT");
        __Ownable_init();
        __UUPSUpgradeable_init();
        
    }

    function safeMint(address to, uint256 tokenId) public onlyOwner {
        _safeMint(to, tokenId);
    }

    function _authorizeUpgrade(address newImplementation)
        internal
        onlyOwner
        override
    {}

    uint256 private _tokenIds;
    mapping(uint256 => string) tokenURIs;

    function tokenURI(uint256 tokenId) public view virtual override returns (string memory) {return tokenURIs[tokenId];}

    function current() public view returns (uint256) {
        return _tokenIds;
    }

    function create(address to, string memory tokenURI) public returns (uint256)
    {
        _tokenIds++;
        uint256 newItemId = current();
        _safeMint(to, newItemId);
        tokenURIs[newItemId] = tokenURI;
        _approve(msg.sender,newItemId);
        return newItemId;
    }
}
