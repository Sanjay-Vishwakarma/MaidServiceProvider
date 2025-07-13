package com.maid.service.provider.controller;

import com.maid.service.provider.dto.ContactDto;
import com.maid.service.provider.dto.FeedBackDto;
import com.maid.service.provider.dto.GeoLocation;
import com.maid.service.provider.dto.InquiryDetailsDto;
import com.maid.service.provider.service.MaidService;
import com.maid.service.provider.util.Functions;
import com.maid.service.provider.util.GeoLocationService;
import com.maid.service.provider.util.Response;
import com.maid.service.provider.util.Functions.UserAgentDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/maid")
public class MaidServiceController {

    @Autowired
    private MaidService maidService;

    @PostMapping("/addContact")
    public ResponseEntity<?> addContact(@Valid @RequestBody ContactDto contactDto, HttpServletRequest request) {
        enrichWithRequestMeta(contactDto, request);
        Response response = maidService.saveContactDetails(contactDto);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/addFeedback")
    public ResponseEntity<Response> addFeedback(@Valid @RequestBody FeedBackDto feedBackDto, HttpServletRequest request) {
        enrichWithRequestMeta(feedBackDto, request);
        Response response = maidService.saveFeedbackDetails(feedBackDto);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @PostMapping("/addInquiryDetails")
    public ResponseEntity<Response> addInquiryDetails(@Valid @RequestBody InquiryDetailsDto inquiryDetailsDto, HttpServletRequest request) {
        enrichWithRequestMeta(inquiryDetailsDto, request);
        Response response = maidService.saveInquiryDetails(inquiryDetailsDto);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    // Utility method to enrich DTO with device info
    private void enrichWithRequestMeta(Object dto, HttpServletRequest request) {
        String ipAddress = Functions.extractClientIp(request);
        UserAgentDetails userAgentDetails = Functions.extractUserAgentDetails(request);
        GeoLocation geoLocation = GeoLocationService.getGeoLocation(ipAddress);

        if (dto instanceof ContactDto contactDto) {
            contactDto.setIpAddress(ipAddress);
            contactDto.setOperatingSystem(userAgentDetails.getOperatingSystem());
            contactDto.setDeviceType(userAgentDetails.getDeviceType());
            contactDto.setCity(geoLocation.getCity());
            contactDto.setRegion(geoLocation.getRegion());
            contactDto.setCountry(geoLocation.getCountry());
        } else if (dto instanceof FeedBackDto feedBackDto) {
            feedBackDto.setIpAddress(ipAddress);
            feedBackDto.setOperatingSystem(userAgentDetails.getOperatingSystem());
            feedBackDto.setDeviceType(userAgentDetails.getDeviceType());
            feedBackDto.setCity(geoLocation.getCity());
            feedBackDto.setRegion(geoLocation.getRegion());
            feedBackDto.setCountry(geoLocation.getCountry());
        } else if (dto instanceof InquiryDetailsDto inquiryDetailsDto) {
            inquiryDetailsDto.setIpAddress(ipAddress);
            inquiryDetailsDto.setOperatingSystem(userAgentDetails.getOperatingSystem());
            inquiryDetailsDto.setDeviceType(userAgentDetails.getDeviceType());
            inquiryDetailsDto.setCity(geoLocation.getCity());
            inquiryDetailsDto.setRegion(geoLocation.getRegion());
            inquiryDetailsDto.setCountry(geoLocation.getCountry());
        }
    }


}
