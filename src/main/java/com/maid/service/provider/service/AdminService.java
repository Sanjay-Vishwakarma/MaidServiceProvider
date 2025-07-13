package com.maid.service.provider.service;

import com.maid.service.provider.dto.AdminDto;
import com.maid.service.provider.util.Response;

public interface AdminService {

    Response adminLogin(AdminDto adminDto);

    Response saveAdmin(AdminDto adminDto);
}
