package com.fastcampus.batch.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserGroupMappingRepository extends JpaRepository<UserGroupMappingEntity, UserGroupMappingId> {
  List<UserGroupMappingEntity> findByUsergroupId(String usergroupId);
  //userGroup으로 UserGroupMappingEntity를 찾는 메소드
}
