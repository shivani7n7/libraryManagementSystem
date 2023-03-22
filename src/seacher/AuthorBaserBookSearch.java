package seacher;

import book.BookCopy;
import dataAccessor.DataAccessor;
import dataAccessor.Results;
import dataAccessor.ResultsConverter;

import java.util.List;

public class AuthorBaserBookSearch implements BookSearcher {
    private final List<String> authorNames;
    private final DataAccessor dataAccessor;

    public AuthorBaserBookSearch(List<String> authorNames, DataAccessor dataAccessor){
        this.authorNames = authorNames;
        this.dataAccessor = dataAccessor;
    }

    @Override
    public List<BookCopy> search() {
        Results rst = this.dataAccessor.getBookByAuthorNames(authorNames);
        ResultsConverter r = new ResultsConverter(rst);
        return r.getBookCopiesFromResults();
    }
}
