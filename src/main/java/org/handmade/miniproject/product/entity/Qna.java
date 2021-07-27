package org.handmade.miniproject.product.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Getter
public class Qna extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long qno;

    //구매자, 판매자
    private String username;

    //내용
    private String qnacontent;

    //판매자 답변인지 여부
    private boolean answer;

    //판매자 답변인 경우 질문번호 => qno
    private Long ano;

    private boolean del;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;

    public void changeQnacontent(String qnacontent){
        this.qnacontent = qnacontent;
    }




}
