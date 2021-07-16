package org.handmade.miniproject.product.entity;

import lombok.*;
import org.handmade.miniproject.common.entity.BaseEntity;
import org.handmade.miniproject.common.entity.UploadImage;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Builder
public class Review extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rno;

    //ManyToOne
    private String username;

    private String rcontent;

    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UploadImage> uploadImages = new HashSet<>();

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    private Product product;

    public void addImage(UploadImage image){
        uploadImages.add(image);
    }

    public void changeRcontent(String rcontent){
        this.rcontent = rcontent;
    }

    public void changeuploadImages(Set<UploadImage> uploadImages){
        this.uploadImages = uploadImages;
    }
}
