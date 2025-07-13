package com.maid.service.provider.service;

import com.maid.service.provider.dto.ContactDto;
import com.maid.service.provider.dto.FeedBackDto;
import com.maid.service.provider.dto.InquiryDetailsDto;
import com.maid.service.provider.helper.PageableResponse;
import com.maid.service.provider.util.Response;

public interface MaidService {

    Response saveContactDetails(ContactDto contactDto);

    Response saveFeedbackDetails(FeedBackDto feedBackDto);

    Response saveInquiryDetails(InquiryDetailsDto inquiryDetailsDto);

    PageableResponse<ContactDto> getAllContacts(int page, int size);

    public PageableResponse<FeedBackDto> getAllFeedbacks(int page, int size);

    public PageableResponse<InquiryDetailsDto> getAllInquiryDetails(int page, int size);



}