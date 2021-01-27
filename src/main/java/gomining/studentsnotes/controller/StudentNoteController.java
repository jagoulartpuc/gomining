package gomining.studentsnotes.controller;

import gomining.studentsnotes.domain.StudentNote;
import gomining.studentsnotes.service.StudentNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/note")
public class StudentNoteController {

    @Autowired
    private StudentNoteService service;

    @PostMapping
    public StudentNote postStudentNote(
            @RequestBody StudentNote studentNote
    ) {
        return service.insertNote(studentNote);
    }

    @GetMapping("/all")
    public List<StudentNote> listAllStudentNotes() {
        return service.listNotes();
    }

    @GetMapping
    public StudentNote getStudentNotes(
            @RequestParam String id
    ) {
        return service.getNoteById(id);
    }

    @PutMapping
    public StudentNote updateStudentNote(
            @RequestBody StudentNote studentNote
    ) {
        return service.editNote(studentNote);
    }

    @DeleteMapping
    public boolean deleteStudentNote(
            @RequestParam String id
    ) {
        service.deleteNote(id);
        return true;
    }

}
