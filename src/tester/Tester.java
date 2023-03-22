package tester;

import book.BookCopy;
import book.BookDetail;
import dataAccessor.DataAccessor;
import library.BookManager;
import library.Library;
import auth.UserAuthentication;
import library.MemberManager;
import user.Member;
import seacher.*;
import utility.IdGenerator;

import java.util.List;

public class Tester {

    private final Library library = new Library(new BookManager(new DataAccessor()), new MemberManager(new DataAccessor()));
    public List<BookCopy> searchBookByBookName(String bookName){
        if (bookName == null) throw new IllegalArgumentException("Book name can't be null");
        BookSearcher bookSearcher = new NameBasedBookSearch(bookName, new DataAccessor());
        return bookSearcher.search();
    }
    public List<BookCopy> searchBooksByAuthorName(List<String> authorNames){
        if(authorNames == null || authorNames.size() == 0) throw new IllegalArgumentException("Author list can't be empty");
        BookSearcher bookSearcher = new AuthorBaserBookSearch(authorNames, new DataAccessor());
        return bookSearcher.search();
    }
    public List<Member> searchMemberByMemberName(String memberName,String adminToken) throws IllegalAccessException {
        if (memberName == null) throw new IllegalArgumentException("Member name can't be null");
        //validation and authorization for admin
        if(!UserAuthentication.isAdmin(adminToken)) throw new IllegalAccessException("Operation forbidden");

        MemberSearcher memberSearcher = new NameBasedMemberSearch(memberName, new DataAccessor());
        return memberSearcher.search();
    }
//    public Member searchMemberByMemberId(String memberId){
//
//    }
    //Librarian APIs
    public boolean blockMember(String memberId,String adminToken) throws IllegalAccessException {
        if(adminToken == null || !UserAuthentication.isAdmin(adminToken))  throw new IllegalAccessException("Operation forbidden");

        MemberSearcher memberSearcher = new IDBasedMemberSearch(memberId, new DataAccessor());
        List<Member> members = memberSearcher.search();

        if(members == null || members.size() == 0) throw new RuntimeException("No member retrieved for given member ID");
        return library.blockMember(members.get(0));
    }
    //add book
    public void addBook(String bookName,String authorName,String publications,String adminToken) throws IllegalAccessException {
        if(!UserAuthentication.isAdmin(adminToken))  throw new IllegalAccessException("Operation forbidden");
        /**
         * Validation logic here for params
         */
        BookCopy bookCopy = new BookCopy(new BookDetail(bookName,publications,authorName), IdGenerator.getNewID());
        library.addBook(bookCopy);
    }
    //delete book
    public void deleteBook(int bookCopyId,String adminToken) throws IllegalAccessException {
        if(adminToken == null || !UserAuthentication.isAdmin(adminToken))  throw new IllegalAccessException("Operation forbidden");
        /**
         * Validation logic here for params
         */
        BookSearcher bookSearcher = new IDBasedBookSearch(bookCopyId, new DataAccessor());
        List<BookCopy> bookCopies = bookSearcher.search();
        if(bookCopies == null || bookCopies.size() == 0) {
            throw  new RuntimeException("No Book Copy retrieved for given book ID");
        }
        library.deleteBook(bookCopies.get(0));
    }
    //issueBook
    public void issueBook(int bookCopyId,String memberId,String adminToken) throws IllegalAccessException {
        if(adminToken == null || !UserAuthentication.isAdmin(adminToken))  throw new IllegalAccessException("Operation forbidden");
        BookSearcher bookSearcher = new IDBasedBookSearch(bookCopyId, new DataAccessor());
        List<BookCopy> bookCopies = bookSearcher.search();

        MemberSearcher memberSearcher = new IDBasedMemberSearch(memberId, new DataAccessor());
        List<Member> members = memberSearcher.search();

        if(bookCopies == null || bookCopies.size() == 0 || members == null || members.size() == 0) {
            throw  new RuntimeException("No Book Copy retrieved for given book ID");
        }

        library.issueBook(bookCopies.get(0),members.get(0));
    }
    //submit book
    public void submitBook(int bookCopyId,String memberId, String adminToken) throws IllegalAccessException {
        if(adminToken == null || !UserAuthentication.isAdmin(adminToken))  throw new IllegalAccessException("Operation forbidden");
        BookSearcher bookSearcher = new IDBasedBookSearch(bookCopyId, new DataAccessor());
        List<BookCopy> bookCopies = bookSearcher.search();

        MemberSearcher memberSearcher = new IDBasedMemberSearch(memberId, new DataAccessor());
        List<Member> members = memberSearcher.search();

        if(bookCopies == null || bookCopies.size() == 0 || members == null || members.size() == 0) {
            throw  new RuntimeException("No Book Copy retrieved for given book ID");
        }
        library.submitBook(bookCopies.get(0),members.get(0));
    }
    //return get borrowerDetails
    public Member getBookBorrower(int bookCopyId,String adminToken) throws IllegalAccessException {
        if(adminToken == null || !UserAuthentication.isAdmin(adminToken))  throw new IllegalAccessException("Operation forbidden");

        BookSearcher bookSearcher = new IDBasedBookSearch(bookCopyId, new DataAccessor());
        List<BookCopy> bookCopies = bookSearcher.search();
        if(bookCopies == null || bookCopies.size() == 0){
            throw new RuntimeException("No Book Copy retrieved for given book ID");
        }
        return library.getBookBorrower(bookCopies.get(0));
    }
    //get books borrowed by given member
    public List<BookCopy> getBorrowedBooks(String memberId,String adminToken) throws IllegalAccessException {
        if(adminToken == null || !UserAuthentication.isAdmin(adminToken))  throw new IllegalAccessException("Operation forbidden");

        MemberSearcher memberSearcher = new IDBasedMemberSearch(memberId, new DataAccessor());
        List<Member> members = memberSearcher.search();

        if(members == null || members.size() == 0) throw new RuntimeException("No member retrieved for given member ID");
        return library.getBorrowedBooks(members.get(0));
    }
}
