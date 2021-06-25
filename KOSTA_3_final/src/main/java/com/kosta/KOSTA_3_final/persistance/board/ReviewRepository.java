package com.kosta.KOSTA_3_final.persistance.board;

import org.springframework.data.repository.CrudRepository;

import com.kosta.KOSTA_3_final.model.board.Review;

public interface ReviewRepository extends CrudRepository<Review, Long>{

}
