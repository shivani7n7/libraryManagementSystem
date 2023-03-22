package library;

import book.BookCopy;
import user.Member;

import java.util.List;

public class Library {
    private final BookManager bookManager;
    private final MemberManager memberManager;


    public Library(BookManager bookManager, MemberManager memberManager) {
        this.bookManager = bookManager;
        this.memberManager = memberManager;
    }

    public void addBook(BookCopy bookCopy) {
        this.bookManager.addBook(bookCopy);
    }

    public List<BookCopy> getBorrowedBooks(Member member) {
        return this.memberManager.getBorrowedBooks(member);
    }

    public Member getBookBorrower(BookCopy bookCopy) {
        return this.bookManager.getBookBorrower(bookCopy);
    }

    public void submitBook(BookCopy bookCopy, Member member) {
        this.bookManager.submitBook(bookCopy,member);
    }

    public void issueBook(BookCopy bookCopy, Member member) {
        this.bookManager.issueBook(bookCopy,member);
    }

    public void deleteBook(BookCopy bookCopy) {
        this.bookManager.deleteBook(bookCopy);
    }

    public boolean blockMember(Member member) {
        return this.memberManager.blockMember(member);
    }
}
