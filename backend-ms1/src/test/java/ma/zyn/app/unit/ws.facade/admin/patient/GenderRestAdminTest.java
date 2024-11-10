package ma.zyn.app.unit.ws.facade.admin.patient;

import ma.zyn.app.bean.core.patient.Gender;
import ma.zyn.app.service.impl.admin.patient.GenderAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.patient.GenderRestAdmin;
import ma.zyn.app.ws.converter.patient.GenderConverter;
import ma.zyn.app.ws.dto.patient.GenderDto;
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
public class GenderRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private GenderAdminServiceImpl service;
    @Mock
    private GenderConverter converter;

    @InjectMocks
    private GenderRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllGenderTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<GenderDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<GenderDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveGenderTest() throws Exception {
        // Mock data
        GenderDto requestDto = new GenderDto();
        Gender entity = new Gender();
        Gender saved = new Gender();
        GenderDto savedDto = new GenderDto();

        // Mock the converter to return the gender object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved gender DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<GenderDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        GenderDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved gender DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
        assertEquals(savedDto.getDescription(), responseBody.getDescription());
    }

}
