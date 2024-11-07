package com.fastcampus.batch.job;

import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fastcampus.batch.repository.pass.BulkPassRepository;
import com.fastcampus.batch.repository.pass.PassRepository;
import com.fastcampus.batch.repository.user.UserGroupMappingRepository;
import com.fastcampus.batch.repository.user.UserGroupMappingEntity;
import com.fastcampus.batch.repository.pass.BulkPassStatus;
import com.fastcampus.batch.repository.pass.PassEntity;
import com.fastcampus.batch.repository.pass.BulkPassEntity;
import com.fastcampus.batch.mapper.PassModelMapper;

import java.util.ArrayList;

public class AddPassesTasklet implements Tasklet {

  private final PassRepository passRepository;
  private final BulkPassRepository bulkPassRepository;
  private final UserGroupMappingRepository userGroupMappingRepository;

  //생성자 주입
  public AddPassesTasklet(PassRepository passRepository, 
                          BulkPassRepository bulkPassRepository, 
                          UserGroupMappingRepository userGroupMappingRepository) {
    this.passRepository = passRepository;
    this.bulkPassRepository = bulkPassRepository;
    this.userGroupMappingRepository = userGroupMappingRepository;
  }


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // 구현 로직
        //이용권 시작 일시 1일 전 user group 내 각 사용자에게 이용권을 추가해준다.
        final LocalDateTime startedAt = LocalDateTime.now().minusDays(1);
        final List<BulkPassEntity> bulkPasses = bulkPassRepository.findByStatusAndStartedAtGreaterThan(BulkPassStatus.READY, startedAt); //벌크 패스 정보를 가지고 옴 아직 처리가 안되고 준비 될 때
        

        int count = 0;
        //대량 이용권 정보를 돌면서 user group에 속한 userId를 조회하고 해당 userIdfmf 이용권에 추가해준다.
        for (BulkPassEntity bulkPass : bulkPasses) {
          final List<String> userIds = userGroupMappingRepository.findByUsergroupId(bulkPass.getUserGroupId())
          .stream().map(UserGroupMappingEntity::getUserId).collect(Collectors.toList());//userGroupId로 사용자 그룹에 속한 userId를 조회

        
          


        logger.info("AddPassesTasklet executed");
        return RepeatStatus.FINISHED;
    }

    private int addPasses(BulkPassEntity bulkPass, List<String> userIds) {
      /**
       * bulkPass에 있는 정보를 기반으로 각 userId에 대한 pass 정보를 생성해서 저장
       */
      List<PassEntity> passes = new ArrayList<>();
      for (String userId : userIds) {
        PassEntity passEntity = PassModelMapper.INSTANCE.toPassEntity(bulkPass, userId); // setter로 직접 구현해도 된다. => 현재는 mapstruct를 사용해서 한다.


        passes.add(passEntity);
      }
      return passRepository.saveAll(passes).size();
    }
}
