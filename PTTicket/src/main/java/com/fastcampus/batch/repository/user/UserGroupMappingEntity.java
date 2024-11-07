package com.fastcampus.batch.repository.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_group_mapping")
@IdClass(UserGroupMappingId.class) // @IdClass는 복합키를 사용할 때 사용하는 어노테이션
/**
 * 엔터티의 여러 필드 또는 속성에 매핑되는 복합 기본 키 클래스를 지정합니다.
 * 기본 키 클래스의 필드 또는 속성 이름과 엔터티의 기본 키 필드 또는 속성은 일치해야 하며 해당 유형도 동일해야 합니다.
 * 예시
 * UserGroupMappingId 클래스에는 userId와 groupId 두 개의 필드가 있고, UserGroupMappingEntity 엔터티에는 userId와 groupId 두 개의 필드가 있습니다.
 * 이 두 필드는 일치하고 유형도 동일하므로 UserGroupMappingEntity 엔터티에 @IdClass(UserGroupMappingId.class)를 사용할 수 있습니다.
 */
public class UserGroupMappingEntity {
  //pk를 usergroupid와 userId로 지정
  @Id
  private String usergroupId;
  @Id
  private String userId;

  private String userGroupName;
  private String description;
}
