package com.embarkx.firstjobapp.job;

import com.embarkx.firstjobapp.job.Job;
import com.embarkx.firstjobapp.job.JobRepository;
import com.embarkx.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    // private List<Job> jobs = new ArrayList<>();
    JobRepository jobRepository;
    private Long nextId = 1L;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJob(Long id, Job updatedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if (jobOptional.isPresent()) {
            Job job = jobOptional.get();
            job.setTitle(updatedJob.getTitle());
            job.setDescription(updatedJob.getDescription());
            job.setMinSalary(updatedJob.getMinSalary());
            job.setMaxSalary(updatedJob.getMaxSalary());
            job.setLocation(updatedJob.getLocation());
            jobRepository.save(job);
            return true;
        }
        return false;
    }

    @Override
    public boolean patchJob(Long id, Job patchedJob) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job job = jobOptional.get();
            if(patchedJob.getTitle() != null) {
                job.setTitle(patchedJob.getTitle());
            }
            if(patchedJob.getDescription() != null) {
                job.setDescription(patchedJob.getDescription());
            }
            if(patchedJob.getMinSalary() != null) {
                job.setMinSalary(patchedJob.getMinSalary());
            }
            if(patchedJob.getMaxSalary() != null) {
                job.setMaxSalary(patchedJob.getMaxSalary());
            }
            if(patchedJob.getLocation() != null) {
                job.setLocation(patchedJob.getLocation());
            }
            jobRepository.save(job);
            return true;
        }
        return false;
    }
}
//
//    public Job updateJob(Job job) {
//        Job oldJob = getJobById(job.getId());
//        if(oldJob == null) {
//            return null;
//            }
//        oldJob.setId(job.getId());
//        oldJob.setDescription(job.getDescription());
//        oldJob.setLocation(job.getLocation());
//        oldJob.setTitle(job.getTitle());
//        oldJob.setMinSalary(job.getMinSalary());
//        oldJob.setMaxSalary(job.getMaxSalary());
//
//        return oldJob;
//    }

