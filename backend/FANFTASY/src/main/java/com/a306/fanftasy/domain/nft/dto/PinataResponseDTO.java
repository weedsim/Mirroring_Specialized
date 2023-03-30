/*
* 거래 정보를 반영하기 위해 받아오는 DTO
* */


package com.a306.fanftasy.domain.nft.dto;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PinataResponseDTO {
    String ipfsHash;
    String pinSize;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime timestamp;
    boolean isDuplicate;

}
