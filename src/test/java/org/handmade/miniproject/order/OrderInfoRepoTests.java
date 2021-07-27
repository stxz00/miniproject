package org.handmade.miniproject.order;

import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.order.dto.ListOrderInfoDTO;
import org.handmade.miniproject.order.entity.OrderInfo;
import org.handmade.miniproject.order.repository.OrderInfoRepository;
import org.handmade.miniproject.order.service.OrderInfoService;
import org.handmade.miniproject.product.entity.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class OrderInfoRepoTests {

    @Autowired
    private OrderInfoRepository orderInfoRepository;

    @Autowired
    private OrderInfoService orderInfoService;

    @Test
    public void insertOne() {

        long pno = (int)(Math.random()*100)+1;
        long mno = (int)(Math.random()*100)+1;
        Product product = Product.builder().pno(pno).build();
        MemberInfo memberInfo = MemberInfo.builder().username("user"+mno+"@aaa.com").build();

        log.info("======================");
        log.info("pno: "+pno);

        OrderInfo orderInfo = OrderInfo.builder()
                .oName("수취인")
                .oZipcode("우편번호")
                .oAddress1("주소")
                .oAddress2("상세주소")
                .oTel1("전화번호 앞자리")
                .oTel2("전화번호 가운데자리")
                .oTel3("전화번호 뒷자리")
                .oRequest("배송메모")
                .del(false)
                .product(product)
                .memberInfo(memberInfo)
                .build();

        orderInfoRepository.save(orderInfo);

    }

    // 주문 추가
    @Test
    public void insertDummy() {

        IntStream.rangeClosed(1,100).forEach(i -> {

            long pno = (int)(Math.random()*100)+1;
            long mno = (int)(Math.random()*100)+1;
            Product product = Product.builder().pno(pno).build();
            MemberInfo memberInfo = MemberInfo.builder().username("user"+mno+"@aaa.com").build();

            log.info("======================");
            log.info("pno: "+pno);
            log.info("mno: "+mno);

            OrderInfo orderInfo = OrderInfo.builder()
                    .oName("수취인"+i)
                    .oZipcode("우편번호"+i)
                    .oAddress1("주소"+i)
                    .oAddress2("상세주소"+i)
                    .oTel1("전화번호 앞자리"+i)
                    .oTel2("전화번호 가운데자리"+i)
                    .oTel3("전화번호 뒷자리"+i)
                    .oRequest("배송메모"+i)
                    .del(false)
                    .product(product)
                    .memberInfo(memberInfo)
                    .build();

            orderInfoRepository.save(orderInfo);
        });
    }

    // 장바구니 리스트 조회
    @Test
    public void getCartList() {
        String username = "user1@aaa.com";

        Pageable pageable = PageRequest.of(0, 10);

        log.info(username);

        Page<Object[]> result = orderInfoRepository
                .getCartList(pageable, username);

        List<ListOrderInfoDTO> cartListDTO =
                result.getContent()
                        .stream()
                        .map(arr-> orderInfoService.arrToDTO(arr))
                        .collect(Collectors.toList());

        result.getContent().forEach(objects -> log.info(Arrays.toString(objects)));
    }

    // 장바구니에서 주문 수정

    // 주문 리스트 조회
    @Test
    public void getOrderList() {
        String username = "user1@aaa.com";

        Pageable pageable = PageRequest.of(0, 10);

        log.info(username);

        Page<Object[]> result = orderInfoRepository
                .getOrderList(pageable, username);

        List<ListOrderInfoDTO> cartListDTO =
                result.getContent()
                        .stream()
                        .map(arr-> orderInfoService.arrToDTO(arr))
                        .collect(Collectors.toList());

        result.getContent().forEach(objects -> log.info(Arrays.toString(objects)));
    }

    // 주문 1개 조회
    @Test
//    @Transactional
    public void getListDetail() {
        Long ono = 43L;

        OrderInfo entity = orderInfoRepository.getById(ono);

        log.info(entity);
//
//        OrderInfoDTO dto = orderInfoService.entityToDTO(entity);
//
//        log.info(dto);



    }

    // 장바구니에서 삭제
    @Test
    public void deleteCart() {
        Long ono = 101L;

        orderInfoRepository.deleteById(ono);

    }

    // 주문 취소 상태로 변경
    @Test
    @Transactional
    public void deleteOrder() {
        Optional<OrderInfo> result = Optional.ofNullable(orderInfoRepository.getById(100L));
        log.info(result);
        log.info("==================================");

        if(result.isPresent()){
            OrderInfo entity = result.get();
            log.info(entity);
            log.info("==================================");

            // 주문 현황에서 '주문 취소' 상태로 만들어 주어야 하므로 del을 true로 변경해줌
            entity.changeDel(false);

            orderInfoRepository.save(entity);

            OrderInfo fin = orderInfoRepository.findById(entity.getOno()).get();

            log.info(fin);
            log.info("==================================");

        }
    }

}
