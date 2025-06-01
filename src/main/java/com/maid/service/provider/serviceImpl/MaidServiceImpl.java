package com.maid.service.provider.serviceImpl;


import com.maid.service.provider.dto.ContactDto;
import com.maid.service.provider.dto.FeedBackDto;
import com.maid.service.provider.dto.InquiryDetailsDto;
import com.maid.service.provider.entity.ContactDetails;
import com.maid.service.provider.entity.FeedBackDetails;
import com.maid.service.provider.entity.InquiryDetails;
import com.maid.service.provider.repository.ContactRepository;
import com.maid.service.provider.repository.FeedbackRepository;
import com.maid.service.provider.repository.InquiryDetailsRepository;
import com.maid.service.provider.service.MaidService;
import com.maid.service.provider.util.Response;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public  class MaidServiceImpl implements MaidService {


    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private InquiryDetailsRepository inquiryDetailsRepository;

    @Autowired
    private ModelMapper modelMapper ;




    @Override
    public Response saveContactDetails(ContactDto contactDto) {
        try {
            ContactDetails contactDetails = modelMapper.map(contactDto, ContactDetails.class);
            contactDetails.setCreatedAt(LocalDateTime.now());
            ContactDetails savedContact = contactRepository.save(contactDetails);
            return new Response(201, "Contact details saved successfully", savedContact);
        } catch (Exception e) {
            return new Response(500, "Failed to save contact details: " + e.getMessage(), null);
        }
    }


    @Override
    public Response saveFeedbackDetails(FeedBackDto feedBackDto) {
        try {
            FeedBackDetails feedback = modelMapper.map(feedBackDto,FeedBackDetails.class);
            feedback.setCreatedAt(LocalDateTime.now());
            FeedBackDetails savedFeedback = feedbackRepository.save(feedback);
            return new Response(HttpStatus.CREATED.value(), "Feedback submitted successfully", savedFeedback);
        } catch (Exception e) {
            return new Response(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Failed to submit feedback: " + e.getMessage(), null);
        }
    }

    @Override
    public Response saveInquiryDetails(InquiryDetailsDto inquiryDetailsDto) {
        try {
            // DTO to Entity mapping
            InquiryDetails inquiryDetails = modelMapper.map(inquiryDetailsDto, InquiryDetails.class);
            inquiryDetails.setCreatedAt(LocalDateTime.now());
            // Save to DB
            InquiryDetails savedInquiry = inquiryDetailsRepository.save(inquiryDetails);

            // Return success response
            return new Response(201, "Inquiry submitted successfully", savedInquiry);

        } catch (Exception e) {
            // Log the exception (recommended in real applications)
            return new Response(500, "Failed to submit inquiry: " + e.getMessage(), null);
        }
    }

}
