// BookAidl.aidl
package com.fulin.bookaidl;
import com.fulin.bookaidl.Book;

// Declare any non-default types here with import statements

interface BookAidl {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void testService();

    List<Book> getBooks();

    Book addBook(in Book book);
}