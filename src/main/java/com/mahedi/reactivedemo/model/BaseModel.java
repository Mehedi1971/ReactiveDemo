package com.mahedi.reactivedemo.model;


import com.mahedi.reactivedemo.enums.ActiveStatus;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BaseModel {

  @Id
  private Long id;
  private String createdBy;
  private Date createdAt;
  private String updatedBy;
  private Date updatedAt;
  private Integer activeStatus;

  public void setAuditData() {
    Date now = new Date();
    if (this.createdAt == null) {
      this.createdAt = now;
//      this.createdBy = AuthUtils.getLoggedInUsername();
      this.activeStatus = ActiveStatus.ACTIVE.getValue();
    }
    this.updatedAt = now;
//    this.updatedBy = loggedInUser;
  }

  public void updateAuditData(String loggedInUser) {
    Date now = new Date();
    this.updatedAt = now;
    this.updatedBy = loggedInUser;
  }
}
