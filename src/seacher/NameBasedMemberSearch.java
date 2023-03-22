package seacher;

import dataAccessor.DataAccessor;
import dataAccessor.Results;
import dataAccessor.ResultsConverter;
import user.Member;

import java.util.List;

public class NameBasedMemberSearch implements MemberSearcher{
    private final String memberName;
    private final DataAccessor dataAccessor;
    public NameBasedMemberSearch(String memberName, DataAccessor dataAccessor){
        this.memberName = memberName;
        this.dataAccessor = dataAccessor;
    }
    @Override
    public List<Member> search() {
        Results rst = this.dataAccessor.getMemberByMemberName(memberName);
        ResultsConverter r = new ResultsConverter(rst);
        return r.getMembersFromResults();
    }
}
