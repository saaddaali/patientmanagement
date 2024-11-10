package ma.zyn.app.unit.ws.facade.infirmier.localisation;

import ma.zyn.app.bean.core.localisation.SafeZone;
import ma.zyn.app.service.impl.infirmier.localisation.SafeZoneInfirmierServiceImpl;
import ma.zyn.app.ws.facade.infirmier.localisation.SafeZoneRestInfirmier;
import ma.zyn.app.ws.converter.localisation.SafeZoneConverter;
import ma.zyn.app.ws.dto.localisation.SafeZoneDto;
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
public class SafeZoneRestInfirmierTest {

    private MockMvc mockMvc;

    @Mock
    private SafeZoneInfirmierServiceImpl service;
    @Mock
    private SafeZoneConverter converter;

    @InjectMocks
    private SafeZoneRestInfirmier controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllSafeZoneTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<SafeZoneDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<SafeZoneDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveSafeZoneTest() throws Exception {
        // Mock data
        SafeZoneDto requestDto = new SafeZoneDto();
        SafeZone entity = new SafeZone();
        SafeZone saved = new SafeZone();
        SafeZoneDto savedDto = new SafeZoneDto();

        // Mock the converter to return the safeZone object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved safeZone DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<SafeZoneDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        SafeZoneDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved safeZone DTO
        assertEquals(savedDto.getCentreLongitude(), responseBody.getCentreLongitude());
        assertEquals(savedDto.getCentreLatitude(), responseBody.getCentreLatitude());
        assertEquals(savedDto.getRayon(), responseBody.getRayon());
    }

}
