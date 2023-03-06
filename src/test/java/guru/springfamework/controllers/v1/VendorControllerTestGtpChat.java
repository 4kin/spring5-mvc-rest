package guru.springfamework.controllers.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.api.v1.model.VendorListDTO;
import guru.springfamework.services.VendorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class VendorControllerTestGtpChat {

    private static final String BASE_URL = "/api/v1/vendors";
    private static final Long VENDOR_ID = 1L;

    @Mock
    private VendorService vendorService;

    @InjectMocks
    private VendorController vendorController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(vendorController).build();
    }

    @Test
    public void testGetVendorList() throws Exception {
        // Mock the service method
        VendorListDTO vendorListDTO = new VendorListDTO(Arrays.asList(new VendorDTO(), new VendorDTO()));
        when(vendorService.getAllVendors()).thenReturn(vendorListDTO);

        // Send the request and assert the response
        mockMvc.perform(get(BASE_URL))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.vendors", hasSize(2)));
    }

    @Test
    public void testGetVendorById() throws Exception {
        // Mock the service method
        VendorDTO vendorDTO = new VendorDTO();
//        vendorDTO.setId(VENDOR_ID);
        vendorDTO.setName("Vendor");
        when(vendorService.getVendorById(VENDOR_ID)).thenReturn(vendorDTO);

        // Send the request and assert the response
        mockMvc.perform(get(BASE_URL + "/" + VENDOR_ID))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id", equalTo(VENDOR_ID.intValue())))
                .andExpect((ResultMatcher) jsonPath("$.name", equalTo("Vendor")));
    }

    @Test
    public void testCreateNewVendor() throws Exception {
        // Prepare the request body
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Vendor");

        // Mock the service method
        VendorDTO savedVendorDTO = new VendorDTO();
//        savedVendorDTO.setId(VENDOR_ID);
        savedVendorDTO.setName("Vendor");
        when(vendorService.createNewVendor(vendorDTO)).thenReturn(savedVendorDTO);

        // Send the request and assert the response
        mockMvc.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                .andExpect(status().isCreated())
                .andExpect((ResultMatcher) jsonPath("$.id", equalTo(VENDOR_ID.intValue())))
                .andExpect((ResultMatcher) jsonPath("$.name", equalTo("Vendor")));
    }

    @Test
    public void testUpdateVendor() throws Exception {
        // Prepare the request body
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("New Vendor");

        // Mock the service method
        VendorDTO savedVendorDTO = new VendorDTO();
//        savedVendorDTO.setId(VENDOR_ID);
        savedVendorDTO.setName("New Vendor");
        when(vendorService.saveVendorByDTO(VENDOR_ID, vendorDTO)).thenReturn(savedVendorDTO);

        // Send the request and assert the response
        mockMvc.perform(put(BASE_URL + "/" + VENDOR_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id", equalTo(VENDOR_ID.intValue())))
                .andExpect((ResultMatcher) jsonPath("$.name", equalTo("New Vendor")));
    }

    @Test
    public void testPatchVendor() throws Exception {
        // Prepare the request body
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("New Vendor");

        // Mock the service method
        VendorDTO savedVendorDTO = new VendorDTO();
//        savedVendorDTO.setId(VENDOR_ID);
        savedVendorDTO.setName("New Vendor");
        when(vendorService.saveVendorByDTO(VENDOR_ID, vendorDTO)).thenReturn(savedVendorDTO);

        // Send the request and assert the
        mockMvc.perform(patch(BASE_URL + "/" + VENDOR_ID)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(vendorDTO)))
                .andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$.id", equalTo(VENDOR_ID.intValue())))
                .andExpect((ResultMatcher) jsonPath("$.name", equalTo("New Vendor")));
    }

    @Test
    public void testDeleteVendor() throws Exception {
        // Send the request and assert the response
        mockMvc.perform(delete(BASE_URL + "/" + VENDOR_ID))
                .andExpect(status().isOk());

        // Verify that the service method was called
        verify(vendorService, times(1)).deleteVendorById(VENDOR_ID);
    }

    // Utility method to convert an object to a JSON string
    private String asJsonString(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return objectMapper.writeValueAsString(object);
    }
}