package demo.bookstore;

import java.util.List;

import ddd.Service;

public class BookStore implements Service{

	public List<Book> list(){
		throw new UnsupportedOperationException();
	}

	public void add(Book book){
		throw new UnsupportedOperationException();
	}

}
