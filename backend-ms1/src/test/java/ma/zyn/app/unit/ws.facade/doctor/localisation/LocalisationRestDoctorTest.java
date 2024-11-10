package ma.zyn.app.unit.ws.facade.doctor.localisation;

import ma.zyn.app.bean.core.localisation.Localisation;
import ma.zyn.app.service.impl.doctor.localisation.LocalisationDoctorServiceImpl;
import ma.zyn.app.ws.facade.doctor.localisation.LocalisationRestDoctor;
import ma.zyn.app.ws.converter.localisation.LocalisationConverter;
import ma.zyn.app.ws.dto.localisation.LocalisationDto;
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
public class LocalisationRestDoctorTest {

    private MockMvc mockMvc;

    @Mock
    private LocalisationDoctorServiceImpl service;
    @Mock
    private LocalisationConverter converter;

    @InjectMocks
    private LocalisationRestDoctor controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllLocalisationTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<LocalisationDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<LocalisationDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveLocalisationTest() throws Exception {
        // Mock data
        LocalisationDto requestDto = new LocalisationDto();
        Localisation entity = new Localisation();
        Localisation saved = new Localisation();
        LocalisationDto savedDto = new LocalisationDto();

        // Mock the converter to return the localisation object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved localisation DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<LocalisationDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        LocalisationDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved localisation DTO
        assertEquals(savedDto.getDateLocalisation(), responseBody.getDateLocalisation());
        assertEquals(savedDto.getLongitude(), responseBody.getLongitude());
        assertEquals(savedDto.getLatitude(), responseBody.getLatitude());
    }

}
