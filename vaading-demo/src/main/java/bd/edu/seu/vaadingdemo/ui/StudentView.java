package bd.edu.seu.vaadingdemo.ui;import bd.edu.seu.vaadingdemo.model.Student;import bd.edu.seu.vaadingdemo.repository.StudentRepository;import com.vaadin.flow.component.button.Button;import com.vaadin.flow.component.datepicker.DatePicker;import com.vaadin.flow.component.formlayout.FormLayout;import com.vaadin.flow.component.grid.Grid;import com.vaadin.flow.component.notification.Notification;import com.vaadin.flow.component.orderedlayout.HorizontalLayout;import com.vaadin.flow.component.orderedlayout.VerticalLayout;import com.vaadin.flow.component.textfield.TextField;import com.vaadin.flow.router.Route;import java.awt.*;import java.time.LocalDate;@Route(value = "student")public class StudentView extends VerticalLayout {    private StudentRepository studentRepository;    public StudentView(StudentRepository studentRepository){        this.studentRepository = studentRepository;        TextField idField = new TextField("Student id","Enter student id");        TextField nameField = new TextField("Student Name","Enter student Name");        TextField cgpaField = new TextField("Student cgpa","Enter student cgpa");        DatePicker DOBField = new DatePicker("Date Of Birth");        Button submit = new Button("Submit");        Button update = new Button("Update");        FormLayout formLayout = new FormLayout();        formLayout.add(idField,nameField,cgpaField,DOBField,submit,update);        Grid<Student> studentGrid = new Grid<>(Student.class);        studentGrid.setItems(studentRepository.findAll());        submit.addClickListener(buttonClickEvent -> {            long id = Long.parseLong(idField.getValue());            String name = nameField.getValue();            double cgpa = Double.parseDouble(cgpaField.getValue());            LocalDate DOB =  DOBField.getValue();            Student student = new Student(id,name,cgpa,DOB);            Student save = studentRepository.save(student);            studentGrid.setItems(studentRepository.findAll());            Notification.show(save.getName()+" saved..");        });        studentGrid.addItemClickListener(studentItemClickEvent -> {            Student item = studentItemClickEvent.getItem();            idField.setValue(item.getId()+"");            nameField.setValue(item.getName());            cgpaField.setValue(item.getCgpa()+"");            DOBField.setValue(item.getLocalDate());        });        add(formLayout,studentGrid);    }}