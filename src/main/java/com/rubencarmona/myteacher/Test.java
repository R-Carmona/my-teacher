package com.rubencarmona.myteacher;


import java.util.Date;
import java.util.Optional;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.rubencarmona.myteacher.config.Config;
import com.rubencarmona.myteacher.domain.Teacher;
import com.rubencarmona.myteacher.domain.User;
import com.rubencarmona.myteacher.service.MyTeacherDAOService;

@SpringBootApplication
public class Test {

  public static void main(String[] args) {

    @SuppressWarnings("resource")
    AnnotationConfigApplicationContext appContex =
        new AnnotationConfigApplicationContext(Config.class);

    MyTeacherDAOService myTeacherService = appContex.getBean(MyTeacherDAOService.class);


    // System.out.println(myTeacherService.findAllUser());

    // myTeacherService.createUser(new User("Isabel","Amor","Calle Flor de
    // Lí","9632587453T","florde@loto.es","murcia",89632578,"ES996321136981234567891"));

    System.out.println("Antes de Sentancia");
    // myTeacherService.updateAuthoritiesUsers(new AuthoritiesUsers(13,9,1));

    // System.out.println(myTeacherService.findByIdUser(7));
    // myTeacherService.enableUser(7);

    // myTeacherService.saveTeacher(new Teacher("Rubén","Carmona"));
    // myTeacherService.saveTeacher(new Teacher("Pedro Jesús","Tomás"));
    // myTeacherService.saveTeacher(new Teacher("Susana","García"));
    // myTeacherService.disabledTeacher(10);

    Optional<User> userId = myTeacherService.findByIdUser(2);
    User user = userId.get();
    Optional<Teacher> teacherId = myTeacherService.findByIdTeacher(0);
    Teacher teacher = teacherId.get();

    // Lesson lesson = new Lesson(teacher, user, new Date());
    Date date = new Date();

    System.out.println(myTeacherService.findByUserNotLikeUserStudyPlan(user));


    // myTeacherService.saveLesson(lesson);
    // lesson = new Lesson(teacher, user, new Date());
    // System.out.println(lesson);
    // myTeacherService.saveLesson(lesson);
    // lesson = new Lesson(teacher, user, new Date());
    // System.out.println(lesson);
    // myTeacherService.saveLesson(lesson);
    // lesson = new Lesson(teacher, user, new Date());
    // System.out.println(lesson);
    // myTeacherService.saveLesson(lesson);
    // lesson = new Lesson(teacher, user, new Date());
    // System.out.println(lesson);
    // myTeacherService.saveLesson(lesson);
    // lesson = new Lesson(teacher, user, new Date());
    // System.out.println(lesson);
    // myTeacherService.saveLesson(lesson);

    // myTeacherService.deleteByIdLessoon(33);

    // System.out.println(myTeacherService.findAllLessonUser(1));

    // StudyPlan studyPlan = new StudyPlan("36€ Mes x 8",8,new BigDecimal("36.00"));
    // StudyPlan studyPlan2 = new StudyPlan("48€ Mes x 12",12,new BigDecimal("48.00"));
    // StudyPlan studyPlan3 = new StudyPlan("78€ Mes x 24",24,new BigDecimal("78.00"));

    // myTeacherService.saveStudyPlan(studyPlan3);

    // myTeacherService.deleteByIdStudyPlan(37);

    // myTeacherService.deleteUserAuthorityID(11);


    // Optional<User> userId = myTeacherService.findByIdUser(9);
    // User user = userId.get();
    //
    // Optional<StudyPlan> studyPlanID = myTeacherService.findByIdStudyPlan(36);
    // StudyPlan studyPlan = studyPlanID.get();
    //
    // UserStudyPlan userStudyPlan = new UserStudyPlan(studyPlan, user);
    //
    // myTeacherService.saveUserStudyPlan(userStudyPlan);



    System.out.println("Después de Sentancia");



  }

}
