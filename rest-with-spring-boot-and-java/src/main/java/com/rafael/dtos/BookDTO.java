package com.rafael.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.rafael.model.Book;

@JsonPropertyOrder({ "id", "author", "title", "price", "lauchDate" }) // Selecionar a ordem da resposta do json
public class BookDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	@JsonProperty("author")
	private String author;

	private Date lauchDate;

	private Double price;

	private String title;

	public BookDTO(Book book) {
		super();
		this.id = book.getId();
		this.author = book.getAuthor();
		this.lauchDate = book.getLauchDate();
		this.price = book.getPrice();
		this.title = book.getTitle();
	}

	public BookDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getLauchDate() {
		return lauchDate;
	}

	public void setLauchDate(Date lauchDate) {
		this.lauchDate = lauchDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDTO other = (BookDTO) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "BookDTO [id=" + id + ", author=" + author + ", lauchDate=" + lauchDate + ", price=" + price + ", title="
				+ title + "]";
	}

}
