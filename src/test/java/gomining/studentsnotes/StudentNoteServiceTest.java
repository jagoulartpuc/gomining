package gomining.studentsnotes;

import gomining.studentsnotes.domain.StudentNote;
import gomining.studentsnotes.repository.StudentNoteRepository;
import gomining.studentsnotes.service.StudentNoteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.given;

@RunWith(MockitoJUnitRunner.class)
public class StudentNoteServiceTest {

    @InjectMocks
    private StudentNoteService service;

    @Mock
    private StudentNoteRepository repository;

    @Test
    public void studentNoteServiceShouldInsertNote() {
        StudentNote studentNote = new StudentNote();

        given(repository.insert(studentNote)).willReturn(studentNote);

        service.insertNote(studentNote);
        assertEquals(service.insertNote(studentNote), repository.insert(studentNote));
    }

    @Test
    public void studentNoteServiceShouldDeleteNote() {
        String id = "432423bh43534cvs";

        StudentNote studentNote = new StudentNote();
        studentNote.setId(id);
        service.insertNote(studentNote);
        service.deleteNote(id);
        assertTrue(service.listNotes().isEmpty());
    }

    @Test
    public void studentNoteServiceShouldGetNoteById() {
        String id = "432423bh43534cvs";

        StudentNote studentNote = new StudentNote();
        studentNote.setId(id);
        given(repository.insert(studentNote)).willReturn(studentNote);
        given(repository.findById(id)).willReturn(java.util.Optional.of(studentNote));

        StudentNote noteById = service.getNoteById(id);
        assertEquals(studentNote, noteById);
    }

    @Test
    public void studentNoteServiceShouldEditNote() {
        StudentNote studentNote = new StudentNote();
        studentNote.setNote(5.5);
        studentNote.setApproved(false);

        given(repository.insert(studentNote)).willReturn(studentNote);
        service.insertNote(studentNote);

        StudentNote reproved = studentNote;

        studentNote.setNote(7.0);
        studentNote.setApproved(true);
        given(repository.save(studentNote)).willReturn(studentNote);
        service.editNote(studentNote);

        assertNotSame(studentNote.getNote(), reproved.getNote());
    }

}