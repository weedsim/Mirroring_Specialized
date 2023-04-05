// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

import "@openzeppelin/contracts/access/Ownable.sol";
import "contracts/NFT.sol";
import "contracts/Sale.sol";

/**
 * PJT Ⅲ - Req.1-SC1 SaleFactory 구현
 * 상태 변수나 함수의 시그니처, 이벤트는 구현에 따라 변경할 수 있습니다.
 */
contract SaleFactory is Ownable {
    address public admin; //관리자 계정
    address public nftContractAddress; //nft스마트 컨트랙트
    
    address[] public sales; //판매 정보 저장할 배열
    uint256 public saleIds; //판매 정보 인덱스
    
    event NewSale(
        address indexed _saleContract,
        address indexed _owner,
        uint256 _workId
    );

    constructor(address _nftContractAddress) {
        admin = msg.sender;
        nftContractAddress = _nftContractAddress;
    }

    /**
     * @dev 반드시 구현해야하는 함수입니다. 
     */
    function createSale(
        uint256 _tokenId,
        uint256 _price
    ) public returns (address) {
        //생성될 판매정보의 인덱스
        // uint256 newSaleId = current();
        //판매정보 생성
        Sale newSale = new Sale(payable(msg.sender), _tokenId, _price*(10**18), nftContractAddress, admin);
        //판매 정보 등록
        address saleContractAddress = address(newSale);
        sales.push(saleContractAddress);
        emit NewSale(saleContractAddress, msg.sender, saleIds);
        saleIds++;
        return saleContractAddress;
    }

    // function current() public view returns (uint256) {
    //     return saleIds;
    // }

    function allSales() public view returns (address[] memory) {
        return sales;
    }
}


