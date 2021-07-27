package org.handmade.miniproject.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.dto.MemberInfoDTO;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.member.entity.Role;
import org.handmade.miniproject.member.repository.MemberInfoRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberInfoServiceImpl implements MemberInfoService {

    private final MemberInfoRepository memberInfoRepository;

    private final PasswordEncoder passwordEncoder;


    /*@Override
    public String register(MemberInfoDTO memberInfoDTO) {
        return null;
    }*/

    @Override
    public String customerRegister(MemberInfoDTO memberInfoDTO) {
        String encodedPassword = passwordEncoder.encode(memberInfoDTO.getPassword());

        memberInfoDTO.setPassword(encodedPassword);

        memberInfoDTO.setEnabled(true);

        Role role = new Role();
        role.setNum(1);//customer
        memberInfoDTO.getRoles().add(role);
        memberInfoRepository.save(dtoToEntity(memberInfoDTO));
        return memberInfoDTO.getUsername();
    }

    @Override
    public String sellerRegister(MemberInfoDTO memberInfoDTO) {
        String encodedPassword = passwordEncoder.encode(memberInfoDTO.getPassword());

        memberInfoDTO.setPassword(encodedPassword);

        memberInfoDTO.setEnabled(true);

        Role role = new Role();
        role.setNum(2);//seller
        memberInfoDTO.getRoles().add(role);

        memberInfoRepository.save(dtoToEntity(memberInfoDTO));

        return memberInfoDTO.getUsername();
    }

    @Override
    public String adminRegister(MemberInfoDTO memberInfoDTO) {

        System.out.println(memberInfoDTO.toString());

        String encodedPassword = passwordEncoder.encode(memberInfoDTO.getPassword());

        memberInfoDTO.setPassword(encodedPassword);

        memberInfoDTO.setEnabled(true);

        Role role = new Role();
        role.setNum(3);//admin
        memberInfoDTO.getRoles().add(role);

        memberInfoRepository.save(dtoToEntity(memberInfoDTO));

        return memberInfoDTO.getUsername();
    }


    @Override
    public MemberInfoDTO getMemberInfo(String username) {
        Optional<MemberInfo> entity = memberInfoRepository.findById(username);

        if(entity.isPresent()){

            MemberInfo result = entity.get();
            return entityToDTO(result);

        }

        return null;
    }

    @Override
    public String modifyInfo(MemberInfoDTO memberInfoDTO) {
        //DB에서 해당 아이디와 일치하는 레코드 검색
        Optional<MemberInfo> resEntity = memberInfoRepository.findById(memberInfoDTO.getUsername());

        //검색한 결과가 존재할 경우 받아온 정보를 덮어씌워 저장

        if(resEntity.isPresent()){
            MemberInfo result = resEntity.get();

            result.changeUserPwd(memberInfoDTO.getPassword());
            result.changeNickname(memberInfoDTO.getNickname());
            result.changeMname(memberInfoDTO.getMname());
            result.changeMzipcode(memberInfoDTO.getMzipcode());
            result.changeMaddress1(memberInfoDTO.getMaddress1());
            result.changeMaddress2(memberInfoDTO.getMaddress2());
            result.changeMtel1(memberInfoDTO.getMtel1());
            result.changeMtel2(memberInfoDTO.getMtel2());
            result.changeMtel3(memberInfoDTO.getMtel3());
            result.changeBrno(memberInfoDTO.getBrno());

            memberInfoRepository.save(result);

            return memberInfoDTO.getUsername();

        }

        return null;
    }

    @Override
    public String deleteMemberInfo(String username) {
        Optional<MemberInfo> member = memberInfoRepository.findById(username);

        if(member.isPresent()){

            MemberInfo result = member.get();

            result.changeDel(true);
            memberInfoRepository.save(result);

            return result.getUsername();

        }

        return null;

    }

    /*@Override
    public String registerCustomer(MemberInfoDTO memberInfoDTO) {

        return null;
    }

    @Override
    public String registerSeller(MemberInfoDTO memberInfoDTO) {
        return null;
    }*/
}
/*


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.handmade.miniproject.member.dto.MemberInfoDTO;
import org.handmade.miniproject.member.entity.MemberInfo;
import org.handmade.miniproject.member.repository.MemberInfoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemberInfoServiceImpl implements MemberInfoService {

    private final MemberInfoRepository memberInfoRepository;

    //회원 등록
    @Override
    public String register(MemberInfoDTO memberInfoDTO) {
        log.info(memberInfoDTO);

        MemberInfo entity = dtoToEntity(memberInfoDTO);

        //중복 닉네임이 없을 때 등록
        if(memberInfoRepository.findById(entity.getUsername()).isEmpty()) {

            log.info("==============================");
            log.info(entity);

            MemberInfo result = memberInfoRepository.save(entity);

            //등록시 가입한 유저의 ID(이메일) 반환
            return result.getUsername();
        }

        return null;

    }

    //회원 정보 출력
    @Override
    public MemberInfoDTO getMemberInfo(String username) {

        Optional<MemberInfo> entity = memberInfoRepository.findById(username);

        if(entity.isPresent()){

            MemberInfo result = entity.get();
            return entityToDTO(result);

        }

        return null;

    }

    //회원 정보 수정
    @Override
    public String modifyInfo(MemberInfoDTO memberInfoDTO) {

        //DB에서 해당 아이디와 일치하는 레코드 검색
        Optional<MemberInfo> resEntity = memberInfoRepository.findById(memberInfoDTO.getUsername());

        //검색한 결과가 존재할 경우 받아온 정보를 덮어씌워 저장

        if(resEntity.isPresent()){
            MemberInfo result = resEntity.get();

            result.changeUserPwd(memberInfoDTO.getUserPwd());
            result.changeNickname(memberInfoDTO.getNickname());
            result.changeMname(memberInfoDTO.getMname());
            result.changeMzipcode(memberInfoDTO.getMzipcode());
            result.changeMaddress1(memberInfoDTO.getMaddress1());
            result.changeMaddress2(memberInfoDTO.getMaddress2());
            result.changeMtel1(memberInfoDTO.getMtel1());
            result.changeMtel2(memberInfoDTO.getMtel2());
            result.changeMtel3(memberInfoDTO.getMtel3());
            result.changeBrno(memberInfoDTO.getBrno());

            memberInfoRepository.save(result);

            return memberInfoDTO.getUsername();

        }

        return null;
    }

    //회원 정보 삭제
    @Override
    public String deleteMemberInfo(String username) {

        Optional<MemberInfo> member = memberInfoRepository.findById(username);

        if(member.isPresent()){

            MemberInfo result = member.get();

            result.changeDel(true);
            memberInfoRepository.save(result);

            return result.getUsername();

        }

        return null;

    }

    //구매자 회원가입
    @Override
    public String registerCustomer(MemberInfoDTO memberInfoDTO) {
        log.info(memberInfoDTO);

        MemberInfo entity = dtoToEntity(memberInfoDTO);

        log.info("==============================");
        entity.addMemberRole(MemberRole.CUSTOMER);
        log.info(entity);


        MemberInfo result = memberInfoRepository.save(entity);

        //등록시 가입한 유저의 ID(이메일) 반환
        return result.getUsername();
    }

    //판매자 회원가입
    @Override
    public String registerSeller(MemberInfoDTO memberInfoDTO) {
        log.info(memberInfoDTO);

        MemberInfo entity = dtoToEntity(memberInfoDTO);

        log.info("==============================");
        entity.addMemberRole(MemberRole.CUSTOMER); //판매자도 구입할 수 있도록
        entity.addMemberRole(MemberRole.SELLER);
        log.info(entity);


        MemberInfo result = memberInfoRepository.save(entity);

        //등록시 가입한 유저의 ID(이메일) 반환
        return result.getUsername();
    }


}
*/
