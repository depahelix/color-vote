package com.depahelix.colorvote;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "vote")
public class Vote {

  @Id
  @GeneratedValue(strategy=GenerationType.SEQUENCE)
  private Long id;
  private UUID useruuid;
  private String username;
  private Timestamp votetimestamp;
  private String favoritecolor;

  protected Vote() {}

  public Vote(String username, UUID useruuid, String favoritecolor) {
    this.username = username;
    this.useruuid = useruuid;
    this.favoritecolor = favoritecolor;
    this.votetimestamp = Timestamp.from(Instant.now());
  }

  public String getFavoritecolor() {
    return this.favoritecolor;
  }

  @Override
  public String toString() {
    return String.format(
        "Vote[id=%d, useruuid=%s, username='%s', favoritecolor='%s', votetimestamp='%s']",
        id, useruuid, username, favoritecolor, votetimestamp);
  }

}
