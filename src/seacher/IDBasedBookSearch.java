package seacher;

import book.BookCopy;
import dataAccessor.DataAccessor;
import dataAccessor.Results;
import dataAccessor.ResultsConverter;

import java.util.List;
public class IDBasedBookSearch implements BookSearcher{
    private final int bookCopyId;
    private final DataAccessor dataAccessor;
    public IDBasedBookSearch(int bookCopyId, DataAccessor dataAccessor){
        this.bookCopyId = bookCopyId;
        this.dataAccessor = dataAccessor;
    }
    @Override
    public List<BookCopy> search() {
        Results rst = this.dataAccessor.getBookByBookId(bookCopyId);
        ResultsConverter r = new ResultsConverter(rst);
        return r.getBookCopiesFromResults();
    }
}
