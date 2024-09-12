package com.jasp.miniorderapi;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity // JPA 어노테이션 : 해당 클래스를 엔터티로 인식 : 데이터베이스 테이블과 매핑
@Table(name = "orders") // H2 데이터베이스의 예약어 문제를 피하기 위해 테이블 이름을 변경 | 엔터티와 매핑될 DB 테이블의 이름을 지정
@Data // LOMBOK 어노테이션 : 메소드 자동 생성 EX) SETTER GETTER
@NoArgsConstructor // 인자가 없는 기본 생성자를 자동 생성
@AllArgsConstructor // 모든 필드를 매개변수로 받는 생성자를 자동 생성
@Builder // 빌더 패턴을 자동으로 생성 : 빌드 패턴이란 다양한 구성의 인스턴스를 만드는 패턴 -> 생성자의 매게변수를 하나하나 메소드로 받아들인 후에 통합 빌드에서 객체 생성
public class Order {

    // JPA ID를 통해 기본키 자동 생성 1,2,3,4,.. 자동으로 증가하여 저장해줌
    @Id // JPA 기본 키 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //GenerationType.IDENTITY 전략을 통해 데이터베이스에서 자동으로 고유한 기본 키 값을 생성

    private Long id; // 고유 ID

    private String buyer; // 구매자 정보

    @Enumerated(EnumType.STRING)
    private Category category; // 무엇을 주문했는지

    private LocalDateTime orderDate; // 주문날짜

    private Double amount; //가격
}

// ENUM 열거형 타입을 활용해 제한된 종류만 존재할 수 있도록 강제한다.
enum Category {
    FOOD,
    ELECTRONICS,
    CLOTHING,

}
