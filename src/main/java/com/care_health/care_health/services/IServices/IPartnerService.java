package com.care_health.care_health.services.IServices;

import com.care_health.care_health.dtos.request.partner.PartnerRequest;
import com.care_health.care_health.dtos.response.partner.PartnerResponse;

import java.util.UUID;

public interface IPartnerService {
    PartnerResponse createNewPartner (PartnerRequest partnerRequest);

    PartnerResponse getAllPartner();

    PartnerResponse deletePartner(UUID partnerId);

}
