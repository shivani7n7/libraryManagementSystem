package library;

import book.BookCopy;
import dataAccessor.DataAccessor;
import dataAccessor.Results;
import dataAccessor.ResultsConverter;
import user.Member;

import java.util.List;

public class MemberManager {
    private final DataAccessor dataAccessor;

    public MemberManager(DataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    public List<BookCopy> getBorrowedBooks(Member member) {
        Results rst = this.dataAccessor.getBorrowedBooks(member);
        ResultsConverter r = new ResultsConverter(rst);
        return r.getBookCopiesFromResults();
    }

    public boolean blockMember(Member member) {
        return this.dataAccessor.markAsBlock(member);
    }
}
