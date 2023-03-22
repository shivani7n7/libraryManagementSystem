package book;

public class BookCopy {
    private final BookDetail bookDetail;
    private final int bookCopyId;

    public BookCopy(BookDetail bookDetail, int bookCopyId){
        this.bookDetail = bookDetail;
        this.bookCopyId = bookCopyId;
    }

}
