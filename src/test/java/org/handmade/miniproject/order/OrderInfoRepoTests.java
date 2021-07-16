package org.handmade.miniproject.order;

import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.order.entity.OrderInfo;
import org.handmade.miniproject.order.repository.OrderInfoRepository;
import org.handmade.miniproject.order.service.OrderInfoService;
import org.handmade.miniproject.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class OrderInfoRepoTests {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderInfoService orderInfoService;

    // 주문 추가
    @Test
    public void insertDummy() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            long pno = (int)(Math.random()*100)+1;
            Product product = Product.builder().pno(pno).build();

            log.info("======================");
            log.info("pno: "+pno);

            OrderInfo orderInfo = OrderInfo.builder()
                    .oUsername("수취인"+i)
                    .oZipcode("우편번호"+i)
                    .oAddress1("주소"+i)
                    .oAddress2("상세주소"+i)
                    .oTel1("전화번호 앞자리"+i)
                    .oTel2("전화번호 가운데자리"+i)
                    .oTel3("전화번호 뒷자리"+i)
                    .oRequest("배송메모"+i)
                    .del(false)
                    .product(product)
                    .build();

            orderInfoRepository.save(orderInfo);
        });
    }

    // 장바구니 리스트 조회

    // 장바구니 1개 조회

    // 장바구니에서 주문 수정

    // 주문 리스트 조회

    // 주문 1개 조회

    // 결제 완료 상태로 변경

    // 주문 취소 상태로 변경

}
