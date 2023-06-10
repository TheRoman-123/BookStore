package com.bookstore.controller;

import com.bookstore.entity.Book;
import com.bookstore.service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping("/add")
    public Book addBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PostMapping("/add/image")
    public ResponseEntity<String> uploadImage(
            @RequestParam("id") int id,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            Book book = bookService.findOne(id);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> it = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(it.next());
            String fileName = id + ".png";

            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("src/main/resources/static/image/book/" + fileName));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity<>("Upload Success!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Upload Failed!", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/bookList")
    public List<Book> getBookList() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable("id") int id) {
        Book book = bookService.findOne(id);
        return book;
    }

    @PostMapping("/update")
    public Book updateBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    //    @RequestMapping(value = "/update/image", method = RequestMethod.POST)
    @PutMapping("/update/image")
    public ResponseEntity<String> updateImage(
            @RequestParam("id") int id,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        try {
            Book book = bookService.findOne(id);
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Iterator<String> it = multipartRequest.getFileNames();
            MultipartFile multipartFile = multipartRequest.getFile(it.next());
            String fileName = id + ".png";

            Files.delete(Paths.get("src/main/resources/static/image/book/" + fileName));

            byte[] bytes = multipartFile.getBytes();
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("src/main/resources/static/image/book/" + fileName));
            stream.write(bytes);
            stream.close();
            return new ResponseEntity<>("Upload Success!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Upload Failed!", HttpStatus.BAD_REQUEST);
        }
    }

    //    @PostMapping("/remove")
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeBook(@RequestBody String id) throws IOException {
        bookService.removeOne(Integer.parseInt(id));
        String fileName = id + ".png";
        Files.delete(Paths.get("src/main/resources/static/image/book/" + fileName));
        return new ResponseEntity<>("Remove Success!", HttpStatus.OK);
    }

    @PostMapping("/search")
    public List<Book> searchByTitle(@RequestBody String keyword) {
        List<Book> bookList = bookService.searchByTitle(keyword);
        return bookList;
    }
}
