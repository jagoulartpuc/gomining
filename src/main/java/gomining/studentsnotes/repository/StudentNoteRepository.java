package gomining.studentsnotes.repository;

import gomining.studentsnotes.domain.StudentNote;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentNoteRepository extends MongoRepository<StudentNote, String> {

}
