package com.bookstore.controller;

import com.bookstore.dto.BookDto;
import com.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BookDto> getBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ) {
        return bookService.getBooks(page, size, sort);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Long createBook(
            @RequestBody BookDto bookDto
    ) {
        return bookService.createBook(bookDto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void updateBook(
            @PathVariable("id") Long bookId,
            @RequestBody BookDto bookDto
    ) {
        bookService.updateBook(bookId, bookDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteBook(
            @PathVariable("id") Long bookId
    ) {
        bookService.deleteBook(bookId);
    }

//    @PostMapping("/add/image")
//    public ResponseEntity<String> uploadImage(
//            @RequestParam("id") int id,
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) {
//        try {
//            Book book = bookService.findOne(id);
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            Iterator<String> it = multipartRequest.getFileNames();
//            MultipartFile multipartFile = multipartRequest.getFile(it.next());
//            String fileName = id + ".png";
//
//            byte[] bytes = multipartFile.getBytes();
//            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("src/main/resources/static/image/book/" + fileName));
//            stream.write(bytes);
//            stream.close();
//            return new ResponseEntity<>("Upload Success!", HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Upload Failed!", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//
//    @GetMapping("/{id}")
//    public Book getBook(@PathVariable("id") int id) {
//        Book book = bookService.findOne(id);
//        return book;
//    }
//
//    @PostMapping("/update")
//    public Book updateBook(@RequestBody Book book) {
//        return bookService.save(book);
//    }
//
//    //    @RequestMapping(value = "/update/image", method = RequestMethod.POST)
//    @PutMapping("/update/image")
//    public ResponseEntity<String> updateImage(
//            @RequestParam("id") int id,
//            HttpServletRequest request,
//            HttpServletResponse response
//    ) {
//        try {
//            Book book = bookService.findOne(id);
//            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
//            Iterator<String> it = multipartRequest.getFileNames();
//            MultipartFile multipartFile = multipartRequest.getFile(it.next());
//            String fileName = id + ".png";
//
//            Files.delete(Paths.get("src/main/resources/static/image/book/" + fileName));
//
//            byte[] bytes = multipartFile.getBytes();
//            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("src/main/resources/static/image/book/" + fileName));
//            stream.write(bytes);
//            stream.close();
//            return new ResponseEntity<>("Upload Success!", HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new ResponseEntity<>("Upload Failed!", HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    //    @PostMapping("/remove")
//    @DeleteMapping("/remove")
//    public ResponseEntity<String> removeBook(@RequestBody String id) throws IOException {
//        bookService.removeOne(Integer.parseInt(id));
//        String fileName = id + ".png";
//        Files.delete(Paths.get("src/main/resources/static/image/book/" + fileName));
//        return new ResponseEntity<>("Remove Success!", HttpStatus.OK);
//    }
//
//    @PostMapping("/search")
//    public List<Book> searchByTitle(@RequestBody String keyword) {
//        List<Book> bookList = bookService.searchByTitle(keyword);
//        return bookList;
//    }
}
