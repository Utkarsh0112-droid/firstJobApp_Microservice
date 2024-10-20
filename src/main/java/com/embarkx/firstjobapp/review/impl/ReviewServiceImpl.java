package com.embarkx.firstjobapp.review.impl;

import com.embarkx.firstjobapp.company.Company;
import com.embarkx.firstjobapp.company.CompanyService;
import com.embarkx.firstjobapp.review.Review;
import com.embarkx.firstjobapp.review.ReviewRepository;
import com.embarkx.firstjobapp.review.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    CompanyService companyService;
    @Autowired
    private ReviewRepository reviewRepository;
    @Override
    public List<Review> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews;
    }

    @Override
    public boolean addReview(Review review, Long companyId) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            review.setCompany(company);
            reviewRepository.save(review);
            return true;
        }
        return false;
    }

    @Override
    public Review getReview(Long companyId, Long reviewId) {
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            Review review = reviewRepository.findById(reviewId).orElse(null);
            if(review != null){
                review.setCompany(company);
                reviewRepository.save(review);
            }
            return review;
        }
    return null;
    }
    @Override
    public boolean updateReview(Long companyId, Long reviewId, Review updatedreview){
        Company company = companyService.getCompanyById(companyId);
        if(company != null){
            updatedreview.setCompany(companyService.getCompanyById(companyId));
            updatedreview.setId(reviewId);
            reviewRepository.save(updatedreview);
            return true;
        }
    return false;
    }

    @Override
    public boolean deleteReview(Long companyId, Long reviewId) {
        if(companyService.getCompanyById(companyId) != null && reviewRepository.existsById(reviewId)){

            Review review = reviewRepository.findById(reviewId).orElse(null);
            //we will get company associated with that company
            Company company = review.getCompany();
            company.getReviews().remove(review);
            companyService.updateCompany(company,companyId);
            //remove review from review repo
            reviewRepository.deleteById(reviewId);
            return true;
        }
        return false;
    }
}
