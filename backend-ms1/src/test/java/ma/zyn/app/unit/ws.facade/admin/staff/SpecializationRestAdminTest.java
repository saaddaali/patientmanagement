package ma.zyn.app.unit.ws.facade.admin.staff;

import ma.zyn.app.bean.core.staff.Specialization;
import ma.zyn.app.service.impl.admin.staff.SpecializationAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.staff.SpecializationRestAdmin;
import ma.zyn.app.ws.converter.staff.SpecializationConverter;
import ma.zyn.app.ws.dto.staff.SpecializationDto;
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
public class SpecializationRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private SpecializationAdminServiceImpl service;
    @Mock
    private SpecializationConverter converter;

    @InjectMocks
    private SpecializationRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllSpecializationTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<SpecializationDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<SpecializationDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveSpecializationTest() throws Exception {
        // Mock data
        SpecializationDto requestDto = new SpecializationDto();
        Specialization entity = new Specialization();
        Specialization saved = new Specialization();
        SpecializationDto savedDto = new SpecializationDto();

        // Mock the converter to return the specialization object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved specialization DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<SpecializationDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        SpecializationDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved specialization DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
