package gomining.studentsnotes;

import com.fasterxml.jackson.databind.ObjectMapper;
import gomining.studentsnotes.controller.StudentNoteController;
import gomining.studentsnotes.domain.StudentNote;
import gomining.studentsnotes.repository.StudentNoteRepository;
import gomining.studentsnotes.service.StudentNoteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(StudentNoteController.class)
public class StudentNoteIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private StudentNoteRepository repository;

    @Mock
    private StudentNoteService service;

    @InjectMocks
    private StudentNoteController controller;

    @Before
    public void setup() {
        repository = Mockito.mock(StudentNoteRepository.class);
        service = Mockito.mock(StudentNoteService.class);
        service.setRepository(repository);
        controller.setService(service);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void StudentNoteAppShouldPostNewNote() throws Exception {
        StudentNote studentNote = new StudentNote();
        String id = "534bhj34b3";
        studentNote.setId(id);
        studentNote.setNote(6.9);
        studentNote.setStudent("Pedro");

        String json = "    {\n" +
                "    \"id\": \"h5j34h53454c\",\n" +
                "    \"name\": \"Pedro\",\n" +
                "    \"note\": 6.9\n" +
                "    }";

        mockMvc.perform(post("/note")
                .contentType("application/json")
                .content(json))
                .andExpect(status().isOk());
        Assert.assertEquals(studentNote.getId(), repository.findById(id).orElseThrow().getId());
    }

}