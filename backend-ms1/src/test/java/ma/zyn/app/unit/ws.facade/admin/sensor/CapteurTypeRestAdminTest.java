package ma.zyn.app.unit.ws.facade.admin.sensor;

import ma.zyn.app.bean.core.sensor.CapteurType;
import ma.zyn.app.service.impl.admin.sensor.CapteurTypeAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.sensor.CapteurTypeRestAdmin;
import ma.zyn.app.ws.converter.sensor.CapteurTypeConverter;
import ma.zyn.app.ws.dto.sensor.CapteurTypeDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CapteurTypeRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private CapteurTypeAdminServiceImpl service;
    @Mock
    private CapteurTypeConverter converter;

    @InjectMocks
    private CapteurTypeRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllCapteurTypeTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<CapteurTypeDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<CapteurTypeDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveCapteurTypeTest() throws Exception {
        // Mock data
        CapteurTypeDto requestDto = new CapteurTypeDto();
        CapteurType entity = new CapteurType();
        CapteurType saved = new CapteurType();
        CapteurTypeDto savedDto = new CapteurTypeDto();

        // Mock the converter to return the capteurType object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved capteurType DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<CapteurTypeDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        CapteurTypeDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved capteurType DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
