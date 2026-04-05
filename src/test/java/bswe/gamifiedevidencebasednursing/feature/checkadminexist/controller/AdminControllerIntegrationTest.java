package bswe.gamifiedevidencebasednursing.feature.checkadminexist.controller;

import bswe.gamifiedevidencebasednursing.feature.checkadminexist.service.AdminService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AdminControllerIntegrationTest {

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @MockitoBean
    private AdminService adminService;

    @Test
    void isThereAdmin_shouldReturnTrue_whenAdminExists() throws Exception {
        // Initialize MockMvc manually
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        // Given
        when(adminService.isThereAdmin()).thenReturn(true);

        // When & Then
        mockMvc.perform(get("/api/admin/isThereAdmin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    void isThereAdmin_shouldReturnFalse_whenNoAdminExists() throws Exception {
        // Initialize MockMvc manually
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();

        // Given
        when(adminService.isThereAdmin()).thenReturn(false);

        // When & Then
        mockMvc.perform(get("/api/admin/isThereAdmin")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("false"));
    }
}
