package com.embarkx.firstjobapp.review;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    @Autowired
    ReviewService reviewService;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean reviewSaved = reviewService.addReview(review, companyId);
        if(reviewSaved) {
            return new ResponseEntity<>("Review added", HttpStatus.CREATED);

        }
        return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
        }

        @GetMapping("/reviews/{reviewId}")
        public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
                return new ResponseEntity<>(reviewService.getReview(reviewId, companyId), HttpStatus.OK);
        }


}
