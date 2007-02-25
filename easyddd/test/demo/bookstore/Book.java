package demo.bookstore;

import javax.persistence.Entity;
import javax.persistence.Id;

import ddd.model.GeneralFeature;

@Entity
public class Book {

	@Id
	@GeneralFeature.Info(displayName=" È√˚")
	private	String	name;
	
	private	String	author;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
}
