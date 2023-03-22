package dataAccessor;

import book.BookCopy;
import user.Member;

import java.util.List;

public class DataAccessor {

    //get functions
    public Results getBookByBookName(String bookName){
        return null;
    }

    public Results getBookByAuthorNames(List<String> authors){
        return null;
    }

    public Results getBookByBookId(int bookCopyId){
        return null;
    }

    public Results getMemberByMemberName(String memberName){
        return null;
    }
    public Results getMemberByMemberId(String memberId){
        return null;
    }

    public int insertBookCopy(BookCopy bookCopy){
       //SQL Query
        return 1;
    }

    public int deleteBookCopy(BookCopy bookCopy) {
        return 1;
    }

    public int issueBookToMember(BookCopy bookCopy, Member member) {
        return 1;
    }

    public int submitBookFromMember(BookCopy bookCopy, Member member) {
        return 1;
    }


    public Results getBookBorrower(BookCopy bookCopy) {
        return null;
    }

    public Results getBorrowedBooks(Member member) {
        Results rst = new Results();
        return rst;
    }


    public boolean markAsBlock(Member member) {
        return true;
    }
    public boolean isCopyAvailable(BookCopy bookCopy){
        return true;
    }
}
