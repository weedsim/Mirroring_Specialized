package com.a306.fanftasy.domain.user.entity;

import com.a306.fanftasy.domain.user.dto.UserJoinDTO;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    private String address;
    private String email;
    private String nickname;
    @Column(name = "profile_img")
    private String profileImg;
    private String phone;
    private String role;

    private String name;
    @Column(name = "total_sales")
    private int totalSales;
    @Column(name = "total_price")
    private int totalPrice;
    private String company;
    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name="user_key")
    private String userKey;

    public static User ofUser(UserJoinDTO joinDto){
        User user = User.builder()
                .address(joinDto.getAddress())
                .email(joinDto.getEmail())
                .nickname(joinDto.getNickname())
                .name(joinDto.getName())
                .phone(joinDto.getPhone())
                .role(joinDto.getRole())
                .company(joinDto.getCompany())
                .userKey(joinDto.getKey())
                .build();
        return user;
    }

    public void plusTotalSales(int num){
        this.totalSales += num;
    }
    public void plusTotalPrice(double price){
        this.totalPrice += price;
    }
}
