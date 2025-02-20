package com.mahedi.reactivedemo.config;

import com.mahedi.reactivedemo.enums.ActiveStatus;
import com.mahedi.reactivedemo.model.BaseModel;
import java.time.LocalDateTime;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AuditEntityCallback implements BeforeConvertCallback<BaseModel> {

  @Override
  public Mono<BaseModel> onBeforeConvert(BaseModel entity, SqlIdentifier table) {
    LocalDateTime now = LocalDateTime.now();

    if (entity.getCreatedAt() == null) {
      entity.setCreatedAt(now);
      entity.setCreatedBy("ADMIN");
      entity.setActiveStatus(ActiveStatus.ACTIVE.getValue());
    }

    if (entity.getId() != null) {
      entity.setUpdatedAt(now);
      entity.setUpdatedBy("ADMIN");
    }

    return Mono.just(entity);
  }


}
