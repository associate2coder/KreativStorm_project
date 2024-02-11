package com.springbootproject.service;

import com.springbootproject.dto.student.StudentDto;
import com.springbootproject.exception.student.*;
import com.springbootproject.object.Student;
import com.springbootproject.repository.StudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    private static Student testStudent1;
    private static Student testStudent2;
    private static Student testStudent3;
    private static Student testStudent4;
    private static Student testStudent5;
    private static Student testStudent6;
    private static Student testStudent7;
    private static Student testStudent8;
    private static Student testStudent9;
    private static Student testStudent10;
    private static StudentDto testStudentDto1;
    private static StudentDto testStudentDto2;
    private static StudentDto testStudentDto3;
    private static StudentDto testStudentDto4;
    private static StudentDto testStudentDto5;
    private static StudentDto testStudentDto6;
    private static StudentDto testStudentDto7;
    private static StudentDto testStudentDto8;
    private static StudentDto testStudentDto9;
    private static StudentDto testStudentDto10;


    @Mock
    private StudentRepository studentRepository;
    @InjectMocks
    private StudentServiceImpl studentServiceImpl;

    @BeforeAll
    public static void initialization() {

        //TestStudent 1
        testStudent1 = new Student();
        testStudent1.setId(1);
        testStudent1.setName("Simon DTO");
        testStudent1.setAge(2);
        testStudent1.setEmail("test@mail.com");

        //TestStudent 2
        testStudent2 = new Student();
        testStudent2.setId(0);
        testStudent2.setName("Péter DTO");
        testStudent2.setAge(4);
        testStudent2.setEmail("email@provider.com");

        //TestStudent 3
        testStudent3 = new Student();
        testStudent3.setId(3);
        testStudent3.setName(" ");
        testStudent3.setAge(2);
        testStudent3.setEmail("super123@poweeeeeeeeeeeeeeeeeeeeeeeeeeeer.com");

        //TestStudent 4
        testStudent4 = new Student();
        testStudent4.setId(4);
        testStudent4.setName("DTO Guy");
        testStudent4.setAge(-1999);
        testStudent4.setEmail("notan@emailaddress.com");

        //TestStudent 5
        testStudent5 = new Student();
        testStudent5.setId(5);
        testStudent5.setName("DTO Girl");
        testStudent5.setAge(0);
        testStudent5.setEmail("notan@emailaddress.com");

        //TestStudent 6
        testStudent6 = new Student();
        testStudent6.setId(6);
        testStudent6.setName("Emailtest");
        testStudent6.setAge(2);
        testStudent6.setEmail("notthrightformat.emailaddress@com");

        //TestStudent 7
        testStudent7 = new Student();
        testStudent7.setId(7);
        testStudent7.setName("Email Test");
        testStudent7.setAge(2);
        testStudent7.setEmail("not.areal@emailaddress.com");

        //TestStudent 8
        testStudent8 = new Student();
        testStudent8.setId(8);
        testStudent8.setName("Email Test Two");
        testStudent8.setAge(2);
        testStudent8.setEmail("not@correct@emailaddress.com");

        //TestStudent 9
        testStudent9 = new Student();
        testStudent9.setId(9);
        testStudent9.setName("Email Test Two");
        testStudent9.setAge(2);
        testStudent9.setEmail("notcorrect@emailaddresscom");

        //TestStudent 10
        testStudent10 = null;

        //TestStudentDto 1
        testStudentDto1 = new StudentDto();
        testStudentDto1.setId(1);
        testStudentDto1.setName("Simon DTO");
        testStudentDto1.setAge(2);
        testStudentDto1.setEmail("test@mail.com");

        //TestStudentDto 2
        testStudentDto2 = new StudentDto();
        testStudentDto2.setId(0);
        testStudentDto2.setName("Péter DTO");
        testStudentDto2.setAge(4);
        testStudentDto2.setEmail("email@provider.com");

        //TestStudentDto 3
        testStudentDto3 = new StudentDto();
        testStudentDto3.setId(3);
        testStudentDto3.setName(" ");
        testStudentDto3.setAge(2);
        testStudentDto3.setEmail("super123@poweeeeeeeeeeeeeeeeeeeeeeeeeeeer.com");

        //TestStudentDto 4
        testStudentDto4 = new StudentDto();
        testStudentDto4.setId(4);
        testStudentDto4.setName("DTO Guy");
        testStudentDto4.setAge(-1999);
        testStudentDto4.setEmail("notan@emailaddress.com");

        //TestStudentDto 5
        testStudentDto5 = new StudentDto();
        testStudentDto5.setId(5);
        testStudentDto5.setName("DTO Girl");
        testStudentDto5.setAge(0);
        testStudentDto5.setEmail("notan@emailaddress.com");

        //TestStudentDto 6
        testStudentDto6 = new StudentDto();
        testStudentDto6.setId(6);
        testStudentDto6.setName("Emailtest");
        testStudentDto6.setAge(2);
        testStudentDto6.setEmail("notthrightformat.emailaddress@com");

        //TestStudentDto 7
        testStudentDto7 = new StudentDto();
        testStudentDto7.setId(7);
        testStudentDto7.setName("Email Test");
        testStudentDto7.setAge(2);
        testStudentDto7.setEmail("not.areal@emailaddress.com");

        //TestStudentDto 8
        testStudentDto8 = new StudentDto();
        testStudentDto8.setId(8);
        testStudentDto8.setName("Email Test Two");
        testStudentDto8.setAge(2);
        testStudentDto8.setEmail("not@correct@emailaddress.com");

        //TestStudentDto 9
        testStudentDto9 = new StudentDto();
        testStudentDto9.setId(9);
        testStudentDto9.setName("Email Test Two");
        testStudentDto9.setAge(2);
        testStudentDto9.setEmail("notcorrect@emailaddresscom");

        //TestStudentDto 10
        testStudentDto10 = null;
    }

    @Test
    @DisplayName("#1: Empty StudentDto should throw StudentDtoNullException")
    void StudentServiceImpl_checkIfStudentDtoIsValid_throwAnExceptionIfStudentDtoIsEmpty() {

        assertThrows(StudentDtoNullException.class, () -> {
            studentServiceImpl.checkIfStudentDtoIsValid(testStudentDto10);
        });
    }

    @Test
    @DisplayName("#2: 0 or below StudentDto.getId() should throw StudentDtoWrongIdException")
    void StudentServiceImpl_checkIfStudentDtoIsValid_throwAnExceptionIfStudentDtoIdIsZeroOrBelow() {

        assertThrows(StudentDtoWrongIdException.class, () -> {
            studentServiceImpl.checkIfStudentDtoIsValid(testStudentDto2);
        });
    }

    @Test
    @DisplayName("#3: Blank StudentDto.getName() should throw StudentDtoWrongNameException")
    void StudentServiceImpl_checkIfStudentDtoIsValid_throwAnExceptionIfStudentDtoNameIsBlank() {

        assertThrows(StudentDtoWrongNameException.class, () -> {
            studentServiceImpl.checkIfStudentDtoIsValid(testStudentDto3);
        });
    }

    @Test
    @DisplayName("#4: 0 or below StudentDto.getAge() should throw StudentDtoWrongAgeException")
    void StudentServiceImpl_checkIfStudentDtoIsValid_throwAnExceptionIfStudentDtoAgeIsZeroOrBelow() {

        assertThrows(StudentDtoWrongAgeException.class, () -> {
            studentServiceImpl.checkIfStudentDtoIsValid(testStudentDto4);
        });
        assertThrows(StudentDtoWrongAgeException.class, () -> {
            studentServiceImpl.checkIfStudentDtoIsValid(testStudentDto5);
        });
    }

    @Test
    @DisplayName("#5: Blank or incorrect StudentDto.getEmail() should throw StudentDtoWrongEmailException")
    void StudentServiceImpl_checkIfStudentDtoIsValid_throwAnExceptionIfStudentDtoEmailIsInvalidOrBlank() {
        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.checkIfStudentDtoIsValid(testStudentDto6);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.checkIfStudentDtoIsValid(testStudentDto7);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.checkIfStudentDtoIsValid(testStudentDto8);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.checkIfStudentDtoIsValid(testStudentDto9);
        });
    }


    @Test
    @DisplayName("#6: Empty Student should throw StudentNullException")
    void StudentServiceImpl_checkIfStudentIsValid_throwAnExceptionIfStudentIsEmpty() {

        assertThrows(StudentNullException.class, () -> {
            studentServiceImpl.checkIfStudentIsValid(testStudent10);
        });
    }

    @Test
    @DisplayName("#7: 0 or below Student.getId() should throw StudentWrongIdException")
    void StudentServiceImpl_checkIfStudentIsValid_throwAnExceptionIfStudentIdIsZeroOrBelow() {

        assertThrows(StudentWrongIdException.class, () -> {
            studentServiceImpl.checkIfStudentIsValid(testStudent2);
        });
    }

    @Test
    @DisplayName("#8: Blank Student.getName() should throw StudentWrongNameException")
    void StudentServiceImpl_checkIfStudentIsValid_throwAnExceptionIfStudentNameIsBlank() {

        assertThrows(StudentWrongNameException.class, () -> {
            studentServiceImpl.checkIfStudentIsValid(testStudent3);
        });
    }

    @Test
    @DisplayName("#9: 0 or below Student.getAge() should throw StudentWrongAgeException")
    void StudentServiceImpl_checkIfStudentIsValid_throwAnExceptionIfStudentAgeIsZeroOrBelow() {

        assertThrows(StudentWrongAgeException.class, () -> {
            studentServiceImpl.checkIfStudentIsValid(testStudent4);
        });
        assertThrows(StudentWrongAgeException.class, () -> {
            studentServiceImpl.checkIfStudentIsValid(testStudent5);
        });
    }

    @Test
    @DisplayName("#10: Blank or incorrect Student.getEmail() should throw StudentWrongEmailException")
    void StudentServiceImpl_checkIfStudentIsValid_throwAnExceptionIfStudentEmailIsInvalidOrBlank() {
        assertThrows(StudentWrongEmailException.class, () -> {
            studentServiceImpl.checkIfStudentIsValid(testStudent6);
        });

        assertThrows(StudentWrongEmailException.class, () -> {
            studentServiceImpl.checkIfStudentIsValid(testStudent7);
        });

        assertThrows(StudentWrongEmailException.class, () -> {
            studentServiceImpl.checkIfStudentIsValid(testStudent8);
        });

        assertThrows(StudentWrongEmailException.class, () -> {
            studentServiceImpl.checkIfStudentIsValid(testStudent9);
        });
    }

    @Test
    @DisplayName("#11: Positive Student test, should not have any issues")
    void StudentServiceImpl_checkIfStudentIsValid_positiveTestShouldNotThrowAnException() {

        assertDoesNotThrow(() -> {
            studentServiceImpl.checkIfStudentIsValid(testStudent1);
        });
    }

    @Test
    @DisplayName("#12: Positive StudentDto test, should not have any issues")
    void StudentServiceImpl_checkIfStudentDtoIsValid_positiveTestShouldNotThrowAnException() {

        assertDoesNotThrow(() -> {
            studentServiceImpl.checkIfStudentDtoIsValid(testStudentDto1);
        });
    }

    @Test
    @DisplayName("#13: Testing saveStudent(), postive test, should not throw Exception")
    void StudentServiceImpl_saveStudent_testingWhetherItCanSaveAStudentSuccessfully() {

        assertDoesNotThrow(() -> {
            studentServiceImpl.saveStudent(testStudentDto1);
        });

        verify(studentRepository, Mockito.times(1)).save(any(Student.class));
    }

    @Test
    @DisplayName("#14: Testing saveStudent(), bad StudentDto values throw various StudentDtoExceptions")
    void StudentServiceImpl_saveStudent_badStudentDtoValuesThrowStudentDtoException() {

        assertThrows(StudentDtoWrongIdException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto2);
        });

        assertThrows(StudentDtoWrongNameException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto3);
        });

        assertThrows(StudentDtoWrongAgeException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto4);
        });

        assertThrows(StudentDtoWrongAgeException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto5);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto6);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto7);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto8);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto9);
        });

        assertThrows(StudentDtoNullException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto10);
        });
    }

    @Test
    @DisplayName("#15: Testing saveMultipleStudentsAtOnce(), positive test, uploading Student list should not throw any exceptions")
    void StudentServiceImpl_saveMultipleStudentsAtOnce_goodStudentDtoValuesDoNotThrowStudentDtoExceptions() {

        List<StudentDto> studentDtoListTest = new ArrayList<>();

        studentDtoListTest.add(testStudentDto1);
        studentDtoListTest.add(new StudentDto(2, "test name", 12, "test@email.com"));


        assertDoesNotThrow(() -> {
            studentServiceImpl.saveMultipleStudentsAtOnce(studentDtoListTest);
        });

        verify(studentRepository, Mockito.times(1)).saveAll(anyIterable());
    }

    @Test
    @DisplayName("#16: Testing saveMultipleStudentsAtOnce(), bad StudentDto values throw various StudentDtoExceptions")
    void StudentServiceImpl_saveMultipleStudentsAtOnce_badStudentDtoValuesThrowStudentDtoException() {

        List<StudentDto> studentDtoListTest = new ArrayList<>();

        studentDtoListTest.add(testStudentDto2);

        assertThrows(StudentDtoWrongIdException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto2);
        });

        studentDtoListTest.add(testStudentDto3);

        assertThrows(StudentDtoWrongNameException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto3);
        });

        studentDtoListTest.add(testStudentDto4);

        assertThrows(StudentDtoWrongAgeException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto4);
        });

        studentDtoListTest.add(testStudentDto5);

        assertThrows(StudentDtoWrongAgeException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto5);
        });

        studentDtoListTest.add(testStudentDto6);

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto6);
        });

        studentDtoListTest.add(testStudentDto7);

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto7);
        });

        studentDtoListTest.add(testStudentDto8);

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto8);
        });

        studentDtoListTest.add(testStudentDto9);

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto9);
        });

        studentDtoListTest.add(testStudentDto10);

        assertThrows(StudentDtoNullException.class, () -> {
            studentServiceImpl.saveStudent(testStudentDto10);
        });
    }

    @Test
    @DisplayName("#17: Testing updateStudent(), update Student in student table should not throw exceptions in case of valid Student")
    void StudentServiceImpl_updateStudent_positiveTestShouldNotThrowAnyExceptions() {
        when(studentRepository.findById(testStudentDto1.getId())).thenReturn(Optional.of(testStudent1));

        assertDoesNotThrow(() -> {
            studentServiceImpl.updateStudent(testStudentDto1);
        });

        verify(studentRepository, Mockito.times(1)).save(any(Student.class));
    }

    @Test
    @DisplayName("#18: Testing updateStudent(), update Students in student table should throw the right exceptions in case of invalid Student data")
    void StudentServiceImpl_updateStudent_updateShouldThrowTheRightExceptions() {
        assertThrows(StudentDtoWrongIdException.class, () -> {
            studentServiceImpl.updateStudent(testStudentDto2);
        });

        assertThrows(StudentDtoWrongNameException.class, () -> {
            studentServiceImpl.updateStudent(testStudentDto3);
        });

        assertThrows(StudentDtoWrongAgeException.class, () -> {
            studentServiceImpl.updateStudent(testStudentDto4);
        });

        assertThrows(StudentDtoWrongAgeException.class, () -> {
            studentServiceImpl.updateStudent(testStudentDto5);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.updateStudent(testStudentDto6);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.updateStudent(testStudentDto7);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.updateStudent(testStudentDto8);
        });

        assertThrows(StudentDtoWrongEmailException.class, () -> {
            studentServiceImpl.updateStudent(testStudentDto9);
        });

        assertThrows(StudentDtoNullException.class, () -> {
            studentServiceImpl.updateStudent(testStudentDto10);
        });
    }


    @Test
    @DisplayName("#19: Testing checkIfStudentExistsById(), should not throw an exception in case the Student exists")
    void StudentServiceImpl_checkIfStudentExistsById_doesNotThrowAnExceptionIfStudentExists() {

        int existentStudentId = 1;
        when(studentRepository.existsById(existentStudentId)).thenReturn(true);

        assertDoesNotThrow(() -> {
            studentServiceImpl.checkIfStudentExistsById(Optional.ofNullable(testStudent1));
        });
    }

    @Test
    @DisplayName("#20: Testing checkIfStudentExistsById(), should throw an exception if Student does not exist")
    void StudentServiceImpl_checkIfStudentExistsById_throwAnExceptionIfStudentDoesNotExist() {

        int nonExistentStudentId = 1;
        when(studentRepository.existsById(nonExistentStudentId)).thenReturn(false);


        assertThrows(StudentWithSuchAnIdDoesNotExistException.class, () -> {
            studentServiceImpl.checkIfStudentExistsById(Optional.ofNullable(testStudent1));
        });
    }

    @Test
    @DisplayName("#21: Testing findStudentById(), should not throw an exception if Student exists")
    void StudentServiceImpl_findStudentById_positiveTestDoesNotThrowAnExceptionIfStudentExist() {

        int existingStudentId = 1;
        when(studentRepository.findById(existingStudentId)).thenReturn(Optional.ofNullable(testStudent1));

        assertDoesNotThrow(() -> {
            studentServiceImpl.findStudentById(testStudent1.getId());
        });
    }

    @Test
    @DisplayName("#22: Testing findStudentById(), throws an exception if Student does not exist")
    void StudentServiceImpl_findStudentById_throwsAnExceptionIfStudentDoesNotExist() {

        int existingStudentId = 1;
        when(studentRepository.findById(existingStudentId)).thenReturn(Optional.ofNullable(null));

        assertThrows(StudentWithSuchAnIdDoesNotExistException.class, () -> {
            studentServiceImpl.findStudentById(testStudent1.getId());
        });
    }

    @Test
    @DisplayName("#23: Testing findAllStudentsButReturnAsStudentDtoList(), should not throw an exception if Student table is not empty")
    void StudentServiceImpl_findAllStudentsButReturnAsStudentDtoList_doesNotThrowsAnExceptionIfStudentTableIsNotEmpty() {

        List<Student> studentList = new ArrayList<>();

        studentList.add(testStudent1);

        when(studentRepository.findAll()).thenReturn(studentList);

        assertDoesNotThrow(() -> {
            studentServiceImpl.findAllStudentsButReturnAsStudentDtoList();
        });
    }

    @Test
    @DisplayName("#24: Testing findAllStudentsButReturnAsStudentDtoList(), throws an exception if Student table is empty")
    void StudentServiceImpl_findAllStudentsButReturnAsStudentDtoList_throwsAnExceptionIfStudentTableIsEmpty() {

        when(studentRepository.findAll()).thenThrow(StudentTableIsEmptyException.class);

        assertThrows(StudentTableIsEmptyException.class, () -> {
            studentServiceImpl.findAllStudentsButReturnAsStudentDtoList();
        });
    }

    @Test
    @DisplayName("#25: Testing findAllStudents(), should not throw an exception if Student table is not empty")
    void StudentServiceImpl_findAllStudents_doesNotThrowsAnExceptionIfStudentTableIsNotEmpty() {

        List<Student> studentList = new ArrayList<>();

        studentList.add(testStudent1);

        when(studentRepository.findAll()).thenReturn(studentList);

        assertDoesNotThrow(() -> {
            studentServiceImpl.findAllStudents();
        });
    }

    @Test
    @DisplayName("#26: Testing findAllStudents(), throws an exception if Student table is empty")
    void StudentServiceImpl_findAllStudents_throwsAnExceptionIfStudentTableIsEmpty() {

        when(studentRepository.findAll()).thenThrow(StudentTableIsEmptyException.class);

        assertThrows(StudentTableIsEmptyException.class, () -> {
            studentServiceImpl.findAllStudents();
        });
    }

    @Test
    @DisplayName("#26: Testing deleteStudentById(), should not throw an exception if Student exist")
    void StudentServiceImpl_deleteStudentById_doesNotThrowsAnExceptionIfStudentExist() {

        int idOfStudentThatExists = 1;

        when(studentRepository.existsById(idOfStudentThatExists)).thenReturn(true);

        assertDoesNotThrow(() -> {
            studentServiceImpl.deleteStudentById(idOfStudentThatExists);
        });

        verify(studentRepository, times(1)).deleteById(idOfStudentThatExists);
    }

    @Test
    @DisplayName("#27: Testing deleteStudentById(), should throw a StudentWithSuchAnIdDoesNotExistException exception if Student does not exist")
    void StudentServiceImpl_deleteStudentById_throwsAnExceptionIfStudentDoesNotExist() {

        int idOfStudentThatDoesNotExists = 2;

        when(studentRepository.existsById(idOfStudentThatDoesNotExists)).thenReturn(false);

        assertThrows(StudentWithSuchAnIdDoesNotExistException.class, () -> {
            studentServiceImpl.deleteStudentById(idOfStudentThatDoesNotExists);
        });

        verify(studentRepository, never()).deleteById(idOfStudentThatDoesNotExists);
    }
}