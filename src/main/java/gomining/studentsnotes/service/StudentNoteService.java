package gomining.studentsnotes.service;

import gomining.studentsnotes.domain.StudentNote;
import gomining.studentsnotes.repository.StudentNoteRepository;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Setter
public class StudentNoteService {

    @Autowired
    private StudentNoteRepository repository;

    public StudentNote insertNote(StudentNote studentNote) {
        return repository.insert(studentNote);
    }

    public StudentNote editNote(StudentNote studentNote) {
        return repository.save(studentNote);
    }

    public List<StudentNote> listNotes() {
        return repository.findAll();
    }

    public StudentNote getNoteById(String id) {
        return repository.findById(id).orElseThrow();
    }

    public void deleteNote(String id) {
        repository.deleteById(id);
    }
}
