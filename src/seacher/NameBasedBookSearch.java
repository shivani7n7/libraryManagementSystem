package seacher;

import book.BookCopy;
import dataAccessor.DataAccessor;
import dataAccessor.Results;
import dataAccessor.ResultsConverter;

import java.util.List;

public class NameBasedBookSearch implements BookSearcher {
    private final String bookName;
    private final DataAccessor dataAccessor;
    public NameBasedBookSearch(String bookName, DataAccessor dataAccessor){
        this.bookName = bookName;
        this.dataAccessor = dataAccessor;
    }
    @Override
    public List<BookCopy> search() {
        Results rst = this.dataAccessor.getBookByBookName(bookName);
        ResultsConverter r = new ResultsConverter(rst);
        return r.getBookCopiesFromResults();
    }
}
