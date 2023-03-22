package library;

import book.BookCopy;
import dataAccessor.DataAccessor;
import dataAccessor.Results;
import dataAccessor.ResultsConverter;
import user.Member;

public class BookManager {
    private final DataAccessor dataAccessor;

    public BookManager(DataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    public void addBook(BookCopy bookCopy)  {
        /**
         * Validation of params
         */
        int res = this.dataAccessor.insertBookCopy(bookCopy);
//         if(res != 1) throw new SQLTimeoutException("Book can't be added");
    }
    public void deleteBook(BookCopy bookCopy){
        /**
         * Check if book is available and not-issued
         * If yes, delete it. There might be cases that due to multi threading we delete the copy, which just got
         * issued by another thread. We should handle such scenrios as well.
         */
        if(this.dataAccessor.isCopyAvailable(bookCopy)) this.dataAccessor.deleteBookCopy(bookCopy);
//        if(res != 1) throw new SQLTimeoutException("Book can't be added");
    }
    public void issueBook(BookCopy bookCopy, Member member){
        int res = this.dataAccessor.issueBookToMember(bookCopy,member);
    }

    public void submitBook(BookCopy bookCopy, Member member) {
        int res = this.dataAccessor.submitBookFromMember(bookCopy,member);
    }

    public Member getBookBorrower(BookCopy bookCopy) {
        Results rst = this.dataAccessor.getBookBorrower(bookCopy);
        ResultsConverter r = new ResultsConverter(rst);
        return r.getMembersFromResults().get(0);
    }
}
