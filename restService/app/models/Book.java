package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import play.db.ebean.Model;

@Entity
public class Book extends Model {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2658435387158619753L;

	@Id 
	@GeneratedValue
	private Long id;
	
	private String title;
	
	private String author;
	
	private String url;
	
    public Book(){
    	
    }
    
    public Book(String url, String title) {
        this.url = url;
        this.title = title;
    }
	 
    public Book(String url, String title, String author) {
        this.url = url;
        this.title = title;
        this.author = author;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
