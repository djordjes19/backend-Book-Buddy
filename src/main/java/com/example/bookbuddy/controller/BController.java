package com.example.bookbuddy.controller;


import java.util.List;

import com.example.bookbuddy.model.Book;
import com.example.bookbuddy.model.Review;
import com.example.bookbuddy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookbuddy.dto.ReviewDTO;
import com.example.bookbuddy.dto.UserDTO;
import com.example.bookbuddy.service.BBService;



@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/")
public class BController {

    @Autowired
    BBService service;

    @GetMapping("allUsers")
    public ResponseEntity<List<User>> getallUsers() {
        return service.getallUsers();
    }

    @PostMapping("register")
    public ResponseEntity<Boolean> register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("login")
    public ResponseEntity<UserDTO> login(@RequestParam(name = "username") String username,
                                         @RequestParam(name = "password") String password) {
        return service.login(username, password);
    }


    @PostMapping("deleteUser")
    public ResponseEntity<Boolean> deleteUser(@RequestParam(name = "username") String username) {
        return service.deleteUser(username);
    }

    @GetMapping("allBooks")
    public ResponseEntity<List<Book>> allBooks(){
        return service.allBooks();
    }

    @PostMapping("addBook")
    public ResponseEntity<Book> addBook(@RequestParam(name="title")String title, @RequestParam(name="author")String author,
                                        @RequestParam(name="publishedYear")int publishedYear, @RequestParam(name="rating")double rating,@RequestParam(name="photo")String photo){
        return service.addBook(title, author, publishedYear, rating,photo);
    }

    @GetMapping("getBookByTitle")
    public ResponseEntity<Book> getBookByTitle(@RequestParam(name="title")String title){
        return service.getBookByTitle(title);
    }

    @PostMapping("deleteBook")
    public ResponseEntity<Boolean> deleteBook(@RequestParam(name="title")String title){
        return service.deleteBook(title);
    }

    @PostMapping("updateBook")
    public ResponseEntity<Boolean> updateBook(@RequestParam(name="title")String title, @RequestParam(name="author")String author,
                                              @RequestParam(name="publishedYear")int publishedYear, @RequestParam(name="rating")double rating,@RequestParam(name="photo")String photo){
        return service.updateBook(title, author, publishedYear, rating,photo);

    }

    @GetMapping("booksByAuthor")
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return service.getBooksByAuthor(author);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
        return service.getBookById(id);
    }

    @PostMapping("addReview")
    public ResponseEntity<Review> addReview(@RequestBody  ReviewDTO reviewDto) {
        return service.addReview(reviewDto);
    }

    @GetMapping("review/{reviewId}")
    public ResponseEntity<User> getUserByReviewId(@PathVariable int reviewId) {
        return service.getUserByReviewId(reviewId);
    }

    @PostMapping("reviewsFromBook")
    public List<Review> reviewsFromBook(@RequestBody Book book){
        return service.reviewsFromBook(book);
    }

//    @GetMapping("/image/{id}")
//    public ResponseEntity<String> getBookImage(@PathVariable Integer id) {
//        // Pretpostavljamo da Book entitet sadrži URL slike u polju 'image'
//        Book book = service.getBookById(id);
//
//        if (book == null || book.getPhoto() == null) {
//            return ResponseEntity.notFound().build();
//        }
//
//        return ResponseEntity.ok(book.getPhoto());  // Vraćamo URL slike
//    }



}

