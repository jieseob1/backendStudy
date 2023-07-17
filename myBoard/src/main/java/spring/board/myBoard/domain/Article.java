package spring.board.myBoard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})

@EntityListeners(AuditingEntityListener.class)
@Entity
public class Article {

    @Idcd
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


//    private String title; //제목
//    private String content;
//    private String hashTag;
//
//    private LocalDateTime createdAt;
//    private String createdBy;
//    private LocalDateTime modifiedAt;
//    private String modifiedBy;


}
