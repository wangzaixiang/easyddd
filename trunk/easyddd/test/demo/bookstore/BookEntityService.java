package demo.bookstore;

import ddd.EntityService;

public class BookEntityService implements EntityService<Book> {

	public void lendOut(Book book, String who) {
		throw new UnsupportedOperationException();
	}

	public void returnBack(Book book) {
		throw new UnsupportedOperationException();
	}

}
