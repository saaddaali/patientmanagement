package ma.zyn.app.unit.ws.facade.admin.healthcare;

import ma.zyn.app.bean.core.healthcare.InfirmierPatient;
import ma.zyn.app.service.impl.admin.healthcare.InfirmierPatientAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.healthcare.InfirmierPatientRestAdmin;
import ma.zyn.app.ws.converter.healthcare.InfirmierPatientConverter;
import ma.zyn.app.ws.dto.healthcare.InfirmierPatientDto;
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
public class InfirmierPatientRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private InfirmierPatientAdminServiceImpl service;
    @Mock
    private InfirmierPatientConverter converter;

    @InjectMocks
    private InfirmierPatientRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllInfirmierPatientTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<InfirmierPatientDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<InfirmierPatientDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveInfirmierPatientTest() throws Exception {
        // Mock data
        InfirmierPatientDto requestDto = new InfirmierPatientDto();
        InfirmierPatient entity = new InfirmierPatient();
        InfirmierPatient saved = new InfirmierPatient();
        InfirmierPatientDto savedDto = new InfirmierPatientDto();

        // Mock the converter to return the infirmierPatient object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved infirmierPatient DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<InfirmierPatientDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        InfirmierPatientDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved infirmierPatient DTO
        assertEquals(savedDto.getDateDebut(), responseBody.getDateDebut());
        assertEquals(savedDto.getDateFin(), responseBody.getDateFin());
    }

}
