package controllers;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.fasterxml.jackson.databind.JsonNode;

import models.Book;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.BodyParser;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Results;

import play.api.libs.ws.Response;
import play.api.libs.ws.WS;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;

public class BookController {
	
	 public static Result allBooks(){
	        List<Book> books = new Model.Finder<String , Book>(String.class, Book.class).all();
	        return Results.ok(Json.toJson(books));
	    }
	 
	 @BodyParser.Of(BodyParser.Json.class)
     public static Result submitBook(){
        JsonNode jsonNode = Controller.request().body().asJson();
        String url = jsonNode.findPath("url").asText();
        String title = jsonNode.findPath("title").asText();
        String author = jsonNode.findPath("author").asText();
        JsonNode response = fetchInformation(url);
        Book book = null;
        if(response == null){
        	book = new Book(url,title,author);
        	book.save();
        }
        return Results.created();
    }
	 
	 
	 public static Result getBook(String bookId){
		 Book book = new Model.Finder<String, Book>(String.class, Book.class).byId(bookId);
	        if(book == null){
	            return Results.notFound("No book found with bookId " + bookId);
	        }
	        return Results.ok(Json.toJson(book));
	    }
	 
	    private static JsonNode fetchInformation(String url){
	        String restServiceUrl = "http://gooseextractor-t20.rhcloud.com/api/v1/extract?url="+url;
	        Future<Response> future = WS.url(restServiceUrl).get();
	        try {
	            Response result = Await.result(future, Duration.apply(30, TimeUnit.SECONDS));
	            JsonNode jsonNode = Json.parse(result.json().toString());
	            return jsonNode;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	 
	    }
}
