package org.handmade.miniproject.order.service;

import com.querydsl.core.types.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.common.dto.ListResponseDTO;
import org.handmade.miniproject.common.dto.PageMaker;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.member.repository.MemberInfoRepository;
import org.handmade.miniproject.order.dto.ListOrderInfoDTO;
import org.handmade.miniproject.order.dto.OrderInfoDTO;
import org.handmade.miniproject.order.dto.OrderInfoListRequestDTO;
import org.handmade.miniproject.order.entity.OrderInfo;
import org.handmade.miniproject.order.repository.OrderInfoRepository;
import org.handmade.miniproject.product.entity.Product;
import org.handmade.miniproject.product.repository.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderInfoServiceImpl implements OrderInfoService {

    private final OrderInfoRepository orderInfoRepository;
    private final MemberInfoRepository memberInfoRepository;
    private final ProductRepository productRepository;

    @Override
    public Long register(Long pno, String username) {

       /* Product pEntity = productRepository.findById(orderInfoDTO.getPno()).get();
        MemberInfo mEntity = memberInfoRepository.findById(orderInfoDTO.getOName()).get();

        log.info(orderInfoDTO);
        log.info(pEntity);
        log.info(mEntity);

        OrderInfo entity = dtoToEntity(orderInfoDTO, pEntity, mEntity);

        log.info("========================================================");
        log.info(entity);*/

        Product pEntity = productRepository.findById(pno).get();

        MemberInfo mEntity = memberInfoRepository.findById(username).get();

        OrderInfoDTO dto = new OrderInfoDTO();

        dto.setPname(pEntity.getPname());
        dto.setPrice(pEntity.getPrice());
        dto.setUsername(mEntity.getUsername());
        dto.setMZipcode(mEntity.getMzipcode());
        dto.setMAddress1(mEntity.getMaddress1());
        dto.setMAddress2(mEntity.getMaddress2());
        dto.setMTel1(mEntity.getMtel1());
        dto.setMTel2(mEntity.getMtel2());
        dto.setMTel3(mEntity.getMtel3());
        dto.setOName(mEntity.getUsername());
        dto.setOZipcode(mEntity.getMzipcode());
        dto.setOAddress1(mEntity.getMaddress1());
        dto.setOAddress2(mEntity.getMaddress2());
        dto.setOTel1(mEntity.getMtel1());
        dto.setOTel2(mEntity.getMtel2());
        dto.setOTel3(mEntity.getMtel3());

        OrderInfo entity = dtoToEntity(dto,pEntity,mEntity);

        // 주문 등록
        OrderInfo result = orderInfoRepository.save(entity);

        return result.getOno();

    }

    @Override
    public ListResponseDTO<ListOrderInfoDTO> getCartList(OrderInfoListRequestDTO listRequestDTO) {

        // getCartList를 받아와 페이징 처리
        Pageable pageable = PageRequest.of((listRequestDTO.getPage()<=0 ? 0 : listRequestDTO.getPage()) -1, 10000);
        Page<Object[]> result = orderInfoRepository
                .getCartList(pageable, listRequestDTO.getUsername());

        //페이징된 결과를 DTO의 리스트 형태로 정렬
        List<ListOrderInfoDTO> cartListDTO =
                result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());

        PageMaker pageMaker = new PageMaker(1, 10000, (int)result.getTotalElements());
        pageMaker.makePageList(pageable);

        result.getContent().forEach(objects -> log.info(Arrays.toString(objects)));

        System.out.println(cartListDTO.get(0));

        return ListResponseDTO.<ListOrderInfoDTO>builder()
                .dtoList(cartListDTO)
                .pageMaker(pageMaker)
                .listRequestDTO(listRequestDTO)
                .build();
    }

    @Override
    @Transactional
    public OrderInfoDTO getListDetail(Long ono) {
        System.out.println(ono);
        OrderInfoDTO dto =  entityToDTO(orderInfoRepository.findById(ono).get());
        MemberInfo mEntity =  memberInfoRepository.findById("dlgoska00").get();
        dto.setMAddress1(mEntity.getMaddress1());
        dto.setMAddress2(mEntity.getMaddress2());
        dto.setMName(mEntity.getMname());
        dto.setMTel1(mEntity.getMtel1());
        dto.setMTel2(mEntity.getMtel2());
        dto.setMTel3(mEntity.getMtel3());
        dto.setMZipcode(mEntity.getMzipcode());
        return dto;
    }

    @Override
    public Long deleteCart(Long ono) {
        orderInfoRepository.deleteById(ono);
        return ono;
    }

    @Override
    public Long payOrder(OrderInfoDTO orderInfoDTO) {

        Optional<OrderInfo> result = Optional.ofNullable(orderInfoRepository.getById(orderInfoDTO.getOno()));

        if(result.isPresent()){

            OrderInfo entity = result.get();

            entity.changeOName(orderInfoDTO.getOName());
            entity.changeOZipcode(orderInfoDTO.getOZipcode());
            entity.changeOAddress1(orderInfoDTO.getOAddress1());
            entity.changeOAddress2(orderInfoDTO.getOAddress2());
            entity.changeOTel1(orderInfoDTO.getOTel1());
            entity.changeOTel2(orderInfoDTO.getOTel2());
            entity.changeOTel3(orderInfoDTO.getOTel3());
            entity.changeORequest(orderInfoDTO.getORequest());
            entity.changePayment(true);

            orderInfoRepository.save(entity);

            return orderInfoDTO.getOno();
        }

        return null;
    }


    @Override
    public ListResponseDTO<ListOrderInfoDTO> getOrderList(OrderInfoListRequestDTO listRequestDTO) {

        Pageable pageable = PageRequest.of((listRequestDTO.getPage()<=0 ? 0 : listRequestDTO.getPage()) -1, 10000);
        Page<Object[]> result = orderInfoRepository
                .getOrderList(pageable, listRequestDTO.getUsername());

        List<ListOrderInfoDTO> orderListDTO =
                result.getContent().stream().map( arr -> arrToDTO(arr) ).collect(Collectors.toList());
        PageMaker pageMaker = new PageMaker(1, 50000, (int)result.getTotalElements());
        pageMaker.makePageList(pageable);

        result.getContent().forEach(objects -> log.info(Arrays.toString(objects)));

        return ListResponseDTO.<ListOrderInfoDTO>builder()
                .dtoList(orderListDTO)
                .pageMaker(pageMaker)
                .listRequestDTO(listRequestDTO)
                .build();
    }

    @Override
    public OrderInfoDTO modifyOrder(OrderInfoDTO orderInfoDTO) {
        Optional<OrderInfo> result = Optional.ofNullable(orderInfoRepository.getById(orderInfoDTO.getOno()));

        if(result.isPresent()){
            OrderInfo entity = result.get();

            entity.changeOName(orderInfoDTO.getOName());
            entity.changeOZipcode(orderInfoDTO.getOZipcode());
            entity.changeOAddress1(orderInfoDTO.getOAddress1());
            entity.changeOAddress2(orderInfoDTO.getOAddress2());
            entity.changeOTel1(orderInfoDTO.getOTel1());
            entity.changeOTel2(orderInfoDTO.getOTel2());
            entity.changeOTel3(orderInfoDTO.getOTel3());
            entity.changeORequest(orderInfoDTO.getORequest());

            return orderInfoDTO;
        }
        return null;
    }

    @Override
    public Long deleteOrder(Long ono) {
        Optional<OrderInfo> result = Optional.ofNullable(orderInfoRepository.getById(ono));

        if(result.isPresent()){
            OrderInfo entity = result.get();

            // 주문 현황에서 '주문 취소' 상태로 만들어 주어야 하므로 del을 true로 변경해줌
            entity.changeDel(true);

            orderInfoRepository.save(entity);

            return ono;
        }

        return null;

    }
}
