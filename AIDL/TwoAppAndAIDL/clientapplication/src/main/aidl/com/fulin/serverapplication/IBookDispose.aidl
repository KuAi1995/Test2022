// IBookDispose.aidl
package com.fulin.serverapplication;
import com.fulin.serverapplication.IBook;

// Declare any non-default types here with import statements

interface IBookDispose {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void testService();

    List<Book> getBooks();

    Book addBook(in Book book);
}