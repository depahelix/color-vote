package com.depahelix.colorvote;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface VoteRepository extends CrudRepository<Vote, UUID> {
  List<Vote> findByUseruuid(UUID useruuid);
}