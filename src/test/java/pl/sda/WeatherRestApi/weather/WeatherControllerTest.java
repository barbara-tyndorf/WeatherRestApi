package pl.sda.WeatherRestApi.weather;

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
import pl.sda.WeatherRestApi.location.Location;

import java.util.Arrays;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(WeatherController.class)
class WeatherControllerTest {

    @MockBean
    private WeatherService weatherService;

    @Autowired
    private MockMvc mvc;

    @Test
    void when_get_all_weathers_then_weathers_json_array_should_be_returned() throws Exception {
      //given
        Mockito.when(weatherService.getAll()).thenReturn(Arrays.asList(
              new WeatherDTO(1L, 20, "30",20,1020, 20, "13-06-2020", "0"),
              new WeatherDTO(2L, 25, "15",10,1011, 80, "13-06-2020", "1")
      ));
        //when
        //then
        mvc.perform(MockMvcRequestBuilders.get("/weather")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].temperature", equalTo(20d)))
                .andExpect(jsonPath("$[1].windDirection", equalTo("15")))
                .andExpect(status().isOk())
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void when_add_movie_then_movie_should_be_added_to_storage() throws Exception {
//        //given
//        WeatherDTO weatherDTO = new WeatherDTO(1L, 20, "30",20,1020, 20, "13-06-2020", "0");
//        Mockito.when(weatherService.add(Mockito.any())).thenReturn(weatherDTO);
//
//        //when
//        //then
//        mvc.perform(
//                MockMvcRequestBuilders.post("/weather")
//                        .content("{\"id\":1L,\"temperature\":\20\",\"windDirection\":\"30\"," +
//                                "\"windSpeed\":\20\",\"pressure\":\"1020\",\"humidity\":\"20\",
//                                "\date\":"13-06-2020",\"locationId\":"0"";
//
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(status().is2xxSuccessful())
//                .andExpect(jsonPath("$.title", equalTo("Harry Potter")));
    }
}