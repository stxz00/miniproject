package org.handmade.miniproject.product.repository.dynamic;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.JPQLQuery;
import org.handmade.miniproject.member.entity.QMemberInfo;
import org.handmade.miniproject.product.entity.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;
import java.util.stream.Collectors;

public class ProductSearchImpl extends QuerydslRepositorySupport implements ProductSearch{
    //상품 검색 기능 구현

    public ProductSearchImpl() {
        super(Product.class);
    }

    @Override
    public Page<Object[]> getSearchList(String type, String keyword,String cname, Pageable pageable) {

        QProduct product = QProduct.product;
        QFavorite favorite = QFavorite.favorite;
        QReview review = QReview.review;
        QQna qna = QQna.qna;
        QMemberInfo memberInfo = QMemberInfo.memberInfo;
        QCategory category = QCategory.category;


        JPQLQuery query = from(product);
        query.leftJoin(favorite).on(favorite.product.eq(product));
        query.leftJoin(review).on(review.product.eq(product));
        query.leftJoin(memberInfo).on(memberInfo.username.eq(product.memberInfo.username));
        query.leftJoin(category).on(category.cname.eq(product.category.cname));

        JPQLQuery<Tuple> tuple = query.select(product, favorite.countDistinct(),review.countDistinct());

        if(keyword != null && type != null && !keyword.equals("undefined") && !type.equals("undefined")){
            BooleanBuilder condition = new BooleanBuilder();
            String[] typeArr = type.split("");
            for (String t: typeArr){
                if(t.equals("t")){ //제목 : 상품명
                    condition.or(product.pname.contains(keyword));
                }else if(t.equals("w")){ //작성자 : 판매자
                    condition.or(memberInfo.username.contains(keyword));
                }else if(t.equals("c")){ //내용 : 상품 내용
                    condition.or(product.pcontent.contains(keyword));
                }
            }//end for
            tuple.where(condition);
        }

        if(cname != null && !cname.equals("undefined") ){
            System.out.println("네이밍이!! = "+cname);
            tuple.where(category.cname.eq(cname));
        }

        tuple.where(product.pno.gt(0L)); //상품 인덱스를 실행하기 위한 쿼리
        tuple.where(product.del.eq(false)); //상품 삭제 여부 false(0)인 것만 조회
        tuple.groupBy(product); //상품 pno 로 묶고
        tuple.orderBy(product.pno.desc()); // pno 역순으로 조회

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> tupleList = tuple.fetch();

        List<Object[]> arrList =
                tupleList.stream().map(tuple1 -> tuple1.toArray()).collect(Collectors.toList());
        long totalCount = tuple.fetchCount();


        return new PageImpl<>(arrList,pageable,totalCount);
    }

    @Override
    public Page<Object[]> getFavoriteList(String keyword, Pageable pageable) {
        QFavorite favorite = QFavorite.favorite;
        QProduct product = QProduct.product;

        JPQLQuery query = from(favorite);

        query.leftJoin(product).on(favorite.favorite.eq(favorite));

        JPQLQuery<Tuple> tuple = query.select(favorite, favorite.countDistinct());

        BooleanBuilder condition = new BooleanBuilder();
        condition.or(favorite.username.contains(keyword));
        tuple.where(condition);


        tuple.where(favorite.fno.gt(0L));

        tuple.groupBy(favorite);
        tuple.orderBy(favorite.fno.desc()); // fno 역순으로 조회

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> tupleList = tuple.fetch();

        List<Object[]> arrList =
                tupleList.stream().map(tuple1 -> tuple1.toArray()).collect(Collectors.toList());
        long totalCount = tuple.fetchCount();

        return new PageImpl<>(arrList,pageable,totalCount);

    }

    @Override
    public Page<Object[]> getQnaList(String keyword, Pageable pageable) {

        QQna qna = QQna.qna;
        QProduct product = QProduct.product;

        JPQLQuery query = from(qna);

        query.leftJoin(product).on(qna.qna.eq(qna));

        JPQLQuery<Tuple> tuple = query.select(qna, qna.countDistinct());

        BooleanBuilder condition = new BooleanBuilder();
        condition.or(qna.product.pno.eq(Long.parseLong(keyword)));
        tuple.where(condition);


        tuple.where(qna.qno.gt(0L));

        tuple.groupBy(qna);
        tuple.orderBy(qna.qno.desc()); // fno 역순으로 조회

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> tupleList = tuple.fetch();

        List<Object[]> arrList =
                tupleList.stream().map(tuple1 -> tuple1.toArray()).collect(Collectors.toList());
        long totalCount = tuple.fetchCount();

        return new PageImpl<>(arrList,pageable,totalCount);
    }

    @Override
    public Page<Object[]> getReviewList(String keyword, Pageable pageable) {
        QReview review = QReview.review;
        QProduct product = QProduct.product;

        JPQLQuery query = from(review);

        query.leftJoin(product).on(review.review.eq(review));

        JPQLQuery<Tuple> tuple = query.select(review, review.countDistinct());

        BooleanBuilder condition = new BooleanBuilder();
        condition.or(review.product.pno.eq(Long.parseLong(keyword)));
        tuple.where(condition);


        tuple.where(review.rno.gt(0L));

        tuple.groupBy(review);
        tuple.orderBy(review.rno.desc()); // fno 역순으로 조회

        tuple.offset(pageable.getOffset());
        tuple.limit(pageable.getPageSize());

        List<Tuple> tupleList = tuple.fetch();

        List<Object[]> arrList =
                tupleList.stream().map(tuple1 -> tuple1.toArray()).collect(Collectors.toList());
        long totalCount = tuple.fetchCount();

        return new PageImpl<>(arrList,pageable,totalCount);
    }
}
