package ma.zyn.app.unit.ws.facade.doctor.warning;

import ma.zyn.app.bean.core.warning.WarningPatient;
import ma.zyn.app.service.impl.doctor.warning.WarningPatientDoctorServiceImpl;
import ma.zyn.app.ws.facade.doctor.warning.WarningPatientRestDoctor;
import ma.zyn.app.ws.converter.warning.WarningPatientConverter;
import ma.zyn.app.ws.dto.warning.WarningPatientDto;
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
public class WarningPatientRestDoctorTest {

    private MockMvc mockMvc;

    @Mock
    private WarningPatientDoctorServiceImpl service;
    @Mock
    private WarningPatientConverter converter;

    @InjectMocks
    private WarningPatientRestDoctor controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllWarningPatientTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<WarningPatientDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<WarningPatientDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveWarningPatientTest() throws Exception {
        // Mock data
        WarningPatientDto requestDto = new WarningPatientDto();
        WarningPatient entity = new WarningPatient();
        WarningPatient saved = new WarningPatient();
        WarningPatientDto savedDto = new WarningPatientDto();

        // Mock the converter to return the warningPatient object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved warningPatient DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<WarningPatientDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        WarningPatientDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved warningPatient DTO
        assertEquals(savedDto.getMessage(), responseBody.getMessage());
        assertEquals(savedDto.getDateWarning(), responseBody.getDateWarning());
    }

}
