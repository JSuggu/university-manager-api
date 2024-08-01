package com.example.api_university_manager.components.student;

import com.example.api_university_manager.components.course.Course;
import com.example.api_university_manager.components.course.CourseDTO;
import com.example.api_university_manager.components.course.CourseRepository;
import com.example.api_university_manager.components.degree.Degree;
import com.example.api_university_manager.components.degree.DegreeDTO;
import com.example.api_university_manager.components.degree.DegreeRepository;
import com.example.api_university_manager.components.jwt.Token;
import com.example.api_university_manager.components.student_course.StudentCourse;
import com.example.api_university_manager.util.OtherUtilities;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final DegreeRepository degreeRepository;
    private final CourseRepository courseRepository;
    private final PasswordEncoder passwordEncoder;
    private final OtherUtilities otherUtilities;
    private final ModelMapper modelMapper;


    public StudentService(StudentRepository studentRepository, DegreeRepository degreeRepository, CourseRepository courseRepository, PasswordEncoder passwordEncoder, OtherUtilities otherUtilities, ModelMapper modelMapper){
        this.studentRepository = studentRepository;
        this.degreeRepository = degreeRepository;
        this.courseRepository = courseRepository;
        this.passwordEncoder = passwordEncoder;
        this.otherUtilities = otherUtilities;
        this.modelMapper = modelMapper;
    }

    public List<StudentDTO> getAllStudents(){
        List<Student> allStudents = studentRepository.findAll();

        return allStudents
                .stream()
                .map(student -> {
                    Set<CourseDTO> mappedCourses = student.getCourseSet()
                            .stream()
                            .map(studentCourse -> modelMapper.map(studentCourse.getCourse(), CourseDTO.class))
                            .collect(Collectors.toSet());

                    StudentDTO mappedStudent = modelMapper.map(student, StudentDTO.class);
                    mappedStudent.setCourseSet(mappedCourses);

                    return mappedStudent;
                })
                .toList();
    }

    @Transactional
    public StudentDTO saveStudent(StudentDTO newStudent){
        Student studentToSave = new Student(newStudent.getNames(), newStudent.getDni(), newStudent.getUsername(), newStudent.getPassword(), null, null);
        studentToSave.setPassword(passwordEncoder.encode(studentToSave.getPassword()));

        Set<Degree> degreesToRegister = new HashSet<>();

        if(newStudent.getDegreeSet() != null && !newStudent.getDegreeSet().isEmpty()){
            for(DegreeDTO degree: newStudent.getDegreeSet()){
                Degree savedDegree = null;

                if(degree.getId() != null) savedDegree = degreeRepository.findById(degree.getId()).orElseThrow(() -> new RuntimeException("Degree not found", new NotFoundException()));
                else if(degree.getName() != null) savedDegree = degreeRepository.findByName(degree.getName()).orElseThrow(() -> new RuntimeException("Degree not found", new NotFoundException()));

                if(savedDegree != null) degreesToRegister.add(modelMapper.map(savedDegree, Degree.class));
            }
        }

        Set<StudentCourse> courseToRegister = new HashSet<>();

        if(newStudent.getCourseSet() != null && !newStudent.getCourseSet().isEmpty()){
            for(CourseDTO course: newStudent.getCourseSet()){
                StudentCourse newStudentCourse;
                Course savedCourse = null;

                if(course.getId() != null) savedCourse = courseRepository.findById(course.getId()).orElseThrow(() -> new RuntimeException("Course not found", new NotFoundException()));
                else if(course.getName() != null) savedCourse = courseRepository.findByName(course.getName()).orElseThrow(() -> new RuntimeException("Course not found", new NotFoundException()));

                if(savedCourse != null){
                    newStudentCourse = new StudentCourse(studentToSave, savedCourse, false);
                    courseToRegister.add(newStudentCourse);
                }
            }
        }

        studentToSave.setDegreeSet(degreesToRegister);
        studentToSave.setCourseSet(courseToRegister);

        Student savedStudent = studentRepository.save(studentToSave);

        Set<CourseDTO> mappedCourses = savedStudent.getCourseSet()
                .stream()
                .map(studentCourse -> modelMapper.map(studentCourse.getCourse(), CourseDTO.class))
                .collect(Collectors.toSet());

        StudentDTO finalStudent = modelMapper.map(savedStudent, StudentDTO.class);

        finalStudent.setCourseSet(mappedCourses);

        return finalStudent;
    }

    public Token login(StudentDTO requestData) {
        return otherUtilities.loginProcess(modelMapper.map(requestData, Student.class));
    }

    @Transactional
    public StudentDTO updateStudent(Long id, StudentDTO updatedStudent) {
        Student studentToUpdate = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found", new NotFoundException()));

        studentToUpdate.setNames(updatedStudent.getNames());
        studentToUpdate.setDni(updatedStudent.getDni());
        studentToUpdate.setUsername(updatedStudent.getUsername());
        studentToUpdate.setPassword(updatedStudent.getPassword());

        Student savedStudent = studentRepository.save(studentToUpdate);
        return modelMapper.map(savedStudent, StudentDTO.class);
    }

    @Transactional
    public StudentDTO updateDegreesOfStudent(Long idStudent, List<DegreeDTO> requestData){
        Student studentToUpdate = studentRepository.findById(idStudent).orElseThrow(() -> new RuntimeException("Student not found", new NotFoundException()));

        studentToUpdate.setDegreeSet(requestData
                .stream()
                .map(degreeDTO -> modelMapper.map(degreeDTO, Degree.class))
                .collect(Collectors.toSet())
        );

        Student updatedDegreeOfStudent = studentRepository.save(studentToUpdate);

        return modelMapper.map(updatedDegreeOfStudent, StudentDTO.class);
    }

    @Transactional
    public StudentDTO updateCoursesOfStudent(Long idStudent, List<CourseDTO> requestData){
        Student studentToUpdate = studentRepository.findById(idStudent).orElseThrow(() -> new RuntimeException("Student not found", new NotFoundException()));

        studentToUpdate.setCourseSet(requestData
                .stream()
                .map(courseDTO -> {
                    Course course = modelMapper.map(courseDTO, Course.class);
                    return new StudentCourse(studentToUpdate, course, false);
                })
                .collect(Collectors.toSet())
        );

        Student updatedCoursesOfStudent = studentRepository.save(studentToUpdate);

        return modelMapper.map(updatedCoursesOfStudent, StudentDTO.class);
    }

    public void deleteStudent(Long id){
        studentRepository.deleteById(id);
    }
}
