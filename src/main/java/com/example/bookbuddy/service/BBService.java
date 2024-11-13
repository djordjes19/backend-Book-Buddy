package com.example.bookbuddy.service;

import java.util.List;
import java.util.Random;

import com.example.bookbuddy.dto.ReviewDTO;
import com.example.bookbuddy.dto.UserDTO;
import com.example.bookbuddy.model.Book;
import com.example.bookbuddy.model.Review;
import com.example.bookbuddy.model.User;
import com.example.bookbuddy.model.UserType;
import com.example.bookbuddy.repositories.BookRepository;
import com.example.bookbuddy.repositories.ReviewRepository;
import com.example.bookbuddy.repositories.UserRepository;
import com.example.bookbuddy.repositories.UserTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@Service
public class BBService {

	@Autowired
	BookRepository br;

	@Autowired
	UserRepository ur;

	@Autowired
	ReviewRepository rr;

	@Autowired
	UserTypeRepository utr;

	// svi korisnici

	public ResponseEntity<List<User>> getallUsers() {
		return new ResponseEntity<List<User>>(ur.findAll(), HttpStatus.OK);
	}

	// registracija
	public ResponseEntity<Boolean> register(@RequestBody User user) {
		if (user.getUsername() != null && ur.findByUsername(user.getUsername()) != null) {
			System.out.println("Korisnik već postoji: " + user.getUsername());
			return new ResponseEntity<Boolean>(false, HttpStatus.OK);
		}

		System.out.println("Primljen korisnik: " + user);

		User u = new User();
		u.setName(user.getName());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());
		u.setEmail(user.getEmail());
		List<UserType> utl = utr.findAll();
		Random random = new Random();
		UserType ut = utl.get(random.nextInt(utl.size()));
//		UserType ut = (UserType)utr.findById(user.getUserType().getIduserType());
		u.setUserType(ut);

		System.out.println("Čuvanje korisnika u bazi");
		ur.save(u);

		System.out.println("Korisnik uspešno registrovan");
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	// logovanje
	public ResponseEntity<UserDTO> login(@RequestParam(name = "username") String username,
										 @RequestParam(name = "password") String password) {

		UserDTO userdto = new UserDTO();

		if (ur.findByUsername(username) != null) {
			User user = (User) ur.findByUsername(username);
			if (user.getPassword().equals(password)) {
				userdto.setUsername(username);
				userdto.setName(user.getName());
				userdto.setEmail(user.getEmail());
				userdto.setIdUser(user.getIdUser());
				userdto.setIdUserType(user.getUserType().getIduserType());
			}
		}
		return new ResponseEntity<UserDTO>(userdto, HttpStatus.OK);
	}

	// brisanje korisnika
	public ResponseEntity<Boolean> deleteUser(@RequestParam(name = "username") String username) {
		User u = (User) ur.findByUsername(username);
		ur.delete(u);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	// sve knjige
	public ResponseEntity<List<Book>> allBooks() {
		return new ResponseEntity<List<Book>>(br.findAll(), HttpStatus.OK);
	}

	// dodavanje knjige
	public ResponseEntity<Book> addBook(@RequestParam(name = "title") String title,
			@RequestParam(name = "author") String author, @RequestParam(name = "publishedYear") int publishedYear,
			@RequestParam(name = "rating") double rating, @RequestParam(name = "photo") String photo) {
		Book book = new Book();
		book.setTitle(title);
		book.setPhoto(photo);
		book.setAuthor(author);
		book.setPublishedYear(publishedYear);
		book.setRating(rating);
		br.save(book);

		return new ResponseEntity<Book>(book, HttpStatus.OK);
	}

	// vrati knjigu na osnovu naslova
	public ResponseEntity<Book> getBookByTitle(@RequestParam(name = "title") String title) {
		return new ResponseEntity<Book>(br.findByTitle(title), HttpStatus.OK);
	}

	// brisanje knjige
	public ResponseEntity<Boolean> deleteBook(@RequestParam(name = "title") String title) {
		Book book = br.findByTitle(title);
		List<Review> lr = book.getReviews();
		for (Review r : lr) {
			rr.delete(r);
		}
		br.delete(book);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}

	public ResponseEntity<Boolean> updateBook(@RequestParam(name = "title") String title,
			@RequestParam(name = "author") String author, @RequestParam(name = "publishedYear") int publishedYear,
			@RequestParam(name = "rating") double rating, @RequestParam(name = "photo") String photo) {
		Book b = br.findByTitle(title);

		boolean updated = false;

		// author
		if (!author.isEmpty() && !author.equals(b.getAuthor())) {
			b.setAuthor(author);
			updated = true;

		}

		// photo
		if (!photo.isEmpty() && !photo.equals(b.getPhoto())) {
			b.setPhoto(photo);
			updated = true;
		}

		// publishedYear
		if (publishedYear > 0 && publishedYear != b.getPublishedYear()) {
			b.setPublishedYear(publishedYear);
			updated = true;

		}

		if (rating > 0 && rating != b.getRating()) {
			b.setRating(rating);
			updated = true;

		}

		if (updated) {
			br.save(b);

			return new ResponseEntity<Boolean>(true, HttpStatus.OK);
		}
		return new ResponseEntity<Boolean>(false, HttpStatus.OK);

	}

	// dobavljanje knjiga po autoru
	public List<Book> getBooksByAuthor(@RequestParam String author) {
		return br.findByAuthor(author);
	}

	// book by id
	public ResponseEntity<Book> getBookById(@PathVariable("id") int id) {
		return new ResponseEntity<Book>(br.findById(id).orElse(null), HttpStatus.OK);

	}

	// dodavanje reviewa
	public ResponseEntity<Review> addReview(@RequestBody ReviewDTO reviewDto) {
		Book book = br.findById(reviewDto.getIdBook()).orElse(null);
		User user = ur.findById(reviewDto.getIdUser()).orElse(null);

		Review review = new Review();
		review.setText(reviewDto.getText());
		review.setDaterev(reviewDto.getDaterev());
		review.setBook(book);
		review.setUser(user);

		Review savedReview = rr.save(review);
		return ResponseEntity.ok(savedReview);
	}

	// user by rev
	public ResponseEntity<User> getUserByReviewId(int reviewId) {
		Review review = rr.findById(reviewId);
		if (review != null) {
			User user = review.getUser();
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public List<Review> reviewsFromBook(@RequestBody Book book) {
		Book foundBook = br.findBookByIdBook(book.getIdBook());
		return foundBook.getReviews();

	}

//	public Book getBookById(Integer id) {
//		// Pronaći knjigu po ID-ju
//		return br.findById(id).orElse(null);  // Vraća knjigu ako postoji, inače null
//	}
}
