package com.maid.service.provider.service;

import com.maid.service.provider.dto.ContactDto;
import com.maid.service.provider.dto.FeedBackDto;
import com.maid.service.provider.dto.InquiryDetailsDto;
import com.maid.service.provider.util.Response;
import jakarta.validation.Valid;

public interface MaidService {

    Response saveContactDetails(ContactDto contactDto);

    Response saveFeedbackDetails(FeedBackDto feedBackDto);

    Response saveInquiryDetails(InquiryDetailsDto inquiryDetailsDto);
}
