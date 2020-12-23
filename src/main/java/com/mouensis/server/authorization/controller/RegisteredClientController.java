package com.mouensis.server.authorization.controller;

import com.mouensis.framework.web.domain.PageInfo;
import com.mouensis.framework.web.domain.Pagination;
import com.mouensis.server.authorization.dto.client.RegisteredClientDto;
import com.mouensis.server.authorization.dto.client.QueryRegisteredClientDto;
import com.mouensis.server.authorization.entity.RegisteredClientEntity;
import com.mouensis.server.authorization.service.RegisteredClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客户端Controller
 *
 * @author zhuyuan
 * @date 2020-12-13 23:01
 */
@RestController
@RequestMapping("/registered-clients")
@Tag(name = "registered-client:*", description = "客户端管理")
@SecurityRequirement(name = "security-bearer")
public class RegisteredClientController {

    private RegisteredClientService registeredClientService;

    @Autowired
    public void setRegisteredClientService(RegisteredClientService registeredClientService) {
        this.registeredClientService = registeredClientService;
    }

    @GetMapping("")
    @Operation(operationId = "registered-client:list", summary = "分页查询客户端列表")
    public ResponseEntity<PageInfo<RegisteredClientDto>> list(@Parameter(description = "客户端ID") QueryRegisteredClientDto queryRegisteredClientDto,
                                                       @Parameter(description = "分页信息") Pagination pagination) {
        Example<RegisteredClientEntity> example = queryRegisteredClientDto.toExample(RegisteredClientEntity.class);
        Pageable pageable = pagination.toPageable(RegisteredClientEntity.class);
        Page<RegisteredClientEntity> entityPage = registeredClientService.listPage(example, pageable);
        PageInfo<RegisteredClientDto> dtoPage = PageInfo.of(entityPage, registeredClientEntity -> {
            RegisteredClientDto registeredClientDto = new RegisteredClientDto();
            BeanUtils.copyProperties(registeredClientEntity, registeredClientDto);
            return registeredClientDto;
        });
        return ResponseEntity.ok(dtoPage);
    }
}
