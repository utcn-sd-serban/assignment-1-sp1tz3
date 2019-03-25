package ro.utcn.sp1tz3.Assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.utcn.sp1tz3.Assignment1.entity.Answer;
import ro.utcn.sp1tz3.Assignment1.entity.Question;
import ro.utcn.sp1tz3.Assignment1.entity.Tag;
import ro.utcn.sp1tz3.Assignment1.entity.User;
import ro.utcn.sp1tz3.Assignment1.exception.UserNotFoundException;
import ro.utcn.sp1tz3.Assignment1.repository.TagRepository;
import ro.utcn.sp1tz3.Assignment1.service.AnswerManagementsService;
import ro.utcn.sp1tz3.Assignment1.service.QuestionManagementService;
import ro.utcn.sp1tz3.Assignment1.service.TagManagementService;
import ro.utcn.sp1tz3.Assignment1.service.UserManagementService;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ConsoleController implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final UserManagementService userManagementService;
    private final QuestionManagementService questionManagementService;
    private final AnswerManagementsService answerManagementsService;
    private final TagManagementService tagManagementService;
    private boolean log = false;
    private User currentUser;

    @Override
    public void run(String... args){
        System.out.println("welcome to StackOverflow");
        boolean done = false;
        while(!done){
            System.out.println("Enter a command: ");
            String command = scanner.next().trim();
            try{
                done = handleCommand(command);
            } catch (UserNotFoundException userNotFoundException){
                System.out.println("The user with the given ID was not found");
            }
        }
    }

    private boolean handleCommand(String command){
        switch(command){
            case "register":
                handleRegister();
                return false;
            case "login":
                handleLogin();
                return false;
            case "logoff":
                handleLogoff();
                return false;
            case "list":
                handleList();
                return false;
            case "listtags":
                handleListTags();
                return false;
            case "listusers":
                handleListUsers();
                return false;
            case "ask":
                handleAddQuestion();
                return false;
            case "addtag":
                handleAddTag();
                return false;
            case "filter":
                handleFilterQuestions();
                return false;
            case "answer":
                handleAddAnswer();
                return false;
            case "remove":
                handleRemove();
                return false;
            case "exit":
                return true;
            default:
                System.out.println("Unknown command. Try again");
                return false;
        }
    }

    private void handleRegister(){
        System.out.println("Username");
        String username = scanner.next().trim();
        System.out.println("Password");
        String password = scanner.next().trim();
        List<User> users = userManagementService.listUsers();
        boolean taken = false;
        for(User s: users){
            if(s.getUsername().equals(username)) {
                System.out.println("Username already taken.");
                taken = true;
            }
        }
        if(!taken) {
            userManagementService.addUser(username, password, "regular");
            users.forEach(s -> {
                if (s.getUsername().equals(username) && s.getPassword().equals(password))
                    currentUser = s;
            });
            log = true;
            System.out.println("Registered as user: " + currentUser.getUsername());
        }
    }

    private void handleLogin(){
        if(!log) {
            System.out.println("Username");
            String username = scanner.next().trim();
            System.out.println("Password");
            String password = scanner.next().trim();
            List<User> users = userManagementService.listUsers();
            for(User i: users) {
                if (i.getUsername().equals(username) && i.getPassword().equals(password)) {
                    log = true;
                    currentUser = i;
                    System.out.println("Logged in as user: " + i.getUsername());
                    break;
                }
            }
            if(!log)
                System.out.println("User not found");
        }
        else {
            System.out.println("Already logged in");
        }
    }

    private void handleList(){
        if(log) {
            questionManagementService.listQuestions().forEach(s -> {
                System.out.println(s.toString());
                s.getTags().forEach(x->System.out.println(x.toString()));
                answerManagementsService.listAnswers().forEach(a-> {
                    if(a.getQuestionid().equals(s.getQuestionid()))
                        System.out.println("    " + a.toString());
                });
            });
        }
        else System.out.println("You must be logged in.");
    }

    private void handleAddQuestion(){
        if(log) {
            System.out.println("title");
            scanner.nextLine();
            String title = scanner.nextLine();
            System.out.println("text");
            String text = scanner.nextLine();
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            System.out.println("Add Tag(s)");
            ArrayList<Tag> setTag = new ArrayList<>();
            List<Tag> tags = tagManagementService.listTags();
            String exit = "start";
            while(!exit.equals("exit")) {
                boolean exists = false;
                String tag = scanner.next();
                exit = tag;
                for(Tag t: tags){
                    if(t.getTitle().equals(tag)){
                        setTag.add(t);
                        exists = true;
                    }
                }
                if(!exists && !tag.equals("exit"))
                    setTag.add(new Tag(tag));
            }
            Question question = questionManagementService.addQuestion(currentUser.getUserid(), title, text, ts, setTag);
            System.out.println("Added question " + question.toString());
        } else {
            System.out.println("You must be logged in.");
        }
    }

    private void handleAddAnswer(){
        if(log) {
            System.out.println("Question id");
            int questionid = scanner.nextInt();
            System.out.println("text");
            scanner.nextLine();
            String text = scanner.nextLine();
            Date date = new Date();
            Timestamp ts = new Timestamp(date.getTime());
            Answer answer = answerManagementsService.addAnswer(questionid, currentUser.getUserid(), text, ts);
            System.out.println("Answered question " + questionid + " with " + answer + ".");
        } else {
            System.out.println("You must be logged in.");
        }
    }


    private void handleRemove(){
        if(log){
            System.out.println("Question id");
            int id = scanner.nextInt();
            if(currentUser.getUserid().equals(questionManagementService.get(id).getUserid())){
                questionManagementService.removeQuestion(id);
                System.out.println("Removed question " + id);
            } else {
                System.out.println("You can't delete other peoples questions.");
            }
        }
    }

    private void handleLogoff(){
        log = false;
        System.out.println("Logged off.");
    }

    private void handleListUsers(){
        userManagementService.listUsers().forEach(s -> System.out.println(s.toString()));
    }

    private void handleFilterQuestions(){
        if(log) {
            System.out.println("Text to filter");
            String filter = scanner.next().trim();
            questionManagementService.listQuestions().forEach(s -> {
                if (s.getTitle().toLowerCase().contains(filter))
                    System.out.println(s.toString());
            });
        } else {
            System.out.println("You must be logged in.");
        }
    }

    private void handleListTags(){
        if(log) {
            tagManagementService.listTags().forEach(s -> System.out.println(s.toString()));
        } else {
            System.out.println("You must be logged in.");
        }
    }

    private void handleAddTag(){
        if(log){
            tagManagementService.addTag(scanner.next().trim());
        }
        else System.out.println("You must be logged in.");
    }
}
