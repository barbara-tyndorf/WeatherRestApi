package pl.sda.WeatherRestApi.location;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;


import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(LocationController.class)
class LocationControllerTest {

    @MockBean
    private LocationService locationService;

    @Autowired
    private MockMvc mvc;

    @Test
    void when_send_get_request_to_get_all_location_then_return_location_list() throws Exception {
        //given
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLatitude(28);
        locationDTO.setLongitude(34);
        locationDTO.setName("Szczecin");
        Mockito.when(locationService.getAll()).thenReturn(Arrays.asList(locationDTO));

        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/location")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].name", equalTo("Szczecin")));
    }

    //FIXME
    @Test
    void when_add_location_which_is_not_valid_then_error_code_should_be_returned() throws Exception {
        //given

        //when
        //then
        mvc.perform(MockMvcRequestBuilders.post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{}"))
                .andExpect(status().is4xxClientError());
    }


}