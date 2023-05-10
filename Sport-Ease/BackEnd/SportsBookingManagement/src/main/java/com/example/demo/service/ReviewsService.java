package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ReviewsDto;

public interface ReviewsService {
    ReviewsDto getReviewById(Long id);
    List<ReviewsDto> getAllReviews();
    ReviewsDto addReview(ReviewsDto reviewsDto);
    void deleteReviewById(Long id);
	ReviewsDto updateReview(Long id, ReviewsDto reviewsDto);
//	void deleteReview(Long id);
	List<ReviewsDto> getReviewsByAcademy(Long academyId);
}
