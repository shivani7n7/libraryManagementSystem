package dataAccessor;

import book.BookCopy;
import user.Member;

import java.util.List;

public class ResultsConverter {
    private final Results results;

    public ResultsConverter(Results results) {
        this.results = results;
    }

    public List<BookCopy> getBookCopiesFromResults(){
        return null;
    }
    public List<Member> getMembersFromResults(){
        return null;
    }
}
