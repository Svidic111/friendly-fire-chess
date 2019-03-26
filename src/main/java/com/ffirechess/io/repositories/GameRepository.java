package com.ffirechess.io.repositories;

import com.ffirechess.io.entity.GameEntity;
import com.ffirechess.io.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends PagingAndSortingRepository<GameEntity, Long> {

    @Query(value = "select * from games where white_player=:id or black_player=:id", nativeQuery = true)
    Page<GameEntity> findAllUserGames(@Param("id") long id, Pageable pageable);

    GameEntity findByGameId(String gameId);

}
