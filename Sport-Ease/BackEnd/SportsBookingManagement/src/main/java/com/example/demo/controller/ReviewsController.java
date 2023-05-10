package com.example.demo.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ReviewsDto;
import com.example.demo.service.ReviewsService;

@RestController
@RequestMapping("/reviews")
public class ReviewsController {

    @Autowired
    private ReviewsService reviewsService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("")
    public ResponseEntity<List<ReviewsDto>> getAllReviews() {
        List<ReviewsDto> reviewsDtoList = reviewsService.getAllReviews();
        return new ResponseEntity<>(reviewsDtoList, HttpStatus.OK);
    }

    @GetMapping("/getReviewById/{id}")
    public ResponseEntity<ReviewsDto> getReviewById(@PathVariable Long id) {
        ReviewsDto reviewsDto = reviewsService.getReviewById(id);
        return new ResponseEntity<>(reviewsDto, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<ReviewsDto> addReview(@RequestBody ReviewsDto reviewsDto) {
        ReviewsDto savedReviewsDto = reviewsService.addReview(reviewsDto);
        return new ResponseEntity<>(savedReviewsDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReviewsDto> updateReview(@PathVariable Long id, @RequestBody ReviewsDto reviewsDto) {
        ReviewsDto savedReviewsDto = reviewsService.updateReview(id, reviewsDto);
        return new ResponseEntity<>(savedReviewsDto, HttpStatus.OK);
    }
}

