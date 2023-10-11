package library.vo;

public class BookVo {
	
	// 멤버변수
		private String title;
		private String author;
		private String publisher;
		private String genre;
		private String location;
		
		// 생성자
		public BookVo() {
			super();
		}
		
		public BookVo(String title, String author, String publisher, String genre, String location) {
			super();
			this.title = title;
			this.author = author;
			this.publisher = publisher;
			this.genre = genre;
			this.location = location;
		}

		// getter setter
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
		public String getPublisher() {
			return publisher;
		}
		public void setPublisher(String publisher) {
			this.publisher = publisher;
		}
		public String getGenre() {
			return genre;
		}
		public void setGenre(String genre) {
			this.genre = genre;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
		
		
		// toString
		@Override
		public String toString() {
			return "BookVo [title=" + title + ", author=" + author + ", publisher=" + publisher + ", genre=" + genre
					+ ", location=" + location + "]";
		}
	
}
