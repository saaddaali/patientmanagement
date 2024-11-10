package ma.zyn.app.unit.ws.facade.admin.staff;

import ma.zyn.app.bean.core.staff.Doctor;
import ma.zyn.app.service.impl.admin.staff.DoctorAdminServiceImpl;
import ma.zyn.app.ws.facade.admin.staff.DoctorRestAdmin;
import ma.zyn.app.ws.converter.staff.DoctorConverter;
import ma.zyn.app.ws.dto.staff.DoctorDto;
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
public class DoctorRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private DoctorAdminServiceImpl service;
    @Mock
    private DoctorConverter converter;

    @InjectMocks
    private DoctorRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllDoctorTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<DoctorDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<DoctorDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveDoctorTest() throws Exception {
        // Mock data
        DoctorDto requestDto = new DoctorDto();
        Doctor entity = new Doctor();
        Doctor saved = new Doctor();
        DoctorDto savedDto = new DoctorDto();

        // Mock the converter to return the doctor object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved doctor DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<DoctorDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        DoctorDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved doctor DTO
        assertEquals(savedDto.getPasswordChanged(), responseBody.getPasswordChanged());
        assertEquals(savedDto.getAccountNonLocked(), responseBody.getAccountNonLocked());
        assertEquals(savedDto.getPassword(), responseBody.getPassword());
        assertEquals(savedDto.getEmail(), responseBody.getEmail());
        assertEquals(savedDto.getEnabled(), responseBody.getEnabled());
        assertEquals(savedDto.getCredentialsNonExpired(), responseBody.getCredentialsNonExpired());
        assertEquals(savedDto.getAccountNonExpired(), responseBody.getAccountNonExpired());
        assertEquals(savedDto.getUsername(), responseBody.getUsername());
    }

}
