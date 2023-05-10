package com.example.demo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ReviewsDto;
import com.example.demo.exception.ReviewNotFoundException;
import com.example.demo.pojo.Academy;
import com.example.demo.pojo.Reviews;
import com.example.demo.repo.ReviewsRepository;

@Service
@Transactional
public class ReviewsServiceImpl implements ReviewsService {

    @Autowired
    private ReviewsRepository reviewsRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ReviewsDto> getAllReviews() {
        List<Reviews> reviewsList = reviewsRepository.findAll();
        return reviewsList.stream()
                .map(reviews -> modelMapper.map(reviews, ReviewsDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReviewsDto getReviewById(Long id) {
        Reviews reviews = reviewsRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));
        return modelMapper.map(reviews, ReviewsDto.class);
    }

    @Override
    public ReviewsDto addReview(ReviewsDto reviewsDto) {
        Reviews reviews = modelMapper.map(reviewsDto, Reviews.class);
        reviews.setId(null);
        Reviews savedReviews = reviewsRepository.save(reviews);
        return modelMapper.map(savedReviews, ReviewsDto.class);
    }

    @Override
    public ReviewsDto updateReview(Long id, ReviewsDto reviewsDto) {
        Reviews existingReviews = reviewsRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));
        Reviews updatedReviews = modelMapper.map(reviewsDto, Reviews.class);
        updatedReviews.setId(existingReviews.getId());
        updatedReviews.setAcademy(existingReviews.getAcademy());
        Reviews savedReviews = reviewsRepository.save(updatedReviews);
        return modelMapper.map(savedReviews, ReviewsDto.class);
    }

    @Override
    public void deleteReviewById(Long id) {
        Reviews reviews = reviewsRepository.findById(id)
                .orElseThrow(() -> new ReviewNotFoundException("Review not found with id: " + id));
        reviewsRepository.delete(reviews);
    }

    @Override
    public List<ReviewsDto> getReviewsByAcademy(Long academyId) {
        Academy academy = new Academy();
        academy.setId(academyId);
        List<Reviews> reviewsList = reviewsRepository.findByAcademy(academy);
        return reviewsList.stream()
                .map(reviews -> modelMapper.map(reviews, ReviewsDto.class))
                .collect(Collectors.toList());
    }
}


//	    @Override
//	    public void deleteReviewById(Long id) {
//	        reviewsRepository.deleteById(id);
//	    }
//
//		@Override
//		public ReviewsDto getReviewById(Long id) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public List<ReviewsDto> getAllReviews() {
//			// TODO Auto-generated method stub
//			return null;
//		}
//
//		@Override
//		public ReviewsDto addReview(ReviewsDto reviewsDto) {
//			// TODO Auto-generated method stub
//			return null;
//		}
//	}


